package org.kylerow.scalasynth.module

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.scalamock.scalatest.MockFactory
import org.kylerow.scalasynth.midi.SSNoteOnMidiMessage
import org.kylerow.scalasynth.SSConfiguration
import org.kylerow.scalasynth.note._
import java.util.logging.LogManager
import java.util.logging.Logger
import org.kylerow.util._
import org.kylerow.scalasynth.Injectable
import com.google.inject.Guice
import com.google.inject.AbstractModule

@RunWith(classOf[JUnitRunner])
class BasicOsillatorSpec
	extends FlatSpec 
	with ShouldMatchers
	with MockFactory{
  
	"math" should "work :)" in 
  	{
	 
		val basicOscillatorModule = new BasicOscillator
		assert(
		     1==basicOscillatorModule.square(45) && 
		    -1==basicOscillatorModule.square(125),
		    "Square wave broken")
		
		assert(
		     0.8939966636005579 == basicOscillatorModule.sine(90) &&
		    -0.45990349068959124== basicOscillatorModule.sine(123),
		    "Sine wave broken")
		    
	    assert(
		     0.35211024345883846 == basicOscillatorModule.saw(90) &&
		    -0.15211600060625413 == basicOscillatorModule.saw(123),
		    "Saw wave broken")
  	}
	
	"Incoming midi note" should "be stored in the instance" in {
	   // arrange
	  var basicOscillatorModule = new BasicOscillator
	  
	   // act
	   basicOscillatorModule.eventMessage(1)(a4);
	  
	   // assert
	   assert(basicOscillatorModule.playingNote==a4)
	}
	
	"NextAudioBuffer" should "use byteTone and thus wave() to get the next set of samples" in {
		// arrange
		val mockWave = mockFunction[Double,Double]
		val basicOscillatorModule = new BasicOscillator
		
		mockWave expects (*)  returning 42 anyNumberOfTimes;
		basicOscillatorModule.wave=mockWave
		basicOscillatorModule.playingNote = a4.on;
		basicOscillatorModule.configuration = new SSConfiguration();
		basicOscillatorModule.logger = Logger.getLogger("Basic-Oscillator")
		// act
		val result = basicOscillatorModule.nextAudioBuffer(1)();
		
		// assert
		assert(	result(1).value==4200 &&
				result(42).value==4200 && 
				result(1977).value==4200)
		assert( result.length==4096 )
	}
	"Separate Audio Buffers" should "be aligned" in {
		val logger = Logger.getLogger(getClass().getName())
		//fineLogging()
		val basicOscillator = new BasicOscillator
		basicOscillator.wave=basicOscillator.sine
		basicOscillator.playingNote = a4.on;
		basicOscillator.configuration = new SSConfiguration();
		basicOscillator.logger = Logger.getLogger("Basic-Oscillator")
		
		var result1 = basicOscillator.nextAudioBuffer(1)();
		var result2 = basicOscillator.nextAudioBuffer(1)();
		
		var a4Period = 1.0/440.0;
		var samplesPerA4Period = a4Period * 96000;
		var halfSPP = samplesPerA4Period / 2 
		
		var index1 = Math.ceil(4096-halfSPP).asInstanceOf[Int]
		var index2 = Math.ceil(halfSPP).asInstanceOf[Int]
		
		logger.fine(
		    "[index1="+index1+
		    ",index2="+index2+
		    ",result1.index1="+result1(index1)+
		    ",result2.index2="+result2(index2)+"]")
		    
		assert(result1(index1).value == result2(index2).value && result1(index1).value!=0 )
	}
}
package org.kylerow.scalasynth.module

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.scalamock.scalatest.MockFactory
import org.kylerow.scalasynth.midi.SSNoteOnMidiMessage
import org.kylerow.scalasynth.SSConfiguration
import org.kylerow.scalasynth.note._

@RunWith(classOf[JUnitRunner])
class BasicOsillatorModuleSpec
	extends FlatSpec 
	with ShouldMatchers
	with MockFactory{
  
	"math" should "work :)" in 
  	{
		val basicOscillatorModule = new BasicOscillatorModule
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
	   var basicOscillatorModule = new BasicOscillatorModule
	  
	   // act
	   basicOscillatorModule.eventMessage(1)(a4);
	  
	   // assert
	   assert(basicOscillatorModule.playingNote==a4)
	}
	
	"NextAudioBuffer" should "use byteTone and thus wave() to get the next set of samples" in {
		// arrange
		val mockWave = mockFunction[Double,Double]
		val basicOscillatorModule = new BasicOscillatorModule
		
		mockWave expects (*)  returning 42 anyNumberOfTimes;
		basicOscillatorModule.wave=mockWave
		basicOscillatorModule.playingNote = a4.on;
		basicOscillatorModule.configuration = new SSConfiguration();
		
		// act
		val result = basicOscillatorModule.nextAudioBuffer(1)();
		
		// assert
		assert(	result(1).value==4200 &&
				result(42).value==4200 && 
				result(1977).value==4200)
		assert( result.length==4096 )
	}
}
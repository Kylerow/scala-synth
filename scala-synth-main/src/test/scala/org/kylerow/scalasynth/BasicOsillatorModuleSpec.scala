package org.kylerow.scalasynth

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.scalamock.scalatest.MockFactory
import org.kylerow.scalasynth.midi.SSNoteOnMidiMessage
import org.kylerow.scalasynth.util.Note


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
	   basicOscillatorModule.midiMessage(1)(SSNoteOnMidiMessage(Note(true,65)));
	  
	   // assert
	   assert(basicOscillatorModule.playingNote==Note(true,65))
	}
}
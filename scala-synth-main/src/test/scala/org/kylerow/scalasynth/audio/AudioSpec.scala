package org.kylerow.scalasynth.audio

import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.scalamock.scalatest.MockFactory
import org.scalatest.junit.JUnitRunner
import org.kylerow.scalasynth.module.Module
import org.kylerow.scalasynth.module.BasicOscillatorModule
//import org.mockito.Mock._  

@RunWith(classOf[JUnitRunner])
class AudioSpec 
extends FlatSpec 
	with ShouldMatchers
	with MockFactory{
  
  "Audio Companion" should 
  "implicitly convert (Module,Int) to class with >> operator that calls registerSender" in {
    
	import org.kylerow.scalasynth.audio.Audio._
	
	// arrange 
    val myTuple = (new BasicOscillatorModule, 1)
    val mockAudio = mock[Audio]
    (mockAudio.registerSender _).expects( myTuple )
    
    // act
    myTuple >> mockAudio
  }
}
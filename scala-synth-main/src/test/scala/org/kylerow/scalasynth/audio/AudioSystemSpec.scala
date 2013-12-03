package org.kylerow.scalasynth.audio

import org.scalamock.scalatest.MockFactory
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.junit.Assert._
import org.kylerow.scalasynth.SSConfiguration

@RunWith(classOf[JUnitRunner])
class AudioSystemSpec 
extends FlatSpec 
	with ShouldMatchers
	with MockFactory{
	"AudioPort" should 
			"be returned, set with an appropriate" +
			"dataline" in {
	  
	  // arrange
	  var audioSystem = new AudioSystem
	  audioSystem.config = new SSConfiguration
	  
	  // act
	  var result = audioSystem getPort
	  
	  // assert
	  assertNotNull(result)
	  assertEquals(result.sourceDataLine.getFormat().getSampleRate(),96000f,0)
	  assertEquals(result.sourceDataLine.getFormat().getSampleSizeInBits(),16,0)
	}
}
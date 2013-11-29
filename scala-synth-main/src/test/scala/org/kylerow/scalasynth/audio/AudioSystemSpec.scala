package org.kylerow.scalasynth.audio

import org.scalamock.scalatest.MockFactory
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.junit.Assert._

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
	  
	  // act
	  var result = audioSystem getPort
	  
	  // assert
	  assertNotNull(result)
	  assertEquals(result.getDataLine().getFormat().getSampleRate(),96000f,0)
	  assertEquals(result.getDataLine().getFormat().getSampleSizeInBits(),16,0)
	}
}
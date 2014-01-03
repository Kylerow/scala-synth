package org.kylerow.scalasynth.audio

import org.scalatest.FlatSpec
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalamock.scalatest.MockFactory
import org.scalatest.junit.JUnitRunner
import org.kylerow.scalasynth.SSConfiguration

@RunWith(classOf[JUnitRunner])
class AudioSystemSpec 
extends FlatSpec 
	with ShouldMatchers
	with MockFactory{

	"getPort" should "return mocked audioport" in{
	  // arrange
	  val mockConfig = stub[SSConfiguration]
	  (mockConfig.getSampleRate _) when() returns(44100);
	  (mockConfig.getSampleSize _) when() returns(8);
	  (mockConfig.getWriteLength _) when() returns(4096);
	  
	  val mockAudioPortCreator = mock[AudioPortCreator]
	  (mockAudioPortCreator.create _) expects( where{
	    (typeName,audioPortOptions) =>
		    (typeName equals "JAudioLibs/Jack") &&
		    (audioPortOptions != null)
	  })
	  
	  val audioSystem = new AudioSystem
	  audioSystem.audioPortCreator = mockAudioPortCreator
	  audioSystem.config = mockConfig
	  
	  // act
	  val audioPort = audioSystem getPort 	  
	}
}
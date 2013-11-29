package org.kylerow.scalasynth.audio

import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.scalamock.scalatest.MockFactory
import org.scalatest.junit.JUnitRunner
import org.kylerow.scalasynth.module.Module
import org.kylerow.scalasynth.module.BasicOscillatorModule
import org.kylerow.scalasynth.Word

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
    (mockAudio.attachSender _).expects( myTuple )
    
    // act
    myTuple >> mockAudio
  }
  
  "RunAudio" should "loop and send to dataline" in {
    import scala.concurrent._
	import ExecutionContext.Implicits.global
	  
    // arrange
    val audio = new AudioImplementation();
    val stubModule = stub[Module]
    val wordArray = Array(Word(42));
    val stubAudioPort = stub[AudioPort]
    val stubAudioSystem = stub[AudioSystem]
    
    (stubModule.nextAudioBuffer(_ :Int) ).when(1).returns(wordArray)
    (stubAudioSystem.getPort _) when() returns (stubAudioPort)
    audio.audioSystem = stubAudioSystem;
    
    // act
    audio.senderOfRecord=(stubModule,1)
    future { audio runAudio (stubModule,1) }
    Thread.sleep(1000);
    
    // assert
    (stubModule.nextAudioBuffer _) verify(1) atLeastOnce()
    (stubAudioPort.sendData _) verify(wordArray) atLeastOnce()
  }
  "RunAudio" should "not loop and not send to dataline" in {
    import scala.concurrent._
	import ExecutionContext.Implicits.global
	  
    // arrange
    val audio = new AudioImplementation();
    val stubModule = stub[Module]
    val wordArray = Array(Word(42));
    val stubAudioPort = stub[AudioPort]
    val stubAudioSystem = stub[AudioSystem]
    
    (stubModule.nextAudioBuffer(_ :Int) ).when(1).returns(wordArray)
    (stubAudioSystem.getPort _) when() returns (stubAudioPort)
    audio.audioSystem = stubAudioSystem;
    
    // act
    audio.senderOfRecord=null;
    future { audio runAudio (stubModule,1) }
    Thread.sleep(1000);
    
    // assert
    (stubModule.nextAudioBuffer _) verify(1) never()
    (stubAudioPort.sendData _) verify(wordArray) never()
  }
}
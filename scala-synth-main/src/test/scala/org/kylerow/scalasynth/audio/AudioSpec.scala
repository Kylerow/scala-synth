package org.kylerow.scalasynth.audio

import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.scalamock.scalatest.MockFactory
import org.scalatest.junit.JUnitRunner
import org.kylerow.scalasynth.module.Module
import org.kylerow.scalasynth.module.BasicOscillator
import org.kylerow.scalasynth.Word
import org.kylerow.scalasynth.module.ModuleRegistry
import org.kylerow.scalasynth.Injectable
import com.google.inject.Guice
import com.google.inject.AbstractModule
import scala.collection.mutable.MutableList

@RunWith(classOf[JUnitRunner])
class AudioSpec 
extends FlatSpec 
	with ShouldMatchers
	with MockFactory{
  
  "Audio Companion" should 
  "implicitly convert (Module,Int) to class with >> operator that calls registerSender" in {
    
	import org.kylerow.scalasynth.audio.Audio._
	
	// arrange 
	val mockModule = mock[Module]
    val myTuple = (mockModule, 1)
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
    
    val wordArray = Array(Word(8,42));
    val stubAudioPort = stub[AudioPort]
    val stubAudioSystem = mock[AudioSystem]
    
    (stubAudioSystem.getPort _) expects() returns (stubAudioPort)
    
    audio.audioSystem = stubAudioSystem;
    val stubModule = new Module{
      def nextAudioBuffer(output :Int)() :MutableList[Word] =
        MutableList[Word]();
      def moreAudio(output :Int)() :Boolean =
        false
	  def getName() :String =
	    "42"
    }
    
    
    // act
    audio.senderOfRecord=(stubModule,1)
    future (audio runAudio (stubModule,1) )
    Thread.sleep(500);
    
  }
  "RunAudio" should "not loop and not send to dataline" in {
    import scala.concurrent._
	import ExecutionContext.Implicits.global
	  
    // arrange
    val audio = new AudioImplementation();
    val wordArray = MutableList(Word(8,42));
    val stubAudioPort = stub[AudioPort]
    val stubAudioSystem = stub[AudioSystem]
    (stubAudioSystem.getPort _) when() returns (stubAudioPort)
     val stubModule = new Module{
      def nextAudioBuffer(output :Int)() :MutableList[Word] =
        MutableList[Word]();
      def moreAudio(output :Int)() :Boolean =
        false
	  def getName() :String =
	    "42"
    }
    audio.audioSystem = stubAudioSystem;
    
    // act
    audio.senderOfRecord=null;
    future { audio runAudio (stubModule,1) }
    Thread.sleep(1000);
    
    // assert
    (stubAudioPort.sendData _) verify(wordArray) never()
  }
}
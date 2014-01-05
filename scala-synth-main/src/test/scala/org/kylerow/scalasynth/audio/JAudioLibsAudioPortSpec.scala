package org.kylerow.scalasynth.audio

import org.scalatest.FlatSpec
import org.scalamock.scalatest.MockFactory
import org.scalatest.matchers.ShouldMatchers
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import scala.collection.mutable.Queue
import scala.collection.mutable.MutableList
import org.kylerow.scalasynth.Word

@RunWith(classOf[JUnitRunner])
class JAudioLibsAudioPortSpec 
extends FlatSpec 
	with ShouldMatchers
	with MockFactory{
  "sendData" should "put data onto queue" in {
    // arrange
    val jAudioLibsAudioPort = new JAudioLibsAudioPort
    val data = new MutableList[Word]
    
    // act
    jAudioLibsAudioPort sendData data
    
    // assert
    assert(jAudioLibsAudioPort.audioQueue.dequeue().eq( data ))
  }
  "getSendableData" should "pull data from queue" in {
    // arrange
    val jAudioLibsAudioPort = new JAudioLibsAudioPort
    val data = new MutableList[Word]
    data+=(Word(15,42))
    jAudioLibsAudioPort.audioQueue.enqueue(data)
    
    // act
    val result = jAudioLibsAudioPort.getSendableData
    assert (result(0) eq data(0))
    assert (result(0) !=null && result(0).value==42)
  }
}
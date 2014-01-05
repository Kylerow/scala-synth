package org.kylerow.scalasynth.audio

import scala.collection.mutable.MutableList
import org.kylerow.scalasynth.Word
import scala.collection.mutable.Queue

class JAudioLibsAudioPort extends AudioPort{
  val audioQueue = new Queue[MutableList[Word]]
	def sendData(data :MutableList[Word]) = 
	  audioQueue enqueue data
	def getSendableData() :Array[Word] =
	  audioQueue.dequeue.toArray
	def getCallbackFunc() :()=>Array[Word] = getSendableData _
}
package org.kylerow.scalasynth.audio

import scala.collection.mutable.MutableList
import org.kylerow.scalasynth.Word

class JAudioLibsAudioPort extends AudioPort{
	def sendData(data :MutableList[Word]) = {}
	def getSendableData() :Array[Word]=null;
	def getCallbackFunc() :()=>Array[Word] = getSendableData _
}
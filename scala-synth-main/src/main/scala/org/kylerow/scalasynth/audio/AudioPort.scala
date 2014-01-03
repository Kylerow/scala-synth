package org.kylerow.scalasynth.audio

import scala.collection.mutable.MutableList
import org.kylerow.scalasynth.Word


trait AudioPort {
	def sendData(data :MutableList[Word]);
}
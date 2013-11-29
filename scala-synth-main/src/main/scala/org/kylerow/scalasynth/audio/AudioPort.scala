package org.kylerow.scalasynth.audio

import org.kylerow.scalasynth.Word
import javax.sound.sampled.DataLine

class AudioPort {
	def sendData(data :Array[Word]) = {
	  
	}
	
	var dataLine :DataLine = _;
	def getDataLine() :DataLine = dataLine;
	def setDataLine(dataLine :DataLine) = this.dataLine=dataLine;
}
package org.kylerow.scalasynth.audio

import org.kylerow.scalasynth.Word
import javax.sound.sampled.DataLine

class AudioPort {
	def sendData(data :Array[Word]) = {
	  // steps to glorious success:
	  // 1) turn array[word] into array[byte]
	  // 2) dataLine.write(buf, 0, BYTE_BUFFER_SIZE)
	}
	
	var dataLine :DataLine = _;
	def getDataLine() :DataLine = dataLine;
	def setDataLine(dataLine :DataLine) = this.dataLine=dataLine;
}
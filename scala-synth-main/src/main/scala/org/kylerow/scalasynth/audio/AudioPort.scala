package org.kylerow.scalasynth.audio

import org.kylerow.scalasynth.Word
import javax.sound.sampled.DataLine
import javax.sound.sampled.SourceDataLine
import scala.collection.mutable.MutableList

class AudioPort {
	var writeLength :Int =_;
	
	def sendData(data :MutableList[Word]) = {
	  val bytes = data.flatMap (word => 
	    for(i <- 0 to (word.size/8-1))
	      yield (word.value>>(i*8))
	      			.asInstanceOf[Byte]).toArray
	  sourceDataLine.write(bytes, 0, writeLength)
	}
	
	var sourceDataLine :SourceDataLine = _;
}
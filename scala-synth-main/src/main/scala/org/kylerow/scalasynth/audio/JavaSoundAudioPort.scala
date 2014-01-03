package org.kylerow.scalasynth.audio

import scala.collection.mutable.MutableList
import javax.sound.sampled.SourceDataLine
import org.kylerow.scalasynth.Word

class JavaSoundAudioPort extends AudioPort{
	var writeLength :Int =_;
	
	override def sendData(data :MutableList[Word]) = {
	  val bytes = data.flatMap (word => 
	    for(i <- 0 to (word.size/8-1))
	      yield (word.value>>(i*8))
	      			.asInstanceOf[Byte]).toArray
	  sourceDataLine.write(bytes, 0, writeLength)
	}
	
	var sourceDataLine :SourceDataLine = _;
}
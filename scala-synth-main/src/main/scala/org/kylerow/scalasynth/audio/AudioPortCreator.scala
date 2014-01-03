package org.kylerow.scalasynth.audio

import javax.sound.sampled.AudioFormat
import javax.sound.sampled.SourceDataLine

class AudioPortCreator {
	var creatorFunctionMap = Map("JavaSound"-> (createJavaSoundAudioPort _))
	
	def create(portType :String, audioPortOptions :AudioPortOptions) :AudioPort = 
	  creatorFunctionMap(portType)(audioPortOptions)
 
	def createJavaSoundAudioPort(audioPortOptions :AudioPortOptions) :AudioPort = {	
	  val af = new AudioFormat(
 	    	        audioPortOptions.sampleRate.asInstanceOf[Float], 
 	    	        audioPortOptions.sampleSize,  	    	        
 	    	        1, 
 	    	        true, false);
 	    	
 	  val mixers = javax.sound.sampled.AudioSystem.getMixerInfo()
 	  val mixer = 
 	    javax.sound.sampled.AudioSystem.getMixer(
 	        mixers(2))
 	  val dataLines = mixer.getSourceLineInfo
 	  val dataLineInfo = (dataLines.filter(_.getLineClass().isAssignableFrom(classOf[SourceDataLine])))(0)
 	  val dataLine = mixer.getLine(dataLineInfo).asInstanceOf[SourceDataLine]
 	  dataLine.open(af,audioPortOptions.writeLength)
 	  dataLine.start();
	  
 	  val audioPort = new JavaSoundAudioPort();
 	  audioPort.sourceDataLine = dataLine
 	  audioPort.writeLength = audioPortOptions.writeLength
  	  audioPort;
	}
}
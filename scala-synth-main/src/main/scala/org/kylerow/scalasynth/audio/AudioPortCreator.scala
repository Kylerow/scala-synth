package org.kylerow.scalasynth.audio

import javax.sound.sampled.AudioFormat
import javax.sound.sampled.SourceDataLine
import com.google.inject.Inject

class AudioPortCreator {
	var creatorFunctionMap = Map(
	    "JavaSound" -> (createJavaSoundAudioPort _),
	    "JAudioLibs/Jack" -> (createJAudioLibsAudioPort _))
	@Inject var jAudioLibsInitializer :JAudioLibsInitializer = _	
	
	def create(portType :String, audioPortOptions :AudioPortOptions) :AudioPort = 
	  creatorFunctionMap(portType)(audioPortOptions)
 
	def createJAudioLibsAudioPort(audioPortOptions :AudioPortOptions) :AudioPort={
	  val jAudioLibsAudioClient =  jAudioLibsInitializer.init("Test1",audioPortOptions)
	  val jAudioLibsAudioPort = new JAudioLibsAudioPort()
	  jAudioLibsAudioClient.setDataRetriever(jAudioLibsAudioPort.getCallbackFunc())
	  jAudioLibsAudioPort
	}
	
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
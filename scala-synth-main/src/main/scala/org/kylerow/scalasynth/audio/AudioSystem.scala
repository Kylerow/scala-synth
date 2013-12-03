package org.kylerow.scalasynth.audio

import javax.sound.sampled.DataLine
import javax.sound.sampled.AudioFormat
import com.google.inject.Inject
import org.kylerow.scalasynth.SSConfiguration

class AudioSystem {
 
	@Inject var config :SSConfiguration = _
	
	def getPort() :AudioPort = {
	  val dataLine = 
 	    javax.sound.sampled.
 	    	AudioSystem
 	    	.getSourceDataLine(
 	    	    new AudioFormat(
 	    	        config.getSampleRate, 
 	    	        config.getSampleSize,  	    	        
 	    	        1, 
 	    	        true, false))   
 
 	  val audioPort = new AudioPort();
 	  audioPort.sourceDataLine = dataLine
  	  audioPort;
  	}
  	
}
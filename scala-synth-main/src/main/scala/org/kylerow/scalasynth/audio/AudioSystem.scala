package org.kylerow.scalasynth.audio

import javax.sound.sampled.DataLine
import javax.sound.sampled.AudioFormat

class AudioSystem {
 	def getPort() :AudioPort = {
  	  // steps to glorious success
  	  // 1) create data-line
 	  val dataLine = 
 	    javax.sound.sampled.
 	    	AudioSystem
 	    	.getSourceDataLine(
 	    	    new AudioFormat(
 	    	        96000f, 16, 1, 
 	    	        true, false))   
 
  	  // 2) create port 
 	  val audioPort = new AudioPort();
 	  audioPort.setDataLine(dataLine);
  	  audioPort;
  	}
  	
}
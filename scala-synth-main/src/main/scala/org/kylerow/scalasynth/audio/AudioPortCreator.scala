package org.kylerow.scalasynth.audio

class AudioPortCreator {
	def create(portType :String, audioPortOptions :AudioPortOptions) :AudioPort = {null}
  /*
	  val af = new AudioFormat(
 	    	        config.getSampleRate, 
 	    	        config.getSampleSize,  	    	        
 	    	        1, 
 	    	        true, false);
	  
	/*  val dataLine = 
 	    javax.sound.sampled.
 	    	AudioSystem
 	    	.getSourceDataLine(af)   
 	  */
 	    	
 	  val mixers = javax.sound.sampled.AudioSystem.getMixerInfo()
 	  val mixer = 
 	    javax.sound.sampled.AudioSystem.getMixer(
 	        mixers(2))
 	  val dataLines = mixer.getSourceLineInfo
 	  val dataLineInfo = (dataLines.filter(_.getLineClass().isAssignableFrom(classOf[SourceDataLine])))(0)
 	  val dataLine = mixer.getLine(dataLineInfo).asInstanceOf[SourceDataLine]
 	  dataLine.open(af,config.getWriteLength())
 	  dataLine.start();
	  
 	  val audioPort = new AudioPort();
 	  audioPort.sourceDataLine = dataLine
 	  audioPort.writeLength = config.getWriteLength()
  	  audioPort;*/
}
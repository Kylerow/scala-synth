package org.kylerow.scalasynth.audio

import javax.sound.sampled.DataLine
import javax.sound.sampled.AudioFormat
import com.google.inject.Inject
import org.kylerow.scalasynth.SSConfiguration
import javax.sound.sampled.SourceDataLine

class AudioSystem {
 
	@Inject var config :SSConfiguration = _
	@Inject var audioPortCreator :AudioPortCreator = _
	
	def getPort() :AudioPort = {
	  audioPortCreator create ("JAudioLibs/Jack",new AudioPortOptions{
	    sampleRate = config.getSampleRate
	    sampleSize = config.getSampleSize
	    writeLength = config.getWriteLength
	  })
	}
}
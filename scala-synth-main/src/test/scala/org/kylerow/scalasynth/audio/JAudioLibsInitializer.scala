package org.kylerow.scalasynth.audio

import java.util.ServiceLoader
import org.jaudiolibs.audioservers.AudioServerProvider
import scala.collection.JavaConversions._
import org.jaudiolibs.audioservers.AudioConfiguration
import org.jaudiolibs.audioservers.ext.ClientID
import org.jaudiolibs.audioservers.ext.Connections
import scala.concurrent._
import ExecutionContext.Implicits.global
	  
	  
class JAudioLibsInitializer {
	def init(
	    clientName :String, 
	    audioPortOptions :AudioPortOptions)
	:JAudioLibsAudioClient = 
		{
			val audioServiceProvider = 
			  ServiceLoader
			  		.load(classOf[AudioServerProvider])
					.filter(_.getLibraryName eq "JACK")
					.last;
			val jAudioLibsAudioClient = new JAudioLibsAudioClient;
			val audioConfiguration = new AudioConfiguration(
                44100.0f, //sample rate
                0, // input channels
                1, // output channels
                256, //buffer size
                new ClientID(clientName),
                Connections.OUTPUT);
			val server = audioServiceProvider createServer
							(audioConfiguration, jAudioLibsAudioClient);
			future(server run)
			jAudioLibsAudioClient
		}
}
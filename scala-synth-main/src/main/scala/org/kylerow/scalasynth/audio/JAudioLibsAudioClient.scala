package org.kylerow.scalasynth.audio

import org.jaudiolibs.audioservers.AudioClient
import org.jaudiolibs.audioservers.AudioConfiguration
import java.nio.FloatBuffer
import org.kylerow.scalasynth.Word

class JAudioLibsAudioClient extends AudioClient{
	private var getData :()=>Array[Word] = _
	def setDataRetriever(dataRetriever :()=>Array[Word])=
	  getData = dataRetriever;
	def configure(audioConfiguration :AudioConfiguration) = {}
	def shutdown() = {}
	def process(
	    time :Long, 
	    inputs :java.util.List[FloatBuffer], 
	    outputs :java.util.List[FloatBuffer], 
	    nframes :Int) :Boolean = {
	  
	  outputs.clear()
	  outputs.add(
	        FloatBuffer.wrap(
	            getData().map(_.value.asInstanceOf[Float])))
	            
	  true
	}
}
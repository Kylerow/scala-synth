package org.kylerow.scalasynth.module

import org.kylerow.scalasynth.midi.SSMidiMessage
import javax.inject.Inject
import org.kylerow.scalasynth.SSConfiguration
import org.kylerow.scalasynth.Word
import org.kylerow.scalasynth.Injectable
import scala.collection.mutable.MutableList
import org.kylerow.scalasynth.audio.AudioOutputs
import org.kylerow.scalasynth.midi.EventReceiver

trait Module extends Injectable {
  
	@Inject var moduleRegistry :ModuleRegistry = _;
	@Inject var configuration :SSConfiguration = _;
	
	def nextAudioBuffer(output :Int)() :MutableList[Word];
	def moreAudio(output :Int)() :Boolean;
	
	def register(name :String) :Module = 
	  moduleRegistry.put(name,this);
}
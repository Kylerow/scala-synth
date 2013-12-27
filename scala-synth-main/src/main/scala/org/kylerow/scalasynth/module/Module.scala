package org.kylerow.scalasynth.module

import org.kylerow.scalasynth.midi.SSMidiMessage
import javax.inject.Inject
import org.kylerow.scalasynth.SSConfiguration
import org.kylerow.scalasynth.Word
import org.kylerow.scalasynth.Injectable
import scala.collection.mutable.MutableList

trait Module extends Injectable{
	var moduleRegistry :ModuleRegistry = _;
	//moduleRegistry = injector.getInstance(classOf[ModuleRegistry]);
	//moduleRegistry.put(getName(),this)
	
	@Inject var configuration :SSConfiguration = _;
	def nextAudioBuffer(output :Int)() :MutableList[Word];
	def moreAudio(output :Int)() :Boolean;
	def getName() :String;
}
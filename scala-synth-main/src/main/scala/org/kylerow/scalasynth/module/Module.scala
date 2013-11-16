package org.kylerow.scalasynth.module

import org.kylerow.scalasynth.midi.SSMidiMessage
import javax.inject.Inject
import org.kylerow.scalasynth.SSConfiguration
import org.kylerow.scalasynth.Word

trait Module {
	@Inject var ssConfiguration :SSConfiguration = _;
	def midiMessage(input :Int)(message :SSMidiMessage);
    def nextAudioBuffer(output :Int)() :Array[Word];
	def moreAudio(output :Int)() :Boolean;
}
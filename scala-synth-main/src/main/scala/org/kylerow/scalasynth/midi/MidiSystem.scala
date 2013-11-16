package org.kylerow.scalasynth.midi

import javax.sound.midi.MidiDevice

class MidiSystem {
	def getMidiDeviceInfo() = 
	  javax.sound.midi.MidiSystem.getMidiDeviceInfo().toList
	def getMidiDevice(deviceInfo :MidiDevice.Info) = 
	  javax.sound.midi.MidiSystem.getMidiDevice(deviceInfo);
}
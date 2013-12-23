package org.kylerow.scalasynth.midi


class MidiSystem {
	def getMidiDeviceInfo() :List[MidiDeviceInfo] = 
	  javax.sound.midi.MidiSystem.getMidiDeviceInfo().toList.map(x=>MidiDeviceInfo(x))
	def getMidiDevice(deviceInfo :MidiDeviceInfo) :MidiDevice = 
	  MidiDevice(javax.sound.midi.MidiSystem.getMidiDevice(deviceInfo.getMidiDeviceInfo));
}
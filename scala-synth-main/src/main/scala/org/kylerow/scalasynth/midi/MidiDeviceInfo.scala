package org.kylerow.scalasynth.midi

class MidiDeviceInfo {
  var midiDeviceInfo :javax.sound.midi.MidiDevice.Info = _
	def getMidiDeviceInfo = midiDeviceInfo
	def setMidiDeviceInfo(midiDeviceInfo :javax.sound.midi.MidiDevice.Info)={
	  this.midiDeviceInfo = midiDeviceInfo
	  this
	}
}

object MidiDeviceInfo {
  def apply(midiDeviceInfo :javax.sound.midi.MidiDevice.Info) = 
    new MidiDeviceInfo().setMidiDeviceInfo(midiDeviceInfo)
}
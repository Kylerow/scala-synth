package org.kylerow.scalasynth.midi
import scala.collection.JavaConversions._

class MidiDevice {
  var midiDevice :javax.sound.midi.MidiDevice = _
	def setMidiDevice(midiDevice :javax.sound.midi.MidiDevice) = {
	  this.midiDevice = midiDevice;
	  this
	}
  def getTransmitter = Transmitter(midiDevice.getTransmitter())
  def getTransmitters = midiDevice.getTransmitters().map(x=>Transmitter(x)).toList
  def open = midiDevice.open();
}
object MidiDevice {
  def apply(midiDevice :javax.sound.midi.MidiDevice) = 
		  new MidiDevice().setMidiDevice(midiDevice)
}
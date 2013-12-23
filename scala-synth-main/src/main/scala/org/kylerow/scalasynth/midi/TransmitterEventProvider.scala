package org.kylerow.scalasynth.midi

import javax.sound.midi.Receiver
import javax.sound.midi.MidiMessage
import org.kylerow.scalasynth.note.a4

class TransmitterEventProvider extends EventProvider {
  var transmitter :Transmitter = _;
  def getTransmitter() = {this.transmitter}
  def setTransmitter(transmitter :Transmitter) = {
    this.transmitter = transmitter;
    this;
  }
  def begin() = {
    transmitter setCallback { sendEvent(_) }
    this
  }
}
object TransmitterEventProvider {
  def apply (transmitter :Transmitter) = 
    new TransmitterEventProvider()
  			.setTransmitter(transmitter)
  			.begin()
}
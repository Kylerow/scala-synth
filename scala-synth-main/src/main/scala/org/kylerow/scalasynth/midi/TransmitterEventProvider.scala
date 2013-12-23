package org.kylerow.scalasynth.midi

class TransmitterEventProvider extends EventProvider {
  var transmitter :Transmitter = _;
  def getTransmitter() = {this.transmitter}
  def setTransmitter(transmitter :Transmitter) =
    {this.transmitter = transmitter;this;}
}
object TransmitterEventProvider {
  def apply (transmitter :Transmitter) = 
    new TransmitterEventProvider().setTransmitter(transmitter)
}
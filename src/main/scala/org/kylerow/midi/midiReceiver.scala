package org.kylerow.midi

import javax.sound.midi.Receiver
import javax.sound.midi.MidiMessage

object midiReceiver {
  def apply( input :MidiMessage => Unit ) = {
    new Receiver{
      def send( msg :MidiMessage, timeStamp :Long ) = input(msg)
      def close() = {}
    }
  }
}
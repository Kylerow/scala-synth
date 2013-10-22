package org.kylerow

import javax.sound.midi.Receiver
import javax.sound.midi.MidiMessage
import javax.sound.midi.Transmitter
import javax.sound.midi.MidiSystem
import javax.sound.midi.MidiDevice
import javax.sound.midi.MidiUnavailableException
import org.kylerow.midi.Midi

case class Note(noteVal :Int);

/** Linux Notes:
 *  	- Make sure virtual midi is setup: sudo modprobe snd-virmidi
 *      - use patchage to connect virtual keyboard to the midi port (really any midi port:))
 *   
 * @author KyleRow
 */

object ScalaSynth extends Injectable {
  
  def receptacle (message :MidiMessage) = 
    println("midi received: "+message.toString);
  
  def main(args : Array[String]) {
    var midi = Midi()
    
    println("Connecting to All Virtual Midi...")
    midi >>> receptacle
    
    println("Running...")
    while(true){
      Thread.sleep(3000);
    }
  }
}

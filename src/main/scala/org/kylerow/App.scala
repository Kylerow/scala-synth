package org.kylerow

import javax.sound.midi.Receiver
import javax.sound.midi.MidiMessage
import javax.sound.midi.Transmitter
import javax.sound.midi.MidiSystem
import javax.sound.midi.MidiDevice
import javax.sound.midi.MidiUnavailableException
import org.kylerow.midi.{midiReceiver,transmitters}

case class Note(noteVal :Int);

/** Linux Notes:
 *  	- Make sure virtual midi is setup: sudo modprobe snd-virmidi
 *      - use patchage to connect virtual keyboard to the midi port (really any midi port:))
 *   
 * @author KyleRow
 */

object ScalaSynth {
  
  def receptacle (message :MidiMessage) = 
    println("midi received: "+message.toString);
  
  def connect (tx :List[Transmitter], rx :Receiver) = 
    tx.filter(_!=null).foreach({ _.setReceiver(rx)});
  
  def main(args : Array[String]) {
    println("Connecting to All Virtual Midi...")
    connect (transmitters(), midiReceiver(receptacle))
    
    println("Running...")
    while(true){
      Thread.sleep(3000);
    }
  }
}

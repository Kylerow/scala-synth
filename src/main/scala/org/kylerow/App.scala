package org.kylerow

import javax.sound.midi.Receiver
import javax.sound.midi.MidiMessage
import javax.sound.midi.Transmitter
import javax.sound.midi.MidiSystem
import javax.sound.midi.MidiDevice
import scala.collection.JavaConversions._
import javax.sound.midi.MidiUnavailableException

case class Note(noteVal :Int);

/** Linux Notes:
 *  	- Make sure virtual midi is setup: sudo modprobe snd-virmidi
 *      - use patchage to connect virtual keyboard to the midi port (really any midi port:))
 *   
 * @author KyleRow
 */
object ScalaSynth {
  
  def transmitters :List[Transmitter] = {
   // MidiSystem.getMidiDeviceInfo().toList.map(x => MidiSystem.getMidiDevice(x).open())
    
    
    (for (info <- MidiSystem.getMidiDeviceInfo().toList;
    	device = MidiSystem.getMidiDevice(info)) yield {
        device open();
        try { device getTransmitter } catch {
          case un :MidiUnavailableException  => null;
          case ex :Throwable => println("Exception: "+ ex);  null;
        }
      }) :::
    (for (
      info <- MidiSystem.getMidiDeviceInfo().toList;
      transmitter <- MidiSystem.getMidiDevice(info).getTransmitters()
      ) yield transmitter )
  }
  
  def receiver( input :MidiMessage => Unit ) = {
    new Receiver{
      def send( msg :MidiMessage, timeStamp :Long ) = input(msg)
      def close() = {}
    }
  }
  
  def receptacle (message :MidiMessage) = println("midi received: "+message.toString);
  
  def connect (tx :List[Transmitter], rx :Receiver) = tx.filter(_!=null).foreach({ _.setReceiver(rx)});
  
  def main(args : Array[String]) {
    println("Connecting to All Virtual Midi...")
    connect (transmitters, receiver(receptacle))
    
    println("Running...")
    while(true){
      Thread.sleep(3000);
    }
  }
}

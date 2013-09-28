package org.kylerow

import javax.sound.midi.Receiver
import javax.sound.midi.MidiMessage
import javax.sound.midi.Transmitter
import javax.sound.midi.MidiSystem
import javax.sound.midi.MidiDevice
import scala.collection.JavaConversions._

case class Note(noteVal :Int);

/**
 * @author KyleRow
 */
object ScalaSynth {
  
  def receiver( input :MidiMessage => Unit ) = {
    new Receiver{
      def send( msg :MidiMessage, timeStamp :Long ) = input(msg)
      def close() = {}
    }
  }
  
  def transmitters :List[Transmitter] = {
    for (
      info <- MidiSystem.getMidiDeviceInfo().toList ;
      transmitter <- MidiSystem.getMidiDevice(info).getTransmitters()
      ) yield transmitter;
  }
  
  def connect (tx :List[Transmitter], rx :Receiver) = tx.foreach({_.setReceiver(rx)});
  
  def receptacle (message :MidiMessage) = println("midi received: "+message.toString);
  
  def main(args : Array[String]) {
    connect (transmitters, receiver(receptacle));
    while(true){
      println("Waiting to quit...")
      Thread.sleep(1000);
    }
  }
}

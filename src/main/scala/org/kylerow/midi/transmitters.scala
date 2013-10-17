package org.kylerow.midi

import javax.sound.midi.MidiSystem
import javax.sound.midi.Transmitter
import scala.collection.JavaConversions._
import org.kylerow.util.{avoidException}
import javax.sound.midi.MidiUnavailableException

object transmitters {
  def avoidMidiUnavailableException[R](func :()=>R) = avoidException(classOf[MidiUnavailableException])(func);
  def avoidAll[R](func :()=>R) = avoidException(classOf[Exception])(func);
 
  def apply() :List[Transmitter] = 
     MidiSystem
   		.getMidiDeviceInfo()
   		.toList
   		.flatMap(x => avoidMidiUnavailableException (()=>{
   		  MidiSystem
   		  	.getMidiDevice(x)
   		  	.open(); 
   		  MidiSystem
   		  	.getMidiDevice(x)
   		  	.getTransmitters()
   		    .toList.::(avoidAll(()=>MidiSystem.getMidiDevice(x).getTransmitter()))
   		}));
}
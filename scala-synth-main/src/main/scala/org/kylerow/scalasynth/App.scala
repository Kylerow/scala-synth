package org.kylerow.scalasynth
import javax.sound.midi.MidiMessage
import org.kylerow.midi.Midi
import org.kylerow.scalasynth.Injectable


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

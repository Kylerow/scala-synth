package org.kylerow.scalasynth.midi

import javax.sound.midi.MidiMessage
import javax.sound.midi.Receiver
import javax.sound.midi.MidiUnavailableException
import org.kylerow.scalasynth.Injectable
import com.google.inject.Inject
import org.kylerow.scalasynth
import javax.sound.midi
import org.kylerow.scalasynth.note._

class Midi extends EventProvider{
	@Inject var eventProviderLocator :EventProviderLocator = _ 
    @Inject var eventConnector :EventConnector = _
	
    def registerReceiver(eventReceiver :EventReceiver) = {
      val systemProviders = eventProviderLocator.getAllSystemMidiTransmitters();
      val eventProviders = systemProviders ::: List(this);
      eventConnector.connect(eventProviders,eventReceiver);
    }
    
    def >> (receiver :EventReceiver) = 
      registerReceiver(receiver)
    
    def playNote (note :Note) = 
      this.sendEvent(note)
}

object Midi extends Injectable{
  def apply() :Midi = injector.getInstance(classOf[Midi]);
}
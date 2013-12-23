package org.kylerow.scalasynth.midi

class EventProvider {
  var receivers = List[EventReceiver]();
  
  def addReceiver(eventReceiver :EventReceiver) = 
    receivers :::= List(eventReceiver)
  
  def sendEvent(event :Event) = 
    receivers.foreach(_.receive(event))
}
package org.kylerow.scalasynth.midi

class EventConnector {
	def connect(providers :List[EventProvider],receiver :EventReceiver) = 
	  providers.foreach(_.addReceiver(receiver))
}
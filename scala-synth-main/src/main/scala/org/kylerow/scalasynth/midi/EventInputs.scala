package org.kylerow.scalasynth.midi

trait EventInputs extends EventReceiver{
	val numberOfEventInputs :Int
	def eventMessage(input :Int)(event :Event);
	override def receive(event :Event) = eventMessage(1)(event)
}
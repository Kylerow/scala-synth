package org.kylerow.scalasynth.midi

trait EventReceiver {
	def receive(event :Event);
}
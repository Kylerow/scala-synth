package org.kylerow.scalasynth.note

import org.kylerow.scalasynth.midi.Midi

trait Note {
	var playing :Boolean = false;
	def >> (midi :Midi)= {
	  midi.playNote(this)
	}
	def  off = {this.playing=false; this;}
	def  on = {this.playing=true; this;}
	def value :Int;
}
object a4 extends Note {
  override def value = 69
}
object c5 extends Note {
  override def value = 72
}
object d5 extends Note{
  override def value = 74
}

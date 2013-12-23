package org.kylerow.scalasynth.util

import javax.sound.midi.MidiUnavailableException


object AvoidException {
 def avoidException[T <: Throwable,R](clz :Class[T])(func :()=>R) :R = {
	  try{
	    func()
	  } catch {
          case un :R  => null.asInstanceOf[R];
          case ex :Throwable => println("Exception: "+ ex); throw ex;
      }
  }
  def avoidMidiUnavailableException[R](func :()=>R) = avoidException(classOf[MidiUnavailableException])(func);
  def avoidAll[R](func :()=>R) = avoidException(classOf[Exception])(func);
 
}


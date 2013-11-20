package org.kylerow.scalasynth.audio


import org.kylerow.scalasynth.Injectable

/**
 * The primary audio control - provides input and output
 * 
 * **Note: Syntax I want from the output
 * 			(module, input #) >> audioInstance
 *         This means - I'll probably have to make a 
 *         implicit Tuple2 that offers the >> method
 *         
 *         I also want, as a part of this syntax, that the
 *         audioInstance actually does the work of taking the output
 *         so the >> method should delagate to the audio instance
 *         that is passed in
 * 
 * 
 * @Author Kyle
 */
class Audio {

  
}

object Midi extends Injectable{
  def apply() :Audio = injector.getInstance(classOf[Audio]);
}
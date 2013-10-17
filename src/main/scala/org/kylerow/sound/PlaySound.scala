package org.kylerow.sound

import javax.sound.sampled.AudioSystem 
import java.io.File 
import javax.sound.sampled.SourceDataLine 
import javax.sound.sampled.DataLine
import javax.sound.sampled.AudioFormat 
import math._ 

/**
 * Stole..err Borrowed this from http://vigtig.it/blog/blog/2011/04/12/programming-music/
 */
object PlaySound {   
  val SAMPLE_RATE = 96000f 
  //96khz for audiophiles 
  val BYTE_BUFFER_SIZE = 1000 
  val buf: Array[Byte] = Array.ofDim(BYTE_BUFFER_SIZE) 
  val af = new AudioFormat(SAMPLE_RATE, 8, 1, true, false) 
  val sdl = AudioSystem.getSourceDataLine(af)   
  //Oscillators 
  
  def square(d: Double): Double = if (sin(d) < 0) -1 else 1 
  def sine(d: Double): Double = sin(d) 
  def saw(d: Double): Double = (Pi - (d % (Pi * 2))) / Pi   
  //Returns hertz for nth key 
  
  def key(n: Int): Int = (440f * pow((pow(2, 1 / 12f)), n - 49 - 12)).toInt   
  def main(args: Array[String]) = { 
    println(af) 
    sdl.open(af) 
    sdl.start()   
    frereJacques(saw) 
    frereJacques(square) 
    frereJacques(sine)   
    sdl.drain() 
    sdl.stop() 
    sdl.close() 
  }   
  def frereJacques(osc: Double => Double) = {   
    def tone(tone: Int, speed: Double = 2) = {
      for (i <- 0 until (SAMPLE_RATE / (speed * 2)).toInt by BYTE_BUFFER_SIZE) { 
        for (j <- 0 until BYTE_BUFFER_SIZE) { 
          val angle = (i + j) / (SAMPLE_RATE / tone) * 2.0 * Pi 
          buf(j) = (osc(angle) * 100).toByte 
          val left = (sdl.available - sdl.getBufferSize) 
        } 
        sdl.write(buf, 0, BYTE_BUFFER_SIZE) 
      } 
    }
    val (c, d, e, f, g, a, gdeep) = (key(52), key(54), key(56), key(57), key(59), key(61), key(47));  
  }   
}
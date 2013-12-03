package org.kylerow.scalasynth.audio

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.scalamock.scalatest.MockFactory
import org.kylerow.scalasynth.Word
import javax.sound.sampled.SourceDataLine


@RunWith(classOf[JUnitRunner])
class AudioPortSpec 
extends FlatSpec 
	with ShouldMatchers
	with MockFactory{
	"sendData" should "attempt to put data to the dataline" in {
	  // arrange
	  val wordArray = Array(Word(8,42))
	  val audioPort = new AudioPort
	  audioPort.writeLength=10000;
	  
	  val dataLine = mock[SourceDataLine]
	  audioPort.sourceDataLine = dataLine
	  
	  (dataLine.write _) expects (
	      where {
	        (x :Array[Byte], y :Int, z :Int) => x(0)==42 && y==0 && z==10000 
	      })
	  
	  // act
	  val result = audioPort sendData wordArray
	  
	}
	"sendData" should "put bytes in order, in larger word sizes" in {
	  
	  // arrange
	  val wordArray = Array(Word(16,127),Word(16,1025),Word(16,128))
	  val audioPort = new AudioPort
	  audioPort.writeLength=10000;
	  
	  val dataLine = mock[SourceDataLine]
	  audioPort.sourceDataLine = dataLine
	  
	  (dataLine.write _) expects 
	   (where {
	        (x :Array[Byte], y :Int, z :Int) => 
	          x.deep == Array[Byte](127,0,1,4,-128,0).deep && y==0 && z==10000 
	      })
	      
	  // act
	  val result = audioPort sendData wordArray
	  
	}
}
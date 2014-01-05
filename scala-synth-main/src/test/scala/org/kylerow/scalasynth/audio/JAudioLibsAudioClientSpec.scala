package org.kylerow.scalasynth.audio

import org.scalatest.FlatSpec
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import scala.collection.JavaConversions
import org.scalamock.scalatest.MockFactory
import java.nio.FloatBuffer
import org.kylerow.scalasynth.Word

@RunWith(classOf[JUnitRunner])
class JAudioLibsAudioClientSpec
extends FlatSpec 
	with ShouldMatchers
	with MockFactory{
	"Process" should 
			"get audio from the callback method " +
			"and put it into output buffers that are passed in" in {
	  // arrange
	  val mockCallback = mockFunction[Array[Word]]
	 (mockCallback).expects()
	  .returns(
	      Array(
	          Word(8,1),
	          Word(8,2),
	          Word(8,3)))
	  
	  // act
	  var resultBuffer  = new java.util.ArrayList[FloatBuffer]
	  val jAudioLibsAudioClient = new JAudioLibsAudioClient();
	  jAudioLibsAudioClient.setDataRetriever(mockCallback);
	  jAudioLibsAudioClient.process(0,null,resultBuffer,100);
	  
	  assert(resultBuffer.get(0).equals(FloatBuffer.wrap(Array[Float](1,2,3))))
	}
}
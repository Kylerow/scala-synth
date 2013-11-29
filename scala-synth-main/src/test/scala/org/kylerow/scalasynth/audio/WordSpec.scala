package org.kylerow.scalasynth.audio

import org.scalamock.scalatest.MockFactory
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers


@RunWith(classOf[JUnitRunner])
class WordSpec 
extends FlatSpec 
	with ShouldMatchers
	with MockFactory{
  
	"Implicit conversion" should 
	"allow getBytes method that converts to array of bytes" in{
		// arrange
	  	// act
		// assert
	}

}
package org.kylerow.scalasynth.module

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.scalamock.scalatest.MockFactory
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.kylerow.scalasynth.Word
import org.kylerow.scalasynth.Injectable
import com.google.inject.Guice
import com.google.inject.AbstractModule
import scala.collection.mutable.MutableList

@RunWith(classOf[JUnitRunner])
class ModuleSpec 
extends FlatSpec 
	with ShouldMatchers
	with MockFactory{
  "Register" should "register with ModuleRegistry" in{
	  // arrange
	  val mockModuleRegistry = mock[ModuleRegistry]
	   val module = new Module(){
	    def moreAudio(x :Int)() :Boolean = {false}
	    def nextAudioBuffer(x :Int)() :MutableList[Word] = {null}
	  }
	  (mockModuleRegistry.put _).expects (where {
	    (x :(String,Module))=> x._1.equals("test1") && x._2==module
	  })
	 
	  module.moduleRegistry = mockModuleRegistry;
	  
	  // act
	  module register ("test1")
  }
}
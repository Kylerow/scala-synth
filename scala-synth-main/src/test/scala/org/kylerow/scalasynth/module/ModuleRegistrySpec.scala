package org.kylerow.scalasynth.module

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.scalamock.scalatest.MockFactory
import scala.collection.mutable.HashMap
import org.kylerow.scalasynth.Word
import scala.collection.mutable.MutableList

@RunWith(classOf[JUnitRunner])
class ModuleRegistrySpec 
	extends FlatSpec 
	with ShouldMatchers
	with MockFactory{

	"get" should "return appropriate module from map" in {
	  // arrange
	  val module = new Module(){
	    def moreAudio(x :Int)() :Boolean = {false}
	    def nextAudioBuffer(x :Int)() :MutableList[Word] = {null}
	    def getName()="";
	  }
	  val map = HashMap[String,Module]("Test" -> module)
	  val moduleRegistry = new ModuleRegistry
	  moduleRegistry.registry = map;
	  
	  // act
	  val result = moduleRegistry get "Test"
	  
	  // assert
	  assert(result eq module)
	}
	
	"put" should "register the appropriate module" in {
	  // arrange
	  val module = new Module(){
	    def moreAudio(x :Int)() :Boolean = {false}
	    def nextAudioBuffer(x :Int)() :MutableList[Word] = {null}
	    def getName()="";
	  }
	  val map = HashMap[String,Module]()
	  val moduleRegistry = new ModuleRegistry
	  moduleRegistry.registry = map;
	  
	  // act
	  moduleRegistry put ("Test2" -> module)
	  
	  // assert
	  assert(map.get("Test2").get eq module)
	}
}
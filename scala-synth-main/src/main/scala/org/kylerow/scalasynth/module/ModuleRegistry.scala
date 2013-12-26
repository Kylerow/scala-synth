package org.kylerow.scalasynth.module

import scala.collection.mutable.HashMap
import com.google.inject.Inject

class ModuleRegistry {
	@Inject var registry :HashMap[String,Module] = _
	
	def get(key :String) :Module = 
	  registry.get(key).get
	  
	def put(entry :(String,Module)) =
	  registry put (entry._1,entry._2)
	
}
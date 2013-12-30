package org.kylerow.util

import org.kylerow.scalasynth.Injectable
import com.google.inject.Guice
import com.google.inject.AbstractModule

object fakeInjector {
	def useMapping[T](mapping: Tuple2[Class[T],T])={
	  Injectable.injector = Guice.createInjector(new AbstractModule{
		  def configure() = 
		    bind(mapping._1).toInstance(mapping._2)
		  });
	  this
	}
	def and[T](mapping: Tuple2[Class[T],T])={
	  Injectable.injector = Injectable.injector.createChildInjector(new AbstractModule{
		  def configure() = 
		    bind(mapping._1).toInstance(mapping._2)
		  });
	  this
	} 
}
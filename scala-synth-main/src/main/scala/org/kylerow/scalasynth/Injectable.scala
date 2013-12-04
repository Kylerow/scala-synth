package org.kylerow.scalasynth

import com.google.inject.Injector
import com.google.inject.Guice

trait Injectable {
	implicit var injector = Injectable.injector;
}
object Injectable{
  var injector:Injector = Guice.createInjector();
}
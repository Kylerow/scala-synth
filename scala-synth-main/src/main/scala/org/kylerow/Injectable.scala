package org.kylerow

import com.google.inject.Injector
import com.google.inject.Guice

trait Injectable {
	implicit var injector :Injector = Guice.createInjector();
}
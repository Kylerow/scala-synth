package org.kylerow.util

import java.util.logging.LogManager
import java.util.logging.Level

object fineLogging {
	def apply() = {
	   var log = LogManager.getLogManager().getLogger("");
    	log.setLevel(Level.FINE)
		for (h <- log.getHandlers()) 
	    	h.setLevel(Level.FINE);
	}
}
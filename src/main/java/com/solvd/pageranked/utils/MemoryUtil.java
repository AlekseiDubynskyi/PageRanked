package com.solvd.pageranked.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MemoryUtil {

	private static final Logger LOG = LoggerFactory.getLogger(MemoryUtil.class);
	
	public static long getUsedMemory() {
		System.gc() ;
		System.gc() ;
		System.gc() ;
		
		return ( Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() ) / ( 1024 * 1024 ) ;
	}
	
	public static void printUsedMemory() {
		LOG.debug (String.format("memory used is %d MB", getUsedMemory())) ;
	}
	
}

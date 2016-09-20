package com.gzpy.util;

import java.util.UUID;

public class GenerateGUID {
	public static String getGuid(){
		  UUID uuid = UUID.randomUUID();
		  return uuid.toString();  
	}
}

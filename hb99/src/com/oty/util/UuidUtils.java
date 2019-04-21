package com.oty.util;

import org.commontemplate.util.coder.UUID;

import pub.functions.StrFuncs;

public class UuidUtils {

	public static String getSeq(String mobile){
		if(!StrFuncs.notEmpty(mobile)){
			return "";
		} 
		return UUID.nameUUIDFromBytes(mobile.getBytes()).toString().replace("-", "");
	}
	
	public static String genUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public static void main(String args[]){
		UUID uuid = UUID.randomUUID(); 
		System.out.println(uuid.toString());
		System.out.println(UUID.nameUUIDFromBytes("1".getBytes()).toString().replace("-", "")); 
		System.out.println(UUID.nameUUIDFromBytes("2".getBytes()).toString().replace("-", ""));
	}
	
}

package com.itravel.server.services.rest.utils;

import org.apache.commons.codec.binary.Base64;

public class AuthUtil {
	private static final Base64 base64=new Base64();  
	public static String[] decodes(String authToken){
		byte[] b=authToken.getBytes();  
        b=base64.decode(b);  
        String s=new String(b);  
        return s.split(":");  
	}
	
	public static String encodes(String userName,String password){
		byte[] encodes = base64.encode((userName+":"+password).getBytes());
		return new String(encodes);
	}
	
	public static void main(String[] args) {
		System.out.println(AuthUtil.encodes("admin", "admin")); //YWRtaW46YWRtaW4=
	}
}

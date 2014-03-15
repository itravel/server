package com.itravel.server.services.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.google.common.io.ByteSink;
import com.google.common.io.ByteSource;
import com.google.common.io.Files;

public final class ImageResourceUtil {
	private static String filePath = "d:/";
	
	public static boolean saveImage(InputStream input,String fileName){
		ByteSink byteSink=  Files.asByteSink(new File(filePath+fileName+".png"));
		try {
			byteSink.writeFrom(input);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	public static InputStream readImageAsStream(String fileName) throws FileNotFoundException,IOException{
		ByteSource source = Files.asByteSource(new File(filePath+fileName+".png"));
		return source.openBufferedStream();
		
	}
	
}

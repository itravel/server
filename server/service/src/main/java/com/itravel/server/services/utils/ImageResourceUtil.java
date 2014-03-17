package com.itravel.server.services.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import com.google.common.io.ByteSink;
import com.google.common.io.ByteSource;
import com.google.common.io.Files;

public final class ImageResourceUtil {
	private static String rootPath = "d:/";
	
	public static String saveImage(InputStream input,ImageCategory category,String fileNamePrefix){
		String filePath = category.name().toLowerCase()+fileNamePrefix+String.valueOf(UUID.randomUUID())+".png";
		ByteSink byteSink=  Files.asByteSink(new File(rootPath+filePath));
		try {
			byteSink.writeFrom(input);
			return filePath;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "" ;
		
	}
	
	public static InputStream readImageAsStream(String filePath) throws FileNotFoundException,IOException{
		ByteSource source = Files.asByteSource(new File(rootPath+filePath));
		return source.openBufferedStream();
	}
	
}

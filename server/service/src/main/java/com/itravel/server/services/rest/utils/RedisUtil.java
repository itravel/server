package com.itravel.server.services.rest.utils;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import redis.clients.jedis.Jedis;

public class RedisUtil {
	public Jedis getConnent(){
		Jedis jj = new  Jedis("localhost");
		return jj;
	}
	
	 public byte[] getCompressedImage(BufferedImage image){
		byte[] imageData = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, "png", baos);
			imageData = baos.toByteArray();
		} catch (IOException ex) {
			imageData = null;
		}
		return imageData;
	}
	 
	 private BufferedImage getDecompressedImage(byte[] imageData){
		 try {
			 ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
			 return ImageIO.read(bais);
		 } catch (IOException ex) {
			 return null;
		 	}
	}
	 
	public void setImage(String key,BufferedImage image){
		Jedis jj = getConnent();
		jj.set(key.getBytes(), getCompressedImage(image));
		jj.close();
	}
	
	public BufferedImage getImage(String key){
		Jedis jj = getConnent();
		BufferedImage ss = getDecompressedImage(jj.get(key.getBytes()));
		jj.close();
		return ss;
	}
}

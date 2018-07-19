package com.cncg.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.sun.corba.se.impl.ior.ByteBuffer;

import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;

public class MD5Util {

	public static String EncoderPwdByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		MessageDigest md5=MessageDigest.getInstance("MD5");
		BASE64Encoder base64en=new BASE64Encoder();
		return base64en.encode(md5.digest(str.getBytes("utf-8")));
	}
	
	
	public static byte[] DecoderPwdByMd5(String str) throws NoSuchAlgorithmException, IOException{
		MessageDigest md5=MessageDigest.getInstance("MD5");
		BASE64Decoder base64de=new BASE64Decoder();
		return base64de.decodeBuffer(str);
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		//System.out.println(EncoderPwdByMd5("jack"));
		System.out.println(DecoderPwdByMd5(EncoderPwdByMd5("jack")));
		
	}
}
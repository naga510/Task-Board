package com.src.board.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class UserUtil {

	/**
	    * From a base 64 representation, returns the corresponding byte[] 
	    * @param data String The base64 representation
	    * @return byte[]
	    */
	   public static byte[] base64ToByte(String data) {
	       Decoder decoder= Base64.getDecoder();
	       return decoder.decode(data);
	   }
	   
	   /**
	    * From a byte[] returns a base 64 representation
	    * @param data byte[]
	    * @return String
	    */
	   public static String byteToBase64(byte[] data){
	       Encoder endecoder = Base64.getEncoder();
	       return endecoder.encodeToString(data);
	   }
	   
	   public static byte[] getHash(int iterationNb, String password, byte[] salt) throws NoSuchAlgorithmException, UnsupportedEncodingException {
	       MessageDigest digest = MessageDigest.getInstance("SHA-256");
	       digest.reset();
	       digest.update(salt);
	       byte[] input = digest.digest(password.getBytes("UTF-8"));
	       for (int i = 0; i < iterationNb; i++) {
	           digest.reset();
	           input = digest.digest(input);
	       }
	       return input;
	   }
	   
	  
}

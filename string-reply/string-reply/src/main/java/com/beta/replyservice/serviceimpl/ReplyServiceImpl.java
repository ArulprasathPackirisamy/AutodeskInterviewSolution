package com.beta.replyservice.serviceimpl;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

import com.beta.replyservice.serviceinterface.ReplyServiceInt;




@Service("replyServiceImpl")
public class ReplyServiceImpl implements ReplyServiceInt {

  @Override
  public String inputMessage(String mess) {
    String[] inputArr = mess.split("-");
    String firstInput = inputArr[0];
    String secInput = inputArr[1];

    for (byte bValue : firstInput.getBytes()) {
      if (bValue == '1') {
    	  secInput = retrieveReversedString(secInput);
      } else {
    	  secInput = retrieveMd5HashedValue(secInput);
      }
    }
    return secInput;
  }

  protected String retrieveReversedString(String s) {
    return new StringBuilder(s).reverse().toString();
  }

  protected String retrieveMd5HashedValue(String s) {
    MessageDigest md = null;
    
      try {
		md = MessageDigest.getInstance("MD5");
	} catch (NoSuchAlgorithmException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    byte[] inputBytes = s.getBytes(StandardCharsets.UTF_8);
    byte[] outputMd5 = md.digest(inputBytes);
    BigInteger bigIntegerValue = new BigInteger(1, outputMd5);
    return String.format("%0" + (outputMd5.length << 1) + "x", bigIntegerValue);
  }
}

package com.spidy.util;
import java.security.*;import java.nio.charset.StandardCharsets;
public class PasswordUtil { public static String hash(String v){try{byte[] b=MessageDigest.getInstance("SHA-256").digest(v.getBytes(StandardCharsets.UTF_8));StringBuilder s=new StringBuilder();for(byte x:b)s.append(String.format("%02x",x));return s.toString();}catch(Exception e){throw new RuntimeException(e);}} }

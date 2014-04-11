/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivenwang.util;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;
import java.util.UUID;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 使用DES加密和解密的方法
 */
public class VerifyTools {
    
    private static AlgorithmParameterSpec iv = null;
    private static Key keyy = null;
    private static final String keyyy = "12345678";
    private static final String ivvvv = "12345678";
    
    private static final String rekeyyy = "12345678";
    private static final String reivvvv = "12345678";

    //获取一个基于毫秒+字符串生成的UUID
    public static synchronized String getUUID(String string){
    	return UUID.nameUUIDFromBytes((String.valueOf(System.nanoTime())+string).getBytes()).toString().replaceAll("-", "");
    }
    
    private static void VerifyToolsInit() throws Exception{
        
        String key = keyyy;
        String Vector = ivvvv;
        byte[] bytes = new byte[8];
        bytes = Arrays.copyOf(padRight(key,bytes.length,' ').getBytes("utf-8"), bytes.length);
        byte[] ivbyte = new byte[8];
        ivbyte = Arrays.copyOf(padRight(Vector,ivbyte.length,' ').getBytes("utf-8"), ivbyte.length);
        
        DESKeySpec keySpec = new DESKeySpec(bytes);
        iv = new IvParameterSpec(ivbyte);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        keyy = keyFactory.generateSecret(keySpec);
 //       System.out.println(new String(keyy.getEncoded()));     key的值
    }
    
    private static void VerifyToolsReInit() throws Exception{
    	
    	String key = rekeyyy;
    	String Vector = reivvvv;
    	byte[] bytes = new byte[8];
    	bytes = Arrays.copyOf(padRight(key,bytes.length,' ').getBytes("utf-8"), bytes.length);
    	
    	byte[] ivbyte = new byte[8];
    	ivbyte = Arrays.copyOf(padRight(Vector,ivbyte.length,' ').getBytes("utf-8"), ivbyte.length);
    	
    	DESKeySpec keySpec = new DESKeySpec(bytes);
    	iv = new IvParameterSpec(ivbyte);
    	SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
    	keyy = keyFactory.generateSecret(keySpec);
    }
    
    
    /**
     * 加密方法1
     * @param data
     * @return 加密后的密文
     * @throws Exception
     */
    public static String encode(String data) throws Exception {
    	VerifyToolsInit();
        Cipher enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");// 得到加密对象Cipher
        enCipher.init(Cipher.ENCRYPT_MODE, keyy, iv);// 设置工作模式为加密模式，给出密钥和向量
        byte[] pasByte = enCipher.doFinal(data.getBytes("UTF-8"));
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encode(pasByte);
    }

    /**
     * 加密方法2
     * @param data
     * @return 加密后的密文
     * @throws Exception
     */
    public static String reEncode(String data) throws Exception {
    	VerifyToolsReInit();
    	Cipher enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");// 得到加密对象Cipher
    	enCipher.init(Cipher.ENCRYPT_MODE, keyy, iv);// 设置工作模式为加密模式，给出密钥和向量
    	byte[] pasByte = enCipher.doFinal(data.getBytes("UTF-8"));
    	BASE64Encoder base64Encoder = new BASE64Encoder();
    	return base64Encoder.encode(pasByte);
    }

    
    /**
     * 解密方法1
     * @param data
     * @return 解密后的原文
     * @throws Exception
     */
    public static String decode(String data) throws Exception {
    	VerifyToolsInit();
        Cipher deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        deCipher.init(Cipher.DECRYPT_MODE, keyy, iv);
//        deCipher.getIV();   vector的值
        BASE64Decoder base64Decoder = new BASE64Decoder();
        byte[] pasByte = deCipher.doFinal(base64Decoder.decodeBuffer(data));
        return new String(pasByte, "UTF-8");
    }
    /**
     * 解密方法2
     * @param data
     * @return 解密后的原文
     * @throws Exception
     */
    public static String reDecode(String data) throws Exception {
    	VerifyToolsReInit();
    	Cipher deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
    	deCipher.init(Cipher.DECRYPT_MODE, keyy, iv);
    	BASE64Decoder base64Decoder = new BASE64Decoder();
    	byte[] pasByte = deCipher.doFinal(base64Decoder.decodeBuffer(data));
    	return new String(pasByte, "UTF-8");
    }

    /**
     * 组合解密方法 解密顺序为 解密2-解密1
     * @param data
     * @return 解密后的原文
     * @throws Exception
     */
    public static String decodeComplete(String data) throws Exception {
    	return VerifyTools.decode(VerifyTools.reDecode(data));
    }
    
    /**
     * 右补位，左对齐
     *
     * @param oriStr 原字符串
     * @param len 目标字符串长度
     * @param alexin 补位字符
     * @return 目标字符串
     */
    private static String padRight(String oriStr, int len, char alexin) {
        int strlen = oriStr.length();
        String str = "";
        if (strlen < len) {
            for (int i = 0; i < len - strlen; i++) {
                str = str + alexin;
            }
        }
        str = oriStr + str;
        return str;
    }

    public static void main(String[] args) {
        try {
            String test = "6222600110057831559";//123456
            System.out.println("加密前的字符：" + test);
            System.out.println("加密后的字符：" + VerifyTools.encode(test));
            System.out.println("解密后的字符：" + VerifyTools.decode(VerifyTools.encode(test)));
            System.out.println("再次加密后的字符：" + VerifyTools.reEncode(VerifyTools.encode(test)));
            System.out.println("一次解密后的字符：" + VerifyTools.reDecode(VerifyTools.reEncode(VerifyTools.encode(test))));
            System.out.println("最终解密后的字符：" + VerifyTools.decodeComplete(VerifyTools.reEncode(VerifyTools.encode(test))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

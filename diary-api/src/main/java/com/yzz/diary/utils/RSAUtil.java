package com.yzz.diary.utils;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import com.yzz.diary.config.Config;

/**
 * RSA加密工具类
 * @author ZSK
 *
 */
public class RSAUtil {
	private static final String ENCY_ALGORITHM="RSA";//加密算法
	private static final String SIGN_ALGORITHM="MD5withRSA";//签名算法
	private static final int MAX_ENCY_LEN = 117;//加密数据最大长度
	private static final int MAX_DECY_LEN = 128;//解密最大长度
	
	private static final String PUB_KEY=Config.PUB_KEY;
	private static final String PRI_KEY=Config.PRI_KEY;
	
	
	/**
	 * 生成public-key 和 private-key
	 * @return
	 * @throws Exception
	 */
	public static Map<String,Object> generateKeys() throws Exception{
		KeyPairGenerator kpg = KeyPairGenerator.getInstance( ENCY_ALGORITHM );
		kpg.initialize( 1024 );
		KeyPair kp = kpg.generateKeyPair();
		RSAPublicKey pubKey = (RSAPublicKey) kp.getPublic();
		RSAPrivateKey priKey = (RSAPrivateKey) kp.getPrivate();
		Map<String,Object> kMap = new HashMap<String,Object>(2);
		kMap.put( PUB_KEY, pubKey );
		kMap.put( PRI_KEY, priKey );
		
		return kMap;
	}
	
	/**
	 * 获取私钥
	 * @param keyMap
	 * @return
	 * @throws Exception
	 */
    public static String getPrivateKey(Map<String, Object> keyMap)
            throws Exception {
        Key key = (Key) keyMap.get(PRI_KEY);
        return Base64Util.encode(key.getEncoded());
    }

    /**
     * 获取公钥
     * @param keyMap 密钥对
     * @return
     * @throws Exception
     */
    public static String getPublicKey(Map<String, Object> keyMap)
            throws Exception {
        Key key = (Key) keyMap.get(PUB_KEY);
        return Base64Util.encode(key.getEncoded());
    }
    
    /**
     * 私钥加密
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
	public static byte[] encyByPriKey( byte[] data, String key ) throws Exception{
		byte[] keyBytes = Base64Util.decode(key);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ENCY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCY_LEN) {
                cache = cipher.doFinal(data, offSet, MAX_ENCY_LEN);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            offSet += MAX_ENCY_LEN;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
	}
	
	
	/**
	 * 根据私钥解密
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decyByPriKey( byte[] data, String key ) throws Exception{
		byte[] keyBytes = Base64Util.decode(key);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ENCY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECY_LEN) {
                cache = cipher.doFinal(data, offSet, MAX_DECY_LEN);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            offSet += MAX_DECY_LEN;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
	}
	
	/**
	 * 公钥加密
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encyByPubKey(byte[] data, String key) throws Exception{
		byte[] keyBytes = Base64Util.decode(key);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ENCY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCY_LEN) {
                cache = cipher.doFinal(data, offSet, MAX_ENCY_LEN);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            offSet += MAX_ENCY_LEN;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
	}
	
	
	/**
	 * 公钥解密
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decyByPubKey( byte[] data, String key ) throws Exception{
		byte[] keyBytes = Base64Util.decode(key);
		X509EncodedKeySpec x509EncodeKeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory kFac = KeyFactory.getInstance(ENCY_ALGORITHM);
		Key pubK = kFac.generatePublic(x509EncodeKeySpec);
		Cipher cipher = Cipher.getInstance( "RSA" );
		cipher.init( Cipher.DECRYPT_MODE, pubK );
		int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECY_LEN) {
                cache = cipher.doFinal(data, offSet, MAX_DECY_LEN);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            offSet += MAX_DECY_LEN;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
	}
	

}

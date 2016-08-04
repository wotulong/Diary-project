package com.yzz.diary.utils;

import java.io.IOException;

import net.iharder.Base64;



/**
 * Base64工具
 * @author ZSK
 *
 */
public class Base64Util {
	private static final int CACHE_SIZE=1024;
	
	/**
	 * 解码
	 * @param data
	 * @return
	 * @throws IOException 
	 */
	public static byte[] decode( String data ) throws IOException{
		return Base64.decode(data);
	}
	
	/**
	 * 编码
	 * @param data
	 * @return
	 * @throws IOException 
	 */
	public static String encode( byte[] data ) throws IOException{
		return new String( Base64.encodeBytes(data) );
	}
	
	/**
	 * 文件编码到比特数据
	 * @param filePath
	 * @return
	 * @throws Exception 
	 */
	public static String encodeFile( String filePath ) throws Exception{
		byte[] byteFile = FileUtil.file2Bytes( filePath );
		return encode( byteFile );
	}
	
	/**
	 * 比特数据解码到文件
	 * @param filePath
	 * @param base64Str
	 * @throws IOException 
	 */
	public static void decode2File( String filePath, String base64Str ) throws IOException{
		byte[] bytes = decode( base64Str );
		FileUtil.bytes2File( bytes, filePath );
	}
}

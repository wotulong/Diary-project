package com.yzz.diary.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 文件工具
 * @author ZSK
 *
 */
public class FileUtil {
	private static int CACHE_SIZE = 2048;
	
	/**
	 * 读取文件为比特数组
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static byte[] file2Bytes( String filePath ) throws Exception{
		byte[] data = new byte[0];
		File file = new File( filePath );
		if( file.exists() && file.isFile() ){
			FileInputStream is = new FileInputStream(file);
			ByteArrayOutputStream os = new ByteArrayOutputStream(2048);
			byte[] cache = new byte[CACHE_SIZE];
			int nRead = 0;
			while( (nRead=is.read(cache))!=-1 ){
				os.write( cache, 0, nRead );
				//os.flush();
			}
			os.close();
			is.close();
			
			data = os.toByteArray();
		}
		
		return data;
	}
	
	/**
	 * 比特数组写入文件
	 * @param data
	 * @param filePath
	 * @throws IOException
	 */
	public static void bytes2File( byte[] data, String filePath ) throws IOException{
		InputStream is = new ByteArrayInputStream( data );
		File file = new File( filePath );
		if( !file.getParentFile().exists() ){
			file.getParentFile().mkdirs();
		}
		OutputStream os = new FileOutputStream( file );
		byte[] cache = new byte[CACHE_SIZE];
		int nRead = 0;
		
		while( (nRead=is.read(cache)) != -1 ){
			os.write( cache, 0, nRead );
		}
		
		os.close();
		is.close();
		
	}
}

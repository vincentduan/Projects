package com.vincent.NIO;

import java.io.FileInputStream;

public class Test2 {
	/**
	 * 使用IO读取指定文件的前1024个字节
	 * @param args 
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception
	{
	    FileInputStream is = new FileInputStream("D:\\temp_buffer.tmp");
	 
	    byte[] buffer = new byte[1024];
	 
	    is.read(buffer);
	 
	    System.out.println(new String(buffer));
	 
	    is.close();
	}

}

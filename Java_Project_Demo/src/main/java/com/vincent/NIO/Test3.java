package com.vincent.NIO;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Test3 {
	/**
	 * 使用NIO读取指定文件的前1024个字节
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception
	{
	    FileInputStream is = new FileInputStream("D:\\temp_buffer.tmp");
	 
	    //为该文件输入流生成一个唯一的文件通道FileChannel
	    FileChannel channel = is.getChannel();
	 
	    //开辟一个字节为1024的缓冲区
	    ByteBuffer buffer = ByteBuffer.allocate(1024);
	 
	    channel.read(buffer);
	 
	    System.out.println(new String(buffer.array()));
	    System.out.println(buffer.isDirect() + ", " + buffer.isReadOnly());
	 
	    channel.close();
	    is.close();
	}

}

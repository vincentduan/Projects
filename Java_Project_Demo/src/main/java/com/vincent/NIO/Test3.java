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
	 
	    //Ϊ���ļ����������Ψһ���ļ�ͨ��  FileChannel
	    FileChannel channel = is.getChannel();
	 
	    //����һ������Ϊ1024���ֽڻ�����
	    ByteBuffer buffer = ByteBuffer.allocate(1024);
	 
	    channel.read(buffer);
	 
	    System.out.println(new String(buffer.array()));
	    System.out.println(buffer.isDirect() + ", " + buffer.isReadOnly());
	 
	    channel.close();
	    is.close();
	}

}

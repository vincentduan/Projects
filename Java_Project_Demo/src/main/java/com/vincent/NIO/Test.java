package com.vincent.NIO;

import java.nio.ByteBuffer;

public class Test {
	public static void main(String[] args) throws Exception {
		ByteBuffer b = ByteBuffer.allocate(15); //创建15个字节的缓冲区
        System.out.println("limit=" + b.limit() + " capacity=" + b.capacity() + " position=" + b.position());
        for (int i = 0; i < 10; i++) {
            //存入10个字节数据
            b.put((byte) i);
        }
        System.out.println("limit=" + b.limit() + " capacity=" + b.capacity() + " position=" + b.position());
        b.flip(); // 重置position
        System.out.println("limit=" + b.limit() + " capacity=" + b.capacity() + " position=" + b.position());
        for (int i = 0; i < 5; i++) {
            System.out.print(b.get());
        }
        System.out.println("limit=" + b.limit() + " capacity=" + b.capacity() + " position=" + b.position());
        b.flip();
        System.out.println("limit=" + b.limit() + " capacity=" + b.capacity() + " position=" + b.position());
	}

}

package com.vincent.demo;

import java.io.File;
import java.io.IOException;

import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;

/**
 * sl4j+logback
 *
 */
public class LogDemo {
	public static void main(String[] args) throws IOException {
		File clsFile = ResourceUtils.getFile("classpath:file1.txt");
		System.out.println(clsFile.isFile());
		String httpFilePath = "file:D://file1.txt";
		File httpFile = ResourceUtils.getFile(httpFilePath);
		System.out.println(httpFile.isFile());
		
/*		// ① 将文件内容拷贝到一个 byte[] 中
        byte[] fileData = FileCopyUtils.copyToByteArray(httpFile);
        // ② 将文件内容拷贝到一个 String 中
        String fileStr = FileCopyUtils.copyToString(new FileReader(httpFile));*/
        // ③ 将文件内容拷贝到另一个目标文件
        FileCopyUtils.copy(httpFile, new File(httpFile.getParent()+ "/file2.txt"));

	}
}

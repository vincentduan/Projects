package com.vincent.LTP;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class XunFeiXML {
	public static void main(String[] args) throws Exception {
		BufferedReader br = null;
		BufferedReader innet = null;
		URLConnection conn = null;
		FileInputStream fis = new FileInputStream("D://ollie//news.txt");
		FileOutputStream fos = new FileOutputStream(
				"D://ollie//news_result_xunfei.txt");
		br = new BufferedReader(new InputStreamReader(fis));
		String str = "";
		String api_key = "q7A8x2Q3gPRTdKpF8Z9V2skS5TEgnHnffSvhbeQf";
		String pattern = "all";
		String format = "xml";
		String text = "";
		while ((str = br.readLine()) != null) {
			//提取关键字
			if (str.contains("会议")) {
				text += str+"\n";
			}
		}
		text = URLEncoder.encode(text, "utf-8");
		URL url = new URL("http://api.ltp-cloud.com/analysis/?" + "api_key="
				+ api_key + "&" + "text=" + text + "&" + "format=" + format
				+ "&" + "pattern=" + pattern);
		conn = url.openConnection();
		conn.connect();
		innet = new BufferedReader(new InputStreamReader(
				conn.getInputStream(), "utf-8"));
		String line;
		while ((line = innet.readLine()) != null) {
			System.out.println(line);
        	byte[] data = line.getBytes();
            fos.write(data);
		}
		innet.close();
		fos.close();
		br.close();
	}
}

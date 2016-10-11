package com.vincent.LTP;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class XunFeiXML {
	
	public static void main(String[] args) throws Exception {
		doPost();
	}
	
	public static void doPost() throws Exception {
		String api_key = "q7A8x2Q3gPRTdKpF8Z9V2skS5TEgnHnffSvhbeQf";
		String pattern = "all";
		String format = "xml";
		PrintWriter out = null;
        BufferedReader in = null;
        BufferedReader br = null;
        FileInputStream fis = new FileInputStream("D://ollie//news.txt");
		FileOutputStream fos = new FileOutputStream(
				"D://ollie//news_result_xunfei.txt");
		br = new BufferedReader(new InputStreamReader(fis));
		String str = "";
		String text = "";
		while ((str = br.readLine()) != null) {
			//提取关键字
			if (str.contains("会议")) {
				text += str+"\n";
			}
		}
        String param = "api_key="
				+ api_key + "&" + "text=" + text + "&" + "format=" + format
				+ "&" + "pattern=" + pattern;
        try {
            URL realUrl = new URL("http://api.ltp-cloud.com/analysis/");
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
    			System.out.println(line);
            	byte[] data = line.getBytes();
                fos.write(data);
    		}
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
                if(br!=null){
                    br.close();
                }
                if(fos!=null){
                	fos.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
	}
	
	public void doGet() throws Exception{
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
		//获得结果
		innet = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
		String line;
		//保存文件到D://ollie//news_result_xunfei.txt
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

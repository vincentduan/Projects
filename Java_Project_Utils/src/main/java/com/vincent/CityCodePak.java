package com.vincent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 处理csv格式文件
 * 
 * @author iie4b
 *
 */
public class CityCodePak {
	public static void main(String[] args) throws IOException {
		int i = 0;//个数
		FileWriter out = new FileWriter (new File("C:\\Users\\iie4b\\Desktop\\temp\\add.csv"));
		FileReader fr = new FileReader("C:\\Users\\iie4b\\Desktop\\GeoLite2-City-CSV_20170207\\GeoLite2-City-Locations-en.csv");
		BufferedReader bf = new BufferedReader(fr);
		String valueString = "";
		//第一行为表头
		bf.readLine();
		while ((valueString = bf.readLine()) != null) {
			i++;
			try {
				String [] val = valueString.split(",");
				String cityCode = val[0];
				String province = val[7].replaceAll("\"", "");
				String cityName = val[10].replaceAll("\"", "");
				out.write(i + "," + cityCode + "," + province +"," + cityName +"\n"); 
				//System.out.println(cityCode + "," + province +"," + cityName);
			} catch (Exception e) {
				//System.out.println(valueString);
			}
			
		}
		System.out.println(i);
		bf.close();
		out.close();
	}
}

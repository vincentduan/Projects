package com.vincent.LTP;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
/**
 * 根据讯飞云得到的xml，进行解析
 * @author duandingyang
 *
 */
@SuppressWarnings("unchecked")
public class ReadXML {
	
	public static void main(String[] args) throws Exception {
		ReadXML readXML = new ReadXML();
		readXML.parseXML();
	}
	
	public void parseXML() throws Exception{
		SAXReader saxReader = new SAXReader();

        Document document = saxReader.read(new File("D://ollie//news_result2.txt"));

        // 获取根元素
        Element root = document.getRootElement();
        //System.out.println("Root: " + root.getName());

        // 获取所有子元素
        List<Element> childList = root.elements();
        //System.out.println("total child count: " + childList.size());

        // 获取特定名称的子元素
        List<Element> doc = root.elements("doc");
        //System.out.println("doc child: " + doc.size());
        
        // 获取名字为指定名称的第一个子元素
        Element firstDocElement = root.element("doc");
        // 输出其属性
        List<Element> paras = firstDocElement.elements();
        String para_id = "";
        for(Element para : paras){
        	String people = "人物:";//人物
			String time = "时间:";//时间
			String organization = "组织机构:";//组织机构
			String location = "地点:";//地点
			String main="主干:"; //主干
        	para_id = para.attributeValue("id");
        	List<Element> sentences = para.elements();
        	int sentence_id = 0;
        	for (Element sentence : sentences){
        		sentence_id = Integer.parseInt(sentence.attributeValue("id"));
        		List<Element> words = sentence.elements();
        		int word_id = 0;
        		for (Element word : words){
        			word_id = Integer.parseInt(word.attributeValue("id"));
        			String word_cont = word.attributeValue("cont");//单词内容
        			String word_ne = word.attributeValue("ne");//命名实体识别
        			String word_semparent = word.attributeValue("semparent");//语义依存分析父节点id
        			String word_semrelate = word.attributeValue("semrelate");//语义依存分析相应关系
        			String word_head = word.attributeValue("relate");
        			/*if(word_ne.contains("Nh") || word_ne.contains("Ns") || word_ne.contains("Ni") || word_semrelate.contains("Tmod") || word_semrelate.contains("Time")){
        				//System.out.println("para:"+para_id + ",sentence:"+sentence_id + ",word_id:" + word_id + ",word_cont:"+word_cont + ",word_ne:"+word_ne + ",word_semparent:" + word_semparent + ",semrelate:" + word_semrelate);
        				//doFormat(para_id+"", sentence_id+"", word_id+"", word_cont, word_ne, word_semparent, word_semrelate);
        			}*/
        			if(word_ne.toLowerCase().contains("nh")){
        				//人物
        				people += word_cont+",";
        			}
        			if(word_ne.toLowerCase().contains("ns") || word_ne.toLowerCase().contains("nl")){
        				//地名
        				location += word_cont+",";
        			}
        			if(word_ne.toLowerCase().contains("ni")){
        				//组织机构
        				organization += word_cont+",";
        			}
        			if(word_ne.toLowerCase().contains("nt") || word_semrelate.toLowerCase().contains("tmod") || word_semrelate.toLowerCase().contains("time")){
        				//时间
        				time += word_cont+",";
        			}
        			//依存句法分析
        			if(word_head.toLowerCase().contains("hed")){
        				List<Element> word_args = word.elements();
        				for(Element word_arg : word_args){
        					int beg = Integer.parseInt(word_arg.attributeValue("beg"));
        					int end = Integer.parseInt(word_arg.attributeValue("end"));
        					for(int i = beg;i<=end;i++){
        						Element e = (Element) sentence.selectSingleNode("/xml4nlp/doc/para[@id='"+para_id+"']/sent[@id='"+sentence_id+"']"+"/word[@id='"+i+"']");
        						main += e.attributeValue("cont");
        						//System.out.print(e.attributeValue("cont"));
        					}
        				}
        			}
        		}
        	}
        	main += ",";
        	System.out.println();
        	FileOutputStream fos = new FileOutputStream("D://ollie//result2.txt", true);
    		byte[] para_id_byte = ("段落:" + para_id + "\n").getBytes();
    		fos.write(para_id_byte);
    		byte[] people_byte = ("\t\t" + people + "\n").getBytes();
    		fos.write(people_byte);
    		byte[] time_byte = ("\t\t" + time + "\n").getBytes();
    		fos.write(time_byte);
    		byte[] location_byte = ("\t\t" + location + "\n").getBytes();
    		fos.write(location_byte);
    		byte[] organization_byte = ("\t\t" + organization + "\n").getBytes();
    		fos.write(organization_byte);
    		byte[] main_byte = ("\t\t" + main + "\n").getBytes();
    		fos.write(main_byte);
    		fos.close();
        }
	}
	
	public static void doFormat(String para_id, String sentence_id, String word_id, String word_cont, String word_ne, String word_semparent, String word_semrelate) throws Exception{
		FileOutputStream fos = new FileOutputStream("D://ollie//result.txt", true);
		byte[] para_id_byte = ("段落id：" + para_id + "\n").getBytes();
		fos.write(para_id_byte);
		byte[] sentence_id_byte = ("\t句子id：" + sentence_id + "\n").getBytes();
		fos.write(sentence_id_byte);
		byte[] word_id_byte = ("\t\t单词id：" + word_id + "\n").getBytes();
		fos.write(word_id_byte);
		byte[] word_cont_byte = ("\t\t实体：" + word_cont + "\n").getBytes();
		fos.write(word_cont_byte);
		byte[] word_ne_byte = ("\t\t实体识别：" + word_ne + "\n").getBytes();
		fos.write(word_ne_byte);
		byte[] word_semrelate_byte = ("\t\t语义依存分析：" + word_semrelate + "\n").getBytes();
		fos.write(word_semrelate_byte);
	    fos.close();
	}
	
	
}

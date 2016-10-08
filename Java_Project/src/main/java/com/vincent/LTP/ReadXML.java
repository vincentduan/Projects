package com.vincent.LTP;

import java.io.File;
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
		
		SAXReader saxReader = new SAXReader();

        Document document = saxReader.read(new File("D://ollie//news_result_xunfei.txt"));

        // 获取根元素
        Element root = document.getRootElement();
        System.out.println("Root: " + root.getName());

        // 获取所有子元素
        List<Element> childList = root.elements();
        System.out.println("total child count: " + childList.size());

        // 获取特定名称的子元素
        List<Element> doc = root.elements("doc");
        System.out.println("doc child: " + doc.size());
        
        // 获取名字为指定名称的第一个子元素
        Element firstDocElement = root.element("doc");
        // 输出其属性
        List<Element> paras = firstDocElement.elements();
        int para_id = 0;
        for(Element para : paras){
        	para_id = Integer.parseInt(para.attributeValue("id"));
        	List<Element> sentences = para.elements();
        	int sentence_id = 0;
        	for (Element sentence : sentences){
        		sentence_id = Integer.parseInt(sentence.attributeValue("id"));
        		List<Element> words = sentence.elements();
        		int word_id = 0;
        		for (Element word : words){
        			word_id = Integer.parseInt(word.attributeValue("id"));
        			String word_cont = word.attributeValue("cont");
        			String word_ne = word.attributeValue("ne");
        			System.out.println("para:"+para_id + "sentence:"+sentence_id + "word_id:" + word_id + "word_cont:"+word_cont + "word_ne:"+word_ne);
        		}
        	}
        }
        /*System.out.println("迭代输出-----------------------");
        // 迭代输出
        for (Iterator iter = root.elementIterator(); iter.hasNext();)
        {
            Element e = (Element) iter.next();
            System.out.println(e.attributeValue("name"));

        }

        System.out.println("用DOMReader-----------------------");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        // 注意要用完整类名
        org.w3c.dom.Document document2 = db.parse(new File("students.xml "));

        DOMReader domReader = new DOMReader();

        // 将JAXP的Document转换为dom4j的Document
        Document document3 = domReader.read(document2);

        Element rootElement = document3.getRootElement();

        System.out.println("Root: " + rootElement.getName());*/

	}
}

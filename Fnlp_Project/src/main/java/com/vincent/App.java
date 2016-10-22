package com.vincent;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.fnlp.nlp.cn.tag.POSTagger;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args) throws Exception {
		Options opt = new Options();
		String[] str = {"周杰伦出生于台湾，生日为79年1月18日，他曾经的绯闻女友是蔡依林。"}; 
		opt.addOption("h", false, "Print help for this application");
		opt.addOption("f", false, "segment file. Default string mode.");
		opt.addOption("s", false, "segment string");
		BasicParser parser = new BasicParser();
		CommandLine cl = parser.parse(opt, str);

		if (args.length == 0 || cl.hasOption('h')) {
			HelpFormatter f = new HelpFormatter();
			f.printHelp(
					"Tagger:\n"
							+ "java edu.fudan.nlp.tag.POSTagger -f cws_model_file pos_model_file input_file output_file;\n"
							+ "java edu.fudan.nlp.tag.POSTagger -s cws_model_file pos_model_file string_to_segement",
							opt);
			return;
		}
		String[] arg = cl.getArgs();
		String cws_model_file,pos_model_file;
		String input;
		String output = null;
		if (cl.hasOption("f") && arg.length == 4) {
			cws_model_file = arg[0];
			pos_model_file = arg[1];
			input = arg[2];
			output = arg[3];
		} else if (arg.length == 3) {
			cws_model_file = arg[0];
			pos_model_file = arg[1];
			input = arg[2];
		} else {
			System.err.println("paramenters format error!");
			System.err.println("Print option \"-h\" for help.");
			return;
		}
		POSTagger pos = new POSTagger(cws_model_file, pos_model_file);
		if (cl.hasOption("f")) {
			String s = pos.tagFile(input);
			OutputStreamWriter w = new OutputStreamWriter(new FileOutputStream(
					output), "utf8");
			w.write(s);
			w.close();
		} else {
			String s = pos.tag(input);
			System.out.println(s);
		}
	}
}

package com.home.learn.kafka.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.MathContext;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.nio.channels.FileLockInterruptionException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class testThings3 {

	public static void main(String[] args) throws IOException {
		
		String regex="([A-Z]{6}\\s+)([A-Z]{7}\\s+)(\\d{4}\\d{2}\\d{2})"
				+ "((?:[01]\\d|2[0123])(?:[012345]\\d)(?:[012345]\\d))(\\d+{13})([A-Za-z]{4})([A-Za-z0-9]{8})"
				+ "([A-za-z0-9]{17})([A-Za-z0-9]{2})([A-za-z0-9]{7})(\\d{4}\\d{2}\\d{2})"
				+ "((?:[01]\\d|2[0123])(?:[012345]\\d)(?:[012345]\\d)(\\d+{6}))(\\d{4}\\d{2}\\d{2})"
				+ "((?:[01]\\d|2[0123])(?:[012345]\\d)(?:[012345]\\d))([0-9]{3})(\\d{4}\\d{2}\\d{2})([A-za-z0-9]{9})";
		
		Pattern pattern=Pattern.compile(regex);
		
		String text="ELIADE   CENTRAL  20191231"
				+ "1346181234567890123EFERCAF00001"
				+ "VIN00000000000001U8PSV000120191231"
				+ "21012700006520191231"
				+ "13461800020200112ABCD12345";
		
		File file =new File("D:/testFileData.txt");
		//BufferedReader br=new BufferedReader(new FileReader(file));

		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		
		String line=null;
		StringBuilder stringBuilder=new StringBuilder();

		while(br.ready()) {
			//stringBuilder.append(br.readLine());
			line=br.readLine();
		}
		br.close();

		parseData(line,pattern);
			
	}
	public static void parseData(String str,Pattern pattern) {
		Matcher matcher=pattern.matcher(str);
		System.out.println(matcher.matches());
		System.out.println(matcher.group(1));
	}

}

package com.home.learn.kafka.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class testThings2 {

	public static void main(String[] args) throws IOException {
		
		String regex="([A-Z]{6}\\s+)([A-Z]{7}\\s+)(\\d{4}\\d{2}\\d{2})"
				+ "((?:[01]\\d|2[0123])(?:[012345]\\d)(?:[012345]\\d))(\\d+{13})([A-Za-z]{4})([A-Za-z0-9]{8})"
				+ "([A-za-z0-9]{17})([A-Za-z0-9]{2})([A-za-z0-9]{7})(\\d{4}\\d{2}\\d{2})"
				+ "((?:[01]\\d|2[0123])(?:[012345]\\d)(?:[012345]\\d)(\\d+{6}))(\\d{4}\\d{2}\\d{2})"
				+ "((?:[01]\\d|2[0123])(?:[012345]\\d)(?:[012345]\\d))([0-9]{3})(\\d{4}\\d{2}\\d{2})([A-za-z0-9]{9})";
		String text="ELIADE   CENTRAL  20191231"
				+ "1346181234567890123EFERCAF00001"
				+ "VIN00000000000001U8PSV000120191231"
				+ "21012700006520191231"
				+ "13461800020200112ABCD12345";
//		Matcher matcher=Pattern.compile(regex).matcher("ELIADE   CENTRAL  20191231"
//				+ "1346181234567890123EFERCAF00001"
//				+ "VIN00000000000001U8PSV000120191231"
//				+ "21012700006520191231"
//				+ "13461800020200112ABCD12345");
		Matcher matcher=Pattern.compile(regex).matcher(text);
		boolean b1=matcher.matches();
		System.out.println("1---->"+b1);
		String first=matcher.group(6);
		System.out.println(first);
		System.out.println(matcher.group());
		
		System.out.println(matcher.find());
		

	}

}

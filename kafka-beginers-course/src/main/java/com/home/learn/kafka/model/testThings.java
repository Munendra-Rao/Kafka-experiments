package com.home.learn.kafka.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class testThings {

	public static void main(String[] args) {

		boolean b1=Pattern.compile("[A-Z]{6}\\s+").matcher("ELIADE   ").matches(); 
		System.out.println("1---->"+b1);

		boolean b2=Pattern.compile("[A-Z]{0,7}\\s+").matcher("CENTRAL  ").matches(); 
		System.out.println("2---->"+b2);
		
		boolean b3=Pattern.compile("^\\d{4}\\d{2}\\d{2}$").matcher("20191231").matches(); 
		System.out.println("3---->"+b3);
		
		boolean b4=Pattern.compile("(?:[01]\\d|2[0123])(?:[012345]\\d)(?:[012345]\\d)").matcher("134618").matches(); 
		System.out.println("4---->"+b4);
		
		boolean b5=Pattern.compile("\\d+").matcher("1234567890123").matches(); 
		System.out.println("5---->"+b5);
		
		boolean b6=Pattern.compile("[A-Z]{4}").matcher("EFER").matches(); 
		System.out.println("6---->"+b6);
		
		boolean b7=Pattern.compile("[A-za-z0-9]{8}").matcher("CAF00001").matches(); 
		System.out.println("7---->"+b7);
		
		boolean b8=Pattern.compile("[A-za-z0-9]{17}").matcher("VIN00000000000001").matches(); 
		System.out.println("8---->"+b8);
		
		boolean b9=Pattern.compile("[A-za-z0-9]{2}").matcher("U8").matches(); 
		System.out.println("9---->"+b9);
		
		boolean b10=Pattern.compile("[A-za-z0-9]{7}").matcher("PSV0001").matches(); 
		System.out.println("10---->"+b10);
		
		boolean b11=Pattern.compile("^\\d{4}\\d{2}\\d{2}$").matcher("20191231").matches(); 
		System.out.println("11---->"+b11);
		
		boolean b12=Pattern.compile("(?:[01]\\d|2[0123])(?:[012345]\\d)(?:[012345]\\d)(\\d+{6})").matcher("210127000065").matches(); 
		System.out.println("12---->"+b12);
		
		boolean b13=Pattern.compile("^\\d{4}\\d{2}\\d{2}$").matcher("20191231").matches(); 
		System.out.println("13---->"+b13);
		
		boolean b14=Pattern.compile("(?:[01]\\d|2[0123])(?:[012345]\\d)(?:[012345]\\d)").matcher("134618").matches(); 
		System.out.println("14---->"+b14);
		
		boolean b15=Pattern.compile("[0-9]{3}").matcher("000").matches(); 
		System.out.println("15---->"+b15);
		
		boolean b16=Pattern.compile("^\\d{4}\\d{2}\\d{2}$").matcher("20200112").matches(); 
		System.out.println("16---->"+b16);
		
		boolean b17=Pattern.compile("[A-za-z0-9]{9}").matcher("ABCD12345").matches(); 
		System.out.println("17---->"+b17);
		
		
	}

}
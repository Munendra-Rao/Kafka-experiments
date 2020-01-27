package com.home.learn.kafka.Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.DateFormatter;

import com.home.learn.kafka.model.EFER;

public class FileProcess {

	public List<Object> list=new ArrayList<>();
	
	//regex
	String regex="([A-Z]{6}\\s+)([A-Z]{7}\\s+)(\\d{4}\\d{2}\\d{2})"
			+ "((?:[01]\\d|2[0123])(?:[012345]\\d)(?:[012345]\\d))(\\d+{13})([A-Za-z]{4})([A-Za-z0-9]{8})"
			+ "([A-za-z0-9]{17})([A-Za-z0-9]{2})([A-za-z0-9]{7})(\\d{4}\\d{2}\\d{2})"
			+ "((?:[01]\\d|2[0123])(?:[012345]\\d)(?:[012345]\\d)(\\d+{6}))(\\d{4}\\d{2}\\d{2})"
			+ "((?:[01]\\d|2[0123])(?:[012345]\\d)(?:[012345]\\d))([0-9]{3})(\\d{4}\\d{2}\\d{2})([A-za-z0-9]{9})";

	Pattern pattern=Pattern.compile(regex);
	
	public void readFile() throws IOException {
		File file =new File("D:/testFileData.txt");
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String line;
		while((line=br.readLine())!=null) {
			System.out.println(line);
			//parse data
			parseData(line);
		}
		br.close();
		
	}
	public void parseData(String input) {
		
		Matcher matcher=pattern.matcher(input);
		DateTimeFormatter dateTimeFormatter=DateTimeFormatter.BASIC_ISO_DATE;
		DateTimeFormatter timeFormatter=DateTimeFormatter.ofPattern("HHmmss");
		DateTimeFormatter timeFormatter1=DateTimeFormatter.ofPattern("HHmmssSSSSSS");
		matcher.matches();
		
		System.out.println("integration time "+matcher.group(12));
		System.out.println("integration time "+matcher.group(13));
		System.out.println("event date-->"+matcher.group(14));
		System.out.println("event date-->"+matcher.group(15));
		System.out.println("event date-->"+matcher.group(16));
		System.out.println("event date-->"+matcher.group(17));
		System.out.println("event date-->"+matcher.group(18));
		
		EFER efer=new EFER();
		efer.setSending_application(matcher.group(1));
		efer.setSite(matcher.group(2));		
		efer.setMovementDate(LocalDate.parse(matcher.group(3),dateTimeFormatter));
		efer.setMovemenTime(LocalTime.parse(matcher.group(4),timeFormatter));
		efer.setRecordID(Long.parseLong(matcher.group(5)));
		efer.setMovementCode(matcher.group(6));
		efer.setCaf(matcher.group(7));
		efer.setVin(matcher.group(8));
		efer.setFactoryCode(matcher.group(9));
		efer.setPsvOrder(matcher.group(10));
		efer.setIntegrationDate(LocalDate.parse(matcher.group(11),dateTimeFormatter));
		efer.setIntegrationTime(LocalTime.parse(matcher.group(12),timeFormatter1));
		efer.setEventDate(LocalDate.parse(matcher.group(14),dateTimeFormatter));
		efer.setEventTime(LocalTime.parse(matcher.group(15),timeFormatter));
		efer.setRelatedCAFStatus(matcher.group(16));
		efer.setEliadeFunctionalDate(LocalDate.parse(matcher.group(17),dateTimeFormatter));
		efer.setLogisticAddressCode(matcher.group(18));
		//create respected object
		System.out.println(efer);
		list.add(efer);
		
	}
	
	
}

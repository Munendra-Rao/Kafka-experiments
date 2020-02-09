package com.home.learn.kafka.project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventProcessor {
	
	private String regex="([A-Z]{6}\\s+)([A-Z]{7}\\s+)(\\d{4}\\d{2}\\d{2})"
			+ "((?:[01]\\d|2[0123])(?:[012345]\\d)(?:[012345]\\d))(\\d+{13})([A-Za-z]{4})([A-Za-z0-9]{8})"
			+ "([A-za-z0-9]{17})([A-Za-z0-9]{2})([A-za-z0-9]{7})(\\d{4}\\d{2}\\d{2})"
			+ "((?:[01]\\d|2[0123])(?:[012345]\\d)(?:[012345]\\d)(\\d+{6}))(\\d{4}\\d{2}\\d{2})"
			+ "((?:[01]\\d|2[0123])(?:[012345]\\d)(?:[012345]\\d))([0-9]{3})(\\d{4}\\d{2}\\d{2})([A-za-z0-9]{9})";

	Pattern pattern=Pattern.compile(regex);
	
	EventProducerToKafka eventProducerToKafka=new EventProducerToKafka();

	
	public void fileProcess(File file) {
		String line;
		try {
			//BufferedReader br=Files.newBufferedReader(Paths.get("D:/file3.txt"));
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			while((line=br.readLine())!=null) {
				EFERJsonDto eferJsonDto=parseData(line);				
				eventProducerToKafka.produceToKafka(eferJsonDto);
			}
			br.close();
		}
		catch(Exception e) {}
		
	}
	public EFERJsonDto parseData(String curLine) {
		
		EFERJsonDto efer=new EFERJsonDto();
		
		Matcher matcher=pattern.matcher(curLine);
		DateTimeFormatter dateTimeFormatter=DateTimeFormatter.BASIC_ISO_DATE;
		DateTimeFormatter timeFormatter=DateTimeFormatter.ofPattern("HHmmss");
		DateTimeFormatter timeFormatter1=DateTimeFormatter.ofPattern("HHmmssSSSSSS");
		matcher.matches();
		
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
		
		return efer; 
	}

}

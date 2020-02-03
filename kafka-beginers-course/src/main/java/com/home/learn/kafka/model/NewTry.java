package com.home.learn.kafka.model;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewTry {

	public static void main(String[] args) {

		Logger logger=LoggerFactory.getLogger(NewTry.class);
		
		String regex="([A-Z]{6}\\s+)([A-Z]{7}\\s+)(\\d{4}\\d{2}\\d{2})"
				+ "((?:[01]\\d|2[0123])(?:[012345]\\d)(?:[012345]\\d))(\\d+{13})([A-Za-z]{4})([A-Za-z0-9]{8})"
				+ "([A-za-z0-9]{17})([A-Za-z0-9]{2})([A-za-z0-9]{7})(\\d{4}\\d{2}\\d{2})"
				+ "((?:[01]\\d|2[0123])(?:[012345]\\d)(?:[012345]\\d)(\\d+{6}))(\\d{4}\\d{2}\\d{2})"
				+ "((?:[01]\\d|2[0123])(?:[012345]\\d)(?:[012345]\\d))([0-9]{3})(\\d{4}\\d{2}\\d{2})([A-za-z0-9]{9})";
		
		Pattern pattern=Pattern.compile(regex);
		
		String data;
		
		try(BufferedReader br=Files.newBufferedReader(Paths.get("D:\\file3.txt"))){
			
		Stream<String> stream;	
			while((data=br.readLine())!=null) {
	
				//stream the data for filtering prpose
				stream=Stream.of(data);
				 if(stream.filter(d->d.contains("EFER")) != null) {
					 System.out.println("Yes -- >EFER");
				 }
				
			}
		}catch(Exception e) {
			logger.error("File is not availbe "+e.getMessage());
		}

	}

}

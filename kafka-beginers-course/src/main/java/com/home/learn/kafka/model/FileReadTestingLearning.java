package com.home.learn.kafka.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReadTestingLearning {

	public static void main(String[] args) throws IOException {

		String fileName="D:\\file4.txt";
		
		FileReadTestingLearning fileReadTestingLearning=new FileReadTestingLearning();
		fileReadTestingLearning.readFile(fileName);

	}
	
	public void readFile(String fileName) throws IOException {
		String line=null;
		BufferedReader br=Files.newBufferedReader(Paths.get(fileName));
		while((line=br.readLine())!=null) {
			System.out.println(line);
		}
		
		
	}

}

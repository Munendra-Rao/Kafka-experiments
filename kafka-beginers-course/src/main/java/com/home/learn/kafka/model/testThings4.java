package com.home.learn.kafka.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


public class testThings4 {

	public static void main(String[] args) throws IOException {
		
		testThings4 mainObj=new testThings4();
		String file1="D:/file1.txt";
		String file2="D:/file2.txt";
		String file3="D:/testFileData.txt";
		
		mainObj.fileReader(file1);
		mainObj.fileReader(file2);
		mainObj.fileReader(file3);

	}
	public void fileReader(String fileName) throws IOException {
		
		BufferedReader br=Files.newBufferedReader(Paths.get(fileName));
		
		String line;
		while((line=br.readLine())!=null) {
			if(line.contains("EFER")){
				FurtherTesting(line);
			}else {
			System.out.println("printed from fileREader-------->"+line);
			}
		}
		br.close();
	}

	public void FurtherTesting(String line) {
		System.out.println("printed from furtherTesting-------->"+line);
	}
	
}

package com.home.learn.kafka.project;

import java.io.File;

public class MainRun {

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		File file=new File("D:/file3.txt");
		
		EventProcessor eventProcessor=new EventProcessor();
		eventProcessor.fileProcess(file);

	}

}

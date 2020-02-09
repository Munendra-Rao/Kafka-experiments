package com.home.learn.kafka.project;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EventProcessorTest {

	@Mock
	EventProcessor eventProcessor;
	
	//@Test
	public void testFileProcess_emptyFile() {
		//arrange
		File file =new File("src/test/resources/data.txt");
		
		eventProcessor.fileProcess(file);
			
		//assertTrue(file.exists());
	}
	
	//@Test
	public void testFileProcess_notEmptyFile() {
		eventProcessor.fileProcess();
		assertEquals(expected, actual);
	}

	@Test(expected=Exception.class)
	public void testFileProcess_RuntimeException() {
		//arrange
		//File file =new File("src/test/resources/data.txt");
		File file = null;
		eventProcessor.fileProcess(file);
			
		//assertTrue(file.exists());
	}
	//@Test
	public void testParseData() {
		
	}

}

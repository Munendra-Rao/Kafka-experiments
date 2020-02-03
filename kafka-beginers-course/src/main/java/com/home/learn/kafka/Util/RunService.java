package com.home.learn.kafka.Util;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import com.home.learn.kafka.model.*;

import org.slf4j.Logger;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.internal.util.logging.Log;

import org.hibernate.validator.internal.util.logging.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;


public class RunService {
	
	public static void main(String [] args) throws IOException {
		
		Logger logger=org.slf4j.LoggerFactory.getLogger(RunService.class);
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		
		//read data in bytes
		
				byte[] jsonData=Files.readAllBytes(Paths.get("D:\\file4.txt"));
				ObjectMapper mapper=new ObjectMapper();
				Employee emp=mapper.readValue(jsonData, Employee.class);
				System.out.println("json to Obj ---"+"\n"+emp);
				Set<ConstraintViolation<Employee>> violations=validator.validate(emp);
				for (ConstraintViolation<Employee> violation : violations) {
				    logger.error(violation.getMessage()); 
				    logger.info("reject the object");
				}
				
				//emp.setName("");
				
				String jSonFromObj=mapper.writeValueAsString(emp);
				System.out.println("Obj to Json --->"+"\n"+jSonFromObj);
	}
	

}

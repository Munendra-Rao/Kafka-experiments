package com.home.learn.kafka;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class KafkaJsonSerializer implements Serializer{
	
	public void configure(Map map,boolean b) {}

	public void close() {}
	
	public byte[] serialize(String s, Object o) {
		
		byte[] retVal=null;
		
		ObjectMapper objectMapper=new ObjectMapper();
		
		try {
			//rading kafka byte array data
			retVal=objectMapper.writeValueAsBytes(o);

		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		}
		
		return retVal;
	}
	

}

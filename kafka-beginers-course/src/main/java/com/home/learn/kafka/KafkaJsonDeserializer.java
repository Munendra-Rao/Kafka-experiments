package com.home.learn.kafka;

import java.io.IOException;
import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class KafkaJsonDeserializer<T> implements Deserializer{
	
	private Class<T> type;
	
	public KafkaJsonDeserializer(Class type) {
		this.type=type;
	}

	public Object deserialize(String s, byte[] bytes) {
		
		ObjectMapper objectMapper=new ObjectMapper();
		
		T obj=null;
		try {
			obj=objectMapper.readValue(bytes, type);
		} catch (JsonParseException e) {
			
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void close() {}
	
	public void configure(Map map,boolean b) {}
	

}

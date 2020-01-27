package com.home.learn.kafka;

import java.util.Map;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class KafkaJsonSer1 implements Serializer<JsonNode>{
	
	private final ObjectMapper objectMapper=new ObjectMapper();

	@Override
	public byte[] serialize(String arg0, JsonNode data) {
		if(data==null) {
			return null;
		}
		try {
			objectMapper.writeValueAsBytes(data);
		}catch(Exception e) {
			throw new SerializationException("error serializing json message",e);
		}
		return null;
	}
	
	public KafkaJsonSer1() {
		
	}
	public void close() {
		
	}
	public void configure(Map<String,?> config,boolean isKey) {
		
	}

}

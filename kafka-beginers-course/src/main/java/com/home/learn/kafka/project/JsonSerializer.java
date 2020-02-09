package com.home.learn.kafka.project;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonSerializer implements Serializer<JsonNode>{

	 private final ObjectMapper objectMapper = new ObjectMapper();
	 
	 public JsonSerializer() {}
	 
	@Override
	public byte[] serialize(String topic, JsonNode data) {
		if (data == null)
            return null;

        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Error serializing JSON message", e);
        }
    }

}

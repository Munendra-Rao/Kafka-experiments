package com.home.learn.kafka.project;

import java.util.Collections;
import java.util.Set;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDeserializer implements Deserializer<JsonNode>{
	
	 private ObjectMapper objectMapper = new ObjectMapper();

	    public JsonDeserializer() {
	        this(Collections.emptySet());
	    }

	    
	    JsonDeserializer(final Set<DeserializationFeature> deserializationFeatures) {
	        deserializationFeatures.forEach(objectMapper::enable);
	    }

	    @Override
	    public JsonNode deserialize(String topic, byte[] bytes) {
	        if (bytes == null)
	            return null;

	        JsonNode data;
	        try {
	            data = objectMapper.readTree(bytes);
	        } catch (Exception e) {
	            throw new SerializationException(e);
	        }

	        return data;
	    }

}

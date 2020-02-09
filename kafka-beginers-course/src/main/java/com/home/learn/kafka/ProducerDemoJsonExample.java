package com.home.learn.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.learn.kafka.project.JsonSerializer;

public class ProducerDemoJsonExample {
	
	/**
	 * @param args
	 */
	public static void main(String [] args) {
	
		Logger logger=LoggerFactory.getLogger(ProducerDemoJsonExample.class);
				
		String bootStrapServer="127.0.0.1:9092";
		//define properties	
		
		Properties properties=new Properties();
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootStrapServer);
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());
		
		String topic="first_topic";
		String key="Key_"+Integer.toString(1);
		
		KafkaProducer producer=new KafkaProducer(properties);		
		
		Customer cus=new Customer(1, "Munendra");
		
		
		
		ObjectMapper mapper=new ObjectMapper();
		
		JsonNode value = mapper.valueToTree(cus);
        	producer.send(new ProducerRecord<>(topic,key,value), (metadata, exception) -> {
        		if (exception != null) {
        			logger.error("Error sending message {} on topic {} with key {} : {}", value, topic, key, exception);
        		}
        });
		
		producer.flush();
		producer.close();
		
		//addressProducer.flush();
		//addressProducer.close();
	}

}

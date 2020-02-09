package com.home.learn.kafka.project;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.learn.kafka.ProducerDemoJsonExample;

public class EventProducerToKafka {
	
	public void produceToKafka(EFERJsonDto eferJsonDto){
		
		Logger logger=LoggerFactory.getLogger(ProducerDemoJsonExample.class);
		
		String bootStrapServer="127.0.0.1:9092";
		//define properties	
		
		Properties properties=new Properties();
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootStrapServer);
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());
		
		
		String topic="first_topic";
		String key="Key_"+Integer.toString(1);
		
		//producer
		KafkaProducer producer=new KafkaProducer(properties);
			
		ObjectMapper mapper=new ObjectMapper();
		
		JsonNode value = mapper.valueToTree(eferJsonDto);
        	producer.send(new ProducerRecord<>(topic,key,value), (metadata, exception) -> {
        		if (exception != null) {
        			logger.error("Error sending message {} on topic {} with key {} : {}", value, topic, key, exception);
        		}
        });
		
		producer.flush();
		producer.close();
		
	}


}

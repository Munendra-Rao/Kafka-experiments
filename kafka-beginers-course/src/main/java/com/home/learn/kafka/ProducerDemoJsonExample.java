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
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, new JsonPOJOSerializer<>().getClass().getName());
		
		
		

		
		String topic="first_topic";
		String key="Key_"+Integer.toString(1);
		
		//cretate Kafka produver infues with property
		KafkaProducer producer=new KafkaProducer(properties);
		//KafkaProducer<String, Address> addressProducer=new KafkaProducer<String, Address>(properties);
		
		//Customer obj to sed
		Customer cus=new Customer(1, "Munendra");
		//Address Obj to send
		//Address address=new Address("RukminiNaga", 268);
		
		//create Producer Records
		//ProducerRecord<String, Customer> customerRrecord=new ProducerRecord<String, Customer>("first_topic", cus);
		//ProducerRecord<String, Address> addressRecord=new ProducerRecord<String, Address>("first_topic", address);
		
		
		
		ObjectMapper mapper=new ObjectMapper();
		
		JsonNode value = mapper.valueToTree(cus);
        	producer.send(new ProducerRecord<>(topic,key,value), (metadata, exception) -> {
        		if (exception != null) {
        			logger.error("Error sending message {} on topic {} with key {} : {}", value, topic, key, exception);
        		}
        });
		
    		//customerProducer.send(customerRrecord);
    		//addressProducer.send(addressRecord);
		
		
		producer.flush();
		producer.close();
		
		//addressProducer.flush();
		//addressProducer.close();
	}

}

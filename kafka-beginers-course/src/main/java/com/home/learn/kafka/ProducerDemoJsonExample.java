package com.home.learn.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class ProducerDemoJsonExample {
	
	public static void main(String [] args) {
	
		String bootStrapServer="127.0.0.1:9092";
		//define properties	
		Properties properties=new Properties();
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootStrapServer);
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaJsonSerializer.class.getName());
			
		
		//cretate Kafka produver infues with property
		KafkaProducer<String, Customer> producer=new KafkaProducer<String, Customer>(properties);
		
		//Customer obj to sed
		Customer cus=new Customer(1, "Munendra");
		
		//create Producer Records
		ProducerRecord<String, Customer> record=new ProducerRecord<String, Customer>("first_topic", cus);
		
		producer.send(record);
		producer.flush();
		producer.close();
		
	}

}

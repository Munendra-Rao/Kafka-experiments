package com.home.learn.kafka.course;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class ProducerExample {

	public static void main(String [] args) {
	
	String bootStrapServer="127.0.0.1:9092";
	
	//producer properties
	Properties prop=new Properties();
	prop.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServer);
	prop.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
	prop.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
	

	//create a producer
	
	KafkaProducer<String, String> producer =new KafkaProducer<>(prop);
	
	//create a producer record
	
	ProducerRecord<String, String> record=new ProducerRecord<String, String>("first_topic", "java");	
	//send data async
	producer.send(record);
	//flush data
	producer.flush();
	//flush and close
	producer.close();
	}

}
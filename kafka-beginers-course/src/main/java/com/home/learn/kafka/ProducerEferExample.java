package com.home.learn.kafka;

import java.io.IOException;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import com.home.learn.kafka.Util.FileProcess;
import com.home.learn.kafka.model.EFER;

public class ProducerEferExample {
	
	static FileProcess fileProcess=new FileProcess();

	public static void main(String[] args) throws IOException {
		
		String bootStrapServer="127.0.0.1:9092";
		//define properties	
		Properties properties=new Properties();
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootStrapServer);
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaJsonSerializer.class.getName());
		
		//call FileProcess and get Object
		
		fileProcess.readFile();
		EFER efer=(EFER) fileProcess.list.get(0);
		String key=efer.getVin();
		
		//Create Kafka produver infues with property
		KafkaProducer<String, EFER> producer=new KafkaProducer<String, EFER>(properties);
				
		//create Producer Records
		ProducerRecord<String,EFER> record=new ProducerRecord<String, EFER>("second_topic",key, efer);
		
		producer.send(record);
			
		producer.flush();
		producer.close();
		
		
	}

}

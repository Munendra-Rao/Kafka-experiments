package com.home.learn.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProducerDemoWithCallback {

	public static void main(String[] args) {
		final Logger logger=LoggerFactory.getLogger(ProducerDemoWithCallback.class);
		
		
		String bootStrapServer="127.0.0.1:9092";
		
		//create producer properties
		Properties properties=new Properties();
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServer);
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		
		//create producer
		KafkaProducer<String, String> producer=new KafkaProducer<String, String>(properties);
		
		for(int i=0;i<10;i++) {
		
		//create a producer record
		ProducerRecord<String, String> record=new ProducerRecord<String, String>("first_topic", "hello world "+i);
		
		//send data ---ASynchronus
		
		producer.send(record,new Callback() {
			
			public void onCompletion(RecordMetadata recordMetadata, Exception e) {

				//exec everytime recor sent successfully
				if(e==null) {
					//record sent successfully
					logger.info("recieved the Meta data \n"
					+"topics "+recordMetadata.topic()+"\n"
					+"offset :"+recordMetadata.offset()+"\n"
					+"timeStamp :"+recordMetadata.timestamp());
					
				}else
				{
					logger.error("error while reproducing"+e);
				}
				
				
			}
		});

		}
		producer.flush();
		producer.close();

	}

}

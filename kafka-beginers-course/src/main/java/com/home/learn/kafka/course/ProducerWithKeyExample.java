package com.home.learn.kafka.course;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProducerWithKeyExample {

	public static void main(String [] args) throws InterruptedException, ExecutionException {
	
	String bootStrapServer="127.0.0.1:9092";
	Logger logger=LoggerFactory.getLogger(ProducerWithKeyExample.class);
	
	//producer properties
	Properties prop=new Properties();
	prop.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServer);
	prop.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
	prop.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
	

	//create a producer
	
	KafkaProducer<String, String> producer =new KafkaProducer<>(prop);
	
	
	
	for(int i=0;i<18;i++) {
	
		
		String topic="first_topic";
		String value="Durga"+Integer.toString(i);
		String key="key_"+Integer.toString(i);
		
	//create producer record
	ProducerRecord<String, String> record=new ProducerRecord<String, String>(topic,key,value);	
	
	logger.info("key:"+key);
	
	//send data async with callback
	producer.send(record, new Callback() {
		
		public void onCompletion(RecordMetadata recordMetaData, Exception e) {
			if(e==null) {
				//the record sent successfully
				logger.info("recieved metadata. \n"+
				"topic "+recordMetaData.topic()+"\n"+
				"Partition"+recordMetaData.partition()+"\n"+
				"offset"+recordMetaData.offset()+"\n"+
				"timestmp"+recordMetaData.timestamp());
				
			}else
			{
				logger.error("error while producing ",e);
			}
			
		}
	}).get();
	}
	
	
	
	
	
	
	
	
	//flush data
	producer.flush();
	//flush and close
	producer.close();
	}

}
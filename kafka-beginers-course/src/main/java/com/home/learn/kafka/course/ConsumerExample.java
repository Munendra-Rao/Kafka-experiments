package com.home.learn.kafka.course;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerExample {

	public static void main(String [] args) {
		
		Logger logger=LoggerFactory.getLogger(ConsumerExample.class.getName());
		
		//consumer configuration
		String bootStrapServer="127.0.0.1:9092";
		String groupId="my-fourth-application";
		String topic="first_topic";
		
		Properties prop=new Properties();
		prop.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServer);
		prop.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
		prop.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		prop.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		prop.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		
		//create Consumer
		KafkaConsumer<String, String> consumer=new KafkaConsumer<>(prop);
			
		
		//subscribe to topic
		consumer.subscribe(Arrays.asList(topic));
		
		//poll the data
		while(true) {
			ConsumerRecords<String, String> consumerRecords=consumer.poll(Duration.ofMillis(100));
			for(ConsumerRecord<String, String> record:consumerRecords) {
				logger.info("key: "+record.key()+" value - "+record.value());
				logger.info("patitions  "+record.partition()+" Offset "+record.offset());
			}
		}
		
	}
}

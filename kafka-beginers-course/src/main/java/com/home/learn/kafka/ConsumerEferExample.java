package com.home.learn.kafka;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.home.learn.kafka.model.EFER;

public class ConsumerEferExample {

	public static void main(String[] args) {
		
		Logger logger=LoggerFactory.getLogger(ConsumerDemo.class.getName());
		String bootStrapServer="127.0.0.1:9092";
		String groupId="my-third-application";
		String topic="second_topic";
		
		//Consumer config properties
		Properties properties=new Properties();
		properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServer);
		properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		//properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaJsonDeserializer<EFER>(EFER.class));
		
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		
		//create Consumer
		KafkaConsumer<String,EFER> consumer=new KafkaConsumer<String, EFER>(properties);
		
		//subscribe to topic
		consumer.subscribe(Arrays.asList(topic));
		
		//poll for new data
		while(true) {
			ConsumerRecords<String, EFER> records=
					consumer.poll(Duration.ofMillis(100));
			for(ConsumerRecord<String, EFER> record:records) {
				logger.info("Key :"+record.key() +" value :"+record.value());
				logger.info("Partition "+record.partition()+" Offset "+record.offset());
			}
			
		}
	

	}

}

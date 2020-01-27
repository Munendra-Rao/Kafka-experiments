package com.home.learn.kafka.course;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerWithThreadExample {

	public static void main(String [] args) {
		
		ConsumerWithThreadExample consumerWithThreadExample=new ConsumerWithThreadExample();
		consumerWithThreadExample.runTest();
		
	}
	public void runTest() {
		
		//consumer configuration
		CountDownLatch latch=new CountDownLatch(1);
		Logger logger=LoggerFactory.getLogger(ConsumerWithThreadExample.class.getName());
		String bootStrapServer="127.0.0.1:9092";
		String groupId="my-fourth-application";
		String topic="first_topic";
		Properties prop=new Properties();
		prop.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServer);
		prop.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
		prop.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		prop.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		prop.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		
		//runnable consumer
		Runnable consumerRunnable=new ConsumerRunnable(prop,latch,topic);
		
		//start the thread
		Thread thread=new Thread(consumerRunnable);
		thread.start();
		
		//add shutdownhook
		Runtime.getRuntime().addShutdownHook(new Thread(()-> {
		logger.info("caught down hook");
		((ConsumerRunnable)consumerRunnable).shutdown();
		}
		));
		
		try {
			latch.wait();
		}catch(Exception e) {
			logger.error("application got interrupted");
		}
		finally {
			logger.info("application is closing");
		}
	}
}

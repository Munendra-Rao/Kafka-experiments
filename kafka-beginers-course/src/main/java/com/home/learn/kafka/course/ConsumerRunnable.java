package com.home.learn.kafka.course;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerRunnable implements Runnable{
	
	private Logger logger=LoggerFactory.getLogger(ConsumerRunnable.class.getName());
	private KafkaConsumer<String, String> consumer;
	private Properties prop;
	private CountDownLatch latch;
	private String topic;
	
	public ConsumerRunnable(Properties prop,CountDownLatch latch,String topic) {
		this.prop=prop;
		this.latch=latch;
		this.topic=topic;
	
		//create consumer
		KafkaConsumer<String, String> consumer=new KafkaConsumer<>(prop);
		//subscribe to topic
		consumer.subscribe(Arrays.asList("topic"));
	}
	

	@Override
	public void run() {

		
		try {
		while(true) {
			ConsumerRecords<String, String> consumerRecords=consumer.poll(Duration.ofMillis(100));
			for(ConsumerRecord<String, String> record:consumerRecords) {
				logger.info("key: "+record.key()+" value - "+record.value());
				logger.info("patitions  "+record.partition()+" Offset "+record.offset());
			}
		}
		}catch(WakeupException e) {
			logger.info("recieved shutdown signal");
		}
		finally{
			consumer.close();
			latch.countDown();
		}
		
	}
	public void shutdown() {
		//the wakeup method is speacial method to interrupt comsumer.poll
		//it will throw the exception
		consumer.wakeup();
	}

}

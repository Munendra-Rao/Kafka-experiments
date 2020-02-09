package com.home.learn.kafka.project;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.learn.kafka.Customer;

public class EventConsumerFromKafka {
	
	public static void main(String [] args) {
		EventConsumerFromKafka eventConsumerFromKafka=new EventConsumerFromKafka();
		eventConsumerFromKafka.consume();
	}
	
	public void consume() {
		Logger logger = LoggerFactory.getLogger(EventConsumerFromKafka.class.getName());
		String bootStrapServer = "127.0.0.1:9092";
		String groupId = "my-fourth-application";
		String topic = "first_topic";

		// Consumer config properties
		Properties properties = new Properties();
		properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServer);
		properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

		// create Consoumer
		KafkaConsumer consumer = new KafkaConsumer<String, JsonNode>(properties);

		// subscribe to topic
		consumer.subscribe(Arrays.asList(topic));

		ObjectMapper mapper = new ObjectMapper();

		EFERJsonDto efer = null;
		// poll for new data
		while (true) {

			ConsumerRecords<String, JsonNode> records = consumer.poll(Duration.ofMillis(100));
			for (ConsumerRecord<String, JsonNode> record : records) {
				
				try {
					efer = mapper.treeToValue(record.value(), EFERJsonDto.class);
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println("Customer Object -------->" + efer);

				logger.info("Key :" + record.key() + " value :" + record.value());
				logger.info("Partition " + record.partition() + " Offset " + record.offset());
			}
		}

	}

}

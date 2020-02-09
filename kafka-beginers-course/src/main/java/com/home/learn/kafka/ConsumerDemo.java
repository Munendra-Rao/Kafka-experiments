package com.home.learn.kafka;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.message.ElectLeadersRequestData.TopicPartitions;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.JsonNodeDeserializer;
import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import com.home.learn.kafka.project.JsonDeserializer;

public class ConsumerDemo {
	
	public static void main(String [] args) throws JsonProcessingException {
		
		Logger logger=LoggerFactory.getLogger(ConsumerDemo.class.getName());
		String bootStrapServer="127.0.0.1:9092";
		String groupId="my-fourth-application";
		String topic="first_topic";
		
		//Deserializer<String> stringDES=new JsonPOJODeserializer<String>();
		//Deserializer<JsonNode> JsonDES=new JsonPOJODeserializer<JsonNode>();
		
		//Consumer config properties
		Properties properties=new Properties();
		properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServer);
		//properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, new JsonPOJODeserializer<String>().getClass().getName());
		//properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, new JsonPOJODeserializer<JsonNode>().getClass().getName());
		properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		
		
		
		//create Consoumer
		KafkaConsumer consumer=new KafkaConsumer<String,JsonNode>(properties);
		
		//subscribe to topic
		consumer.subscribe(Arrays.asList(topic));
		
		ObjectMapper mapper = new ObjectMapper();
		
		//poll for new data
		while(true) {
			
			ConsumerRecords<String,JsonNode> records=consumer.poll(Duration.ofMillis(100));
				for(ConsumerRecord<String,JsonNode> record : records) {
					Customer cus=mapper.treeToValue(record.value(),Customer.class);
					
					System.out.println("Customer Object -------->"+cus);
					
					logger.info("Key :"+record.key() +" value :"+record.value());
					logger.info("Partition "+record.partition()+" Offset "+record.offset());
				}
		}
		
	}
}

package com.home.learn.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class ProducerDemoJsonExample {
	
	/**
	 * @param args
	 */
	public static void main(String [] args) {
	
		String bootStrapServer="127.0.0.1:9092";
		//define properties	
		Properties properties=new Properties();
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootStrapServer);
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaJsonSerializer.class.getName());
			
		
		//cretate Kafka produver infues with property
		KafkaProducer<String, Customer> customerProducer=new KafkaProducer<String, Customer>(properties);
		KafkaProducer<String, Address> addressProducer=new KafkaProducer<String, Address>(properties);
		
		//Customer obj to sed
		Customer cus=new Customer(1, "Munendra");
		//Address Obj to send
		Address address=new Address("RukminiNaga", 268);
		
		//create Producer Records
		ProducerRecord<String, Customer> customerRrecord=new ProducerRecord<String, Customer>("first_topic", cus);
		ProducerRecord<String, Address> addressRecord=new ProducerRecord<String, Address>("first_topic", address);
		
		
		customerProducer.send(customerRrecord);
		addressProducer.send(addressRecord);
		
		customerProducer.flush();
		customerProducer.close();
		
		addressProducer.flush();
		addressProducer.close();
	}

}

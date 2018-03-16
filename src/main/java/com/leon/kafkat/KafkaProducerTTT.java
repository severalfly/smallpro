package com.leon.kafkat;

import java.util.Date;
import java.util.Properties;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import com.alibaba.fastjson.JSONObject;

public class KafkaProducerTTT
{
	private final Producer<String, String> producer;
	public final static String TOPIC = "test";

	private KafkaProducerTTT()
	{
		Properties props = new Properties();
		//此处配置的是kafka的端口
		//		props.put("metadata.broker.list", "localhost:9092");
		String ips = "localhost:9092";
		props.put("broker.list", ips);

		//配置value的序列化类
		//		props.put("serializer.class", "kafka.serializer.StringEncoder");
		//配置key的序列化类
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("bootstrap.servers", ips);
		//		props.put("serializer.class", "kafka.serializer.StringEncoder");

		//request.required.acks
		//0, which means that the producer never waits for an acknowledgement from the broker (the same behavior as 0.7). This option provides the lowest latency but the weakest durability guarantees (some data will be lost when a server fails).
		//1, which means that the producer gets an acknowledgement after the leader replica has received the data. This option provides better durability as the client waits until the server acknowledges the request as successful (only messages that were written to the now-dead leader but not yet replicated will be lost).
		//-1, which means that the producer gets an acknowledgement after all in-sync replicas have received the data. This option provides the best durability, we guarantee that no messages will be lost as long as at least one in sync replica remains.
		props.put("request.required.acks", "-1");

		producer = new KafkaProducer<String, String>(props);
		//		producer.
		//		ProducerConfig i = new ProducerConfig
		//		producer = new Producer<String, String>();
	}

	void produce()
	{
		int messageNo = 1;
		final int COUNT = 10;

		while (messageNo < COUNT)
		{
			String key = String.valueOf(messageNo);
			String data = new Date() + " hello kafka message " + key;
			ProducerRecord<String, String> record = new ProducerRecord<String, String>(TOPIC, data);
			Future<RecordMetadata> i = producer.send(record);
			System.out.println(JSONObject.toJSONString(i));
			//			producer.send(new KeyedMessage<String, String>(TOPIC, key, data));
			System.out.println(data);
			messageNo++;
		}
	}

	public static void main(String[] args)
	{
		new KafkaProducerTTT().produce();
	}
}

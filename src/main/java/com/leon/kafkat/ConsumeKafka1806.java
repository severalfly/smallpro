package com.leon.kafkat;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class ConsumeKafka1806
{

	//		private KafkaConsumer<String, String> consumer = null;
	//	private KafkaConsumer consumer;

	public void run()
	{
		// 初始化

		//		initKafkaConsumer();
		while (true)
		{
			try
			{

				ConsumeKafka1806();
				Thread.sleep(10 * 1000);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				try
				{
					Thread.sleep(10 * 1000L);
				}
				catch (Exception e1)
				{
					e.printStackTrace();
				}
			}
		}
	}

	private void ConsumeKafka1806()
	{
		try
		{
			KafkaConsumer<String, String> consumer = initConsume();

			//			KafkaConsumer<String, String> consumer = KafkaService.initKafkaConsumer(KafkaService.KafkaPlat.TRAIN_CENTER, "jd-group", KafkaService.KafkaTOPIC.BuriedPointUser);

			System.out.println("kafka config succsss");
			while (true)
			{
				ConsumerRecords<String, String> records = consumer.poll(1);// 拉取一次数据
				System.out.println(JSONObject.toJSONString(records));
				for (ConsumerRecord<String, String> record : records)
				{
					//					String data = String.format("kafka success offset = %d, key = %s, value = %s\n", +record.offset(), record.key(), record.value());
					String data = record.value();
					System.out.println(data);
				}

				Thread.sleep(1000);
			}
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}
	}

	private KafkaConsumer<String, String> initConsume()
	{
		Properties props = new Properties();
		// 配置   这里是不需要配置zook 地址的，只需要配置节点就可以，支持逗号分隔的列表
		props.put("bootstrap.servers", "localhost:9092");
		//			props.put("zookeeper.connect", "localhost:2181");

		//group 代表一个消费组
		props.put("group.id", "jd-group");

		//zk连接超时
		props.put("zookeeper.session.timeout.ms", "4000");
		props.put("zookeeper.sync.time.ms", "200");
		props.put("auto.commit.interval.ms", "1000");
		props.put("auto.offset.reset", "earliest");
		//序列化类
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("partition.assignment.strategy", "org.apache.kafka.clients.consumer.RangeAssignor");

		System.out.println("kafka config success 1 ...");

		System.out.println("kafka config success 2 ...");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
		consumer.subscribe(Arrays.asList("test"));// 指定topic消费
		return consumer;
	}

}


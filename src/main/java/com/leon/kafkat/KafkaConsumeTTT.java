package com.leon.kafkat;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.consumer.KafkaConsumer;

public class KafkaConsumeTTT
{
	private final static String TOPIC = "test-topic";

	private static Properties properties;

	static
	{
		properties = new Properties();

		properties.put("zookeeper.connect", "localhost:2181");
		properties.put("group.id", "lijiegroup");
		properties.put("zookeeper.session.timeout.ms", "4000");
		properties.put("zookeeper.sync.time.ms", "200");
		properties.put("auto.commit.interval.ms", "1000");
		properties.put("auto.offset.reset", "smallest");
		properties.put("serializer.class", "org.apache.kafka.common.serialization.StringSerializer");
	}

	/**
	 * 获取消息
	 * 
	 * @throws Exception
	 */
	public void getMsg() throws Exception
	{
		BigDecimal b = new BigDecimal("0");
		//		ConsumerConfig config = new ConsumerConfig(properties);

		KafkaConsumer<String, String> consumer = new KafkaConsumer(properties);

		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();

		topicCountMap.put(TOPIC, new Integer(1));

		//		StringDecoder keyDecoder = new StringDecoder(new VerifiableProperties());
		//
		//		StringDecoder valueDecoder = new StringDecoder(new VerifiableProperties());

		//		Map<String, ConsumerRecords<String, String>> records = consumer.poll(10000);
		//		System.out.println(JSONObject.toJSONString(records));
		consumer.close();
		//		Map<String, List<KafkaStream<String, String>>> consumerMap = consumer.createMessageStreams(topicCountMap, keyDecoder, valueDecoder);
		//
		//		KafkaStream<String, String> stream = consumerMap.get(TOPIC).get(0);
		//
		//		ConsumerIterator<String, String> it = stream.iterator();
		//
		//		while (it.hasNext())
		//		{
		//			String json = it.next().message();
		//			User user = (User) JsonUtils.JsonToObj(json, User.class);
		//			System.out.println(user);
		//		}
	}

}

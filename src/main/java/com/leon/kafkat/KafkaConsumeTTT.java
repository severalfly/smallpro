package com.leon.kafkat;

import java.util.Properties;

import org.apache.kafka.clients.consumer.KafkaConsumer;


public class KafkaConsumeTTT
{
	private final static String TOPIC = "test";

	private KafkaConsumer<String, String> consumer;

	private KafkaConsumeTTT()
	{
		Properties props = new Properties();
        //zookeeper 配置
		props.put("zookeeper.connect", "localhost:2181");

        //group 代表一个消费组
        props.put("group.id", "jd-group");

        //zk连接超时
        props.put("zookeeper.session.timeout.ms", "4000");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.offset.reset", "smallest");
        //序列化类
        props.put("serializer.class", "kafka.serializer.StringEncoder");

		//		Map<?, ?> props1 = new HashMap<String, String>();
		//		ConsumerConfig config = new ConsumerConfig(props1);

		consumer = new KafkaConsumer<String, String>(props);
    }

	void consume()
	{
		//		Map<String, Integer> topicCount = new HashMap<String, Integer>();
		//		topicCount.put(TOPIC, new Integer(1));
		//
		//		Map<String, Integer> topicMap = new HashMap<String, Integer>();
		//		// 1 represents the single thread  
		//		topicCount.put(TOPIC, new Integer(1));
		//		Map<String, List<KafkaLZ4BlockInputStream>> consumerStreamsMap = consumer.;//createMessageStreams(topicMap);
		//		// Get the list of message streams for each topic, using the default decoder.  
		//		List<KafkaStream<byte[], byte[]>> streamList = consumerStreamsMap.get(topic);
		//		for (final KafkaStream<byte[], byte[]> stream : streamList)
		//		{
		//			ConsumerIterator<byte[], byte[]> consumerIte = stream.iterator();
		//			while (consumerIte.hasNext())
		//				System.out.println("Message from Single Topic :: " + new String(consumerIte.next().message()));
		//		}
	}

	public static void main(String[] args)
	{
		//		new KafkaConsumer().consume();
	}

}

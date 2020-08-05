package com.cjl;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public class pipe {

    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-pipe");//标识id
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"master:9092");//kafka接口与端口
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());//序列化
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());//反序列化
        props.put(StreamsConfig.ADMIN_CLIENT_PREFIX, "joker");
        //定义逻辑计算
        final StreamsBuilder builder = new StreamsBuilder();
        //从stream-plaintext-input使用以下拓扑生成器命名的kafka主题创建流：
        KStream<String, String> source = builder.stream("streams-plaintext-input");
        //使用此流执行的最简单的操作将其写入另一个kafka主题，名称为stream-pipe-output
        source.to("stream-pipe-output");
        //KStream<String,String> source = builder.stream("streams-plaintext-input").to("stream-pipe-output");

        //topology通过执行以下操作，我们可以检查从此构建器创建的类型

        final Topology topology = builder.build();

        System.out.println(topology.describe());

        //从上面看来我们已经建立好了两个节点。
        /* Source: KSTREAM-SOURCE-0000000000 (topics: [streams-plaintext-input])
      --> KSTREAM-SINK-0000000001
    Sink: KSTREAM-SINK-0000000001 (topic: stream-pipe-output)
      <-- KSTREAM-SOURCE-0000000000*/
        //到此运行的结果为这样，就是source父节点不断地往sink子节点传输数据
        //现在我们用刚构建的两个组件构建Streams客户端。

        final KafkaStreams cjl = new KafkaStreams(topology, props);
        //调用start（）函数可以触发客户端执行。close（）在此客户端上被调用之前，执行不会停止。
        //因此我们可以添加一个带有倒计时的关闭挂钩，来捕获用户终端并在种植该程序时关闭客户端：
        final CountDownLatch latch = new CountDownLatch(1);

        Runtime.getRuntime().addShutdownHook(new Thread("streams-shut-hook") {
            @Override
            public void run() {
                cjl.close();
                latch.countDown();

            }


        });
        try {
            cjl.close();
            latch.await();
        } catch (Throwable down) {
            System.exit(1);
        }
        System.exit(0);

    }
}

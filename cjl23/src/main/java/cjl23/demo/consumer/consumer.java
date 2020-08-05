package cjl23.demo.consumer;

import com.sun.tools.javac.util.List;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@Component
public class consumer {

    /**
     * 定义此消费者接收topics = "demo"的消息，与controller中的topic对应上即可
     *
     * @param topicPartition
     * @param record         变量代表消息本身，可以通过ConsumerRecord<?,?>类型的record变量来打印接收的消息的各种信息
     */
    @KafkaListener(topics = "prop")
    public void listen(ConsumerRecord <?,?>record) {
       /* Consumer<String, String> consumer = null;
        Collection<TopicPartition> partitions = new ArrayList();


        List<PartitionInfo> partitionInfos;
        //获取主题下所有的分区。如果你知道所指定的分区，可以跳过这一步
        partitionInfos = (List<PartitionInfo>) consumer.partitionsFor("prop");

        if (partitionInfos != null) {
            for (PartitionInfo partition : partitionInfos) {
                partitions.add(new TopicPartition(partition.topic(), partition.partition()));
            }
            //为消费者指定分区
            consumer.assign(partitions);

            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(100);
                for (ConsumerRecord<String, String> record: records) {
                    System.out.printf("topic = %s, partition = %s, offset = %d, customer = %s, country = %s\n", record.topic(), record.partition(), record.offset(), record.key(), record.value());
                }
            }
        }
    }
    */
         System.out.println(record.topic());
         System.out.printf("topic = %s, partition = %s, offset = %d, customer = %s, country = %s\n", record.topic(), record.partition(),record.offset(), record.key(), record.value());


}}

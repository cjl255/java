package cjl22.cjl22.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.*;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Component
@Slf4j
public class consume {


    /**
     * 定义此消费者接收topics = "demo"的消息，与controller中的topic对应上即可
     *  @param record         变量代表消息本身，可以通过ConsumerRecord<?,?>类型的record变量来打印接收的消息的各种信息
     * @param
     * @return
     */
   // ConsumerRecords<String, String> consumerRecords;
    @KafkaListener(topics = "prop")
    public List listen(ConsumerRecord <Integer,String> record) {
        List<String> url = new ArrayList<>();
        /*Consumer<Integer, String> consumer = new KafkaConsumer<Integer, String>();
        Collection<String> topics = Arrays.asList("prop");
        //消费者订阅topic
        consumer.subscribe(topics);
        ConsumerRecords<Integer, String> consumerRecords ;
        while (true) {
            //接下来就要从topic中拉去数据
            consumerRecords = consumer.poll(1000);
            //遍历每一条记录
            for (ConsumerRecord<Integer, String> consumerRecord : consumerRecords) {
                long offset = consumerRecord.offset();
                int partition = consumerRecord.partition();
                Object key = consumerRecord.key();
                Object value = consumerRecord.value();
                System.out.println(offset + " " + partition + " " + key + " " + value);


            }
        }
        for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
            long offset = consumerRecord.offset();
            int partition = consumerRecord.partition();
            Object key = consumerRecord.key();
            Object value = consumerRecord.value();
            System.out.println(offset + " " + partition + " " + key + " " + value);
            url.add(value);

        }*/
        System.out.println(record.topic());
        System.out.printf("topic is %s,offset is %d, partition is %d , value is %s ，key is %s\n", record.topic(), record.offset(),record.partition(), record.value(),record.key());
        url.add(record.value());
        return url;
    }

}

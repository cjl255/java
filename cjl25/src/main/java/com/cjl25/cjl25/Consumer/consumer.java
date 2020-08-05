package com.cjl25.cjl25.Consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 消息接受类
 */
@Component
@Slf4j
public class consumer {

    Queue<String> url = new LinkedList<>();

    @KafkaListener(topics = "wait")
    public String listen (ConsumerRecord<Integer,String> record)
    {

        System.out.println(record.topic());
        System.out.printf("topic is %s,offset is %d, partition is %d , value is %s ，key is %s\n", record.topic(), record.offset(),record.partition(), record.value(),record.key());
        url.add(record.value());
        String one;
        one=url.poll();
        return one;
    }

    /*public Queue<String> URL()
    {
        Map<String,Object> prop = new HashMap<>();
        prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"114.55.32.166");
        prop.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,1000);
        prop.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,true);
        prop.put(ConsumerConfig.GROUP_ID_CONFIG,"wait");
        prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        KafkaConsumer consumer =new KafkaConsumer(prop);
        consumer.subscribe(Collections.singleton("wait"));
        ConsumerRecords<Integer,String > records=consumer.poll(1000);
        for(ConsumerRecord<Integer,String> record : records)
        {
            url.add((String) record.value());
        }
        return url;
    }
    /*public String getoneurl()
    {
        String one;
        one=url.get(0);
        url.remove(0);
        return one;
    }*/
}

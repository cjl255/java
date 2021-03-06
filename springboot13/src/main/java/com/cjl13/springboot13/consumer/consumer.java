package com.cjl13.springboot13.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Component
public class consumer {


    /**
     * 定义此消费者接收topics = "demo"的消息，与controller中的topic对应上即可
     *
     * @param record 变量代表消息本身，可以通过ConsumerRecord<?,?>类型的record变量来打印接收的消息的各种信息
     * @return
     */
    @KafkaListener(topics = "finshedqueue")

    public String listen(ConsumerRecord<?, ?> record) {

        System.out.println(record.topic());
        System.out.printf("topic is %s, offset is %d,partition is %d， value is %s ，key is %s\n", record.topic(), record.offset(),record.partition(),  record.value(),record.key());
        return "success" ;


}}


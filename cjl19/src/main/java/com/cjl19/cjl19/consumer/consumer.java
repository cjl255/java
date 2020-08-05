package com.cjl19.cjl19.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
public class consumer {
    @KafkaListener(topics = "failedqueue")

    public String listen(ConsumerRecord<?, ?> record) {

        System.out.println(record.topic());
        System.out.printf("topic is %s, offset is %d,partition is %d， value is %s ，key is %s\n", record.topic(), record.offset(),record.partition(),  record.value(),record.key());
        return "success" ;
    }
}

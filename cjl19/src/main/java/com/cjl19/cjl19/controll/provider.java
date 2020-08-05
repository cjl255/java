package com.cjl19.cjl19.controll;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class provider {
    String cjl;
    ProducerRecord<?,?> record;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping("sendfailedqueue")
    public String send1( String msg) {
        ProducerRecord<String,?> record;
        kafkaTemplate.send("failedqueue",msg);
        return "success";
    }


}

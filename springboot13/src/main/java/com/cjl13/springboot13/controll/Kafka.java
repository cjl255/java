package com.cjl13.springboot13.controll;



import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


@RestController
@RequestMapping("kafka")
public class Kafka {

    String cjl;
    ProducerRecord<?,?>record;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private AdminClient adminClient;


    @RequestMapping("sendfinshedqueue")
    public String send1( String msg) {
        ProducerRecord<String,?> record;
        kafkaTemplate.send("finshedqueue",msg);
        return "success";
    }

}
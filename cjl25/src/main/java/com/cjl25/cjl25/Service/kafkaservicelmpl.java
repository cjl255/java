package com.cjl25.cjl25.Service;

import com.cjl25.cjl25.Consumer.ConsumerConfiguration;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class kafkaservicelmpl implements kafkaservice {
    @Autowired
    private ConsumerConfiguration con;
    private ConsumerRecord record;

    /*public String geturl()
    {
        List<String> url =  new  ArrayList<>();
        url=con.listener().listen(record);
        String first;
        first=url.get(0);
        url.remove(0);
        return first;

    }

    /*@Override
    public void createtopic(String topic, Integer partition, Short replication) {
        NewTopic topic1 =new NewTopic(topic,partition,replication);
    }*/
}

package cjl22.cjl22.controller;






import cjl22.cjl22.consumer.consume;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;


@RestController
@RequestMapping("kafka")
public class url  {


    @RequestMapping("url")
    public String geturl (ConsumerRecord <Integer,String> record)
    {
        List<String> oneurl = new ArrayList<>();
        //oneurl=listen(record);
        System.out.println("111");
       // ConsumerRecord<String,String> record;
        System.out.println(record.value());
        /*String first_url =null;//取第一个消息
        List<String> oneurl=new ArrayList<>();
        oneurl.add(record.value());//将消息存入列表
        first_url=oneurl.get(0);//获得第一个消息
        oneurl.remove(0);//从列表移除第一个消息，防止重复消费*/
        return "first_url";
    }

}

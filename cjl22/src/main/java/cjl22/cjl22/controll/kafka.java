package cjl22.cjl22.controll;

import cjl22.cjl22.message.message;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("kafka")
public class kafka {

    KafkaTemplate<Object, Object> record;
    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping("send")
    public String send(@RequestParam("key") String key ,@RequestParam("msg") String msg)//三个数为一个级别
    {

        message cjl = new message();
        cjl.setKey(key);
        cjl.setValue(msg);
        String k = 1+"";
        String l = 2+"";
        String m = 3+"";
        if(cjl.getKey().equals(k)){
            kafkaTemplate.send("prop",0,cjl.getKey(),cjl.getValue());
        }
        else if (cjl.getKey().equals(l))
        {
            kafkaTemplate.send("prop",1,cjl.getKey(),cjl.getValue());
        }
        else
        {
            kafkaTemplate.send("prop",2,cjl.getKey(),cjl.getValue());
        }

        return "success" ;
    }

    @RequestMapping("geturl")
    @KafkaListener(topics = "prop")
    public String geturl( ConsumerRecord<?, ?> consumer)
    {
        System.out.println(consumer.value());
        return "111";
    }


}


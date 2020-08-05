package com.cjl25.cjl25.provider;


import com.cjl25.cjl25.Consumer.ConsumerConfiguration;
import com.cjl25.cjl25.Consumer.consumer;
import com.cjl25.cjl25.Message.message;
import com.cjl25.cjl25.Service.kafkaservice;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ExecutionException;

import static ch.qos.logback.core.joran.action.ActionConst.NULL;

/**
 * 消息发送类
 *
 */
@RestController
@RequestMapping("kafka")
public class provider {

    private final KafkaTemplate kafkaTemplate;

    public provider(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Autowired
    private AdminClient adminClient;
    private KafkaTemplate defaultKafkaTemplate;//默认发送，主题固定
    private ConsumerConfiguration con;
    Queue<String> url = new LinkedList<>();

    private consumer pro1;
    private kafkaservice pro2;

    @RequestMapping("send")
    public String send(@RequestParam String key,@RequestParam String msg,String topic)
    {
        message cjl = new message();
        cjl.setKey(key);
        cjl.setValue(msg);
        String k = 1+"";
        String l = 2+"";
        String m = 3+"";
        if(cjl.getKey().equals(k)){
            kafkaTemplate.send(topic,0,cjl.getKey(),cjl.getValue());
        }
        else if (cjl.getKey().equals(l))
        {
            kafkaTemplate.send(topic,1,cjl.getKey(),cjl.getValue());
        }
        else
        {
            kafkaTemplate.send(topic,2,cjl.getKey(),cjl.getValue());
        }
        url.add(cjl.getValue());
        return "success" ;
    }

    @RequestMapping("surl")
    public Queue<String> URL()
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

    @RequestMapping("url")
    public String geturl()
    {
        String firsturl ;
        firsturl=url.poll();
        return firsturl;
    }

    //手动创建topic
    @RequestMapping("topic")
    public void Createtopic(String topic,Integer partition , Short replication)
    {
        NewTopic topic1 = new NewTopic(topic,partition,replication);
        adminClient.createTopics(Arrays.asList(topic1));
    }

    //查询主题信息
    @RequestMapping("Des")
    public void topicinfo(String topic) throws ExecutionException, InterruptedException {
        if(topic!=NULL)
        {
            DescribeTopicsResult result = adminClient.describeTopics(Arrays.asList(topic));
            result.all().get().forEach((k,v)->System.out.println("k: "+k+" ,v: "+v.toString()+"\n"));
        }
    }
    //删除topic
    @RequestMapping("del")
    public void Deletetopic(String topic)
    {
        adminClient.deleteTopics(Collections.singleton(topic));
    }


}

package com.cjl25.cjl25.topic;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaInitialConfiguration {


    //创建TopicName为topic.quick.initial的Topic并设置分区数为8以及副本数为1

    /*@Bean
    public NewTopic initaltopic()
    {
        System.out.println("创建成功");
        return new NewTopic("cjl",3,(short)1);
    }*/



    /**
     * 手动编码创建topic
     * @return
     */
    @Bean
    public KafkaAdmin kafkaAdmin()
    {
        Map<String,Object> prop=new HashMap<>();
        prop.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,"114.55.32.166:9092");
        KafkaAdmin Admin =new KafkaAdmin(prop);
        return Admin;
    }

    @Bean
    public AdminClient adminClient()
    {
        return  AdminClient.create(kafkaAdmin().getConfig());
    }
}

package com.cjl25.cjl25.Consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Configuration
@EnableKafka
public class ConsumerConfiguration {

        @Value("${spring.kafka.consumer.group-id}")
        private String group_id;

        @Value("${spring.kafka.consumer.enable-auto-commit}")
        private boolean enable_auto_commit;

        @Value("${spring.kafka.consumer.auto-commit-interval}")
        private String auto_commit_interval;

        @Value("${spring.kafka.bootstrap-servers}")
        private String bootstrap_ervers;

        @Bean
        public Map<String,Object> ConsumerConfig()
        {
            Map<String,Object> prop = new HashMap<>();
            prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrap_ervers);
            prop.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,auto_commit_interval);
            prop.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,enable_auto_commit);
            prop.put(ConsumerConfig.GROUP_ID_CONFIG,group_id);
            prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
            prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
            return prop;
        }
        private int concurrency=3;

        @Bean
        public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
            ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
            factory.setConsumerFactory(consumerFactory());
            factory.setConcurrency(concurrency);
            factory.getContainerProperties().setPollTimeout(1500);
            return factory;
        }

        @Bean
        public ConsumerFactory<String, String> consumerFactory() {
            return new DefaultKafkaConsumerFactory<>(ConsumerConfig());

        }
        @Bean
        public consumer listener() {
            return new consumer();
        }


        @Bean
        public KafkaConsumer kafkaConsumer(){
            return new KafkaConsumer<>(ConsumerConfig());
        }
    /*@Bean
    public KafkaMessageListenerContainer demoListenerContainer() {
        ContainerProperties properties = new ContainerProperties("prop");

        properties.setGroupId("bean");

        properties.setMessageListener(new MessageListener<Integer,String>() {
            private Logger log = LoggerFactory.getLogger(this.getClass());
            @Override
            public void onMessage(ConsumerRecord<Integer, String> record) {
                log.info("topic.quick.bean receive : " + record.toString());
            }
        });

        return new KafkaMessageListenerContainer(consumerFactory(), properties);
    }*/
}

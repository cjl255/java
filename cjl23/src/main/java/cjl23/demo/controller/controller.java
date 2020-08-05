package cjl23.demo.controller;

import cjl23.demo.message.message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("kafka")
public class controller {

    @Resource
    KafkaTemplate kafkaTemplate ;

    @RequestMapping("send")
    public String send(@RequestParam("key") String key, @RequestParam("msg") String msg) {

        message cjl = new message();
        cjl.setKey(key);
        cjl.setValue(msg);
        /*if (cjl.getKey().equals("high")) ;
        {
            kafkaTemplate.send("prop", 0, "high", msg);

        }*/ //这里不知道为什么会重复运行两次，我用else改了一下

        if (cjl.getKey().equals("mid"))
        {
            kafkaTemplate.send("prop", 1, "mid", msg);
        }

        else if (cjl.getKey().equals("low")) {
            kafkaTemplate.send("prop", 2, "low", msg);
        }
        else
        {
            kafkaTemplate.send("prop", 0, "high", msg);

        }

        return cjl.getKey();
    }
}

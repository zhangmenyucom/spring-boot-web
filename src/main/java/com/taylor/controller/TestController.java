package com.taylor.controller;

import com.taylor.entity.TestEntity;
import com.taylor.producer.SendMessage;
import com.taylor.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping("/test")
@Controller
@Slf4j
public class TestController extends BaseAction {
    @Autowired
    @Qualifier("sendQueueMessage")
    private SendMessage sendQueueMessage;

    @Autowired
    @Qualifier("sendTopicMessage")
    private SendMessage sendTopicMessage;

    @Autowired
    private TestService testService;

    @ResponseBody
    @RequestMapping("/query")
    public List<TestEntity> queryTest(TestEntity test, HttpServletRequest request, HttpServletResponse response) {
        log.debug("这只是一个测试");
        return testService.find(test);
    }

    /**
     * @param message
     * @desc sendQueue(发送消息队列)
     * @author xiaolu.zhang
     * @date 2017年3月2日 下午3:30:15
     */
    @RequestMapping("/queue/{message}")
    @ResponseBody
    public String sendQueue(@PathVariable("message") String message) {
        sendQueueMessage.sendMessage(message);
        return "success";
    }

    /**
     * @param message
     * @desc sendTopic(发送主题订阅)
     * @author xiaolu.zhang
     * @date 2017年3月2日 下午3:30:37
     */
    @RequestMapping("/topic/{message}")
    @ResponseBody
    public String sendTopic(@PathVariable("message") String message) {
        sendTopicMessage.sendMessage(message);
        return "success";
    }
}

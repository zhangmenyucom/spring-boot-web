package com.taylor.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/7/14 18:11
 */
@Component
@Qualifier("jmsQueueTemplate")
@ConfigurationProperties(prefix = JmsQueueTemplate.JMS_TEMPLATE_PREFIX)
public class JmsQueueTemplate extends JmsTemplate {
    public static final String JMS_TEMPLATE_PREFIX = "spring.jms.template";

    @Autowired
    private ConnectionFactory connectionFactory;

    private boolean pubSubDomain = Boolean.FALSE;
}

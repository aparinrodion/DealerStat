package org.leverx.dealerstat.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@PropertySource({"classpath:application.properties"})
@ComponentScan("org.leverx.dealerstat")
@Configuration
public class MailSenderConfig {
    @Value("${mail.host}")
    private String host;
    @Value("${mail.username}")
    private String username;
    @Value("${mail.password}")
    private String password;
    @Value("${mail.port}")
    private Integer port;
    @Value("${mail.protocol}")
    private String protocol;


    @Bean
    JavaMailSender getMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(host);
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(password);
        javaMailSender.setPort(port);
        Properties properties = javaMailSender.getJavaMailProperties();
        properties.setProperty("mail.transport.protocol", protocol);
        return javaMailSender;
    }
}

package com.iralik.graspmath.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class rabbitmqconfig {


    @Value("${rabbitmq.student-queue.name}")
    private String queueStudent;

    @Value("${rabbitmq.tutor-queue.name}")
    private String queueTutor;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    // spring bean for rabbitmq queue

    @Bean
    public Queue queueStudent(){
        return new Queue(queueStudent,true, false, false);

    }
    @Bean
    public Queue queueTutor(){
        return new Queue(queueTutor,true, false, false);
    }
    @Bean
    public TopicExchange emailExchange() {
        return new TopicExchange(exchange);
    }

    //binding bw queue and exchange using routing-key
    @Bean
    public Binding emailBindingForStudent(Queue queueStudent, TopicExchange emailExchange) {
        return BindingBuilder
                .bind(queueStudent)
                .to(emailExchange)
                .with("Registration Conformation");
    }

    @Bean
    public Binding emailBindingForTutor(Queue queueTutor, TopicExchange emailExchange) {
        return BindingBuilder
                .bind(queueTutor)
                .to(emailExchange)
                .with("New Registration.*");
    }
}

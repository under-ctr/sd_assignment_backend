package com.example.project.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMqConfig {
    static final String routingKey = "rabbit";
    static final String queueName = "hello";

    @Bean
    Queue queue(){return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange(){return new TopicExchange("rabbitMQ");}

    @Bean
    Binding binding(Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);

    }
}

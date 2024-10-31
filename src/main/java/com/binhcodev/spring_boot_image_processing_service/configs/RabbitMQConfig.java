package com.binhcodev.spring_boot_image_processing_service.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

@Configuration
public class RabbitMQConfig {
    public static final String EXCHANGE_NAME = "image_transform_exchange";
    public static final String QUEUE_NAME = "image_transform_queue";
    public static final String ROUTING_KEY = "image.transform";

    // @Bean
    // public Queue queue() {
    //     return new Queue(QUEUE_NAME, true);
    // }

    // @Bean
    // public TopicExchange exchange() {
    //     return new TopicExchange(EXCHANGE_NAME);
    // }

    // @Bean
    // public Binding binding(Queue queue, TopicExchange exchange) {
    //     return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    // }

     @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}

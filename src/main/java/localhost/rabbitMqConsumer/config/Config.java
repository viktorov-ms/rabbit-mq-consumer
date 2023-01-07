package localhost.rabbitMqConsumer.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

  @Value("${rabbitmq.topic1}")
  public String topic1;
  @Value("${rabbitmq.topic2}")
  public String topic2;

  @Bean
  public Queue topic1() {
    return new Queue(topic1);
  }

  @Bean
  public Queue topic2() {
    return new Queue(topic2);
  }

  @Bean
  public MessageConverter jackson2MessageConverter() {
    return new Jackson2JsonMessageConverter();
  }
}

package localhost.rabbitMqConsumer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import localhost.rabbitMqConsumer.dto.Person;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class MainService {

  private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  @RabbitListener(queues = {"${rabbitmq.topic1}"})
  public String string(final String message) {
    return message;
  }

  @RabbitListener(queues = {"${rabbitmq.topic2}"})
  public Person main(final byte[] messageByteArray) {
    final Person person = byteArrayToExceptionDto(messageByteArray);
    person.setAge(person.getAge() + 3);
    return person;
  }

  private Person byteArrayToExceptionDto(final byte[] messageJson) {
    try {
      return OBJECT_MAPPER.readValue(messageJson, Person.class);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}

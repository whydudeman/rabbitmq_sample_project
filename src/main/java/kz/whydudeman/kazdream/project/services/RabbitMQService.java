package kz.whydudeman.kazdream.project.services;

import kz.whydudeman.kazdream.project.models.AbstractEntity;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routingkey}")
    private String routingkey;

    public void send(AbstractEntity object) {
        amqpTemplate.convertAndSend(exchange, routingkey, object);
        System.out.println("Send msg = " + object);
    }
}

package io.lib3rtus.playground.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static io.lib3rtus.playground.kafka.GlobalConfig.TOPIC_NAME;

@Slf4j
@Service
public class ConsumerService {

    @KafkaListener(id = "foo", topics = TOPIC_NAME, clientIdPrefix = "myClient")
    public void listen(String message) {
        log.info("Received message: {}", message);
    }
}

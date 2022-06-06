package io.lib3rtus.playground.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lib3rtus.playground.kafka.common.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.util.concurrent.SuccessCallback;

import static io.lib3rtus.playground.kafka.GlobalConfig.TOPIC_NAME;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void produceEvent() {
        try {
            var message = new Message("1", "Title", "Some content", "Pera Peric");
            var listener = kafkaTemplate.send(TOPIC_NAME, objectMapper.writeValueAsString(message));
            log.info("Message dispatched");
            listener.addCallback(
                    result -> log.info("Message successfully sent. Send Result: {}", result),
                    ex -> log.error("Error sending message", ex)
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

package io.lib3rtus.playground.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
public class GlobalConfig {
    public static final String TOPIC_NAME = "TestTopic";

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}

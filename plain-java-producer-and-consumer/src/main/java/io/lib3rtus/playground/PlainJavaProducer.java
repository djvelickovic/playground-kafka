package io.lib3rtus.playground;

import io.lib3rtus.playground.producer.CustomInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

@Slf4j
public class PlainJavaProducer {

    public static void main(String[] args) {

        var props = new Properties();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringSerializer.class.getName());
        props.put(ProducerConfig.ACKS_CONFIG, "all");

        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, CustomInterceptor.class.getName());

        try (var kafkaProducer = new KafkaProducer<String, String>(props)) {
            var record = new ProducerRecord<>("TestTopic1", "key", "Hello world!");
            kafkaProducer.send(record, (recordMetadata, exception) -> {
                if (exception != null) {
                    log.error("Error happened", exception);
                    exception.printStackTrace();
                } else {
                    log.info("Message successfully sent: {}", recordMetadata);
                }
            });
        }
    }

}

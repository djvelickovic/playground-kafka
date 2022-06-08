package io.lib3rtus.playground;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

@Slf4j
public class PlainJavaConsumer {

    public static void main(String[] args) {
        var props = new Properties();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringDeserializer.class.getName());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);

        Duration pollTimeout = Duration.ofMillis(500);

        try (var consumer = new KafkaConsumer<String, String>(props)) {
            consumer.subscribe(Collections.singletonList(GlobalConfig.TOPIC_NAME));

            var records = consumer.poll(pollTimeout);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("topic = %s, partition = %d, offset = %d, customer = %s, value = %s\n",
                        record.topic(), record.partition(), record.offset(), record.key(), record.value());
            }
        }

    }

}

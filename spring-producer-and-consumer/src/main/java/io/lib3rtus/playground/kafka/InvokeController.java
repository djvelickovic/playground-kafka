package io.lib3rtus.playground.kafka;

import io.lib3rtus.playground.kafka.producer.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class InvokeController {

    private final ProducerService producerService;

    @GetMapping
    public ResponseEntity<?> produceMessage() {
        producerService.produceEvent();
        return ResponseEntity.ok().build();
    }
}

package io.lib3rtus.playground;


import lombok.Data;
import lombok.Value;

@Value
public class Message {
    String title;
    String message;
    String signature;
}

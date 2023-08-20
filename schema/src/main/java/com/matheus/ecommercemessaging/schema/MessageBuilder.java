package com.matheus.ecommercemessaging.schema;

import org.springframework.messaging.Message;

public class MessageBuilder<T> {

    public Message<T> build() {
        return new MessageBuilder<T>().build();

    }
}

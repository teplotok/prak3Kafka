package com.clouds.kafkatest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Value(value = "note_topic")
    private String topicNoteName;

    @Value(value = "comment_topic")
    private String topicCommentName;
    public void sendMessageNote(String data) {
        kafkaTemplate.send(topicNoteName, data);
    }

    public void sendMessageComment(String data) {kafkaTemplate.send(topicCommentName, data);}
}

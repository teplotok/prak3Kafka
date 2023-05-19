package com.clouds.kafkatest;


import com.clouds.kafkatest.model.Comment;
import com.clouds.kafkatest.model.Note;
import com.clouds.kafkatest.model.NoteQuery;
import com.clouds.kafkatest.model.RateQuery;
import com.clouds.kafkatest.service.ApiClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class TestController {
    final String uri = "http://172.23.96.1:8086";

    @Autowired
    MessageProducer messageProducer;

    @Autowired
    ApiClient apiClient;



    @GetMapping("/notes")
    public ResponseEntity<List<Note>> getNotes(){
        return apiClient.getNotes();
    }

    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> getComments(){
        return apiClient.getComments();
    }
    @GetMapping("/query1")
    public ResponseEntity<List<NoteQuery>> query1(){
        return  apiClient.query1();
    }

    @GetMapping("/query2")
    public ResponseEntity<List<NoteQuery>> query2(){
        return apiClient.query2();
    }

    @GetMapping("/query3")
    public ResponseEntity<List<RateQuery>> query3(){
        return apiClient.query3();
    }

    @PostMapping("/notes")
    public ResponseEntity<String> createNote(@RequestBody Note note){
        try {
            messageProducer.sendMessageNote(new ObjectMapper().writeValueAsString(note));
            return new ResponseEntity<>("Note was sent to broker.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/comments")
    public ResponseEntity<String> createComment(@RequestBody Comment comment){
        try {
            messageProducer.sendMessageComment(new ObjectMapper().writeValueAsString(comment));
            return new ResponseEntity<>("Comment was sent to broker.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public ResponseEntity<String> ping(){
        return new ResponseEntity<>("ping", HttpStatus.OK);
    }
}

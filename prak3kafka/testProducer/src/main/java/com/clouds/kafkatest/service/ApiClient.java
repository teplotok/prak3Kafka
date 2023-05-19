package com.clouds.kafkatest.service;

import com.clouds.kafkatest.model.Comment;
import com.clouds.kafkatest.model.Note;
import com.clouds.kafkatest.model.NoteQuery;
import com.clouds.kafkatest.model.RateQuery;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ApiClient {
    @Value(value = "${data.address}")
    private String uri;


    public ResponseEntity<List<Note>> getNotes(){

        RestTemplate restTemplate = new RestTemplate();
        List<Note> result = restTemplate.getForObject(uri+"/notes",List.class);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    public ResponseEntity<List<Comment>> getComments(){
        RestTemplate restTemplate = new RestTemplate();
        List<Comment> result = restTemplate.getForObject(uri+"/comments",List.class);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public ResponseEntity<List<NoteQuery>> query1(){
        RestTemplate restTemplate = new RestTemplate();
        List<NoteQuery> result = restTemplate.getForObject(uri+"/query1",List.class);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    public ResponseEntity<List<NoteQuery>> query2(){
        RestTemplate restTemplate = new RestTemplate();
        List<NoteQuery> result = restTemplate.getForObject(uri+"/query2",List.class);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    public ResponseEntity<List<RateQuery>> query3(){
        RestTemplate restTemplate = new RestTemplate();
        List<RateQuery> result = restTemplate.getForObject(uri+"/query3",List.class);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

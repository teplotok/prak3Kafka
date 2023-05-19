package com.clouds.kafkatest;

import com.clouds.kafkatest.model.Comment;
import com.clouds.kafkatest.model.Note;
import com.clouds.kafkatest.model.NoteQuery;
import com.clouds.kafkatest.model.RateQuery;
import com.clouds.kafkatest.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {
    @Autowired
    NoteRepository noteRepository;

    @GetMapping("/")
    public ResponseEntity<String> ping(){
        return new ResponseEntity<>("ping", HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        noteRepository.initiate();
        return new ResponseEntity<>("test", HttpStatus.OK);
    }

    @GetMapping("/query1")
    public ResponseEntity<List<NoteQuery>> query1(){
        List<NoteQuery> notes = new ArrayList<NoteQuery>();
        noteRepository.queryTop3MaxAvgRate().forEach(notes::add);
        if (notes.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @GetMapping("/query2")
    public ResponseEntity<List<NoteQuery>> query2(){
        List<NoteQuery> notes = new ArrayList<NoteQuery>();
        notes.addAll(noteRepository.queryTop5CountComments());
        if (notes.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @GetMapping("/query3")
    public ResponseEntity<List<RateQuery>> query3(){
        List<RateQuery> notes = new ArrayList<RateQuery>();
        notes.addAll(noteRepository.queryCountRate());
        if (notes.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }


    @PostMapping("/notes")
    public ResponseEntity<String> createNote(@RequestBody Note note){
        try {
            noteRepository.save(new Note(note.getName(), note.getText()));
            return new ResponseEntity<>("Note was created.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/notes/{id}")
    public ResponseEntity<String> updateNote(@PathVariable("id") long id, @RequestBody Note note) {
        Note _note = noteRepository.findById(id);

        if (_note != null) {
            _note.setName(note.getName());
            _note.setText(note.getText());
            noteRepository.update(_note);
            return new ResponseEntity<>("Note was updated.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find Note with id=" + id, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/notes")
    public ResponseEntity<List<Note>>getAllNotes(@RequestParam(required = false)String name){
        try {
            List<Note> notes = new ArrayList<Note>();
            noteRepository.findAll().forEach(notes::add);
            if(name!=null){
                notes.clear();
                noteRepository.findByName(name).forEach(notes::add);
            }
            if (notes.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(notes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/comments")
    public ResponseEntity<List<Comment>>getAllComments(){
        return new ResponseEntity<>(noteRepository.findAllComments(), HttpStatus.OK);
    }

    @PostMapping("/comments")
    public ResponseEntity<String> createComment(@RequestBody Comment comment){
        try {
            noteRepository.addComment(new Comment(comment.getName(), comment.getText(),comment.getRate(),comment.getNoteId()));
            return new ResponseEntity<>("Comment was created.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package com.clouds.kafkatest;

import com.clouds.kafkatest.model.Comment;
import com.clouds.kafkatest.model.Note;
import com.clouds.kafkatest.repository.NoteRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class MessageListener {

    @Autowired
    NoteRepository noteRepository;

    @KafkaListener(topics = "note_topic", containerFactory = "kafkaListenerContainerFactory")
    public void listenerNote(String data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(data);
        noteRepository.save(mapper.readValue(data, Note.class));
    }

    @KafkaListener(topics = "comment_topic", containerFactory = "kafkaListenerContainerFactory")
    public void listenerComment(String data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(data);
        noteRepository.addComment(mapper.readValue(data, Comment.class));
    }

}

/*

    @KafkaListener(topics = "${kafka.topic.name}", containerFactory = "kafkaListenerContainerFactory")
    public void listenerStudent(String data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(data);
        Students student = mapper.readValue(data, Students.class);
        studentsRepository.save(student);
    }

    @KafkaListener(topics = "new_topic2", containerFactory = "kafkaListenerContainerFactory")
    public void listenerSubject(String data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(data);
        SchoolSubjects subjects = mapper.readValue(data, SchoolSubjects.class);
        Students student = studentsRepository.findStudentById(subjects.getId_stud());
        subjects.setStudent(student);
        student.getSchoolSubjects().add(subjects);
        studentsRepository.save(student);
        schoolSubjectRepository.save(subjects);
    }*/

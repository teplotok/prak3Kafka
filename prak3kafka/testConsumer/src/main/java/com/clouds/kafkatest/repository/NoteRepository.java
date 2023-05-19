package com.clouds.kafkatest.repository;

import com.clouds.kafkatest.model.*;

import java.util.List;

public interface NoteRepository {
    int save(Note note);

    int addComment(Comment comment);

    List<Comment> findAllComments();

    int update(Note note);

    Note findById(Long id);

    int deleteById(Long id);

    List<Note> findAll();

    List<Note> findByName(String name);

    int deleteAll();

    void initiate();

    List<NoteQuery> queryTop3MaxAvgRate();

    List<NoteQuery> queryTop5CountComments();

    List<RateQuery> queryCountRate();
}

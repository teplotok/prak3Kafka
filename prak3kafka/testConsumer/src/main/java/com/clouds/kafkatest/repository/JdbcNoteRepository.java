package com.clouds.kafkatest.repository;

import com.clouds.kafkatest.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class JdbcNoteRepository  implements  NoteRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public int save(Note note) {
        return jdbcTemplate.update("INSERT INTO notes (name, text) VALUES(?,?)",
                new Object[] { note.getName(), note.getText() });
    }

    @Override
    public int update(Note note) {
        return jdbcTemplate.update("UPDATE notes SET name=?, text=? WHERE id=?",
                new Object[] { note.getName(), note.getText(),note.getId()});
    }

    @Override
    public Note findById(Long id) {
        try {
            Note note = jdbcTemplate.queryForObject("SELECT * FROM notes WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Note.class), id);

            return note;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM notes WHERE id=?", id);
    }

    @Override
    public List<Note> findAll() {
        return jdbcTemplate.query("SELECT * from notes", BeanPropertyRowMapper.newInstance(Note.class));
    }

    @Override
    public List<Note> findByName(String name){
        try {
            List<Note> notes = jdbcTemplate.query("SELECT * FROM notes WHERE name=?",
                    BeanPropertyRowMapper.newInstance(Note.class), name);

            return notes;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from notes");
    }

    @Override
    public  void initiate(){
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS notes" +
            "(" +
            "    id SERIAL PRIMARY KEY," +
            "    name character varying(255) ," +
            "    text character varying(255) " +
            ")");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS comments" +
                "(" +
                "    id SERIAL PRIMARY KEY," +
                "    name character varying(255) ," +
                "    text character varying(255) ," +
                "    rate  integer ," +
                "    noteId integer REFERENCES notes (Id)" +
                ")");
        jdbcTemplate.execute("\n" +
                "INSERT INTO notes (name, text) VALUES('SampleName1','SampleText1');\n" +
                "INSERT INTO notes (name, text) VALUES('SampleName2','SampleText2');\n" +
                "INSERT INTO notes (name, text) VALUES('SampleName3','SampleText3');\n" +
                "INSERT INTO notes (name, text) VALUES('SampleName4','SampleText4');\n" +
                "INSERT INTO notes (name, text) VALUES('SampleName5','SampleText5');\n" +
                "\n" +
                "\n" +
                "INSERT INTO comments (name, text, rate, noteId) VALUES('Jack','SampleText',2,1);\n" +
                "INSERT INTO comments (name, text, rate, noteId) VALUES('Morty','SampleText',3,1);\n" +
                "INSERT INTO comments (name, text, rate, noteId) VALUES('Hero','SampleText',1,1);\n" +
                "INSERT INTO comments (name, text, rate, noteId) VALUES('Sonny','SampleText',1,1);\n" +
                "INSERT INTO comments (name, text, rate, noteId) VALUES('Valle','SampleText',1,1);\n" +
                "\n" +
                "INSERT INTO comments (name, text, rate, noteId) VALUES('Jack','SampleText',2,2);\n" +
                "INSERT INTO comments (name, text, rate, noteId) VALUES('Morty','SampleText',3,2);\n" +
                "INSERT INTO comments (name, text, rate, noteId) VALUES('Hero','SampleText',4,2);\n" +
                "INSERT INTO comments (name, text, rate, noteId) VALUES('Sonny','SampleText',4,2);\n" +
                "INSERT INTO comments (name, text, rate, noteId) VALUES('Valle','SampleText',3,2);\n" +
                "\n" +
                "INSERT INTO comments (name, text, rate, noteId) VALUES('Jack','SampleText',4,3);\n" +
                "INSERT INTO comments (name, text, rate, noteId) VALUES('Morty','SampleText',4,3);\n" +
                "INSERT INTO comments (name, text, rate, noteId) VALUES('Hero','SampleText',5,3);\n" +
                "INSERT INTO comments (name, text, rate, noteId) VALUES('Sonny','SampleText',3,3);\n" +
                "INSERT INTO comments (name, text, rate, noteId) VALUES('Valle','SampleText',4,3);\n" +
                "\n" +
                "INSERT INTO comments (name, text, rate, noteId) VALUES('Jack','SampleText',5,4);\n" +
                "INSERT INTO comments (name, text, rate, noteId) VALUES('Morty','SampleText',5,4);\n" +
                "INSERT INTO comments (name, text, rate, noteId) VALUES('Hero','SampleText',5,4);\n" +
                "INSERT INTO comments (name, text, rate, noteId) VALUES('Sonny','SampleText',5,4);\n" +
                "INSERT INTO comments (name, text, rate, noteId) VALUES('Valle','SampleText',4,4);\n" +
                "\n" +
                "INSERT INTO comments (name, text, rate, noteId) VALUES('Jack','SampleText',5,5);\n" +
                "INSERT INTO comments (name, text, rate, noteId) VALUES('Morty','SampleText',5,5);\n" +
                "INSERT INTO comments (name, text, rate, noteId) VALUES('Hero','SampleText',5,5);\n" +
                "INSERT INTO comments (name, text, rate, noteId) VALUES('Sonny','SampleText',5,5);\n" +
                "INSERT INTO comments (name, text, rate, noteId) VALUES('Valle','SampleText',5,5);\n" +
                "\n" +
                "INSERT INTO comments (name, text, rate, noteId) VALUES('Jack','SampleText',5,4);\n" +
                "INSERT INTO comments (name, text, rate, noteId) VALUES('Morty','SampleText',5,4);\n" +
                "INSERT INTO comments (name, text, rate, noteId) VALUES('Hero','SampleText',5,4);\n" +
                "INSERT INTO comments (name, text, rate, noteId) VALUES('Sonny','SampleText',5,4);\n" +
                "INSERT INTO comments (name, text, rate, noteId) VALUES('Valle','SampleText',4,4);\n" +
                "\n" +
                "INSERT INTO comments (name, text, rate, noteId) VALUES('Jack','SampleText',5,5);\n" +
                "INSERT INTO comments (name, text, rate, noteId) VALUES('Morty','SampleText',5,5);\n" +
                "INSERT INTO comments (name, text, rate, noteId) VALUES('Hero','SampleText',5,5);\n" +
                "INSERT INTO comments (name, text, rate, noteId) VALUES('Sonny','SampleText',5,5);\n" +
                "INSERT INTO comments (name, text, rate, noteId) VALUES('Valle','SampleText',5,5);\n" +
                "\n" +
                "INSERT INTO comments (name, text, rate, noteId) VALUES('Jack','SampleText',5,5);\n" +
                "INSERT INTO comments (name, text, rate, noteId) VALUES('Morty','SampleText',5,5);\n" +
                "INSERT INTO comments (name, text, rate, noteId) VALUES('Hero','SampleText',5,5);\n" +
                "INSERT INTO comments (name, text, rate, noteId) VALUES('Sonny','SampleText',5,5);\n" +
                "INSERT INTO comments (name, text, rate, noteId) VALUES('Valle','SampleText',5,5);");
    }

    @Override
    public int addComment(Comment comment) {
        return jdbcTemplate.update("INSERT INTO comments (name, text, rate, noteId) VALUES(?,?,?,?)",
                new Object[] { comment.getName(), comment.getText(),comment.getRate(),comment.getNoteId() });
    }

    @Override
    public List<Comment> findAllComments() {
        return jdbcTemplate.query("SELECT * from comments", BeanPropertyRowMapper.newInstance(Comment.class));
    }

    @Override
    public List<NoteQuery> queryTop3MaxAvgRate(){
        return jdbcTemplate.query("select notes.name,notes.text,avg(rate) as avg_rate from notes join comments on notes.id=comments.noteid  " +
                "group by(notes.name,notes.text) " +
                "order by avg_rate DESC " +
                "limit 3",BeanPropertyRowMapper.newInstance(NoteQuery.class));
    }

    @Override
    public List<NoteQuery> queryTop5CountComments(){
        return jdbcTemplate.query("select notes.name,notes.text,count(rate) as avg_rate from notes join comments on notes.id=comments.noteid  " +
                "group by(notes.name,notes.text) " +
                "order by avg_rate DESC " +
                "limit 5",BeanPropertyRowMapper.newInstance(NoteQuery.class));
    }

    @Override
    public List<RateQuery> queryCountRate(){
        return jdbcTemplate.query("select rate,count(notes.name) as count_notes from notes join comments on notes.id=comments.noteid  " +
                "group by(rate) " +
                "order by rate DESC",BeanPropertyRowMapper.newInstance(RateQuery.class));
    }
}

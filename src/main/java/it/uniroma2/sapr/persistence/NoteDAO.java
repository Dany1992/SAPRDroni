package it.uniroma2.sapr.persistence;

import it.uniroma2.sapr.pojo.FlightPlain;
import it.uniroma2.sapr.pojo.Note;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Tiziano on 28/07/16.
 */
public interface NoteDAO {
    public boolean insertNote(Note note) throws SQLException;
    public boolean deleteNote(Note note) throws SQLException;
    public ArrayList<Note> selectNote(FlightPlain fp) throws SQLException;
}

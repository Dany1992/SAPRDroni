package it.uniroma2.sapr.persistence;

import it.uniroma2.sapr.pojo.FlightPlan;
import it.uniroma2.sapr.pojo.Note;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Tiziano on 28/07/16.
 */
public interface NoteDAO {
    public boolean insertNote(Note note) throws SQLException;
    public boolean deleteNote(Note note) throws SQLException;
    public boolean updateNote(Note note, String textNote) throws SQLException;
    public ArrayList<Note> selectNote(FlightPlan fp) throws SQLException;
}

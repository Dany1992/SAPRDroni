package it.uniroma2.sapr.persistence;

import it.uniroma2.sapr.bean.RequestFlightPlan;
import it.uniroma2.sapr.pojo.Note;
import java.sql.SQLException;

/**
 * Created by Tiziano on 28/07/16.
 */
public interface NoteDAO {
    public boolean insertNote(Note note) throws SQLException;
    public boolean deleteNote(Note note) throws SQLException;
    public boolean updateNote(Note note) throws SQLException;
    public Note selectNote(int idNote) throws SQLException;
}

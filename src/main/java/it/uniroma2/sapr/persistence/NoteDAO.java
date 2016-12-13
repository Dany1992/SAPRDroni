package it.uniroma2.sapr.persistence;

<<<<<<< HEAD
import it.uniroma2.sapr.bean.RequestFlightPlan;
import it.uniroma2.sapr.pojo.Note;
import java.sql.SQLException;
=======
import it.uniroma2.sapr.pojo.FlightPlan;
import it.uniroma2.sapr.pojo.Note;

import java.sql.SQLException;
import java.util.ArrayList;
>>>>>>> dindiBranch

/**
 * Created by Tiziano on 28/07/16.
 */
public interface NoteDAO {
    public boolean insertNote(Note note) throws SQLException;
    public boolean deleteNote(Note note) throws SQLException;
    public boolean updateNote(Note note) throws SQLException;
<<<<<<< HEAD
    public Note selectNote(int idNote) throws SQLException;
=======
    public ArrayList<Note> selectNote(FlightPlan fp) throws SQLException;
>>>>>>> dindiBranch
}

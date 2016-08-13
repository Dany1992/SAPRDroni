package it.uniroma2.sapr.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import it.uniroma2.sapr.pojo.FlightPlain;
import it.uniroma2.sapr.pojo.Note;

import org.apache.log4j.Logger;

/**
 * Created by Tiziano on 28/07/16.
 */
public class MySQLDbNoteDAO implements NoteDAO {
    String classe = "MySQLDbNoteDAO";
    final static Logger logger = Logger.getLogger("PESISTENCE");

    public boolean insertNote(Note note) throws SQLException {
        String method = "insertNote";
        Connection con = null;
        PreparedStatement pt = null;
        String query = "INSERT INTO note"
                        + "(textNote, date) VALUES"
                        + "(?,?)";
        try{
            //Logger per notificare l'inserimento di un oggetto
            logger.info(String.format("Class:%s-Method:%s::START with dates %s", classe,method,note.toString()));

            //Apro la connessione e preparo la query
            con = MySQLDbDAOFactory.createConnection();
            pt = con.prepareStatement(query);

            //Compilo i campi nella query
            pt.setString(1, note.getTextNote());
            pt.setString(2, note.getDate());

            //eseguo la query
            if(pt.executeUpdate() == 1){
                pt.close();
                con.close();
                System.out.println("Query OK");
                logger.info(String.format("Class:%s-Method:%s::END add note with id-%s", //
                        classe,method,note.getIdNote()));
                return true;
            }else {
                pt.close();
                con.close();
                System.out.println("Query Aborted");
                logger.info(String.format("Class:%s-Method:%s::END don't add note with id-%s", //
                        classe,method,note.getIdNote()));
                return false;
            }
        }catch (Exception e){
            logger.error(String.format("Class:%s-Method:%s::ERROR", classe,method) + e);
            return false;
        } finally {
            if (pt != null) {
                pt.close();
            }

            if (con != null) {
                con.close();
            }
        }
    }

    public boolean deleteNote(Note note) throws SQLException {
        String method = "deleteNote";
        Connection con = null;
        PreparedStatement pt = null;
        int idNote = note.getIdNote();
        String query = "DELETE FROM note WHERE idNote = ?";
        try{
            //Logger per notificare l'inserimento di un oggetto
            logger.info(String.format("Class:%s-Method:%s::START with dates %s", classe,method,idNote));

            //Apro la connessione e preparo la query
            con = MySQLDbDAOFactory.createConnection();
            pt = con.prepareStatement(query);

            //Compilo i campi nella query
            pt.setInt(1,idNote);

            //eseguo la query
            if(pt.executeUpdate() == 1){
                pt.close();
                con.close();
                System.out.println("Query OK");
                logger.info(String.format("Class:%s-Method:%s::END delete note with id-%s", //
                        classe,method,idNote));
                return true;
            }else {
                pt.close();
                con.close();
                System.out.println("Query Aborted");
                logger.info(String.format("Class:%s-Method:%s::END don't delete note with id-%s", //
                        classe,method,idNote));
                return false;
            }
        }catch (Exception e){
            logger.error(String.format("Class:%s-Method:%s::ERROR", classe,method) + e);
            return false;
        } finally {
            if (pt != null) {
                pt.close();
            }

            if (con != null) {
                con.close();
            }
        }
    }

    public ArrayList<Note> selectNote(FlightPlain fp) throws SQLException {
        String method = "selectNote";
        Connection con = null;
        PreparedStatement pt = null;
        ResultSet rs = null;
        ArrayList<Note> result = new ArrayList<Note>();
        String query = "SELECT idNote, textNote, date FROM note WHERE idNote = ?";


        try{
            //Logger per notificare l'inserimento di un oggetto
            //logger.info(String.format("Class:%s-Method:%s::START with dates %s", classe,method,fp.getIdNote()));

            //Apro la connessione e preparo la query
            con = MySQLDbDAOFactory.createConnection();
            pt = con.prepareStatement(query);

            //Compilo i campi nella query
            pt.setInt(1, fp.getIdNote());

            //eseguo la query
            rs = pt.executeQuery();
            if(rs != null){
                while (rs.next()) {
                    int idNote = rs.getInt("idNote");
                    String textNote = rs.getString("textNote");
                    String date = rs.getString("date");
                    Note noteSupport = new Note(idNote, textNote, date);
                    result.add(noteSupport);
                }
                System.out.println("Query OK");
                return result;
                //ADD LOG
            }else {
                pt.close();
                con.close();
                System.out.println("Query Aborted");
                //ADD LOG
                return null;
            }
        }catch (Exception e){
            logger.error(String.format("Class:%s-Method:%s::ERROR", classe,method) + e);
            return null;
        } finally {
            if (pt != null) {
                pt.close();
            }

            if (con != null) {
                con.close();
            }
        }

    }

    public static void main(String args[]) throws ParseException{

        //Inserimento nuova nota
        //Note note = new Note("Il drone e' patito", "2016-07-28");
        MySQLDbNoteDAO mysqlTest = new MySQLDbNoteDAO();
        FlightPlain fp = new FlightPlain("Ciampino", "Fiumicino", "2016-07-29", "20:00:00", "21:00:00", 1, 1, 1, "0000000001");
        int i;
        try {
            System.out.println("Starting Operation.....");
            //mysqlTest.insertNote(note);
            ArrayList<Note> printed = mysqlTest.selectNote(fp);
            for(i = 0; i < printed.size(); i++) {
                System.out.print(printed.get(i));
            }
            //mysqlTest.deleteNote(5);

        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}

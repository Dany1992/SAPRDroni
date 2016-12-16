package it.uniroma2.sapr.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Questa classe si occupa di instaurare la connessione con il db MYSQL e poi di ritornare la l'interfaccia
 * dell'oggetto sul quale si potranno svolgere lo operazioni di CRUD sul database
 * 
 * @author Danilo Butrico
 * @version 1.0
 * @mail dbutricod@gmail.com
 *
 */
public class MySQLDbDAOFactory extends DAOFactory {

	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String DBURL = "jdbc:mysql://localhost/sapr";
		 
	/**
	 * Instaura la connessione con il DB, sarebbe utile magari portare su file esterni la username
	 * e la password. Per il momento sono camblate nel codice. Poi permette di richiamare 
	 * @return 
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	public static Connection createConnection() {
	//Occorre fare un singleton per la connessione al db
	//connection pool implementation
		Connection con = null;
		try {
			Class.forName(DRIVER).newInstance();
			con = DriverManager.getConnection(DBURL, "root", "root");
			return con;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

	public PilotDAO getPilotDAO() {
		return new MySQLDbPilotDAO();
	}
		  
	public SaprDAO getSaprDAO(){
		return new MySQLDbSaprDAO();
	}
        
        public DeviceDAO getDeviceDAO(){
            return new MySQLDbDeviceDAO();
        }
        
        public NoteDAO getNoteDAO(){
            return new MySQLDbNoteDAO();
        }

        public FlightPlanDAO getFlightPlanDAO(){
             return new MySQLDbFlightPlanDAO();
        }

}


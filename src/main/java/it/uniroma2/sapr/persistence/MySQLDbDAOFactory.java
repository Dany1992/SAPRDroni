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

	public static final String DRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";
	public static final String DBURL = "jdbc:odbc:myDataSource";
		 
	/**
	 * Instaura la connessione con il DB, sarebbe utile magari portare su file esterni la username
	 * e la password. Per il momento sono camblate nel codice. Poi permette di richiamare 
	 * @return 
	 * @throws SQLException
	 */
	public static Connection createConnection() throws SQLException {
	//Occorre fare un singleton per la connessione al db
	//connection pool implementation
		String url = "jdbc:odbc:myDataSource";
		Connection con = DriverManager.getConnection(url, "username", "password");
		return con;
	}

	public PilotDAO getPilotDAO() {
		return new MySQLDbPilotDAO();
	}
		  
	public SaprDAO getSaprDAO(){
		return new MySQLDbSaprDAO();
	}
}

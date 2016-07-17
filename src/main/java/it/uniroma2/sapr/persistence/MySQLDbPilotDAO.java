package it.uniroma2.sapr.persistence;


import java.util.logging.Logger;
import com.mysql.cj.jdbc.PreparedStatement;

import it.uniroma2.sapr.bean.RequestPilot;
import it.uniroma2.sapr.tansfer.bean.Pilot;

/**
 * 
 * Questa classe si occupa di eseguire le CRUD sul db rispetto al transfer bean Pilot
 * 
 * @author Danilo Butrico
 * @version 1.0
 * @mail dbutricod@gmail.com
 *
 */
public class MySQLDbPilotDAO implements PilotDAO {
	String classe = "MySQLDbPilotDAO";
	final static Logger logger = Logger.getLogger("PESISTENCE");
	
	/**
	 * Inserisce nel db un pilota
	 * @param pilot è il bean contente tutti i dati da inserire nel db
	 */
	public boolean insertPilot(Pilot pilot){
		String method = "insertPilot";
		String query = "INSERT INTO table_name (pilotLicense, name," +
		"surname,birthDate,nation,taxCode,state,residence,phone,mail,password) VALUES "
		+ "(?,?,?,?,?,?,?,?,?,?,?) ";
		try {
			logger.info(String.format("Class:%s-Method:%s::START with dates %s", classe,method,pilot.toString()));
					
			PreparedStatement pt = (PreparedStatement)MySQLDbDAOFactory.createConnection().prepareStatement(query);
			pt.setString(1, pilot.getPilotLicense());
			pt.setString(2, pilot.getName());
			pt.setString(3, pilot.getSurname());
			pt.setDate(4, pilot.getBirthDate());
			pt.setString(5, pilot.getNation());
			pt.setString(6, pilot.getTaxCode());
			pt.setString(7, pilot.getState());
			pt.setString(8, pilot.getResidence());
			pt.setString(9, pilot.getPhone());
			pt.setString(10, pilot.getMail());
			pt.setString(11, pilot.getPassword());
			
			pt.execute();
			
			logger.info(String.format("Class:%s-Method:%s::END", classe,method));
			return true;
		} catch (Exception e) {
			logger.warning("Errore scrittura db" + e);
			return false;
		}
		
	}

	public boolean deletePilot(Pilot pilot) {
		
		return false;
	}

	public boolean updatePilot(Pilot pilot) {
		
		return false;
	}

	public RequestPilot selectPilot(Long idSapr) {
		
		return null;
	}

}

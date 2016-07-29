package it.uniroma2.sapr.persistence;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;

import org.apache.log4j.Logger;

import it.uniroma2.sapr.bean.RequestPilot;
import it.uniroma2.sapr.pojo.Device;
import it.uniroma2.sapr.pojo.Pilot;

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
	 *  
	 * Inserisce nel db un pilota
	 * @param pilot è il bean contente tutti i dati da inserire nel db 
	 * @throws SQLException
	 */
	public boolean insertPilot(Pilot pilot) throws SQLException{
		String method = "insertPilot";
		Connection con = null;
		PreparedStatement pt = null;
		String query = "INSERT INTO pilot (pilotLicense, name," +
		"surname,birthDate,nation,taxCode,state,residence,phone,mail,password) VALUES "
		+ "(?,?,?,?,?,?,?,?,?,?,?) ";
		
		try {
			//logger per segnalare l'inizio della scrittura del metodo
			logger.info(String.format("Class:%s-Method:%s::START with dates %s", classe,method,pilot.toString()));
			
			con = MySQLDbDAOFactory.createConnection();
			pt = con.prepareStatement(query);
			
			//compilo i campi ? nella query
			pt.setString(1, pilot.getPilotLicense());
			pt.setString(2, pilot.getName());
			pt.setString(3, pilot.getSurname());
			pt.setString(4, pilot.getBirthDate());
			pt.setString(5, pilot.getNation());
			pt.setString(6, pilot.getTaxCode());
			pt.setString(7, pilot.getState());
			pt.setString(8, pilot.getResidence());
			pt.setString(9, pilot.getPhone());
			pt.setString(10, pilot.getMail());
			pt.setString(11, pilot.getPassword());
			
			//eseguo la query
			if(pt.executeUpdate() == 1){
				pt.close();
				con.close();
				System.out.println("a buon fine");
				logger.info(String.format("Class:%s-Method:%s::END add pilot with license code-%s", //
						classe,method,pilot.getPilotLicense()));
				return true;
			}else {
				pt.close();
				con.close();
				System.out.println("male");
				logger.info(String.format("Class:%s-Method:%s::END dont add pilot with license code-%s", //
						classe,method,pilot.getPilotLicense()));
				return false;
			}
			
		} catch (Exception e) {
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
	
	public static void main(String args[]) throws ParseException{
		
		Pilot pilot = new Pilot("Danilo", "Butrico", "Italiana", "Italia", "11l23kk",
				"00132", "22-06-1992", "Roma", "3272871227", "dbutricod@gmail.com", "123");
		MySQLDbPilotDAO mysqlTest = new MySQLDbPilotDAO();
		try {
			System.out.println("sto per iniserire");
			mysqlTest.insertPilot(pilot);
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public boolean deletePilot(Pilot pilot) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updatePilot(Pilot pilot) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public RequestPilot selectPilot(Long idSapr) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

    void insertDevice(Device d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void insertD(Device d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

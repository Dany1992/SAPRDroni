package it.uniroma2.sapr.persistence;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import it.uniroma2.sapr.bean.ResponsePilot;
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
			System.out.println(String.format("Class:%s-Method:%s::START with dates %s", classe,method,pilot.toString()));
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
			System.out.println("Si è verificato il seguente errore: " + e.toString());
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

	/**
	 * Classe che permette di cancellare un pilota attraverso la pilotLicense
	 */
	public boolean deletePilot(Pilot pilot) throws SQLException {
		String method = "deletePilot";
		Connection con = null;
		PreparedStatement pt = null;
		String query = "DELETE FROM pilot WHERE pilotLicense = ? ";
		
		try {
			//logger per segnalare l'inizio della scrittura del metodo
			logger.info(String.format("Class:%s-Method:%s::START with dates %s", classe,method,pilot.toString()));
			System.out.println(String.format("Class:%s-Method:%s::START with dates %s", classe,method,pilot.toString()));

			con = MySQLDbDAOFactory.createConnection();
			pt = con.prepareStatement(query);
			
			//compilo i campi ? nella query
			pt.setString(1, pilot.getPilotLicense());
			
			System.out.println(String.format("Class:%s-Method:%s::execute query[%s]", classe,method,query));
			if(pt.executeUpdate() == 1){
				pt.close();
				con.close();
				logger.info(String.format("Class:%s-Method:%s::END cancel pilot with license code-%s", //
						classe,method,pilot.getPilotLicense()));
				return true;
			}else {
				pt.close();
				con.close();
				System.out.println("male");
				logger.info(String.format("Class:%s-Method:%s::END dont cancel pilot with license code-%s", //
						classe,method,pilot.getPilotLicense()));
				return false;
			}
			
		} catch (Exception e) {
			logger.error(String.format("Class:%s-Method:%s::ERROR", classe,method) + e);
			System.out.println("Si è verificato il seguente errore: " + e.toString());
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

	public boolean updatePilot(Pilot pilot) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	
	public ArrayList<ResponsePilot> selectAllPilot() throws SQLException {
		String method = "selectPilot";
		Connection con = null;
		PreparedStatement pt = null;
		ArrayList<ResponsePilot> listPilots = new ArrayList<ResponsePilot>();
		String query = " SELECT pilotLicense, name," +
		"surname,birthDate,nation,taxCode,state,residence,phone,mail,password "
		+ "FROM pilot ";
		logger.info(String.format("Class:%s-Method:%s::START", classe,method));
		
		try {
			//logger per segnalare l'inizio della scrittura del metodo

			con = MySQLDbDAOFactory.createConnection();
			pt = con.prepareStatement(query);
			
			//eseguo la query
			ResultSet rs = pt.executeQuery();
			
			if(rs != null){
				while (rs.next()) {
					ResponsePilot rispPilot = new ResponsePilot();
                    String license = rs.getString("pilotLicense");
                    rispPilot.setPilotLicense(license);
                    String name = rs.getString("name");
                    rispPilot.setName(name);
                    String surname = rs.getString("surname");
                    rispPilot.setSurname(surname);
                    String birthDate = rs.getString("birthDate");
                    rispPilot.setSurname(birthDate);
                    String nation = rs.getString("nation");
                    rispPilot.setSurname(nation);
					String taxCode = rs.getString("taxCode");
					rispPilot.setSurname(taxCode);
					String state = rs.getString("state");
					rispPilot.setSurname(state);
					String residence = rs.getString("residence");
					rispPilot.setSurname(residence);
					String phone = rs.getString("phone");
					rispPilot.setSurname(phone);
					String mail = rs.getString("mail");
					rispPilot.setSurname(mail);
					
                    listPilots.add(rispPilot);
                }
				
				return listPilots;
			}else {
				pt.close();
				con.close();
				System.out.println("male");
				logger.info(String.format("Class:%s-Method:%s::END", //
						classe,method));
				return listPilots;
			}
			
		} catch (Exception e) {
			logger.error(String.format("Class:%s-Method:%s::ERROR", classe,method) + e);
			System.out.println("Si è verificato il seguente errore: " + e.toString());
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

//    
//    public static void main(String args[]) throws ParseException{
//		MySQLDbPilotDAO tt = new MySQLDbPilotDAO();
//    	try {
//			ArrayList<ResponsePilot> risp = tt.selectAllPilot();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//    	
//	}

	public ResponsePilot selectPilot(String pilotLicense) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


}

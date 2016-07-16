package it.uniroma2.sapr.persistence;

import java.sql.SQLException;

import com.mysql.cj.api.jdbc.Statement;

import it.uniroma2.sapr.bean.RequestPilot;
import it.uniroma2.sapr.tansfer.bean.Pilot;

public class MySQLDbPilotDAO implements PilotDAO {

	public boolean insertPilot(Pilot pilot) {
		try {
			//VANNO AGGIUNGI I LOG
			Statement st = (Statement) MySQLDbDAOFactory.createConnection().createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
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

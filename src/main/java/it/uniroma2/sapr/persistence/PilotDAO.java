package it.uniroma2.sapr.persistence;


import java.sql.SQLException;
import it.uniroma2.sapr.bean.ResponsePilot;
import it.uniroma2.sapr.pojo.Pilot;

public interface PilotDAO {
	public boolean insertPilot(Pilot pilot) throws SQLException;
	public boolean deletePilot(Pilot pilot) throws SQLException;
	public boolean updatePilot(Pilot pilot) throws SQLException;
	public ResponsePilot selectPilot(String pilotLicense) throws SQLException;
}

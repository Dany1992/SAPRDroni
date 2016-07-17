package it.uniroma2.sapr.persistence;


import java.sql.SQLException;

import it.uniroma2.sapr.bean.RequestPilot;
import it.uniroma2.sapr.tansfer.bean.Sapr;

public interface SaprDAO {
	public boolean insertPilot(Sapr sapr) throws SQLException;
	public boolean deletePilot(Sapr sapr) throws SQLException;
	public boolean updatePilot(Sapr sapr) throws SQLException;
	public RequestPilot selectPilot(String idSapr) throws SQLException;
}

package it.uniroma2.sapr.persistence;


import java.sql.SQLException;
import it.uniroma2.sapr.pojo.Sapr;


public interface SaprDAO {
	public boolean insertSapr(Sapr sapr) throws SQLException;
	public boolean deleteSapr(Sapr sapr) throws SQLException;
	public boolean updateSapr(Sapr sapr) throws SQLException;
	public boolean enableSapr(Sapr sapr) throws SQLException;
}
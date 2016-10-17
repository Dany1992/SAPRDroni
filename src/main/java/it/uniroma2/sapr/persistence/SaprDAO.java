package it.uniroma2.sapr.persistence;


import java.sql.SQLException;

import it.uniroma2.sapr.bean.ResponseSapr;
import it.uniroma2.sapr.pojo.Sapr;
import java.util.ArrayList;

public interface SaprDAO {
	public boolean insertSapr(Sapr sapr) throws SQLException;
	public boolean deleteSapr(Sapr sapr) throws SQLException;
	public ArrayList<Sapr> selectSapr(String owner) throws SQLException;
	public ResponseSapr selectSapr(Sapr sapr) throws SQLException;
}
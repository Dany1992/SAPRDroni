package it.uniroma2.sapr.persistence;


import it.uniroma2.sapr.bean.RequestPilot;
import it.uniroma2.sapr.tansfer.bean.Sapr;

public interface SaprDAO {
	public boolean insertPilot(Sapr sapr);
	public boolean deletePilot(Sapr sapr);
	public boolean updatePilot(Sapr sapr);
	public RequestPilot selectPilot(String idSapr);
}

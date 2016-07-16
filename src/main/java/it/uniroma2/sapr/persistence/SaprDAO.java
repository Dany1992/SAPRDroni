package it.uniroma2.sapr.persistence;

import it.uniroma2.sapr.bean.RequestPilot;
import it.uniroma2.sapr.tansfer.bean.Sapr;

public interface SaprDAO {
	public boolean insertPilot(Sapr pilot);
	public boolean deletePilot(Sapr pilot);
	public boolean updatePilot(Sapr pilot);
	public RequestPilot selectPilot(String pilotLicense);
}

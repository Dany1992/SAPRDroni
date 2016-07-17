package it.uniroma2.sapr.persistence;


import it.uniroma2.sapr.bean.RequestPilot;
import it.uniroma2.sapr.tansfer.bean.Pilot;

public interface PilotDAO {
	public boolean insertPilot(Pilot pilot);
	public boolean deletePilot(Pilot pilot);
	public boolean updatePilot(Pilot pilot);
	public RequestPilot selectPilot(Long idSapr);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.uniroma2.sapr.persistence;
import it.uniroma2.sapr.bean.ResponseFlightPlan;
import java.sql.SQLException;
import it.uniroma2.sapr.pojo.FlightPlan;
import it.uniroma2.sapr.pojo.Sapr;
import java.util.ArrayList;

/**
 *
 * @author pierfrancescotommasino
 * interfaccia FlightPlanDAO
 */
public interface FlightPlanDAO {
    	public boolean insertFlightPlan(FlightPlan flight) throws SQLException;
        //modificare la data di partenza datedeparture,timedeparture,nowarriving, non modificare(idsapr,licensepilot,idnote,device)
        public boolean updateFlightPlan(FlightPlan flight) throws SQLException;
	public boolean deleteFlightPlan(FlightPlan flight) throws SQLException;
        // ti chiedo un piano di volo e mi da il preciso piano di volo
        public ResponseFlightPlan selectFlightPlan(FlightPlan flight) throws SQLException;
        // tutti i piani di volo di un certo sapr
	public ArrayList<ResponseFlightPlan> selectFlightPlan(Sapr sapr) throws SQLException;
     
}

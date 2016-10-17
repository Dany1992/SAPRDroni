/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.uniroma2.sapr.persistence;
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
	public boolean deleteFlightPlan(FlightPlan flight) throws SQLException;
	public ArrayList<FlightPlan> selectFlightPlan(Sapr sapr) throws SQLException;
        public FlightPlan selectFlightPlan(FlightPlan flight) throws SQLException;
}

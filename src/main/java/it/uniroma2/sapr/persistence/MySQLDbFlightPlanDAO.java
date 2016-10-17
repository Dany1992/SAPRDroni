/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.uniroma2.sapr.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;

import org.apache.log4j.Logger;

import it.uniroma2.sapr.bean.RequestFlightPlan;
import it.uniroma2.sapr.pojo.FlightPlan;
import it.uniroma2.sapr.pojo.Sapr;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *Questa classe si occupa di eseguire le CRUD sul db rispetto al transfer bean di FlightPlan
 * @author pierfrancescotommasino
 */
public class MySQLDbFlightPlanDAO implements FlightPlanDAO{
    String classe = "MySQLDbFlightPlanDAO";
    final static Logger logger = Logger.getLogger("PESISTENCE");
    /**
	 *  
	 * Inserisce nel db un un flightPlan
	 * @param flight è il bean contente tutti i dati da inserire nel db 
	 * @throws SQLException
	 */
	public boolean insertFlightPlan(FlightPlan flight) throws SQLException{
                System.out.println("funzione insert");
		String method = "insertFlightPlan";
		Connection con = null;
		PreparedStatement pt = null;
		String query = "INSERT INTO flightplan(destination,departure,dateDeparture,timeDeparture,nowArriving,idSapr,idNote,idDevice,pilotLicense) VALUES(?,?,?,?,?,?,?,?,?)";
                System.out.println(query);
		try {
                    
			//logger per segnalare l'inizio della scrittura del metodo
			logger.info(String.format("Class:%s-Method:%s::START with dates %s", classe,method,flight.toString()));
			
			con = MySQLDbDAOFactory.createConnection();
			pt = con.prepareStatement(query);
			
			pt.setString(1, flight.getDestination());
			pt.setString(2, flight.getDeparture());
			pt.setString(3, flight.getDateDeparture());
			pt.setString(4, flight.getTimeDeparture());
			pt.setString(5, flight.getNowArriving());
			pt.setInt(6, flight.getIdSapr());
			pt.setInt(7, flight.getIdNote());
			pt.setInt(8, flight.getIdDevice());
			pt.setString(9, flight.getPilotLicense());
                    
			
			//esito della query
			if(pt.executeUpdate() == 1){
                                System.out.println("Ho inserito il flightPlan");	
                                System.out.println(pt.toString());
                                pt.close();
				con.close();
				
				logger.info(String.format("Class:%s-Method:%s::END add flight plan with destination code-%s", //
						classe,method,flight.getDestination()));
				return true;
			}else {
				pt.close();
				con.close();
				System.out.println("Non ho inserito il flightPlan");
				logger.info(String.format("Class:%s-Method:%s::END dont add flight plan with destination code-%s", //
						classe,method,flight.getDestination()));
				return false;
			}
			
		} catch (Exception e) {
			logger.error(String.format("Class:%s-Method:%s::ERROR", classe,method) + e);
			return false;
		} finally {
			if (pt != null) {
				pt.close();
			}

			if (con != null) {
				con.close();
			}
		}
        }
        
        
        
    public boolean deleteFlightPlan(FlightPlan flight) throws SQLException{
                System.out.println("funzione delete");
		String method = "deleteFlightPlan";
		Connection con = null;
		PreparedStatement pt = null;
		String query = "DELETE FROM flightplan WHERE idsapr=? and pilotLicense=? and dateDeparture=?";
                System.out.println(query);
		try {
                    
			//logger per segnalare l'inizio della scrittura del metodo
			logger.info(String.format("Class:%s-Method:%s::START with dates idSapr=%s,pilotLicense=%s,dateDeparture=%s", classe,method,flight.getIdSapr(),flight.getPilotLicense(),flight.getDateDeparture()));
			
			con = MySQLDbDAOFactory.createConnection();
			pt = con.prepareStatement(query);
			
			pt.setInt(1,flight.getIdSapr());
			pt.setString(2,flight.getPilotLicense());
			pt.setString(3,flight.getDateDeparture());
                       
			
			//esito della query
			if(pt.executeUpdate()==1){
                                System.out.println("Ho eliminato il flightPlan");
                                System.out.println(pt.toString());
                                pt.close();
				con.close();
				
				logger.info(String.format("Class:%s-Method:%s::END delete flight plan with idSapr code-%s",classe,method,flight.getIdSapr()));
				return true;
			}
                        else {
				pt.close();
				con.close();
				System.out.println("Non ho eliminato il flightPlan");
				logger.info(String.format("Class:%s-Method:%s::END dont delete flight plan with idSapr code-%s",classe,method,flight.getIdSapr()));
				return false;
			}
			
                        } catch (Exception e) {
                                logger.error(String.format("Class:%s-Method:%s::ERROR", classe,method) + e);
                                return false;
                        } finally {
                                if (pt != null) {
                                        pt.close();
                                }

                                if (con != null) {
                                        con.close();
                                }
                        }
    };
    
   public FlightPlan selectFlightPlan(FlightPlan flight) throws SQLException{
                System.out.println("funzione selectFlightPlan(FlightPlan flight)");
		String method = "selectFlightPlan";
		Connection con = null;
		PreparedStatement pt = null;
                ResultSet rs=null;
		String query = "SELECT destination,departure,dateDeparture,timeDeparture,nowArriving,idSapr,idNote,idDevice,pilotLicense FROM flightplan WHERE idsapr=? and pilotLicense=? and dateDeparture=?";
                System.out.println(query);
		try {
                    
			//logger per segnalare l'inizio della scrittura del metodo
			logger.info(String.format("Class:%s-Method:%s::START with dates idSapr=%s,pilotLicense=%s,dateDeparture=%s", classe,method,flight.getIdSapr(),flight.getPilotLicense(),flight.getDateDeparture()));
			
			con = MySQLDbDAOFactory.createConnection();
			pt = con.prepareStatement(query);
			pt.setInt(1,flight.getIdSapr());
			pt.setString(2,flight.getPilotLicense());
			pt.setString(3,flight.getDateDeparture());
                        System.out.println(pt.toString());
                        rs = pt.executeQuery();
			//esito della query
			if(rs!=null){
                                System.out.println("Ho selezionato il flightPlan");
				logger.info(String.format("Class:%s-Method:%s::END select flight plan with idSapr code-%s",classe,method,flight.getIdSapr()));
                                rs.next();  
                                FlightPlan flight1=new FlightPlan(rs.getString("destination"),rs.getString("departure"),rs.getString("datedeparture"),rs.getString("timeDeparture"),rs.getString("nowarriving"),rs.getInt("idsapr"),rs.getInt("idNote"),rs.getInt("iddevice"),rs.getString("pilotLicense"));       
                                return flight1;
                        }
                        else {
				pt.close();
				con.close();
				System.out.println("Non ho selezionato il flightPlan");
				logger.info(String.format("Class:%s-Method:%s::END dont select flight plan with idSapr code-%s",classe,method,flight.getIdSapr()));
				return null;
			}
			
                        } catch (Exception e) {
                                logger.error(String.format("Class:%s-Method:%s::ERROR", classe,method) + e);
                                return null;
                        } finally {
                                if (pt != null) {
                                        pt.close();
                                }

                                if (con != null) {
                                        con.close();
                                }
                        }
   };
   
   //seleziona tutti i piani di voli che ha svolto un determinato Sapr(gli viene passato un oggetto di tipo Sapr e ricavo l'idSapr)
    public ArrayList<FlightPlan> selectFlightPlan(Sapr sapr) throws SQLException{
                System.out.println("funzione selectFlightPlan(Sapr sapr)");
		String method = "selectFlightPlan";
		Connection con = null;
		PreparedStatement pt = null;
                ResultSet rs=null;
                ArrayList<FlightPlan> array=new ArrayList<FlightPlan>();
		String query = "SELECT destination,departure,dateDeparture,timeDeparture,nowArriving,idSapr,idNote,idDevice,pilotLicense FROM flightplan WHERE idsapr=?";
                System.out.println(query);
		try {
                    
			//logger per segnalare l'inizio della scrittura del metodo
			logger.info(String.format("Class:%s-Method:%s::START with dates idSapr=%s", classe,method,sapr.getIdSapr()));
			
			con = MySQLDbDAOFactory.createConnection();
			pt = con.prepareStatement(query);
			pt.setInt(1,sapr.getIdSapr());
                        System.out.println(pt.toString());
                        rs = pt.executeQuery();
			//esito della query
			if(rs!=null){
                                System.out.println("Ho selezionato i flightPlan");
				logger.info(String.format("Class:%s-Method:%s::END select flight plan with idSapr code-%s",classe,method,sapr.getIdSapr()));
                                while(rs.next()){  
                                    array.add(new FlightPlan(rs.getString("destination"),rs.getString("departure"),rs.getString("datedeparture"),rs.getString("timeDeparture"),rs.getString("nowarriving"),rs.getInt("idsapr"),rs.getInt("idNote"),rs.getInt("iddevice"),rs.getString("pilotLicense")));       
                                }
                                return array;
                        }
                        else {
				pt.close();
				con.close();
				System.out.println("Non ho selezionato i flightPlan");
				logger.info(String.format("Class:%s-Method:%s::END dont select flight plan with idSapr code-%s",classe,method,sapr.getIdSapr()));
				return null;
			}
			
                        } catch (Exception e) {
                                logger.error(String.format("Class:%s-Method:%s::ERROR", classe,method) + e);
                                return null;
                        } finally {
                                if (pt != null) {
                                        pt.close();
                                }

                                if (con != null) {
                                        con.close();
                                }
                        }
   };
                
                
  public static void main(String args[]) throws ParseException{
      
		//INSERIMENTO:
		FlightPlan flight = new FlightPlan("Roma", "Milano", "2016-08-11", "16:00:00", "23",2,1,1, "0000000002");
		MySQLDbFlightPlanDAO mysqlTest = new MySQLDbFlightPlanDAO();
		try {
			System.out.println("Sto inserendo!");
			mysqlTest.insertFlightPlan(flight);
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
                
//                //CANCELLAZIONE:
//		try {
//			System.out.println("Sto eliminando!");
//			mysqlTest.deleteFlightPlan(2,"0000000001","2016-08-28");
//		} catch (SQLException e) {
//			System.out.println(e);
//			e.printStackTrace();
//		}
                //SELEZIONE 1) :
                try {
			System.out.println("Sto selezionando il flightPlan con un determinato: idsapr,licensePilot,datedeparture!");
			System.out.println(mysqlTest.selectFlightPlan(flight).toString());
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
                //SELEZIONE 2) :
                try {
                        Sapr sapr=new Sapr(2);
			System.out.println("Sto selezionando i flightPlan con un determinato: idsapr");
			System.out.println(mysqlTest.selectFlightPlan(sapr));
                      
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
                
  
  
  }   
    
}

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

import it.uniroma2.sapr.bean.ResponseFlightPlan;
import it.uniroma2.sapr.pojo.Device;
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
    private boolean result=false;
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
		try {
                     for(int i=0;i<flight.getDevices().size();i++){
                        String query = "INSERT INTO flightplan(destination,departure,dateDeparture,timeDeparture,nowArriving,idSapr,idNote,idDevice,pilotLicense) VALUES(?,?,?,?,?,?,?,?,?)";
                        System.out.println(query);
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
			pt.setInt(8, flight.getDevices().get(i).getIdDevice());
			pt.setString(9, flight.getPilotLicense());
    
                       
			
			//esito della query
			if(pt.executeUpdate() == 1){
                                System.out.println("Ho inserito il flightPlan");	
                                System.out.println(pt.toString());
                                pt.close();
				con.close();
				
				logger.info(String.format("Class:%s-Method:%s::END add flight plan with destination code-%s", //
						classe,method,flight.getDestination()));
				result=true;
			}else {
				pt.close();
				con.close();
				System.out.println("Non ho inserito il flightPlan");
				logger.info(String.format("Class:%s-Method:%s::END dont add flight plan with destination code-%s", //
						classe,method,flight.getDestination()));
				result=false;
			}
                    }
                return result;
			
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
			if(pt.executeUpdate()!=0){
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
    
   public ResponseFlightPlan selectFlightPlan(FlightPlan flight) throws SQLException{
                System.out.println("funzione selectFlightPlan(FlightPlan flight)");
		String method = "selectFlightPlan";
                ArrayList<Device> array=new ArrayList<Device>();
		Connection con = null;
		PreparedStatement pt = null;
                ResultSet rs=null;
		String query = "SELECT destination,departure,dateDeparture,timeDeparture,nowArriving,idSapr,idNote,flightplan.idDevice,flightplan.pilotLicense,model,type,weight,producer,active from flightplan,device WHERE idsapr=? and flightplan.pilotLicense=? and dateDeparture=? and flightplan.iddevice=device.iddevice;";
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
                                //da completare la query es:select  destination,departure,dateDeparture,timeDeparture,nowArriving,idSapr,idNote,flightplan.idDevice,flightplan.pilotLicense,model,type,weight,producer from flightplan,device where idsapr=2 and flightplan.pilotlicense="0000000002" and datedeparture="2016-09-11"and flightplan.iddevice=device.iddevice;
                                //deve creare prima glio oggetti Device e poi creare un oggetto di tipo ResponseFlightPlan altrimenti non potri passare un arrayList di Device
                                System.out.println("Ho selezionato il flightPlan");
				logger.info(String.format("Class:%s-Method:%s::END select flight plan with idSapr code-%s",classe,method,flight.getIdSapr()));
        
                                while(rs.next()){ 
                                    array.add(new Device(rs.getInt("iddevice"),rs.getString("model"),rs.getString("type"),rs.getInt("weight"),rs.getString("producer"),rs.getString("pilotlicense"),null, rs.getInt("active")));
                                }
                                rs.first(); 
                                ResponseFlightPlan flight1 = new ResponseFlightPlan(rs.getString("destination"),rs.getString("departure"),rs.getString("datedeparture"),rs.getString("timeDeparture"),rs.getString("nowarriving"),rs.getInt("idsapr"),rs.getInt("idNote"),array,rs.getString("pilotLicense"));
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
    public ArrayList<ResponseFlightPlan> selectFlightPlan(Sapr sapr) throws SQLException{
                System.out.println("funzione selectFlightPlan(Sapr sapr)");
		String method = "selectFlightPlan";
		Connection con = null;
		PreparedStatement pt = null;
                ResultSet rs=null;
                ArrayList<ResponseFlightPlan> array=new ArrayList<ResponseFlightPlan>();
		String query = "SELECT dateDeparture,idSapr,pilotLicense FROM flightplan WHERE idsapr=? group by datedeparture";
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
                                    array.add(selectFlightPlan(new FlightPlan(null,null,rs.getString("datedeparture"),null,null,rs.getInt("idsapr"),0,rs.getString("pilotLicense"),null)));
                                    //array.add(new ResponseFlightPlan(rs.getString("destination"),rs.getString("departure"),rs.getString("datedeparture"),rs.getString("timeDeparture"),rs.getString("nowarriving"),rs.getInt("idsapr"),rs.getInt("idNote"),rs.getInt("iddevice"),rs.getString("pilotLicense")));       
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
    
    
public boolean updateFlightPlan(FlightPlan flight) throws SQLException {
        String method = "updateFlightPlan";
        Connection con = null;
        PreparedStatement pt = null;
        
        String query = "UPDATE FlightPlan SET nowarriving = ? WHERE dateDeparture = ? AND timeDeparture=? AND idSapr=? AND pilotLicense=?";
        try{
            //Logger per notificare l'aggiornamento di un oggetto
            logger.info(String.format("Class:%s-Method:%s::START with idSapr %s", classe,method,flight.getIdSapr()));

            //Apro la connessione e preparo la query
            con = MySQLDbDAOFactory.createConnection();
            pt = con.prepareStatement(query);

            //Compilo i campi nella query
            pt.setString(1,flight.getNowArriving());
            pt.setString(2,flight.getDateDeparture());
            pt.setString(3,flight.getTimeDeparture());
            pt.setInt(4,flight.getIdSapr());
            pt.setString(5,flight.getPilotLicense());
            //eseguo la query
            if(pt.executeUpdate() != 0){
                pt.close();
                con.close();
                System.out.println(query);
                System.out.println("Query OK");
                logger.info(String.format("Class:%s-Method:%s::END update flightplan with id-Sapr:%s", //
                        classe,method,flight.getIdSapr()));
                return true;
            }else {
                pt.close();
                con.close();
                System.out.println("Query Aborted");
                logger.info(String.format("Class:%s-Method:%s::END don't update flightplan with id-Sapr%s", //
                        classe,method,flight.getIdSapr()));
                return false;
            }
        }catch (Exception e){
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
                
                
  public static void main(String args[]) throws ParseException{
      
		//INSERIMENTO:
                ArrayList<Device> d = new ArrayList<Device>();
                d.add(new Device(1,"model1","Camera",500,"producer2","0000000001",null,1));
                d.add(new Device(4,"model3","Brightness Sensor",10,"producer4","0000000001",null,1));
                
		FlightPlan flight = new FlightPlan("Piaggine", "Ciampino", "2016-09-11", "18:00:00", "24",2,1,"0000000002",d);
		MySQLDbFlightPlanDAO mysqlTest = new MySQLDbFlightPlanDAO();
		try {
			System.out.println("Sto inserendo!");
			mysqlTest.insertFlightPlan(flight);
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
                
                //CANCELLAZIONE:
//		try {
//			System.out.println("Sto eliminando!");
//			mysqlTest.deleteFlightPlan(new FlightPlan(null,null,"2016-09-06","07:30:00","23:50",3,0,"0000000003",null));
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
                        Sapr sapr=new Sapr(2,"model2","producer1",2000,4000,"lithium ion",60000,1500,"0000000002",null,1);
			System.out.println("Sto selezionando i flightPlan con un determinato: idsapr");
			System.out.println(mysqlTest.selectFlightPlan(sapr));
                      
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
                //UPDATE :
                try {
			System.out.println("Sto aggiornando!");
			mysqlTest.updateFlightPlan(new FlightPlan(null,null,"2016-09-06","07:30:00","23:50",3,0,"0000000003",null));
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}

  }   

    
}

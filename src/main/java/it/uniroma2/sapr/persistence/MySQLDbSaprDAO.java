package it.uniroma2.sapr.persistence;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import it.uniroma2.sapr.pojo.CheckElement;
import it.uniroma2.sapr.pojo.Sapr;
  

 public class MySQLDbSaprDAO implements SaprDAO {
 
	  String classe = "MySQLDbSaprDAO";
	  final static Logger logger = Logger.getLogger("PERSISTENCE");
 	
 /**
  * Inserisce nel db un sapr
  * @param sapr è il bean contenente tutti i dati da inserire nel db
 */
 
  public boolean insertSapr(Sapr sapr) throws SQLException{
	  String method = "insertSapr";
	  Connection con = null;
	  PreparedStatement pt = null;
	  PreparedStatement pt1 = null;

 	  String query = "INSERT INTO sapr (idSapr, model," +
 		"producer,weight,heavyweight,battery,maxDistance,maxHeight,pilotLicense,active) VALUES "
 		+ "(?,?,?,?,?,?,?,?,?,?) ";
 	  
 	  String query1 = "INSERT INTO checkSAPR (valueCheckElement, idSapr) VALUES "
 	 		+ "(?,?) ";
 	  
 	  try {
			logger.info(String.format("Class:%s-Method:%s::START with dates %s", classe,method,sapr.toString()));
			con = MySQLDbDAOFactory.createConnection();		
		    pt = con.prepareStatement(query);		
		    pt1 = con.prepareStatement(query1);
 			
 			pt.setInt(1, sapr.getIdSapr());
 			pt.setString(2, sapr.getModel());
 			pt.setString(3, sapr.getProducer());
 			pt.setInt(4, sapr.getWeight());
 			pt.setInt(5, sapr.getHeavyweight());
 			pt.setString(6, sapr.getBattery());
 			pt.setInt(7, sapr.getMaxDistance());
 			pt.setInt(8, sapr.getMaxHeight());
 			pt.setString(9, sapr.getPilotLicense());
 			pt.setInt(10, sapr.getActive());
 			
 			if(pt.executeUpdate() == 1){
                pt.close(); 
                
                for(CheckElement e : sapr.getCheckSapr()){
	 				pt1.setString(1, e.getValue());
	 				pt1.setInt(2, sapr.getIdSapr());
	 				
	 				if(pt1.executeUpdate() != 1){
	 					pt1.close();
	 	            	con.close();
	 	            	System.out.println("male");
	 	            	logger.info(String.format("Class:%s-Method:%s::END not add sapr -%s of pilot %s", //
	 	                        classe,method,sapr.getIdSapr(),sapr.getPilotLicense()));
	 	            	return false;
	 				}
                }
                
                pt1.close();
	            con.close();
                System.out.println("a buon fine");
                logger.info(String.format("Class:%s-Method:%s::END add sapr -%s of pilot %s", //
                        classe,method,sapr.getIdSapr(),sapr.getPilotLicense()));
                return true;
 			}
 			else {
            	pt.close();
            	pt1.close();
            	con.close();
            	System.out.println("male");
            	logger.info(String.format("Class:%s-Method:%s::END not add sapr -%s of pilot %s", //
                        classe,method,sapr.getIdSapr(),sapr.getPilotLicense()));
            	return false;
            }

 		} catch (Exception e) {
 			logger.error(String.format("Class:%s-Method:%s::ERROR", classe,method) + e);
 			System.out.println("Si è verificato il seguente errore: " + e.toString());
 			return false;
  		}
 	  	
 	  	finally{
 	  		if (pt != null) 
 	  			pt.close();
 	  			
 	  		if (pt1 != null) 
 	  			pt1.close();
 	  		
 	  		if (con != null) {
 	  			con.close();
 	  		}
 	  	}
 		
  	}
 
  
  	public boolean deleteSapr(Sapr sapr) throws SQLException {
  		/**
         * questo metodo elimina un sapr dal DB
         *
         * @param sapr è il bean contenente tutti i dati da inserire nel db
         * @throws SQLException
         */
  		
        String method = "deleteSapr";
        Connection con = null;
        PreparedStatement pt = null;
        
        int sap = sapr.getIdSapr();

        String query = "UPDATE sapr SET active = 0 WHERE idSapr = ?";

        try {
            //logger per segnalare l'inizio della scrittura del metodo
            logger.info(String.format("Class:%s-Method:%s::START with dates %s", classe, method, sap));

            con = MySQLDbDAOFactory.createConnection();
            pt = con.prepareStatement(query);

            pt.setInt(1, sap);

            // eseguo la query
            if (pt.executeUpdate() == 1) {
            	
                pt.close();
                con.close();
                System.out.println("delete andata a buon fine");
                logger.info(String.format("Class:%s-Method:%s::END delete sapr -%s",
                        classe, method, sap));
                return true;
                
            } else {
            	
                pt.close();
                con.close();
                System.out.println("male");
                logger.info(String.format("Class:%s-Method:%s::END not delete sapr -%s",
                        classe, method, sap));
                return false;
                
            }
            
        } catch (Exception e) {
        	
        	logger.error(String.format("Class:%s-Method:%s::ERROR", classe,method) + e);
        	System.out.println("Si è verificato il seguente errore: " + e.toString());
            return false;
            
        } finally {
        	
            if (pt != null) 
                pt.close();
            

            if (con != null) 
                con.close();
            
        }
    }
  	
  	public boolean enableSapr(Sapr sapr) throws SQLException {
        /**
         * questo metodo setta l'attributo active a 0 (cioè dispositivo inattivo).
         * Questo perchè l'amministratore potrebbe voler vedere i voli passati
         * e se eliminiamo i dispositivi (analogamente anche i sapr) ci perderemo i dettagli.
         *
         * @param device è il bean contente tutti i dati da inserire nel db
         * @throws SQLException
         */
        String method = "enableSapr";
        Connection con = null;
        PreparedStatement pt = null;
        
        int idSapr = sapr.getIdSapr();

        String query = "UPDATE sapr SET active = 1 WHERE idSapr = ?";

        try {
            //logger per segnalare l'inizio della scrittura del metodo
            logger.info(String.format("Class:%s-Method:%s::START with dates %s", classe, method, idSapr));

            con = MySQLDbDAOFactory.createConnection();
            pt = con.prepareStatement(query);

            //compilo il campo ? nella query
            pt.setInt(1, idSapr);

            // eseguo la query
            if (pt.executeUpdate() == 1) {
                pt.close();
                con.close();
                System.out.println("enable Sapr andato a buon fine");
                logger.info(String.format("Class:%s-Method:%s::END enable sapr -%s",
                        classe, method, idSapr));
                return true;
            } else {
                pt.close();
                con.close();
                logger.info(String.format("Class:%s-Method:%s::END not enabled sapr -%s",
                        classe, method, idSapr));
                return false;
            }
        } catch (Exception e) {
            logger.error(String.format("Class:%s-Method:%s::ERROR", classe, method) + e);
            System.out.println(e);
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
	
	
	public boolean removeAllCheckSapr(Sapr sapr) throws SQLException{
        Connection con = null;
        PreparedStatement pt = null;
        String query = "DELETE FROM checkSapr WHERE IdSapr = ?";
        
        try{
            con = MySQLDbDAOFactory.createConnection();
            pt = con.prepareStatement(query);
            
            pt.setInt(1, sapr.getIdSapr());
            
            if (pt.executeUpdate() != 0){
                System.out.println("ELIMINATI TUTTI I CHECKELEMENT DEL SAPR " + sapr.getIdSapr());
                pt.close();
                con.close();
                return true;
            }
            else{    	
                System.out.println("ERRORE CANCELLAZIONE DEI CHECKELEMENT DEL SAPR " + sapr.getIdSapr());
                pt.close();
                con.close();
                return false;
            }         
        }
        catch(Exception e){	
    	    System.out.println("Si è verificato il seguente errore: " + e.toString());
            con.close();
            return false;            
        }
        
        finally{
            if(con != null) con.close();
            if(pt != null) pt.close();   
        }
    }
    
    public boolean insertAllCheckSapr(Sapr sapr, ArrayList<CheckElement> checkSapr) throws SQLException{
        Connection con = null;
        PreparedStatement pt = null;
        String query = "INSERT INTO checkSapr(valueCheckElement,IdSapr) VALUES (?,?)";
         
         try{
             con = MySQLDbDAOFactory.createConnection();
             
             for(CheckElement e : sapr.getCheckSapr()){                    
            	 pt = con.prepareStatement(query);
                 
                 pt.setString(1, e.getValue());
                 pt.setInt(2, sapr.getIdSapr());

                 if(pt.executeUpdate() != 1){
                     pt.close();
                     System.out.println("Riscrittura del checkElement " + e.getValue() + " NON andato a buon fine");
                     logger.info(String.format("Insert del checkElement: %s del sapr %s non andato a buon fine", //
                         classe, e.getValue(), sapr.getIdSapr()));
                     return false;
                 }

                 System.out.println("Riscrittura del checkElement " + e.getValue() + " andato a buon fine");
                 logger.info(String.format("Insert del checkElement: %s del sapr %s andato a buon fine", //
                     classe, e.getValue(), sapr.getIdSapr()));
                 pt.close();
                
            }
            con.close();
            return true;
        }
        catch(Exception e){
            pt.close();
            con.close();
            System.out.println("Si è verificato il seguente errore: " + e.toString());
            return false;
        }
        finally{
            if(con != null) con.close();
            if(pt != null) pt.close();
        }
        
    } 
    
    public boolean updateSapr(Sapr sapr) throws SQLException {
   	 
    	 /**
         * questo metodo modifica un sapr dal DB (non si possono modificare ne
         * l'id del sapr ne la licenza del pilota)
         *
         * @param sapr è il bean contenente tutti i dati da inserire nel db
         * @throws SQLException
         */
   	 
         String method = "updateSapr";
         Connection con = null;
         PreparedStatement pt = null;
 
         String query = "UPDATE sapr SET model = ?, producer = ?, weight = ?, heavyweight = ?, battery = ?, maxDistance = ?, " +
         	    " maxHeight = ?, active = ?  WHERE idSapr = ?";
         
         try {
        	 //logger per segnalare l'inizio della scrittura del metodo
             logger.info(String.format("Class:%s-Method:%s::START with dates %s", classe, method, sapr.getIdSapr()));
 
             con = MySQLDbDAOFactory.createConnection();
             pt = con.prepareStatement(query);
 
             pt.setString(1, sapr.getModel());
  			 pt.setString(2, sapr.getProducer());
  			 pt.setInt(3, sapr.getWeight());
  			 pt.setInt(4, sapr.getHeavyweight());
  			 pt.setString(5, sapr.getBattery());
  			 pt.setInt(6, sapr.getMaxDistance());
  			 pt.setInt(7, sapr.getMaxHeight());
  			 pt.setInt(8, sapr.getActive());
  			 pt.setInt(9, sapr.getIdSapr()); 
  			
             // eseguo la query
             if (pt.executeUpdate() == 1) {
            	 pt.close();
	             System.out.println("update andata a buon fine");
	             logger.info(String.format("Class:%s-Method:%s::END update sapr -%s",
	                     classe, method, sapr.getIdSapr()));
	               
	             removeAllCheckSapr(sapr);
	             insertAllCheckSapr(sapr, sapr.getCheckSapr());
	               
	             con.close();
	             return true;                 
            } else {       	   
                 pt.close();
                 con.close();
                 System.out.println("update andato male");
                 logger.info(String.format("Class:%s-Method:%s::END not update sapr -%s",
                          classe, method, sapr.getIdSapr()));
                 return false;              
            }
             
        } catch (Exception e) {   	
        	logger.error(String.format("Class:%s-Method:%s::ERROR", classe,method) + e);
            System.out.println("Si è verificato il seguente errore: " + e.toString());
            return false;        
        } finally {  	
            if (pt != null) 
                pt.close();
             
            if (con != null) 
                con.close();   
         }
    }
		
			
	public static void main(String args[]) throws ParseException{

	
		ArrayList<CheckElement> checkSapr = new ArrayList<CheckElement>();

		checkSapr.add(new CheckElement("motore"));
		checkSapr.add(new CheckElement("batteria"));
		checkSapr.add(new CheckElement("elica"));
		checkSapr.add(new CheckElement("serbatoio"));
		
  		Sapr sapr = new Sapr(300, "cia", "dv", 503, 930, "f", 221, 61, "0000000001", checkSapr, 1);
  		MySQLDbSaprDAO mysqlTest = new MySQLDbSaprDAO();
  		
  		try {
  			
  			
  			System.out.println("sto per inserire");
  			mysqlTest.insertSapr(sapr);
  			
  			/*System.out.println("sto per eliminare");
  			mysqlTest.deleteSapr(sapr);*/
  			
  			/*System.out.println("sto per modificare");
  			mysqlTest.updateSapr(sapr);*/
  			
  			/*String res;*/
  			
  			/*
  			System.out.println("sto per selezionare");
  			res = (mysqlTest.selectSapr(opzione.ALL, "0000000002")).toString();
  			System.out.println(res);
  			*/
  			
  			/*System.out.println("sto per selezionare");
  			res = (mysqlTest.selectSapr(sapr)).toString();
  			System.out.println(res);*/
  			
  			/*ArrayList<ResponseSapr> risul = new ArrayList<ResponseSapr>();
  			risul = mysqlTest.selectSapr("0000000001");
  			
  			for(ResponseSapr s : risul) {
  				res = s.toString();
  	  			System.out.println(res);
  			}*/
  			
  		} catch (SQLException e) {
  			
  			System.out.println(e);
  			e.printStackTrace();
  			
  		}
	}
 
 }

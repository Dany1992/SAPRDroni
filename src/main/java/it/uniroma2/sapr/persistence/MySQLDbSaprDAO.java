package it.uniroma2.sapr.persistence;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import it.uniroma2.sapr.bean.ResponseSapr;
import it.uniroma2.sapr.pojo.Sapr;
  

 public class MySQLDbSaprDAO implements SaprDAO {
 
	  String classe = "MySQLDbSaprDAO";
	  final static Logger logger = Logger.getLogger("PESISTENCE");
 	
 /**
  * Inserisce nel db un sapr
  * @param sapr è il bean contenente tutti i dati da inserire nel db
 */
 
  public boolean insertSapr(Sapr sapr) throws SQLException{
	  String method = "insertSapr";
	  Connection con = null;
	  PreparedStatement pt = null;

 	  String query = "INSERT INTO sapr (idSapr, model," +
 		"producer,weight,heavyweight,battery,maxDistance,maxHeight,pilotLicense) VALUES "
 		+ "(?,?,?,?,?,?,?,?,?) ";
 	  
 	  try {
			logger.info(String.format("Class:%s-Method:%s::START with dates %s", classe,method,sapr.toString()));
			con = MySQLDbDAOFactory.createConnection();		
		    pt = con.prepareStatement(query);
 			
 			pt.setInt(1, sapr.getIdSapr());
 			pt.setString(2, sapr.getModel());
 			pt.setString(3, sapr.getProducer());
 			pt.setInt(4, sapr.getWeight());
 			pt.setInt(5, sapr.getHeavyweight());
 			pt.setString(6, sapr.getBattery());
 			pt.setInt(7, sapr.getMaxDistance());
 			pt.setInt(8, sapr.getMaxHeight());
 			pt.setString(9, sapr.getPilotLicense());
 			
 			if(pt.executeUpdate() == 1){
                pt.close();
                con.close();
                System.out.println("a buon fine");
                logger.info(String.format("Class:%s-Method:%s::END add sapr -%s of pilot %s", //
                        classe,method,sapr.getIdSapr(),sapr.getPilotLicense()));
                return true;
                
            }else {
            	pt.close();
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
 	  		if (pt != null) {
 	  			pt.close();
 	  		}

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

        String query = "DELETE FROM sapr WHERE idSapr = ?";

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
                logger.info(String.format("Class:%s-Method:%s::END delete sapr -%s",
                        classe, method, sap));
                return true;
                
            } else {
            	
                pt.close();
                con.close();
                logger.info(String.format("Class:%s-Method:%s::END not delete sapr -%s",
                        classe, method, sap));
                return false;
                
            }
            
        } catch (Exception e) {
        	
        	logger.error(String.format("Class:%s-Method:%s::ERROR", classe,method) + e);
            return false;
            
        } finally {
        	
            if (pt != null) 
                pt.close();
            

            if (con != null) 
                con.close();
            
        }
    }
	
	/*seleziona tutti i sapr appartenenti al pilota specificato*/
	
	public ArrayList<Sapr> selectSapr(String owner) throws SQLException {
		
	/**
     * questo metodo prende in input la licenza del pilota e restituisce tutti
     * i sapr a sua disposizione
     *
     * @param sapr è il bean contente tutti i dati da inserire nel db
     * @return ArrayList<Sapr> array di sapr
     * @throws SQLException
     */
		
    String method = "selectSapr";
    Connection con = null;
    PreparedStatement pt = null;
    ArrayList<Sapr> arr_sapr = new ArrayList<Sapr>();

    String query = "SELECT idSapr, model, producer, " +
    "weight, heavyweight, battery, maxDistance, maxHeight, pilotLicense FROM sapr WHERE pilotLicense = ?";

    try {
        //logger per segnalare l'inizio della scrittura del metodo
        logger.info(String.format("Class:%s-Method:%s::START with dates %s", classe, method, owner));

        con = MySQLDbDAOFactory.createConnection();
        pt = con.prepareStatement(query);

        //compilo il campo ? nella query
        pt.setString(1, owner);

        // eseguo la query
        ResultSet rs = pt.executeQuery();
        if (rs != null) {

            logger.info(String.format("Class:%s-Method:%s::END select all sapr of pilot %s",
                    classe, method, owner));

            while (rs.next()) {
                int id = rs.getInt("idSapr");
                String md = rs.getString("model");
                String producer = rs.getString("producer");
                int weight = rs.getInt("weight");
                int heavyweight = rs.getInt("heavyweight");
                String battery = rs.getString("battery");
                int maxDistance = rs.getInt("maxDistance");
                int maxHeight = rs.getInt("maxHeight");
                String pilotLicense = rs.getString("pilotLicense");

                Sapr s = new Sapr(id, md, producer, weight, heavyweight, battery, maxDistance, maxHeight, pilotLicense);
                System.out.println(s.toString());
                arr_sapr.add(s);
            }

            return arr_sapr;
            
        } else {
        	
            pt.close();
            con.close();
            logger.info(String.format("Class:%s-Method:%s::END select no one sapr of pilot %s",
                    classe, method, owner));
            return arr_sapr;
        }

    } catch (Exception e) {
    	
    	logger.error(String.format("Class:%s-Method:%s::ERROR", classe, method) + e);
        return arr_sapr;
        
    } finally {
    	
        if (pt != null) 
            pt.close();
        

        if (con != null) 
            con.close();
        
    }

}
 
	public ResponseSapr selectSapr(Sapr sapr) throws SQLException {
		
		String method = "selectSapr";
		Connection con = null;
		PreparedStatement pt = null;
		ResponseSapr rispSapr = new ResponseSapr();
		String query = "SELECT idSapr, model, producer, weight, heavyweight, battery, maxDistance, " +
        	    " maxHeight, pilotLicense FROM sapr WHERE idSapr = ?";
		int idSapr = sapr.getIdSapr();
						
		try {
			//logger per segnalare l'inizio della scrittura del metodo
			logger.info(String.format("Class:%s-Method:%s::START with dates %s", classe,method,idSapr));
			System.out.println(String.format("Class:%s-Method:%s::START with dates %s", classe,method,idSapr));

			con = MySQLDbDAOFactory.createConnection();
			pt = con.prepareStatement(query);
			
			//compilo i campi ? nella query
			pt.setInt(1, idSapr);
			
			//eseguo la query
			ResultSet rs = pt.executeQuery();
			
			if(rs != null){
				rs.next();
                System.out.println("select andata a buon fine");
				logger.info(String.format("Class:%s-Method:%s::END select sapr with id-%s", //
						classe,method,idSapr));
				System.out.println(String.format("Class:%s-Method:%s::START with dates %s", classe,method,idSapr));
				/****************************************************************************
				 * Qui va parsata la response della query e popolato il bean di risposta    *
				 ****************************************************************************/
				
					//recupero la licenza dalla query 
					int idS = rs.getInt("idSapr");
					rispSapr.setIdSapr(idS);
					String model = rs.getString("model");
                    rispSapr.setModel(model);
                    String producer = rs.getString("producer");
                    rispSapr.setProducer(producer);
                    String battery = rs.getString("battery");
                    rispSapr.setBattery(battery);
                    int weight = rs.getInt("weight");
                    rispSapr.setWeight(weight);
                    int heavyweight = rs.getInt("heavyweight");
                    rispSapr.setHeavyweight(heavyweight);
                    int maxDistance = rs.getInt("maxDistance");
                    rispSapr.setMaxDistance(maxDistance);
                    int maxHeight = rs.getInt("maxHeight");
                    rispSapr.setMaxHeight(maxHeight);
                    String license = rs.getString("pilotLicense");
                    rispSapr.setPilotLicense(license);
                    
                    return rispSapr;
                	
			}else {
				
				pt.close();
				con.close();
				System.out.println("male");
				logger.info(String.format("Class:%s-Method:%s::END not select sapr with id code-%s", //
						classe,method,idSapr));
				return rispSapr;
			}
			
		} catch (Exception e) {
			logger.error(String.format("Class:%s-Method:%s::ERROR", classe,method) + e);
			System.out.println("Si è verificato il seguente errore: " + e.toString());
			return null;
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
	  	
  		Sapr sapr = new Sapr(2314, "ciao", "chiu", 52, 90, "00132", 220, 60, "0000000001");
  		MySQLDbSaprDAO mysqlTest = new MySQLDbSaprDAO();
  		
  		try {
  			
  			System.out.println("sto per inserire");
  			mysqlTest.insertSapr(sapr);
  			
  			/*System.out.println("sto per eliminare");
  			mysqlTest.deleteSapr(sapr);*/
  			
  			String res;
  			
  			System.out.println("sto per selezionare");
  			res = (mysqlTest.selectSapr(sapr)).toString();
  			System.out.println(res);
  			
  			/*ArrayList<Sapr> risul = new ArrayList<Sapr>();
  			risul = mysqlTest.selectSapr("0000000001");
  			
  			for(Sapr s : risul) {
  				res = s.toString();
  			}*/
  			
  		} catch (SQLException e) {
  			
  			System.out.println(e);
  			e.printStackTrace();
  			
  		}
	}
 
 }

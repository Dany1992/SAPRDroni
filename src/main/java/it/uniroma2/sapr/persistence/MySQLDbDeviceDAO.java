package it.uniroma2.sapr.persistence;

import it.uniroma2.sapr.pojo.CheckElement;
import it.uniroma2.sapr.pojo.Device;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

/**
 *
 * @author dario
 */
public class MySQLDbDeviceDAO implements DeviceDAO {

    String classe = "MySQLDbDeviceDAO";
    final static Logger logger = Logger.getLogger("PERSISTENCE");

    public boolean insertDevice(Device device) throws SQLException {
        /**
         * questo metodo inserisce un dispositivo nel DB
         *
         * @param device è il bean contente tutti i dati da inserire nel db
         * @throws SQLException
         */
        String method = "insertDevice";
        Connection con = null;
        PreparedStatement pt = null;
        PreparedStatement pt1 = null;
        
        String query = "INSERT INTO device (idDevice,model,type,weight,producer,pilotLicense) VALUES"
                + "(?,?,?,?,?,?)";
        
        String query1 = "INSERT INTO checkDevice(valueCheckElement,IdDevice) VALUES (?,?)";
        
        try {
            //logger per segnalare l'inizio della scrittura del metodo
            logger.info(String.format("Class:%s-Method:%s::START with dates %s", classe, method, device.toString()));

            con = MySQLDbDAOFactory.createConnection();
            pt = con.prepareStatement(query);
            pt1 = con.prepareStatement(query1);
            
            //compilo i campi ? nella query per inserire in device
            pt.setInt(1, device.getIdDevice());
            pt.setString(2, device.getModel());
            pt.setString(3, device.getType());
            pt.setInt(4, device.getWeight());
            pt.setString(5, device.getProducer());
            pt.setString(6, device.getPilotLicense());
            
            if (pt.executeUpdate() == 1){
                pt.close();
                //compilo i campi ? nella query1
                for(CheckElement e : device.getCheckDevice()){
                    
                    pt1.setString(1, e.getValue());
                    pt1.setInt(2, device.getIdDevice());

                    // l'inserimento nella relazione checkDevice non è andato a buon fine
                    if(pt1.executeUpdate() != 1){
                        pt1.close();
                        System.out.println("inserimento NON andato a buon fine");
                        logger.info(String.format("Class:%s-Method:%s::END not add device -%s of pilot %s", //
                            classe, method, device.getIdDevice(), device.getPilotLicense()));
                        return false;
                    }
                        System.out.println("inserimento andato a buon fine");
                        logger.info(String.format("Class:%s-Method:%s::END add device -%s of pilot %s", //
                                classe, method, device.getIdDevice(), device.getPilotLicense()));

                }
                    
            }
            // la prima non è andata a buon fine
            else{
                pt.close();
                pt1.close();
                con.close();
                System.out.println("inserimento NON andato a buon fine");
                logger.info(String.format("Class:%s-Method:%s::END not add device -%s of pilot %s", //
                        classe, method, device.getIdDevice(), device.getPilotLicense()));
                return false;
            }
            return true;
            
        } catch (Exception e) {
            logger.error(String.format("Class:%s-Method:%s::ERROR", classe, method) + e);
            System.out.println("ERROR: " + e);
            return false;
        } finally {
            if (pt != null) {
                pt.close();
            }
            if (pt1 != null) {
                pt1.close();
            }
            if (con != null) {
                con.close();
            }
        }

    }
    
        public boolean removeAllCheckDevice(Device device) throws SQLException{
            Connection con = null;
            PreparedStatement pt = null;
            String query = "DELETE FROM checkDevice WHERE IdDevice = ?";
            
            try{
                con = MySQLDbDAOFactory.createConnection();
                pt = con.prepareStatement(query);
                
                pt.setInt(1, device.getIdDevice());
                
                if (pt.executeUpdate() != 0){
                    System.out.println("ELIMINATI TUTTI I CHECKELEMENT DEL DISPOSITIVO " + device.getIdDevice());
                    pt.close();
                    con.close();
                    return true;
                }
                else{
                    System.out.println("ERRORE SULLA CANCELLAZIONE DEI CHECKELEMENT DEL DISPOSITIVO " + device.getIdDevice());
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
        
        public boolean insertAllCheckDevice(Device device, ArrayList<CheckElement> check) throws SQLException{
            Connection con = null;
            PreparedStatement pt = null;
            String query = "INSERT INTO checkDevice(valueCheckElement,IdDevice) VALUES (?,?)";
            
            try{
                con = MySQLDbDAOFactory.createConnection();
                
                for(CheckElement e : device.getCheckDevice()){
                    pt = con.prepareStatement(query);
                    // compilo i campi ? nella query per inserire in checkDevice
                    pt.setString(1, e.getValue());
                    pt.setInt(2, device.getIdDevice());

                    // l'inserimento nella relazione checkDevice non è andato a buon fine
                    if(pt.executeUpdate() != 1){
                        pt.close();
                        System.out.println("Riscrittura del checkElement " + e.getValue() + " NON andato a buon fine");
                        logger.info(String.format("Insert non andato a buon fine: checkElement: %s del device %s", //
                            classe, e.getValue(), device.getIdDevice()));
                        return false;
                    }

                    System.out.println("Riscrittura del checkElement " + e.getValue() + " andato a buon fine");

                    logger.info(String.format("Insert andato a buon fine: checkElement: %s del device %s", //
                        classe, e.getValue(), device.getIdDevice()));
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
        
	public boolean updateDevice(Device device) throws SQLException {
  	/**
         * questo metodo modifica un device dal DB (non si puo modificare l'id)
         * Per modificare i checkDevice richiama prima il metodo removeAllCheck che elimina tutti i chek del disp
         * poi insertAllCheck per inserire tutti i nuovi (ma anche i vecchi) check del disp
         * @param device è il bean contenente tutti i dati da inserire nel db
         * @throws SQLException
         */
  	
        String method = "updateDevice";
        Connection con = null;
        PreparedStatement pt = null;
        
        int d = device.getIdDevice();

        String query = "UPDATE device SET model = ?, type = ?, weight = ?, producer = ?, pilotLicense = ?, active = ? "
                + "WHERE idDevice = ?";
        
        try {
            //logger per segnalare l'inizio della scrittura del metodo
            logger.info(String.format("Class:%s-Method:%s::START with dates %s", classe, method, d));

            con = MySQLDbDAOFactory.createConnection();
            pt = con.prepareStatement(query);

            pt.setString(1, device.getModel());
            pt.setString(2, device.getType());
            pt.setInt(3, device.getWeight());
            pt.setString(4, device.getProducer());
            pt.setString(5, device.getPilotLicense());
            pt.setInt(6, device.getActive());
            pt.setInt(7, device.getIdDevice());
 			
            // eseguo la query
            if (pt.executeUpdate() == 1) {
            	// se è andata a buon fine posso modificare anche i checkDevice
                pt.close();
                System.out.println("update su device andata a buon fine");
                logger.info(String.format("Class:%s-Method:%s::END update device -%s",
                        classe, method, d));
                // rimuove tutti i check e li rinserisce insieme ai nuovi
                System.out.println("remove all");
                removeAllCheckDevice(device);
                System.out.println("insert all");
                insertAllCheckDevice(device, device.getCheckDevice());

                
                con.close();
                return true;
                
            } else {
                pt.close();
                con.close();
                System.out.println("update NON andato a buon fine");
                logger.info(String.format("Class:%s-Method:%s::END not update sapr -%s",
                        classe, method, d));
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
        
    public boolean deleteDevice(Device device) throws SQLException {
        /**
         * questo metodo setta l'attributo active a 0 (cioè dispositivo inattivo).
         * Questo perchè l'amministratore potrebbe voler vedere i voli passati
         * e se eliminiamo i dispositivi (analogamente anche i sapr) ci perderemo i dettagli.
         *
         * @param device è il bean contente tutti i dati da inserire nel db
         * @throws SQLException
         */
        String method = "deleteDevice";
        Connection con = null;
        PreparedStatement pt = null;
        
        int dev = device.getIdDevice();

        String query = "UPDATE device SET active = 0 WHERE idDevice = ?";

        try {
            //logger per segnalare l'inizio della scrittura del metodo
            logger.info(String.format("Class:%s-Method:%s::START with dates %s", classe, method, dev));

            con = MySQLDbDAOFactory.createConnection();
            pt = con.prepareStatement(query);

            //compilo il campo ? nella query
            pt.setInt(1, dev);

            // eseguo la query
            if (pt.executeUpdate() == 1) {
                pt.close();
                con.close();
                System.out.println("cancellazione andata a buon fine");
                logger.info(String.format("Class:%s-Method:%s::END delete device -%s",
                        classe, method, dev));
                return true;
            } else {
                pt.close();
                con.close();
                logger.info(String.format("Class:%s-Method:%s::END not delete device -%s",
                        classe, method, dev));
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

    public boolean enableDevice(Device device) throws SQLException {
        /**
         * questo metodo setta l'attributo active a 0 (cioè dispositivo inattivo).
         * Questo perchè l'amministratore potrebbe voler vedere i voli passati
         * e se eliminiamo i dispositivi (analogamente anche i sapr) ci perderemo i dettagli.
         *
         * @param device è il bean contente tutti i dati da inserire nel db
         * @throws SQLException
         */
        String method = "enableDevice";
        Connection con = null;
        PreparedStatement pt = null;
        
        int dev = device.getIdDevice();

        String query = "UPDATE device SET active = 1 WHERE idDevice = ?";

        try {
            //logger per segnalare l'inizio della scrittura del metodo
            logger.info(String.format("Class:%s-Method:%s::START with dates %s", classe, method, dev));

            con = MySQLDbDAOFactory.createConnection();
            pt = con.prepareStatement(query);

            //compilo il campo ? nella query
            pt.setInt(1, dev);

            // eseguo la query
            if (pt.executeUpdate() == 1) {
                pt.close();
                con.close();
                System.out.println("enable Device andato a buon fine");
                logger.info(String.format("Class:%s-Method:%s::END delete device -%s",
                        classe, method, dev));
                return true;
            } else {
                pt.close();
                con.close();
                logger.info(String.format("Class:%s-Method:%s::END not delete device -%s",
                        classe, method, dev));
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

}

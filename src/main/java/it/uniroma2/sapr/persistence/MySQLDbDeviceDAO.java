package it.uniroma2.sapr.persistence;

import it.uniroma2.sapr.bean.ResponseDevice;
import it.uniroma2.sapr.bean.ResponseCheckElement;
import it.uniroma2.sapr.pojo.CheckElement;
import it.uniroma2.sapr.pojo.Device;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
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
        String method = "deleteDevice";
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
    
    public ArrayList<ResponseDevice> selectDevice(String owner) throws SQLException {
        /**
         * questo metodo prende in input l'id del pilota e ci restituisce tutti
         * i suoi dispositivi
         *
         * @param device è il bean contente tutti i dati da inserire nel db
         * @return ArrayList<Device> array di dispositivi
         * @throws SQLException
         */
        String method = "selectDevice";
        Connection con = null;
        PreparedStatement pt = null;
        PreparedStatement pt1 = null;
        ArrayList<ResponseDevice> arr_device = new ArrayList<ResponseDevice>();
        ArrayList<ResponseCheckElement> arr_check = new ArrayList<ResponseCheckElement>();
        
        String query = "SELECT idDevice, model, type, weight, producer, pilotLicense"
                + " FROM device WHERE pilotLicense = ?";
        String query1 = "SELECT valueCheckElement,IdDevice FROM checkDevice WHERE IdDevice = ?";
        try {
            //logger per segnalare l'inizio della scrittura del metodo
            logger.info(String.format("Class:%s-Method:%s::START with dates %s", classe, method, owner));

            con = MySQLDbDAOFactory.createConnection();
            pt = con.prepareStatement(query);
            pt1 = con.prepareStatement(query1);

            //compilo il campo ? nella query
            pt.setString(1, owner);

            // eseguo la query
            ResultSet rs = pt.executeQuery();
            if (rs != null) {

                System.out.println("select andata a buon fine");
                logger.info(String.format("Class:%s-Method:%s::END select all device of pilot %s",
                        classe, method, owner));

                while (rs.next()) {
                    int id = rs.getInt("idDevice");
                    String md = rs.getString("model");
                    String type = rs.getString("type");
                    int weight = rs.getInt("weight");
                    String producer = rs.getString("producer");
                    String pilotLicense = rs.getString("pilotLicense");
                    int active = rs.getInt("active");
                    
                    // estraggo anche i checkElement dei dispositivi
                    pt1.setInt(1, id);
                    ResultSet resultCheck = pt1.executeQuery();
                    while(resultCheck.next()){
                        ResponseCheckElement ck = new ResponseCheckElement(resultCheck.getString("valueCheckElement"));
                        arr_check.add(ck);
                    }
                    
                    ResponseDevice d = new ResponseDevice(id, md, type, weight, producer, pilotLicense, arr_check,active);
                    System.out.println(d.toString());
                    arr_device.add(d);
                    arr_check.clear();
                }

                return arr_device;
            } else {
                pt.close();
                con.close();
                System.out.println("select non andata a buon fine");
                logger.info(String.format("Class:%s-Method:%s::END select no one device of pilot %s",
                        classe, method, owner));
                return arr_device;
            }

        } catch (Exception e) {
            logger.error(String.format("Class:%s-Method:%s::ERROR", classe, method) + e);
            System.out.println(e);
            return arr_device;
        } finally {
            if (pt != null) {
                pt.close();
            }

            if (con != null) {
                con.close();
            }
        }

    }

    public ResponseDevice selectDevice(Device device) throws SQLException {
        /**
         * questo metodo prende in input un device e ci restituisce tutti
         * i suoi dettagli
         *
         * @return Device, tutti i dettagli del dispositivo
         * @throws SQLException
         */
        String method = "selectDevice";
        Connection con = null;
        PreparedStatement pt = null;
        PreparedStatement pt1 = null;
        int idDevice = device.getIdDevice();
        ArrayList<ResponseCheckElement> arr_check = new ArrayList<ResponseCheckElement>();
        
        String query = "SELECT idDevice, model, type, weight, producer, pilotLicense"
                + " FROM device WHERE idDevice = ?";
        String query1 = "SELECT valueCheckElement,IdDevice FROM checkDevice WHERE IdDevice = ?";

        try {
            //logger per segnalare l'inizio della scrittura del metodo
            logger.info(String.format("Class:%s-Method:%s::START with dates %s", classe, method, idDevice));

            con = MySQLDbDAOFactory.createConnection();
            pt = con.prepareStatement(query);
            pt1 = con.prepareStatement(query1);
            //compilo il campo ? nella query
            pt.setInt(1, idDevice);
            
            System.out.println(pt);
            // eseguo la query
            ResultSet rs = pt.executeQuery();
            if (rs != null) {
                rs.next();
                System.out.println("select andata a buon fine");
                logger.info(String.format("Class:%s-Method:%s::END select device %s",
                        classe, method, idDevice));

                int id = rs.getInt("idDevice");
                String md = rs.getString("model");
                String type = rs.getString("type");
                int weight = rs.getInt("weight");
                String producer = rs.getString("producer");
                String pilotLicense = rs.getString("pilotLicense");
                int active = rs.getInt("active");
                
                pt1.setInt(1, id);
                ResultSet resultCheck = pt1.executeQuery();
                    while(resultCheck.next()){
                        ResponseCheckElement ck = new ResponseCheckElement(resultCheck.getString("valueCheckElement"));
                        arr_check.add(ck);
                    }
                
                ResponseDevice d = new ResponseDevice(id, md, type, weight, producer, pilotLicense,arr_check,active);

                System.out.println(d.toString());
                arr_check.clear();
                return d;
            } else {
                pt.close();
                con.close();
                System.out.println("select non andata a buon fine");
                logger.info(String.format("Class:%s-Method:%s::END not select device %s",
                        classe, method, idDevice));
                return null;
            }

        } catch (Exception e) {
            logger.error(String.format("Class:%s-Method:%s::ERROR", classe, method) + e);
            System.out.println("(Catch) select non andata a buon fine");
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

    public static void main(String args[]) throws ParseException {
        CheckElement a = new CheckElement();
        ArrayList<CheckElement> ck = new ArrayList<CheckElement>();
        a.setValue("elica");ck.add(a);
        a.setValue("ventole");ck.add(a);
        a.setValue("motore");ck.add(a);
        
        Device device = new Device(1, "A144", "tipo", 520, "Prod1", "0000000003",ck,1);
        //Device device1 = new Device(1, "A144", "tipo", 520, "Prod1", "0000000003",ck,1);

        //Device deviceDel = new Device(78, "A144", "tipo1", 520, "Prod1", "0000000003",ck,1);

        MySQLDbDeviceDAO mysqlTest = new MySQLDbDeviceDAO();
        try {
            System.out.println("sto per iniziare");
            // test insert
            mysqlTest.insertDevice(device);

            // test delete
            //mysqlTest.deleteDevice(device);
            
            // test update
            mysqlTest.updateDevice(device);
            
            
            /*  PROVARE I COMPONENTI SEPARATI, RIUMIRE E RITESTARE  */
            //mysqlTest.removeAllCheckDevice(device);
            //mysqlTest.insertAllCheckDevice(device, ck);
            
            
            // test select dando in input un pilota
            //mysqlTest.selectDevice("0000000001");

            // test select dando in input un device
            //mysqlTest.selectDevice(device);
            
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

}

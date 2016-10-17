package it.uniroma2.sapr.persistence;

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
                    // compilo i campi ? nella query per inserire in checkDevice
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

    public boolean deleteDevice(Device device) throws SQLException {
        /**
         * questo metodo elimina un dispositivo dal DB
         *
         * @param device è il bean contente tutti i dati da inserire nel db
         * @throws SQLException
         */
        String method = "deleteDevice";
        Connection con = null;
        PreparedStatement pt = null;
        
        int dev = device.getIdDevice();

        String query = "DELETE FROM device WHERE idDevice = ?";

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
                System.out.println("cancellazione NON andata a buon fine");
                logger.info(String.format("Class:%s-Method:%s::END not delete device -%s",
                        classe, method, dev));
                return false;
            }
        } catch (Exception e) {
            logger.error(String.format("Class:%s-Method:%s::ERROR", classe, method) + e);
            System.out.println("cancellazione NON andata a buon fine");
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

    public ArrayList<Device> selectDevice(String owner) throws SQLException {
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
        ArrayList<Device> arr_device = new ArrayList<Device>();
        ArrayList<CheckElement> arr_check = new ArrayList<CheckElement>();
        
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
                    
                    // estraggo anche i checkElement dei dispositivi
                    pt1.setInt(1, id);
                    ResultSet resultCheck = pt1.executeQuery();
                    while(resultCheck.next()){
                        CheckElement ck = new CheckElement(resultCheck.getString("valueCheckElement"));
                        arr_check.add(ck);
                    }
                    
                    Device d = new Device(id, md, type, weight, producer, pilotLicense, arr_check);
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

    public Device selectDevice(Device device) throws SQLException {

        String method = "selectDevice";
        Connection con = null;
        PreparedStatement pt = null;
        PreparedStatement pt1 = null;
        int idDevice = device.getIdDevice();
        ArrayList<CheckElement> arr_check = new ArrayList<CheckElement>();
        
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
                
                pt1.setInt(1, id);
                ResultSet resultCheck = pt1.executeQuery();
                    while(resultCheck.next()){
                        CheckElement ck = new CheckElement(resultCheck.getString("valueCheckElement"));
                        arr_check.add(ck);
                    }
                
                Device d = new Device(id, md, type, weight, producer, pilotLicense,arr_check);

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
        
        ArrayList<CheckElement> ck = new ArrayList<CheckElement>();
        ck.add(new CheckElement("ciao"));
        ck.add(new CheckElement("ciao1"));
        ck.add(new CheckElement("ciao2"));
        
        Device device = new Device(78, "A144", "tipo", 520, "Prod1", "0000000003",ck);

        MySQLDbDeviceDAO mysqlTest = new MySQLDbDeviceDAO();
        try {
            System.out.println("sto per iniziare");
            // test insert
            mysqlTest.insertDevice(device);

            // test delete
            //mysqlTest.deleteDevice(70);
            
            // test select dando in input un pilota
            //mysqlTest.selectDevice("0000000001");

            // test select dando in input un device
            mysqlTest.selectDevice(device);
            
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

}

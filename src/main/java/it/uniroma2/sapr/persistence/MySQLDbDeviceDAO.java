package it.uniroma2.sapr.persistence;

import it.uniroma2.sapr.bean.Request;
import it.uniroma2.sapr.bean.ResponseDevice;
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
    final static Logger logger = Logger.getLogger("PESISTENCE");

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

        String query = "INSERT INTO device (idDevice,model,type,weight,producer,pilotLicense) VALUES"
                + "(?,?,?,?,?,?)";

        try {
            //logger per segnalare l'inizio della scrittura del metodo
            logger.info(String.format("Class:%s-Method:%s::START with dates %s", classe, method, device.toString()));

            con = MySQLDbDAOFactory.createConnection();
            pt = con.prepareStatement(query);

            //compilo i campi ? nella query
            pt.setInt(1, device.getIdDevice());
            pt.setString(2, device.getModel());
            pt.setString(3, device.getType());
            pt.setInt(4, device.getWeight());
            pt.setString(5, device.getProducer());
            pt.setString(6, device.getPilotLicense());

            // eseguo la query
            if (pt.executeUpdate() == 1) {
                pt.close();
                con.close();
                System.out.println("inserimento andato a buon fine");
                logger.info(String.format("Class:%s-Method:%s::END add device -%s of pilot %s", //
                        classe, method, device.getIdDevice(), device.getPilotLicense()));
                return true;
            } else {
                pt.close();
                con.close();
                System.out.println("inserimento NON andato a buon fine");
                logger.info(String.format("Class:%s-Method:%s::END not add device -%s of pilot %s", //
                        classe, method, device.getIdDevice(), device.getPilotLicense()));
                return false;
            }

        } catch (Exception e) {
            logger.error(String.format("Class:%s-Method:%s::ERROR", classe, method) + e);
            System.out.println("inserimento NON andato a buon fine");
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

    public boolean deleteDevice(int device) throws SQLException {
        /**
         * questo metodo elimina un dispositivo dal DB
         *
         * @param device è il bean contente tutti i dati da inserire nel db
         * @throws SQLException
         */
        String method = "deleteDevice";
        Connection con = null;
        PreparedStatement pt = null;

        String query = "DELETE FROM device WHERE idDevice = ?";

        try {
            //logger per segnalare l'inizio della scrittura del metodo
            logger.info(String.format("Class:%s-Method:%s::START with dates %s", classe, method, device));

            con = MySQLDbDAOFactory.createConnection();
            pt = con.prepareStatement(query);

            //compilo il campo ? nella query
            pt.setInt(1, device);

            // eseguo la query
            if (pt.executeUpdate() == 1) {
                pt.close();
                con.close();
                System.out.println("cancellazione andata a buon fine");
                logger.info(String.format("Class:%s-Method:%s::END delete device -%s",
                        classe, method, device));
                return true;
            } else {
                pt.close();
                con.close();
                System.out.println("cancellazione NON andata a buon fine");
                logger.info(String.format("Class:%s-Method:%s::END not delete device -%s",
                        classe, method, device));
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
        ArrayList<ResponseDevice> arr_device = new ArrayList<ResponseDevice>();

        String query = "SELECT idDevice, model, type, weight, producer, pilotLicense"
                + " FROM device WHERE pilotLicense = ?";

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

                    ResponseDevice d = new ResponseDevice(id, md, type, weight, producer, pilotLicense);
                    System.out.println(d.toString());
                    arr_device.add(d);
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
            System.out.println("(Catch) select non andata a buon fine");
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

    public ResponseDevice selectDevice(int idDevice) throws SQLException {

        String method = "selectDevice";
        Connection con = null;
        PreparedStatement pt = null;

        String query = "SELECT idDevice, model, type, weight, producer, pilotLicense"
                + " FROM device WHERE idDevice = ?";

        try {
            //logger per segnalare l'inizio della scrittura del metodo
            logger.info(String.format("Class:%s-Method:%s::START with dates %s", classe, method, idDevice));

            con = MySQLDbDAOFactory.createConnection();
            pt = con.prepareStatement(query);

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
                
                ResponseDevice d = new ResponseDevice(id, md, type, weight, producer, pilotLicense);

                System.out.println(d.toString());

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
        
        Device device = new Device(70, "A144", "tipo", 520, "Prod1", "0000000003");

        MySQLDbDeviceDAO mysqlTest = new MySQLDbDeviceDAO();
        try {
            System.out.println("sto per iniziare");
            // test insert
            //mysqlTest.insertDevice(device);

            // test delete
            //mysqlTest.deleteDevice(70);
            // test select
            mysqlTest.selectDevice("0000000003");
            mysqlTest.selectDevice(1);

        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

}

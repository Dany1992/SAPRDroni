
package it.uniroma2.sapr.persistence;

import it.uniroma2.sapr.bean.RequestDevice;
import it.uniroma2.sapr.pojo.Device;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import org.apache.log4j.Logger;

/**
 *
 * @author dario
 */
public class MySQLDbDeviceDAO implements DeviceDAO{

    String classe = "MySQLDbDeviceDAO";
    final static Logger logger = Logger.getLogger("PESISTENCE");
        
    public boolean insertDevice(Device device) throws SQLException {
        /**
        * questo metodo inserisce un dispositivo nel DB
        * @param device è il bean contente tutti i dati da inserire nel db 
        * @throws SQLException
        */
        String method = "insertDevice";
	Connection con = null;
	PreparedStatement pt = null;

        String query = "INSERT INTO device (idDevice,model,type,weight,producer,pilotLicense) VALUES"+
                "(?,?,?,?,?,?)";
        
        try{
            //logger per segnalare l'inizio della scrittura del metodo
            logger.info(String.format("Class:%s-Method:%s::START with dates %s", classe,method,device.toString()));
                    
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
            if(pt.executeUpdate() == 1){
                pt.close();
                con.close();
		System.out.println("inserimento andato a buon fine");
		logger.info(String.format("Class:%s-Method:%s::END add device -%s of pilot %s", //
                        classe,method,device.getIdDevice(),device.getPilotLicense()));
		return true;
            }else {
		pt.close();
		con.close();
		System.out.println("inserimento NON andato a buon fine");
		logger.info(String.format("Class:%s-Method:%s::END not add device -%s of pilot %s", //
                        classe,method,device.getIdDevice(),device.getPilotLicense()));
		return false;
			}
            
        }catch(Exception e){
            logger.error(String.format("Class:%s-Method:%s::ERROR", classe,method) + e);
            System.out.println("inserimento NON andato a buon fine");
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

    public boolean deleteDevice(Device device) throws SQLException {
    /**
     * questo metodo elimina un dispositivo dal DB
     * @param device è il bean contente tutti i dati da inserire nel db 
     * @throws SQLException
    */
        
        
        return false;
    }    

    public RequestDevice selectDevice(Long idDevice, String owner) throws SQLException {
        
        return null;
    }    
    
    public static void main(String args[]) throws ParseException{
        Device device = new Device(70,"A144","tipo",520,"Prod1","0000000003");
        
        MySQLDbDeviceDAO mysqlTest = new MySQLDbDeviceDAO();
        try {
            System.out.println("sto per iniziare");
            mysqlTest.insertDevice(device);
	} catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
	}
    }
    
}

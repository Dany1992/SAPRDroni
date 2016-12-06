package it.uniroma2.sapr.persistence;

import static it.uniroma2.sapr.persistence.MySQLDbFlightPlanDAO.logger;
import it.uniroma2.sapr.pojo.CheckDevice;
import it.uniroma2.sapr.pojo.CheckElement;
import it.uniroma2.sapr.pojo.Device;
import it.uniroma2.sapr.pojo.Sapr;
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
class MySQLDbCheckDeviceDAO implements CheckDeviceDAO {
    String classe = "MySQLDbCheckDeviceDAO";
    final static Logger logger = Logger.getLogger("PERSISTENCE");

    public boolean insertCheckDevice(CheckDevice cd) throws SQLException {
        System.out.println("funzione insert");
	String method = "insertCheckDevice";
	Connection con = null;
	PreparedStatement pt = null;
        try {
            con = MySQLDbDAOFactory.createConnection();
            for(int i=0;i<cd.getValue().size();i++){
                String query = "INSERT INTO checkDevice(IdDevice, valueCheckElement) VALUES(?,?)";
                System.out.println(query);
                //logger per segnalare l'inizio della scrittura del metodo
		logger.info(String.format("Class:%s-Method:%s::START with dates %s", classe,method,cd.toString()));
		pt = con.prepareStatement(query);
                
                pt.setInt(1, cd.getIdDevice());
                pt.setString(2, cd.getValue().get(i));
                
                //esito della query
                if(pt.executeUpdate() != 1){
                    pt.close();
                    con.close();
                    System.out.println("Non ho inserito il checkDevice");
                    logger.info(String.format("Class:%s-Method:%s::END don't add checkDevice with value-%s", //
                                    classe,method,cd.getValue().get(i)));
                    return false;
                }
                
                 System.out.println("Ho inserito il checkDevice");	
                        System.out.println(pt.toString());
                        pt.close();
                        logger.info(String.format("Class:%s-Method:%s::END add checkDevice with value-%s", //
                                        classe,method,cd.getValue().get(i)));
                }
            con.close();
            return true;    
        }
        catch (Exception e) {
            logger.error(String.format("Class:%s-Method:%s::ERROR", classe,method) + e);
            return false;
        }
        finally{
            pt.close();
            con.close();
        }
    }

    public boolean deleteCheckDevice(CheckDevice cd) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean updateCheckDevice(CheckDevice cd) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<CheckDevice> selectCheckDevice(Device d) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
public static void main(String args[]) throws SQLException{
    ArrayList<String> a = new ArrayList<String>();
    a.add("val1");
    a.add("val2");
    CheckDevice cc = new CheckDevice(1, a);
    
    MySQLDbCheckDeviceDAO mysqlTest = new MySQLDbCheckDeviceDAO();
    System.out.println(mysqlTest.insertCheckDevice(cc));
    
}

}

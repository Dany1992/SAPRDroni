package it.uniroma2.sapr.persistence;

import it.uniroma2.sapr.pojo.CheckDevice;
import it.uniroma2.sapr.pojo.Device;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author dario
 */
public interface CheckDeviceDAO {
    public boolean insertCheckDevice(CheckDevice cd) throws SQLException;
    public boolean deleteCheckDevice(CheckDevice cd) throws SQLException;
    public boolean updateCheckDevice(CheckDevice cd) throws SQLException;
    public ArrayList<CheckDevice> selectCheckDevice(Device d) throws SQLException;
}

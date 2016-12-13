package it.uniroma2.sapr.persistence;


import it.uniroma2.sapr.bean.ResponseDevice;
import it.uniroma2.sapr.pojo.Device;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author dario
 */
public interface DeviceDAO {
    public boolean insertDevice(Device device) throws SQLException;
    public boolean deleteDevice(Device device) throws SQLException;
    public boolean updateDevice(Device device) throws SQLException;
    public boolean enableDevice(Device device) throws SQLException;
}

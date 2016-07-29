package it.uniroma2.sapr.persistence;

import it.uniroma2.sapr.bean.RequestDevice;
import it.uniroma2.sapr.pojo.Device;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author dario
 */
public interface DeviceDAO {
    public boolean insertDevice(Device device) throws SQLException;
    public boolean deleteDevice(int device) throws SQLException;
    public ArrayList<Device> selectDevice(String owner) throws SQLException;
    public Device selectDevice(int idDevice) throws SQLException;
}

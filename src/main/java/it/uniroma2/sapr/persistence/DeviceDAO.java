package it.uniroma2.sapr.persistence;

import it.uniroma2.sapr.bean.RequestDevice;
import it.uniroma2.sapr.pojo.Device;
import java.sql.SQLException;

/**
 *
 * @author dario
 */
public interface DeviceDAO {
    public boolean insertDevice(Device device) throws SQLException;
    public boolean deleteDevice(Device device) throws SQLException;
    public RequestDevice selectDevice(Long idDevice, String owner) throws SQLException;
}

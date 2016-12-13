package it.uniroma2.sapr.service;

<<<<<<< HEAD
import java.util.ArrayList;

=======
import it.uniroma2.sapr.bean.Request;
>>>>>>> darioBranch
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import it.uniroma2.sapr.bean.RequestNote;
import it.uniroma2.sapr.bean.RequestPilot;
import it.uniroma2.sapr.bean.RequestSAPR;
import it.uniroma2.sapr.bean.ResponseListPilots;
import it.uniroma2.sapr.bean.ResponseSapr;
import it.uniroma2.sapr.bean.RequestDevice;
import it.uniroma2.sapr.bean.RequestFlightPlan;
<<<<<<< HEAD
import it.uniroma2.sapr.bean.Request.opzione;
=======
import it.uniroma2.sapr.bean.ResponseDevice;
import java.sql.SQLException;
import java.util.ArrayList;
>>>>>>> darioBranch

@WebService
public interface SAPRDroniInterface {
	
	@WebMethod(operationName = "managerPilot")
	public Boolean requestManagerPilot(@WebParam(name="request")RequestPilot request) throws Exception;
	
	@WebMethod(operationName = "managerSAPR")
	public Boolean requestManagerSAPR(@WebParam(name="request")RequestSAPR request) throws Exception;
	
	@WebMethod(operationName = "managerFlightPlan")  
    public Boolean requestManagerFlightPlan(@WebParam(name="request")RequestFlightPlan request) throws Exception;
    
    @WebMethod(operationName = "managerDevice")
	public Boolean requestManagerDevice(@WebParam(name="request")RequestDevice request) throws Exception;
	
    @WebMethod(operationName = "managerNote")
	public Boolean requestManagerNote(@WebParam(name="request")RequestNote request) throws Exception;
    
    @WebMethod(operationName = "getPilots")
    public ResponseListPilots getPilots() throws Exception;
    
<<<<<<< HEAD
    @WebMethod(operationName = "selectSaprOfPilotWithState")
    public ArrayList<ResponseSapr> selectSaprOfPilotWithState(opzione opzione, String pilotLicense) throws Exception;
    
    @WebMethod(operationName = "selectSaprOfPilot")
    public ArrayList<ResponseSapr> selectSaprOfPilot(String pilotLicense) throws Exception;
    
    @WebMethod(operationName = "selectSapr")
    public ResponseSapr selectSapr(int idSapr) throws Exception;
    
}
=======
    @WebMethod(operationName = "selectDeviceOfPilot")
    public ArrayList<ResponseDevice> selectDevice(String owner) throws SQLException;
    
    @WebMethod(operationName = "selectADevice")
    public ResponseDevice selectADevice(int idDevice) throws SQLException;
 
    @WebMethod(operationName = "selectEnableDevice")
    public ArrayList<ResponseDevice> selectEnableDevice(Request.opzione op, String owner) throws SQLException;
    
}
>>>>>>> darioBranch

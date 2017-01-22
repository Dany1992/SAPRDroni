package it.uniroma2.sapr.service;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import it.uniroma2.sapr.bean.RequestNote;
import it.uniroma2.sapr.bean.RequestPilot;
import it.uniroma2.sapr.bean.RequestSAPR;
import it.uniroma2.sapr.bean.ResponseListPilots;
import it.uniroma2.sapr.bean.ResponseSapr;
import it.uniroma2.sapr.utility.Opzione;
import it.uniroma2.sapr.bean.RequestDevice;
import it.uniroma2.sapr.bean.RequestFlightPlan;
import it.uniroma2.sapr.bean.ResponseFlightPlan;
import it.uniroma2.sapr.bean.ResponseNote;
import it.uniroma2.sapr.bean.ResponsePilot;
import it.uniroma2.sapr.bean.ResponseDevice;


import java.util.ArrayList;

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
    
    @WebMethod(operationName = "getPilot")
    public ResponsePilot getPilot(String licensePilots) throws Exception;
    
    @WebMethod(operationName = "getNote")
    public ResponseNote getNote(int idNote) throws Exception;

    @WebMethod(operationName = "getSaprsOfPilot")
    public ArrayList<ResponseSapr> getSaprsOfPilot(Opzione op, String pilotLicense) throws Exception;

    @WebMethod(operationName = "getSaprs")
    public ArrayList<ResponseSapr> getSaprs(Opzione op) throws Exception;
    
    @WebMethod(operationName = "selectSapr")
    public ResponseSapr getSapr(int idSapr) throws Exception;
    
    @WebMethod(operationName = "getDevice")
    public ResponseDevice getDevice(int idDevice) throws Exception;
 
    @WebMethod(operationName = "getDevicesOfPilot")
    public ArrayList<ResponseDevice> getDevicesOfPilot(Opzione op, String owner) throws Exception;
    
    @WebMethod(operationName = "getDevices")
    public ResponseDevice getDevices(Opzione op) throws Exception;
    
    @WebMethod(operationName = "getFlightPlanByFlight")
    public ResponseFlightPlan getFlightPlanByFlight(int idSapr,String pilotLicense,String dateDeparture) throws Exception;
    
    @WebMethod(operationName = "getFlightPlanBySapr")
    public ArrayList<ResponseFlightPlan> getFlightPlanBySapr(int idSapr) throws Exception;
    
}


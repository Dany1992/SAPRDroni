package it.uniroma2.sapr.service;

import java.util.ArrayList;

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
import it.uniroma2.sapr.bean.Request.opzione;

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
    
    @WebMethod(operationName = "selectSaprOfPilotWithState")
    public ArrayList<ResponseSapr> selectSaprOfPilotWithState(opzione opzione, String pilotLicense) throws Exception;
    
    @WebMethod(operationName = "selectSaprOfPilot")
    public ArrayList<ResponseSapr> selectSaprOfPilot(String pilotLicense) throws Exception;
    
    @WebMethod(operationName = "selectSapr")
    public ResponseSapr selectSapr(int idSapr) throws Exception;
    
}

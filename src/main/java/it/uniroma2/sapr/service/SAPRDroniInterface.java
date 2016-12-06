package it.uniroma2.sapr.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import it.uniroma2.sapr.bean.RequestNote;
import it.uniroma2.sapr.bean.RequestPilot;
import it.uniroma2.sapr.bean.RequestSAPR;
import it.uniroma2.sapr.bean.RequestDevice;
import it.uniroma2.sapr.bean.RequestFlightPlan;
import it.uniroma2.sapr.bean.RequestCheckElement;

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
    
    @WebMethod(operationName = "managerCheckElement")
	public Boolean requestManagerCheckElement(@WebParam(name="request")RequestCheckElement request) throws Exception;

}

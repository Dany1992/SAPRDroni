package it.uniroma2.sapr.service;


import it.uniroma2.sapr.bean.Request;
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
import it.uniroma2.sapr.bean.RequestCheckElement;
import it.uniroma2.sapr.bean.ResponseFlightPlan;
import it.uniroma2.sapr.bean.ResponseNote;
import it.uniroma2.sapr.pojo.FlightPlan;
import it.uniroma2.sapr.bean.ResponseDevice;

import it.uniroma2.sapr.bean.RequestDevice;
import it.uniroma2.sapr.bean.RequestFlightPlan;
import it.uniroma2.sapr.bean.ResponseDevice;
import it.uniroma2.sapr.bean.ResponseFlightPlan;

import java.sql.SQLException;
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
    
    @WebMethod(operationName = "getNote")
    public ResponseNote getNote(int idNote) throws Exception;

    @WebMethod(operationName = "selectSaprOfPilotWithState")
    public ArrayList<ResponseSapr> selectSaprOfPilotWithState(Opzione opzione, String pilotLicense) throws Exception;

    @WebMethod(operationName = "selectSaprOfPilot")
    public ArrayList<ResponseSapr> selectSaprOfPilot(String pilotLicense) throws Exception;
    
    @WebMethod(operationName = "selectSapr")
    public ResponseSapr selectSapr(int idSapr) throws Exception;
    
    //@WebMethod(operationName = "selectDeviceOfPilot")
    //public ArrayList<ResponseDevice> selectDevice(String owner) throws SQLException;
    
    @WebMethod(operationName = "selectADevice")
    public ResponseDevice selectADevice(int idDevice) throws SQLException;
 
    @WebMethod(operationName = "selectEnableDevice")
    public ArrayList<ResponseDevice> selectEnableDevice(Opzione op, String owner) throws SQLException;
    
    @WebMethod(operationName = "selectFlightPlanByFlight")
    public ResponseFlightPlan selectFlightPlanByFlight(int idSapr,String pilotLicense,String dateDeparture) throws SQLException;
    
    @WebMethod(operationName = "selectFlightPlanBySapr")
    public ArrayList<ResponseFlightPlan> selectFlightPlanBySapr(int idSapr) throws SQLException;
}


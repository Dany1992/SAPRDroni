package it.uniroma2.sapr.service;

import javax.jws.WebParam;
import javax.jws.WebService;

import it.uniroma2.sapr.bean.RequestPilot;
import it.uniroma2.sapr.bean.RequestSAPR;

@WebService
public interface SAPRServiceInterface {
	
	public Boolean requestManagerPilot(@WebParam(name="request")RequestPilot request) throws Exception;
	
	public Boolean requestManagerSAPR(@WebParam(name="request")RequestSAPR request) throws Exception;
	
	
}

package it.uniroma2.sapr.service;

import javax.jws.WebParam;
import javax.jws.WebService;

import it.uniroma2.sapr.bean.RequestPilot;

@WebService
public interface SAPRServiceInterface {
	
	public Boolean requestManagerPilot(@WebParam(name="request")RequestPilot request) throws Exception;
	
}

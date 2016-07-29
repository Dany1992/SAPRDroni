package it.uniroma2.sapr.service;

import javax.jws.WebParam;
import javax.jws.WebService;

import it.uniroma2.sapr.bean.RequestNote;
import it.uniroma2.sapr.bean.RequestPilot;
import it.uniroma2.sapr.bean.RequestSAPR;
import it.uniroma2.sapr.bean.RequestDevice;

@WebService
public interface SAPRServiceInterface {
	
	public Boolean requestManagerPilot(@WebParam(name="request")RequestPilot request) throws Exception;
	
	public Boolean requestManagerSAPR(@WebParam(name="request")RequestSAPR request) throws Exception;
	
	public Boolean requestManagerDevice(@WebParam(name="request")RequestDevice request) throws Exception;

	public Boolean requestManagerNote(@WebParam(name="request")RequestNote request) throws Exception;

}

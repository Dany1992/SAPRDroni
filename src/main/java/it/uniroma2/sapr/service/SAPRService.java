package it.uniroma2.sapr.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.BindingType;


import it.uniroma2.sapr.bean.RequestPilot;
import it.uniroma2.sapr.bean.RequestSAPR;

@WebService
@BindingType("http://schemas.xmlsoap.org/wsdl/soap/http")
public class SAPRService implements SAPRServiceInterface{

	@WebMethod(operationName = "managerPilot")
	public Boolean requestManagerPilot(@WebParam(name = "request")RequestPilot request) throws Exception {
		throw new Exception("metodo non implementato");
	}
	
	@WebMethod(operationName = "managerSAPR")
	public Boolean requestManagerSAPR(@WebParam(name = "request")RequestSAPR request) throws Exception {
		throw new Exception("metodo non implementato");
	}
	
	

}

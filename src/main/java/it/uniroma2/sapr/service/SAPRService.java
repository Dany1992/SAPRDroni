package it.uniroma2.sapr.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.BindingType;


import it.uniroma2.sapr.bean.RequestPilot;

@WebService
@BindingType("http://schemas.xmlsoap.org/wsdl/soap/http")
public class SAPRService implements SAPRServiceInterface{

	@WebMethod(operationName = "managerPilot")
	public Boolean requestManagerPilot(@WebParam(name = "request")RequestPilot request) throws Exception {
		throw new Exception("metodo non implementato");
	}

}

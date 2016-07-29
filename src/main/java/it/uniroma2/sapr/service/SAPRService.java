package it.uniroma2.sapr.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.BindingType;


import it.uniroma2.sapr.bean.RequestNote;
import it.uniroma2.sapr.bean.RequestPilot;
import it.uniroma2.sapr.bean.RequestSAPR;
import it.uniroma2.sapr.bean.RequestDevice;

/**
 * Questa classe è colei che si occupa di esporre i servizi offerti dal WS
 * 
 * @author Danilo Butrico
 * @versione 1.0
 * @mail dbutricod@gmail.com
 *
 */

@WebService
@BindingType("http://schemas.xmlsoap.org/wsdl/soap/http")
public class SAPRService implements SAPRServiceInterface{

	/**
	 * Il webMethod che si occupa di aggiungere o eliminare un pilota. Questa operazione viene effettuata
	 * leggendo il campo OPERATION che viene passato dal web nell'oggetto RequestPilot
	 */
	@WebMethod(operationName = "managerPilot")
	public Boolean requestManagerPilot(@WebParam(name = "request")RequestPilot request) throws Exception {
		throw new Exception("metodo non implementato");
	}
	
	/**
	 * Il webMethod che si occupa di aggiungere o eliminare un drone. Questa operazione viene effettuata
	 * leggendo il campo OPERATION che viene passato dal web nell'oggetto RequetSAPR
	 */
	@WebMethod(operationName = "managerSAPR")
	public Boolean requestManagerSAPR(@WebParam(name = "request")RequestSAPR request) throws Exception {
		throw new Exception("metodo non implementato");
	}
	
	/**
	 * Il webMethod che si occupa di aggiungere o eliminare un Device. Questa operazione viene effettuata
	 * leggendo il campo OPERATION che viene passato dal web nell'oggetto RequetSAPR
	 */
	@WebMethod(operationName = "managerDevice")
	public Boolean requestManagerDevice(@WebParam(name = "request")RequestDevice request) throws Exception {
		throw new Exception("metodo non implementato");
	}


	/**
	 * Il webMethod che si occupa di aggiungere o eliminare una Nota. Questa operazione viene effettuata
	 * leggendo il campo OPERATION che viene passato dal web nell'oggetto RequetNote
	 */
	@WebMethod(operationName = "managerNote")
	public Boolean requestManagerNote(@WebParam(name = "request")RequestNote request) throws Exception {
		throw new Exception("metodo non implementato");
	}


}

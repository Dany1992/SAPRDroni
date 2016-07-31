package it.uniroma2.sapr.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.BindingType;

import org.apache.log4j.Logger;

import it.uniroma2.sapr.bean.RequestNote;
import it.uniroma2.sapr.bean.RequestPilot;
import it.uniroma2.sapr.bean.RequestSAPR;
import it.uniroma2.sapr.persistence.DAOFactory;
import it.uniroma2.sapr.persistence.MySQLDbDAOFactory;
import it.uniroma2.sapr.persistence.MySQLDbPilotDAO;
import it.uniroma2.sapr.persistence.PilotDAO;
import it.uniroma2.sapr.persistence.SaprDAO;
import it.uniroma2.sapr.pojo.Pilot;
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
	String classe = "MySQLDbPilotDAO";
	final static Logger logger = Logger.getLogger("SERVICE");
	/**
	 * Il webMethod che si occupa di aggiungere o eliminare un pilota. Questa operazione viene effettuata
	 * leggendo il campo OPERATION che viene passato dal web nell'oggetto RequestPilot
	 */
	@WebMethod(operationName = "managerPilot")
	public Boolean requestManagerPilot(@WebParam(name = "request")RequestPilot request) throws Exception {
		String method = "RequestManaerPilot";
		logger.info(String.format("Class:%s-Method:%s::START", classe,method));
		
		//Trasferisco i dati dalla request al pojo
		Pilot pilot = new Pilot(request.getName(), request.getSurname(), request.getNation(), 
				request.getState(), request.getPilotLicense(), 
				request.getTaxCode(), request.getBirthDate(), request.getResidence(), 
				request.getPhone(), request.getMail(), request.getPassword());
		
		//Creo le classi per accedere al db.
		DAOFactory mySQLFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		PilotDAO pilotDAO = mySQLFactory.getPilotDAO();
		
		//Controllo in base all'operazione nel bean di request quale operazione svolgere
		Boolean result;
		if (request.getOp().equals("INSERISCI")){
			//Inserisco il pilota nel db
			result = pilotDAO.insertPilot(pilot);
			return result;
		}else if (request.getOp().equals("ELIMINA")) {
			result = pilotDAO.deletePilot(pilot);
			return result;
		}else if (request.getOp().equals("AGGIORNA")) {
			result = pilotDAO.updatePilot(pilot);
			return result;
		}else {
			throw new Exception("error operation");
		}
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

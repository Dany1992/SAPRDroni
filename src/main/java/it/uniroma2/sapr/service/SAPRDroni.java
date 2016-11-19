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
import it.uniroma2.sapr.persistence.PilotDAO;
import it.uniroma2.sapr.pojo.Pilot;
import it.uniroma2.sapr.pojo.Device;
import it.uniroma2.sapr.bean.RequestDevice;
import it.uniroma2.sapr.bean.RequestFlightPlan;
import it.uniroma2.sapr.persistence.DeviceDAO;
import it.uniroma2.sapr.persistence.FlightPlanDAO;
import it.uniroma2.sapr.persistence.NoteDAO;
import it.uniroma2.sapr.persistence.SaprDAO;
import it.uniroma2.sapr.pojo.FlightPlan;
import it.uniroma2.sapr.pojo.Note;
import it.uniroma2.sapr.pojo.Sapr;

/**
 * Questa classe è colei che si occupa di esporre i servizi offerti dal WS
 * 
 * @author Danilo Butrico
 * @versione 1.0
 * @mail dbutricod@gmail.com
 *
 */

@WebService(endpointInterface="it.uniroma2.sapr.service.SAPRDroniInterface")
@BindingType("http://schemas.xmlsoap.org/wsdl/soap/http")
public class SAPRDroni implements SAPRDroniInterface{
	String classe = "MySQLDbPilotDAO";
	final static Logger logger = Logger.getLogger("SERVICE");
	/**
	 * Il webMethod che si occupa di aggiungere o eliminare un pilota. Questa operazione viene effettuata
	 * leggendo il campo OPERATION che viene passato dal web nell'oggetto RequestPilot
	 */
	
	public Boolean requestManagerPilot(@WebParam(name = "request")RequestPilot request) throws Exception {
		String method = "RequestManaerPilot";
		if (request == null){
			return false;
		}
		logger.info(String.format("Class:%s-Method:%s::START", classe,method));
		logger.info(String.format("Class:%s-Method:%s::The request is: %s", classe,method,request.toString()));
		
		System.out.println("***********************START WS***********************");
		System.out.println("La richiesta è: " + request.toString());
		
		
		
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
		if (request.getOp().name().equalsIgnoreCase("ADD")){
			System.out.println("inserisci");
			result = pilotDAO.insertPilot(pilot);
		}else if (request.getOp().name().equalsIgnoreCase("DELETE")) {
			result = pilotDAO.deletePilot(pilot);
		}else if (request.getOp().name().equalsIgnoreCase("UPDATE")) {
			result = pilotDAO.updatePilot(pilot);
		}else {
			throw new Exception("ERROR OPERATION");
		}
		
		logger.info(String.format("Class:%s-Method:%s::END", classe,method));
		System.out.println("Result: "+result);
		System.out.println("***********************END WS***********************");

		return result;
	}
	
	/**
	 * Il webMethod che si occupa di aggiungere o eliminare un drone. Questa operazione viene effettuata
	 * leggendo il campo OPERATION che viene passato dal web nell'oggetto RequetSAPR
	 */
	
	public Boolean requestManagerSAPR(@WebParam(name = "request")RequestSAPR request) throws Exception {
            String method = "RequestManagerSAPR";
            logger.info(String.format("Class:%s-Method:%s::START", classe,method));
            logger.info(String.format("Class:%s-Method:%s::The request is: %s", classe,method,request.toString()));
            
            Sapr sapr = new Sapr((int)request.getIdSapr(), request.getModel(), request.getProducer(), 
                            request.getWeight(), request.getHeavyweight(), request.getBattery(), 
                            request.getMaxDistance(), request.getMaxHeight(), request.getPilotLicense(), 
                            request.getCheckSapr(), request.getActive());
            
            //Creo le classi per accedere al db.
            DAOFactory mySQLFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
            SaprDAO saprDAO = mySQLFactory.getSaprDAO();
            

            
            System.out.println("***********************START WS***********************");
            System.out.println("La richiesta è: " + request.toString());
            
            //Controllo in base all'operazione nel bean di request quale operazione svolgere
		Boolean result;
		if (request.getOp().name().equalsIgnoreCase("ADD")){
			System.out.println("inserisci");
			result = saprDAO.insertSapr(sapr);
		}else if (request.getOp().name().equalsIgnoreCase("DELETE")) {
			result = saprDAO.deleteSapr(sapr);
		}else if (request.getOp().name().equalsIgnoreCase("UPDATE")) {
			result = saprDAO.updateSapr(sapr);
		}else {
			throw new Exception("ERROR OPERATION");
		}
		
		logger.info(String.format("Class:%s-Method:%s::END", classe,method));
		System.out.println("***********************END WS***********************");

		return result;
	}
	
	/**
	 * Il webMethod che si occupa di aggiungere o eliminare un Device. Questa operazione viene effettuata
	 * leggendo il campo OPERATION che viene passato dal web nell'oggetto RequetSAPR
	 */
	
	public Boolean requestManagerDevice(@WebParam(name = "request")RequestDevice request) throws Exception {
		String method = "RequestManagerDevice";
		logger.info(String.format("Class:%s-Method:%s::START", classe,method));
		logger.info(String.format("Class:%s-Method:%s::The request is: %s", classe,method,request.toString()));
		
		System.out.println("***********************START WS***********************");
		System.out.println("La richiesta è: " + request.toString());
		
                /* MODIFICARE REQUEST DEVICE --> CHECKELEMENT */
                
		//Trasferisco i dati dalla request al pojo
		Device device = new Device(request.getIdDevice(), request.getModel(), request.getModel(), request.getWeight(),
                                        request.getProducer(), request.getPilotLicense(), request.getCheckDevice(),request.getActive());
                
		//Creo le classi per accedere al db.
		DAOFactory mySQLFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		DeviceDAO deviceDAO = mySQLFactory.getDeviceDAO();
		
		//Controllo in base all'operazione nel bean di request quale operazione svolgere
		Boolean result;
		if (request.getOp().name().equalsIgnoreCase("ADD")){
			System.out.println("inserisci");
			result = deviceDAO.insertDevice(device);
		}else if (request.getOp().name().equalsIgnoreCase("DELETE")) {
			result = deviceDAO.deleteDevice(device);
		}else if (request.getOp().name().equalsIgnoreCase("UPDATE")) {
			result = deviceDAO.updateDevice(device);
		}else {
			throw new Exception("ERROR OPERATION");
		}
		
		logger.info(String.format("Class:%s-Method:%s::END", classe,method));
		System.out.println("***********************END WS***********************");

		return result;
	}


	/**
	 * Il webMethod che si occupa di aggiungere o eliminare una Nota. Questa operazione viene effettuata
	 * leggendo il campo OPERATION che viene passato dal web nell'oggetto RequetNote
	 */
	@WebMethod(operationName = "managerNote")
	public Boolean requestManagerNote(@WebParam(name = "request")RequestNote request) throws Exception {
            String method = "RequestManagerNote";
            
            logger.info(String.format("Class:%s-Method:%s::START", classe,method));
            logger.info(String.format("Class:%s-Method:%s::The request is: %s", classe,method,request.toString()));
            
            System.out.println("***********************START WS***********************");
            System.out.println("Request is: " + request.toString());
            
            Note note = new Note(request.getTextNote(), request.getDate());
            String textNote = new String(request.getTextNote());
            
            //Creo le classi per accedere al db.
            DAOFactory mySQLFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
            NoteDAO noteDAO = mySQLFactory.getNoteDAO();
            
            Boolean result;
		if (request.getOp().name().equalsIgnoreCase("ADD")){
			System.out.println("inserisci");
			result = noteDAO.insertNote(note);
		}else if (request.getOp().name().equalsIgnoreCase("DELETE")) {
			result = noteDAO.deleteNote(note);
		}else if (request.getOp().name().equalsIgnoreCase("UPDATE")) {
			result = noteDAO.updateNote(note, textNote);
		}else {
			throw new Exception("ERROR OPERATION");
		}
                
                logger.info(String.format("Class:%s-Method:%s::END", classe,method));
		System.out.println("***********************END WS***********************");

		return result;
	}
    
	
    public Boolean requestManagerFlightPlan(@WebParam(name = "request")RequestFlightPlan request) throws Exception {
                String method = "RequestFlightPlan";
		logger.info(String.format("Class:%s-Method:%s::START", classe,method));
		logger.info(String.format("Class:%s-Method:%s::The request is: %s", classe,method,request.toString()));
		
		System.out.println("***********************START WS***********************");
		System.out.println("La richiesta è: " + request.toString());
		
		//Trasferisco i dati dalla request al pojo
		FlightPlan flight=new FlightPlan(request.getDestinations(),request.getDeparture(),request.getDateDeparture(),request.getTimeDeparture(),request.getNowArriving(),request.getIdSapr(),request.getIdNote(),request.getPilotLicense(),request.getDevices());
     
		
		//Creo le classi per accedere al db.
		DAOFactory mySQLFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		FlightPlanDAO flightDAO = mySQLFactory.getFlightPlanDAO();
		
		//Controllo in base all'operazione nel bean di request quale operazione svolgere
		Boolean result;
		if (request.getOp().name().equalsIgnoreCase("ADD")){
			System.out.println("inserisci");
			result = flightDAO.insertFlightPlan(flight);
		}else if (request.getOp().name().equalsIgnoreCase("DELETE")) {
			result = flightDAO.deleteFlightPlan(flight);
		}else if (request.getOp().name().equalsIgnoreCase("UPDATE")) {
			result = flightDAO.updateFlightPlan(flight);
		}else {
			throw new Exception("ERROR OPERATION");
		}
		
		logger.info(String.format("Class:%s-Method:%s::END", classe,method));
		System.out.println("***********************END WS***********************");

		return result;
    }


}

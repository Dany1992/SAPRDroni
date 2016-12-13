package it.uniroma2.sapr.service;


import it.uniroma2.sapr.bean.Request;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.BindingType;
import org.apache.log4j.Logger;

import it.uniroma2.sapr.bean.RequestNote;
import it.uniroma2.sapr.bean.RequestPilot;
import it.uniroma2.sapr.bean.RequestSAPR;
import it.uniroma2.sapr.bean.ResponseListPilots;
import it.uniroma2.sapr.bean.ResponsePilot;
import it.uniroma2.sapr.persistence.DAOFactory;
import it.uniroma2.sapr.persistence.PilotDAO;
import it.uniroma2.sapr.pojo.Pilot;
import it.uniroma2.sapr.pojo.Device;
import it.uniroma2.sapr.bean.RequestCheckElement;
import it.uniroma2.sapr.bean.RequestDevice;
import it.uniroma2.sapr.bean.RequestFlightPlan;
import it.uniroma2.sapr.bean.ResponseCheckElement;
import it.uniroma2.sapr.bean.ResponseDevice;
import it.uniroma2.sapr.persistence.DeviceDAO;
import it.uniroma2.sapr.persistence.FlightPlanDAO;
import it.uniroma2.sapr.persistence.MySQLDbDAOFactory;
import it.uniroma2.sapr.persistence.MySQLDbDeviceDAO;
import it.uniroma2.sapr.persistence.NoteDAO;
import it.uniroma2.sapr.persistence.SaprDAO;
import it.uniroma2.sapr.pojo.CheckElement;
import it.uniroma2.sapr.pojo.FlightPlan;
import it.uniroma2.sapr.pojo.Note;
import it.uniroma2.sapr.pojo.Sapr;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

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
	public Boolean requestManagerPilot(RequestPilot request)throws Exception{
		String method = "RequestManaerPilot";
		
		if (request == null){
			System.out.println("Request null");
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
            ArrayList<CheckElement> checkList = new ArrayList<CheckElement>();
            
            for(RequestCheckElement e : request.getCheckSapr()){
            	CheckElement a = new CheckElement(e.getValue());
            	checkList.add(a);
            }
            
            Sapr sapr = new Sapr((int)request.getIdSapr(), request.getModel(), request.getProducer(), 
                            request.getWeight(), request.getHeavyweight(), request.getBattery(), 
                            request.getMaxDistance(), request.getMaxHeight(), request.getPilotLicense(), 
                            checkList, 0);
            
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
		}else if (request.getOp().name().equalsIgnoreCase("ENABLE")) {
			result = saprDAO.enableSapr(sapr);
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
                ArrayList<CheckElement> checkElements = new ArrayList<CheckElement>();
                for (int i = 0; i < request.getCheckDevice().size(); i++) {
                    CheckElement check = new CheckElement(request.getCheckDevice().get(i).getValue());
                    checkElements.add(check);
                }
                
                
		Device device = new Device(request.getIdDevice(), request.getModel(), request.getType(), request.getWeight(),
                                        request.getProducer(), request.getPilotLicense(), checkElements,0);
                
		//Creo le classi per accedere al db.
		DAOFactory mySQLFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		DeviceDAO deviceDAO = mySQLFactory.getDeviceDAO();
		//Controllo in base all'operazione nel bean di request quale operazione svolgere
		Boolean result;
		if (request.getOp().name().equalsIgnoreCase("ADD")){
                    result = deviceDAO.insertDevice(device);
		}else if (request.getOp().name().equalsIgnoreCase("DELETE")) {
                    result = deviceDAO.deleteDevice(device);
		}else if (request.getOp().name().equalsIgnoreCase("UPDATE")) {
                    result = deviceDAO.updateDevice(device);
		}else if (request.getOp().name().equalsIgnoreCase("ENABLE")){
                    result = deviceDAO.enableDevice(device);
                    
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
	public Boolean requestManagerNote(@WebParam(name = "request")RequestNote request) throws Exception {
            String method = "RequestManagerNote";
            
            logger.info(String.format("Class:%s-Method:%s::START", classe,method));
            logger.info(String.format("Class:%s-Method:%s::The request is: %s", classe,method,request.toString()));
            
            System.out.println("***********************START WS***********************");
            System.out.println("Request is: " + request.toString());
            
            Note note = new Note(request.getIdNote(), request.getTextNote(), request.getDate());
            
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
			result = noteDAO.updateNote(note);
		}else {
			throw new Exception("ERROR OPERATION");
		}
		
		logger.info(String.format("Class:%s-Method:%s::END", classe,method));
		System.out.println("Result: "+result);
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

            public ResponseListPilots getPilots() throws Exception {
                    String method = "getPilots";
                    logger.info(String.format("Class:%s-Method:%s::START", classe,method ));

                    ResponseListPilots response = new ResponseListPilots();

                    //Factory per il db
                    DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
                    PilotDAO pilotDAO = mysqlFactory.getPilotDAO();

                    ArrayList<ResponsePilot> listPilots = pilotDAO.selectAllPilot();
                    if(listPilots != null){
                            response.setErrorCode(0);
                            response.setErrorMessage("SUCCESS");
                            response.setPilots(listPilots);
                    }else{
                            response.setErrorCode(-1);
                            response.setErrorMessage("ERROR GET PILOTS");
                    }

                    logger.info(String.format("Class:%s-Method:%s::END", classe,method));
                    return response;
            }

        public ArrayList<ResponseDevice> selectDevice(String owner) throws SQLException {
            /**
             * questo metodo prende in input l'id del pilota e ci restituisce tutti
             * i suoi dispositivi
             *
             * @param device è il bean contente tutti i dati da inserire nel db
             * @return ArrayList<Device> array di dispositivi
             * @throws SQLException
             */
            String method = "selectDevice";
            Connection con = null;
            PreparedStatement pt = null;
            PreparedStatement pt1 = null;
            ArrayList<ResponseDevice> arr_device = new ArrayList<ResponseDevice>();
            ArrayList<ResponseCheckElement> arr_check = new ArrayList<ResponseCheckElement>();

            String query = "SELECT idDevice, model, type, weight, producer, pilotLicense"
                    + " FROM device WHERE pilotLicense = ?";
            String query1 = "SELECT valueCheckElement,IdDevice FROM checkDevice WHERE IdDevice = ?";
            try {
                //logger per segnalare l'inizio della scrittura del metodo
                logger.info(String.format("Class:%s-Method:%s::START with dates %s", classe, method, owner));

                con = MySQLDbDAOFactory.createConnection();
                pt = con.prepareStatement(query);
                pt1 = con.prepareStatement(query1);

                //compilo il campo ? nella query
                pt.setString(1, owner);

                // eseguo la query
                ResultSet rs = pt.executeQuery();
                if (rs != null) {

                    System.out.println("select andata a buon fine");
                    logger.info(String.format("Class:%s-Method:%s::END select all device of pilot %s",
                            classe, method, owner));

                    while (rs.next()) {
                        int id = rs.getInt("idDevice");
                        String md = rs.getString("model");
                        String type = rs.getString("type");
                        int weight = rs.getInt("weight");
                        String producer = rs.getString("producer");
                        String pilotLicense = rs.getString("pilotLicense");
                        int active = rs.getInt("active");

                        // estraggo anche i checkElement dei dispositivi
                        pt1.setInt(1, id);
                        ResultSet resultCheck = pt1.executeQuery();
                        while(resultCheck.next()){
                            ResponseCheckElement ck = new ResponseCheckElement(resultCheck.getString("valueCheckElement"));
                            arr_check.add(ck);
                        }

                        ResponseDevice d = new ResponseDevice(id, md, type, weight, producer, pilotLicense, arr_check,active);
                        System.out.println(d.toString());
                        arr_device.add(d);
                        arr_check.clear();
                    }

                    return arr_device;
                } else {
                    pt.close();
                    con.close();
                    System.out.println("select non andata a buon fine");
                    logger.info(String.format("Class:%s-Method:%s::END select no one device of pilot %s",
                            classe, method, owner));
                    return arr_device;
                }

            } catch (Exception e) {
                logger.error(String.format("Class:%s-Method:%s::ERROR", classe, method) + e);
                System.out.println(e);
                return arr_device;
            } finally {
                if (pt != null) {
                    pt.close();
                }

                if (con != null) {
                    con.close();
                }
            }

        }
        
            
        public ResponseDevice selectADevice(int idDevice) throws SQLException {
            /**
             * questo metodo prende in input un device e ci restituisce tutti
             * i suoi dettagli
             *
             * @return Device, tutti i dettagli del dispositivo
             * @throws SQLException
             */
            String method = "selectDevice";
            Connection con = null;
            PreparedStatement pt = null;
            PreparedStatement pt1 = null;
            ArrayList<ResponseCheckElement> arr_check = new ArrayList<ResponseCheckElement>();

            String query = "SELECT idDevice, model, type, weight, producer, pilotLicense"
                    + " FROM device WHERE idDevice = ?";
            String query1 = "SELECT valueCheckElement,IdDevice FROM checkDevice WHERE IdDevice = ?";

            try {
                //logger per segnalare l'inizio della scrittura del metodo
                logger.info(String.format("Class:%s-Method:%s::START with dates %s", classe, method, idDevice));

                con = MySQLDbDAOFactory.createConnection();
                pt = con.prepareStatement(query);
                pt1 = con.prepareStatement(query1);
                //compilo il campo ? nella query
                pt.setInt(1, idDevice);

                System.out.println(pt);
                // eseguo la query
                ResultSet rs = pt.executeQuery();
                if (rs != null) {
                    rs.next();
                    System.out.println("select andata a buon fine");
                    logger.info(String.format("Class:%s-Method:%s::END select device %s",
                            classe, method, idDevice));

                    int id = rs.getInt("idDevice");
                    String md = rs.getString("model");
                    String type = rs.getString("type");
                    int weight = rs.getInt("weight");
                    String producer = rs.getString("producer");
                    String pilotLicense = rs.getString("pilotLicense");
                    int active = rs.getInt("active");

                    pt1.setInt(1, id);
                    ResultSet resultCheck = pt1.executeQuery();
                        while(resultCheck.next()){
                            ResponseCheckElement ck = new ResponseCheckElement(resultCheck.getString("valueCheckElement"));
                            arr_check.add(ck);
                        }

                    ResponseDevice d = new ResponseDevice(id, md, type, weight, producer, pilotLicense,arr_check,active);

                    System.out.println(d.toString());
                    arr_check.clear();
                    return d;
                } else {
                    pt.close();
                    con.close();
                    System.out.println("select non andata a buon fine");
                    logger.info(String.format("Class:%s-Method:%s::END not select device %s",
                            classe, method, idDevice));
                    return null;
                }

            } catch (Exception e) {
                logger.error(String.format("Class:%s-Method:%s::ERROR", classe, method) + e);
                System.out.println("(Catch) select non andata a buon fine");
                return null;
            } finally {
                if (pt != null) {
                    pt.close();
                }

                if (con != null) {
                    con.close();
                }
            }
        }

        public ArrayList<ResponseDevice> selectEnableDevice(Request.opzione op, String owner) throws SQLException {
            /**
             * questo metodo prende in input l'id del pilota e l'opzione che ci identifica cosa vogliamo
             * ENABLED/DISABLED/ALL sono i soli valori che puo' assumere opzione
             *
             * @param owner il pilota a cui ci riferiamo
             * @param opzione a quali dispositivi siamo interessati
             * @return ArrayList<ResponseDevice> array di dispositivi
             * @throws SQLException
             */
            String method = "selectAllDevice";
            Connection con = null;
            PreparedStatement pt = null;
            PreparedStatement pt1 = null;
            ArrayList<ResponseDevice> arr_device = new ArrayList<ResponseDevice>();
            ArrayList<ResponseCheckElement> arr_check = new ArrayList<ResponseCheckElement>();

            String query = "SELECT idDevice, model, type, weight, producer, pilotLicense"
                    + " FROM device WHERE pilotLicense = ?";
            if (op.name().equalsIgnoreCase("ENABLED")){
                method = "selectDeviceEnabled";
                query += " AND active = 1";
            }else if (op.name().equalsIgnoreCase("DISABLED")) {
                method = "selectDeviceDisabled";
                query += " AND active = 0";
            }else if (op.name().equalsIgnoreCase("ALL")) {
                    
            }
            
            String query1 = "SELECT valueCheckElement,IdDevice FROM checkDevice WHERE IdDevice = ?";
            try {
                //logger per segnalare l'inizio della scrittura del metodo
                logger.info(String.format("Class:%s-Method:%s::START with dates %s", classe, method, owner));

                con = MySQLDbDAOFactory.createConnection();
                pt = con.prepareStatement(query);
                pt1 = con.prepareStatement(query1);

                //compilo il campo ? nella query
                pt.setString(1, owner);

                // eseguo la query
                ResultSet rs = pt.executeQuery();
                if (rs != null) {

                    System.out.println("select andata a buon fine");
                    logger.info(String.format("Class:%s-Method:%s::END select all device of pilot %s",
                            classe, method, owner));

                    while (rs.next()) {
                        int id = rs.getInt("idDevice");
                        String md = rs.getString("model");
                        String type = rs.getString("type");
                        int weight = rs.getInt("weight");
                        String producer = rs.getString("producer");
                        String pilotLicense = rs.getString("pilotLicense");
                        int active = rs.getInt("active");

                        // estraggo anche i checkElement dei dispositivi
                        pt1.setInt(1, id);
                        ResultSet resultCheck = pt1.executeQuery();
                        while(resultCheck.next()){
                            ResponseCheckElement ck = new ResponseCheckElement(resultCheck.getString("valueCheckElement"));
                            arr_check.add(ck);
                        }

                        ResponseDevice d = new ResponseDevice(id, md, type, weight, producer, pilotLicense, arr_check,active);
                        System.out.println(d.toString());
                        arr_device.add(d);
                        arr_check.clear();
                    }

                    return arr_device;
                } else {
                    pt.close();
                    con.close();
                    System.out.println("select non andata a buon fine");
                    logger.info(String.format("Class:%s-Method:%s::END select no one device of pilot %s",
                            classe, method, owner));
                    return arr_device;
                }

            } catch (Exception e) {
                logger.error(String.format("Class:%s-Method:%s::ERROR", classe, method) + e);
                System.out.println(e);
                return arr_device;
            } finally {
                if (pt != null) {
                    pt.close();
                }

                if (con != null) {
                    con.close();
                }
            }

        }

}

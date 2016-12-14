package it.uniroma2.sapr.service;



import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.BindingType;
import org.apache.log4j.Logger;
import it.uniroma2.sapr.bean.RequestNote;
import it.uniroma2.sapr.bean.RequestPilot;
import it.uniroma2.sapr.bean.RequestSAPR;
import it.uniroma2.sapr.bean.ResponseCheckElement;
import it.uniroma2.sapr.bean.ResponseListPilots;
import it.uniroma2.sapr.bean.ResponsePilot;
import it.uniroma2.sapr.bean.ResponseSapr;
import it.uniroma2.sapr.persistence.DAOFactory;
import it.uniroma2.sapr.persistence.PilotDAO;
import it.uniroma2.sapr.pojo.Pilot;
import it.uniroma2.sapr.pojo.Device;
import it.uniroma2.sapr.bean.RequestCheckElement;
import it.uniroma2.sapr.bean.RequestDevice;
import it.uniroma2.sapr.bean.RequestFlightPlan;

import it.uniroma2.sapr.bean.ResponseNote;
import it.uniroma2.sapr.bean.ResponseDevice;
import it.uniroma2.sapr.bean.ResponseFlightPlan;
import it.uniroma2.sapr.persistence.DeviceDAO;
import it.uniroma2.sapr.persistence.FlightPlanDAO;
import it.uniroma2.sapr.persistence.MySQLDbDAOFactory;
import it.uniroma2.sapr.persistence.NoteDAO;
import it.uniroma2.sapr.persistence.SaprDAO;
import it.uniroma2.sapr.pojo.CheckElement;
import it.uniroma2.sapr.pojo.FlightPlan;
import it.uniroma2.sapr.pojo.Note;
import it.uniroma2.sapr.pojo.Sapr;
import it.uniroma2.sapr.utility.Opzione;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        
        public ResponseNote getNote(@WebParam(name = "request")int idNote) throws Exception {
            String method = "getNote";
            logger.info(String.format("Class:%s-Method:%s::START", classe,method ));
            
            System.out.println("Passo1" + idNote);
            
            //Factory per il db
            DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
            NoteDAO noteDAO = mysqlFactory.getNoteDAO();
            
            System.out.println("Passo2");
            
            ResponseNote responseNote = new ResponseNote();
            
            System.out.println("Passo3");
            
            Note nota = noteDAO.selectNote(idNote);
            
            System.out.println("ALL: " + nota.toString());

            responseNote.setIdNote(nota.getIdNote());
            
            System.out.println("ID note: " + nota.getIdNote());
            
            responseNote.setTextNote(nota.getTextNote());
            responseNote.setDate(nota.getDate());  
            
            System.out.println("Passo5");
            System.out.println(responseNote.toString());
            
            logger.info(String.format("Class:%s-Method:%s::END", classe,method));
            return responseNote;
        }
	

  	public ArrayList<ResponseSapr> selectSaprOfPilotWithState(Opzione opzione, String pilotLicense) throws SQLException{
  		/**
	     * questo metodo prende in input un'op
	     * zione:
	     * - unable, tutti i sapr abilitati del pilota (active = 1)
	     * - disable, tutti i sapr disabilitati del pilota (active = 0)
	     * - all, tutti i sapr del pilota
	     *
	     * @param sapr è il bean contente tutti i dati da inserire nel db
	     * @return ArrayList<ResponseSapr> array di sapr
	     * @throws SQLException
	     */	
	    String method = "selectSapr";
	    Connection con = null;
	    PreparedStatement pt = null;
	    PreparedStatement pt1 = null;
	    ArrayList<ResponseSapr> arr_sapr = new ArrayList<ResponseSapr>();
	    String query;
	    
	    if(opzione.name().equalsIgnoreCase("ALL")){
	    	query = "SELECT idSapr, model, producer, " +
	    		    "weight, heavyweight, battery, maxDistance, maxHeight, pilotLicense, active FROM sapr WHERE pilotLicense = ? ";
	    }
	    
	    else{
	    	query = "SELECT idSapr, model, producer, " +
	    		    "weight, heavyweight, battery, maxDistance, maxHeight, pilotLicense, active FROM sapr WHERE pilotLicense = ? AND active = ? ";
	    }
	    
	    
	    String query1 = "SELECT valueCheckElement FROM checkSAPR WHERE idSapr = ?";
    
	    try {
	        //logger per segnalare l'inizio della scrittura del metodo
	        logger.info(String.format("Class:%s-Method:%s::START with dates %s", classe, method, pilotLicense));
	
	        con = MySQLDbDAOFactory.createConnection();
	        pt = con.prepareStatement(query);
	        pt1 = con.prepareStatement(query1);
	  
	        //compilo il primo campo ? nella query
	        pt.setString(1, pilotLicense);
	        
	        //compilo il secondo campo ? nella query, in base alla richiesta
	        if(opzione.name().equalsIgnoreCase("ENABLED"))
	        	pt.setInt(2, 1);
	        
	        if(opzione.name().equalsIgnoreCase("DISABLED"))
	        	pt.setInt(2, 0);
	        
	        // eseguo la query
	        ResultSet rs = pt.executeQuery();
	        if (rs != null) {
	        	
	        	if(opzione.name().equalsIgnoreCase("ENABLED"))
	        		logger.info(String.format("Class:%s-Method:%s::END select enabled sapr of pilot %s",
	        				classe, method, pilotLicense));
	        	
	        	if(opzione.name().equalsIgnoreCase("DISABLED"))
	        		logger.info(String.format("Class:%s-Method:%s::END select disabled sapr of pilot %s",
	        				classe, method, pilotLicense));
	        	
	        	if(opzione.name().equalsIgnoreCase("ALL"))
	        		logger.info(String.format("Class:%s-Method:%s::END select all sapr of pilot %s",
	        				classe, method, pilotLicense));
	
	            while (rs.next()) {
	            	ResponseSapr rispSapr = new ResponseSapr();
	            	ArrayList<ResponseCheckElement> checkSapr = new ArrayList<ResponseCheckElement>();
	            	
	            	//recupero la licenza dalla query 
					int idS = rs.getInt("idSapr");
					rispSapr.setIdSapr(idS);
					String model = rs.getString("model");
	                rispSapr.setModel(model);
	                String producer = rs.getString("producer");
	                rispSapr.setProducer(producer);
	                String battery = rs.getString("battery");
	                rispSapr.setBattery(battery);
	                int weight = rs.getInt("weight");
	                rispSapr.setWeight(weight);
	                int heavyweight = rs.getInt("heavyweight");
	                rispSapr.setHeavyweight(heavyweight);
	                int maxDistance = rs.getInt("maxDistance");
	                rispSapr.setMaxDistance(maxDistance);
	                int maxHeight = rs.getInt("maxHeight");
	                rispSapr.setMaxHeight(maxHeight);
	                String license = rs.getString("pilotLicense");
	                rispSapr.setPilotLicense(license);
	                int active = rs.getInt("active");
	                rispSapr.setActive(active);
	                   
	                pt1.setInt(1, idS);
	                ResultSet rs1 = pt1.executeQuery();
	                
	                while(rs1.next()){	
	                	String valore = rs1.getString("valueCheckElement");
	                	checkSapr.add(new ResponseCheckElement(valore));
	                }
	                
	                rispSapr.setCheckSapr(checkSapr);      
	                arr_sapr.add(rispSapr);
	                
	            }
	
	            return arr_sapr;
	            
	        } else {
	        	
	            pt.close();
	            pt1.close();
	            con.close();
	            logger.info(String.format("Class:%s-Method:%s::END select no one sapr of pilot %s",
	                    classe, method, pilotLicense));
	            return arr_sapr;
	        }
	
	    } catch (Exception e) {
	    	
	    	logger.error(String.format("Class:%s-Method:%s::ERROR", classe, method) + e);
	        return arr_sapr;
	        
	    }finally {
    	
	        if (pt != null) 
	            pt.close();
	        
	        if (pt1 != null) 
	            pt.close();
	        
	        if (con != null) 
	            con.close();
        
    	}

  	}
  	
	/*seleziona tutti i sapr appartenenti al pilota specificato*/
	
	public ArrayList<ResponseSapr> selectSaprOfPilot(String owner) throws SQLException {		
		/**
	     * questo metodo prende in input la licenza del pilota e restituisce tutti
	     * i sapr a sua disposizione
	     *
	     * @param sapr è il bean contente tutti i dati da inserire nel db
	     * @return ArrayList<Sapr> array di sapr
	     * @throws SQLException
	     */	
	    String method = "selectSapr";
	    Connection con = null;
	    PreparedStatement pt = null;
	    PreparedStatement pt1 = null;
	    ArrayList<ResponseSapr> arr_sapr = new ArrayList<ResponseSapr>();
	
	    String query = "SELECT idSapr, model, producer, " +
	    "weight, heavyweight, battery, maxDistance, maxHeight, pilotLicense, active FROM sapr WHERE pilotLicense = ? ";
	    
	    String query1 = "SELECT valueCheckElement FROM checkSAPR WHERE idSapr = ?";
    
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
	
	            logger.info(String.format("Class:%s-Method:%s::END select all sapr of pilot %s",
	                    classe, method, owner));
	
	            while (rs.next()) {
	            	ResponseSapr rispSapr = new ResponseSapr();
	            	ArrayList<ResponseCheckElement> checkSapr = new ArrayList<ResponseCheckElement>();
	            	
	            	//recupero la licenza dalla query 
					int idS = rs.getInt("idSapr");
					rispSapr.setIdSapr(idS);
					String model = rs.getString("model");
	                rispSapr.setModel(model);
	                String producer = rs.getString("producer");
	                rispSapr.setProducer(producer);
	                String battery = rs.getString("battery");
	                rispSapr.setBattery(battery);
	                int weight = rs.getInt("weight");
	                rispSapr.setWeight(weight);
	                int heavyweight = rs.getInt("heavyweight");
	                rispSapr.setHeavyweight(heavyweight);
	                int maxDistance = rs.getInt("maxDistance");
	                rispSapr.setMaxDistance(maxDistance);
	                int maxHeight = rs.getInt("maxHeight");
	                rispSapr.setMaxHeight(maxHeight);
	                String license = rs.getString("pilotLicense");
	                rispSapr.setPilotLicense(license);
	                int active = rs.getInt("active");
	                rispSapr.setActive(active);
	                   
	                pt1.setInt(1, idS);
	                ResultSet rs1 = pt1.executeQuery();
	                
	                while(rs1.next()){	
	                	String valore = rs1.getString("valueCheckElement");
	                	checkSapr.add(new ResponseCheckElement(valore));
	                }
	                
	                rispSapr.setCheckSapr(checkSapr);      
	                arr_sapr.add(rispSapr);
	                
	            }
	
	            return arr_sapr;
	            
	        } else {
	        	
	            pt.close();
	            pt1.close();
	            con.close();
	            logger.info(String.format("Class:%s-Method:%s::END select no one sapr of pilot %s",
	                    classe, method, owner));
	            return arr_sapr;
	        }
	
	    } catch (Exception e) {
	    	
	    	logger.error(String.format("Class:%s-Method:%s::ERROR", classe, method) + e);
	        return arr_sapr;
	        
	    }finally {
    	
	        if (pt != null) 
	            pt.close();
	        
	        if (pt1 != null) 
	            pt.close();
	        
	        if (con != null) 
	            con.close();
        
    	}

	}
 
	public ResponseSapr selectSapr(int idSapr) throws SQLException {
		
		String method = "selectSapr";
		Connection con = null;
		PreparedStatement pt = null;
		PreparedStatement pt1 = null;
		ResponseSapr rispSapr = new ResponseSapr();
		String query = "SELECT idSapr, model, producer, weight, heavyweight, battery, maxDistance, " +
        	    " maxHeight, pilotLicense, active FROM sapr WHERE idSapr = ?";
		String query1 = "SELECT valueCheckElement FROM checkSAPR WHERE idSapr = ? ";
						
		try {
			//logger per segnalare l'inizio della scrittura del metodo
			logger.info(String.format("Class:%s-Method:%s::START with dates %s", classe,method,idSapr));
			System.out.println(String.format("Class:%s-Method:%s::START with dates %s", classe,method,idSapr));

			con = MySQLDbDAOFactory.createConnection();
			pt = con.prepareStatement(query);
			pt1 = con.prepareStatement(query1);
			
			//compilo i campi ? nella query
			pt.setInt(1, idSapr);
			pt1.setInt(1, idSapr);
			
			//eseguo la query
			ResultSet rs = pt.executeQuery();
			ResultSet rs1 = pt1.executeQuery();
			ArrayList<ResponseCheckElement> checkSapr = new ArrayList<ResponseCheckElement>();
			
			if(rs != null && rs1 != null){
				rs.next();
				
                System.out.println("select andata a buon fine");
				logger.info(String.format("Class:%s-Method:%s::END select sapr with id-%s", //
						classe,method,idSapr));
				System.out.println(String.format("Class:%s-Method:%s::START with dates %s", classe,method,idSapr));
				
				//recupero la licenza dalla query 
				int idS = rs.getInt("idSapr");
				rispSapr.setIdSapr(idS);
				String model = rs.getString("model");
                rispSapr.setModel(model);
                String producer = rs.getString("producer");
                rispSapr.setProducer(producer);
                String battery = rs.getString("battery");
                rispSapr.setBattery(battery);
                int weight = rs.getInt("weight");
                rispSapr.setWeight(weight);
                int heavyweight = rs.getInt("heavyweight");
                rispSapr.setHeavyweight(heavyweight);
                int maxDistance = rs.getInt("maxDistance");
                rispSapr.setMaxDistance(maxDistance);
                int maxHeight = rs.getInt("maxHeight");
                rispSapr.setMaxHeight(maxHeight);
                String license = rs.getString("pilotLicense");
                rispSapr.setPilotLicense(license);
                int active = rs.getInt("active");
                rispSapr.setActive(active);
                
                String valore;
                
                while(rs1.next()){
                	valore = rs1.getString("valueCheckElement");
                	checkSapr.add(new ResponseCheckElement(valore));
                }
                    
                rispSapr.setCheckSapr(checkSapr);
                
                return rispSapr;
                	
			}else {
				
				pt.close();
				pt1.close();
				con.close();
				System.out.println("male");
				logger.info(String.format("Class:%s-Method:%s::END not select sapr with id code-%s", //
						classe,method,idSapr));
				return rispSapr;
			}
			
		} catch (Exception e) {
			logger.error(String.format("Class:%s-Method:%s::ERROR", classe,method) + e);
			System.out.println("Si è verificato il seguente errore: " + e.toString());
			return null;
		} finally {
			
			if (pt != null) {
				pt.close();
			}
			
			if (pt1 != null) {
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
            ResponseDevice rispDev = new ResponseDevice();


            String query = "SELECT idDevice, model, type, weight, producer, pilotLicense,active"
                    + " FROM device WHERE idDevice = ?";
            String query1 = "SELECT valueCheckElement,IdDevice FROM checkDevice WHERE IdDevice = ?";

            
            try {
                
                //logger per segnalare l'inizio della scrittura del metodo
                logger.info(String.format("Class:%s-Method:%s::START with dates %s", classe,method,idDevice));
                System.out.println(String.format("Class:%s-Method:%s::START with dates %s", classe,method,idDevice));

                con = MySQLDbDAOFactory.createConnection();
                pt = con.prepareStatement(query);
                pt1 = con.prepareStatement(query1);

                //compilo i campi ? nella query
                pt.setInt(1, idDevice);
                pt1.setInt(1, idDevice);

                //eseguo la query
                ResultSet rs = pt.executeQuery();
                ResultSet rs1 = pt1.executeQuery();
                ArrayList<ResponseCheckElement> checkDevice = new ArrayList<ResponseCheckElement>();

                if(rs != null && rs1 != null){
                    rs.next();

                    System.out.println("select andata a buon fine");
                    logger.info(String.format("Class:%s-Method:%s::END select device with id-%s", //
                    classe,method,idDevice));
                    System.out.println(String.format("Class:%s-Method:%s::START with dates %s", classe,method,idDevice));

                    //recupero la licenza dalla query 
                    int idD = rs.getInt("idDevice");
                    rispDev.setIdDevice(idD);
                    String model = rs.getString("model");
                    rispDev.setModel(model);
                    String type = rs.getString("type");
                    rispDev.setType(type);
                    int weight = rs.getInt("weight");
                    rispDev.setWeight(weight);
                    String producer = rs.getString("producer");
                    rispDev.setProducer(producer);
                    String license = rs.getString("pilotLicense");
                    rispDev.setPilotLicense(license);
                    int active = rs.getInt("active");
                    rispDev.setActive(active);

                    String valore;

                    while(rs1.next()){
                            valore = rs1.getString("valueCheckElement");
                            checkDevice.add(new ResponseCheckElement(valore));
                    }

                    rispDev.setCheckDevice(checkDevice);
                    return rispDev;	
                }else {
                    pt.close();
                    pt1.close();
                    con.close();
                    System.out.println("Select non andata a buon fine");
                    logger.info(String.format("Class:%s-Method:%s::END not select device with id code-%s", //
                    classe,method,idDevice));
                    return rispDev;
                }
			
            } catch (Exception e) {
                logger.error(String.format("Class:%s-Method:%s::ERROR", classe,method) + e);
                System.out.println("Si è verificato il seguente errore: " + e.toString());
                return null;
            } finally {
                if (pt != null) {
                    pt.close();
                }
                if (pt1 != null) {
                    pt1.close();
                }
                if (con != null) {
                    con.close();
                }
            }
        }


        public ArrayList<ResponseDevice> selectEnableDevice(Opzione op, String pilotLicense) throws SQLException {
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

            String query = "SELECT idDevice, model, type, weight, producer, pilotLicense, active"
                    + " FROM device WHERE pilotLicense = ?";

            if (op.name().equalsIgnoreCase("ENABLED")){
                method = "selectDeviceEnabled";
                query += " AND active = 1";
            }else if (op.name().equalsIgnoreCase("DISABLED")) {
                method = "selectDeviceDisabled";
                query += " AND active = 0";
            }else if (op.name().equalsIgnoreCase("ALL")) {
                    
            }
	    
	    String query1 = "SELECT valueCheckElement FROM checkDevice WHERE IdDevice = ?";
    
	    try {
	        //logger per segnalare l'inizio della scrittura del metodo
	        logger.info(String.format("Class:%s-Method:%s::START with dates %s", classe, method, pilotLicense));
	
	        con = MySQLDbDAOFactory.createConnection();
	        pt = con.prepareStatement(query);
	        pt1 = con.prepareStatement(query1);
	  
	        //compilo il primo campo ? nella query
	        pt.setString(1, pilotLicense);
	        
	        // eseguo la query
	        ResultSet rs = pt.executeQuery();
	        if (rs != null) {
                    if(op.name().equalsIgnoreCase("ENABLED"))
                        logger.info(String.format("Class:%s-Method:%s::END select enabled device of pilot %s",
                        classe, method, pilotLicense));

                    if(op.name().equalsIgnoreCase("DISABLED"))
                        logger.info(String.format("Class:%s-Method:%s::END select disabled device of pilot %s",
                        classe, method, pilotLicense));

                    if(op.name().equalsIgnoreCase("ALL"))
                        logger.info(String.format("Class:%s-Method:%s::END select all device of pilot %s",
                        classe, method, pilotLicense));

	            while (rs.next()) {
                        ResponseDevice rispDev = new ResponseDevice();
	            	ArrayList<ResponseCheckElement> checkDevice = new ArrayList<ResponseCheckElement>();
	            	
	            	//recupero la licenza dalla query 
                        int idD = rs.getInt("idDevice");
                        rispDev.setIdDevice(idD);
                        String model = rs.getString("model");
                        rispDev.setModel(model);
                        String type = rs.getString("type");
                        rispDev.setType(type);
                        int weight = rs.getInt("weight");
                        rispDev.setWeight(weight);
                        String producer = rs.getString("producer");
                        rispDev.setProducer(producer);
                        String license = rs.getString("pilotLicense");
                        rispDev.setPilotLicense(license);
                        int active = rs.getInt("active");
                        rispDev.setActive(active);
	                   
	                pt1.setInt(1, idD);
	                ResultSet rs1 = pt1.executeQuery();
	                
	                while(rs1.next()){	
                            String valore = rs1.getString("valueCheckElement");
                            checkDevice.add(new ResponseCheckElement(valore));
	                }
	                rispDev.setCheckDevice(checkDevice);      
	                arr_device.add(rispDev);   
	            }
	            return arr_device;
	            
	        } else {	
	            pt.close();
	            pt1.close();
	            con.close();
	            logger.info(String.format("Class:%s-Method:%s::END select no one sapr of pilot %s",
	                    classe, method, pilotLicense));
	            return arr_device;
	        }
	    } catch (Exception e) {
	    	logger.error(String.format("Class:%s-Method:%s::ERROR", classe, method) + e);
	        return arr_device;
	    }finally {
	        if (pt != null) 
	            pt.close();
	        if (pt1 != null) 
	            pt1.close();
	        if (con != null) 
	            con.close();
            }
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

    public ResponseFlightPlan selectFlightPlanByFlight(int idSapr,String pilotLicense,String dateDeparture) throws SQLException{
                System.out.println("funzione selectFlightPlanByFlightPlan(int idSapr,String pilotLicense,String dateDeparture)");
		String method = "selectFlightPlan";
                ArrayList<Device> array=new ArrayList<Device>();
		Connection con = null;
		PreparedStatement pt = null;
                ResultSet rs=null;
		String query = "SELECT destination,departure,dateDeparture,timeDeparture,nowArriving,idSapr,idNote,flightplan.idDevice,flightplan.pilotLicense,model,type,weight,producer,active from flightplan,device WHERE idsapr=? and flightplan.pilotLicense=? and dateDeparture=? and flightplan.iddevice=device.iddevice;";
                System.out.println(query);
		try {
                    
			//logger per segnalare l'inizio della scrittura del metodo
			logger.info(String.format("Class:%s-Method:%s::START with dates idSapr=%s,pilotLicense=%s,dateDeparture=%s", classe,method,idSapr,pilotLicense,dateDeparture));
			
			con = MySQLDbDAOFactory.createConnection();
			pt = con.prepareStatement(query);
			pt.setInt(1,idSapr);
			pt.setString(2,pilotLicense);
			pt.setString(3,dateDeparture);
                        System.out.println(pt.toString());
                        rs = pt.executeQuery();
			//esito della query
			if(rs!=null){
                                //da completare la query es:select  destination,departure,dateDeparture,timeDeparture,nowArriving,idSapr,idNote,flightplan.idDevice,flightplan.pilotLicense,model,type,weight,producer from flightplan,device where idsapr=2 and flightplan.pilotlicense="0000000002" and datedeparture="2016-09-11"and flightplan.iddevice=device.iddevice;
                                //deve creare prima glio oggetti Device e poi creare un oggetto di tipo ResponseFlightPlan altrimenti non potri passare un arrayList di Device
                                System.out.println("Ho selezionato il flightPlan");
				logger.info(String.format("Class:%s-Method:%s::END select flight plan with idSapr code-%s",classe,method,idSapr));
        
                                while(rs.next()){ 
                                    array.add(new Device(rs.getInt("iddevice"),rs.getString("model"),rs.getString("type"),rs.getInt("weight"),rs.getString("producer"),rs.getString("pilotlicense"),null, rs.getInt("active")));
                                }
                                rs.first(); 
                                ResponseFlightPlan flight1 = new ResponseFlightPlan(rs.getString("destination"),rs.getString("departure"),rs.getString("datedeparture"),rs.getString("timeDeparture"),rs.getString("nowarriving"),rs.getInt("idsapr"),rs.getInt("idNote"),array,rs.getString("pilotLicense"));
                                return flight1;
                        }
                        else {
				pt.close();
				con.close();
				System.out.println("Non ho selezionato il flightPlan");
				logger.info(String.format("Class:%s-Method:%s::END dont select flight plan with idSapr code-%s",classe,method,idSapr));
				return null;
			}
			
                        } catch (Exception e) {
                                logger.error(String.format("Class:%s-Method:%s::ERROR", classe,method) + e);
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
    
    public ArrayList<ResponseFlightPlan> selectFlightPlanBySapr(int idSapr) throws SQLException{
                System.out.println("funzione selectFlightPlanBySapr(int idSapr)");
		String method = "selectFlightPlan";
		Connection con = null;
		PreparedStatement pt = null;
                ResultSet rs=null;
                ArrayList<ResponseFlightPlan> array=new ArrayList<ResponseFlightPlan>();
		String query = "SELECT dateDeparture,idSapr,pilotLicense FROM flightplan WHERE idsapr=? group by datedeparture";
                System.out.println(query);
		try {
                    
			//logger per segnalare l'inizio della scrittura del metodo
			logger.info(String.format("Class:%s-Method:%s::START with dates idSapr=%s", classe,method,idSapr));
			
			con = MySQLDbDAOFactory.createConnection();
			pt = con.prepareStatement(query);
			pt.setInt(1,idSapr);
                        System.out.println(pt.toString());
                        rs = pt.executeQuery();
			//esito della query
			if(rs!=null){
                                System.out.println("Ho selezionato i flightPlan");
				logger.info(String.format("Class:%s-Method:%s::END select flight plan with idSapr code-%s",classe,method,idSapr));
                                while(rs.next()){  
                                    array.add(selectFlightPlanByFlight(rs.getInt("idsapr"),rs.getString("pilotLicense"),rs.getString("datedeparture")));
                                    //array.add(new ResponseFlightPlan(rs.getString("destination"),rs.getString("departure"),rs.getString("datedeparture"),rs.getString("timeDeparture"),rs.getString("nowarriving"),rs.getInt("idsapr"),rs.getInt("idNote"),rs.getInt("iddevice"),rs.getString("pilotLicense")));       
                                }
                                return array;
                        }
                        else {
				pt.close();
				con.close();
				System.out.println("Non ho selezionato i flightPlan");
				logger.info(String.format("Class:%s-Method:%s::END dont select flight plan with idSapr code-%s",classe,method,idSapr));
				return null;
			}
			
                        } catch (Exception e) {
                                logger.error(String.format("Class:%s-Method:%s::ERROR", classe,method) + e);
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

	public ResponsePilot getPilot(String licensePilots) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}

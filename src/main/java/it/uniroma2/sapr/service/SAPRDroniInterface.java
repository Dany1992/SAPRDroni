package it.uniroma2.sapr.service;

import javax.jws.WebParam;
import javax.jws.WebService;

import it.uniroma2.sapr.bean.RequestNote;
import it.uniroma2.sapr.bean.RequestPilot;
import it.uniroma2.sapr.bean.RequestSAPR;
<<<<<<< HEAD:src/main/java/it/uniroma2/sapr/service/SAPRDroniInterface.java
import it.uniroma2.sapr.bean.RequestDevice;
=======
import it.uniroma2.sapr.bean.RequestFlightPlan;
>>>>>>> dindiBranch:src/main/java/it/uniroma2/sapr/service/SAPRServiceInterface.java

@WebService
public interface SAPRDroniInterface {
	
	public Boolean requestManagerPilot(@WebParam(name="request")RequestPilot request) throws Exception;
	
	public Boolean requestManagerSAPR(@WebParam(name="request")RequestSAPR request) throws Exception;
        
        public Boolean requestManagerFlightPlan(@WebParam(name="request")RequestFlightPlan request) throws Exception;
	
	public Boolean requestManagerDevice(@WebParam(name="request")RequestDevice request) throws Exception;

	public Boolean requestManagerNote(@WebParam(name="request")RequestNote request) throws Exception;

}

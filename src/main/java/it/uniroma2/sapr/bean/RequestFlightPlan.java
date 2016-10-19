/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.uniroma2.sapr.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author pierfrancescotommasino
 * Questa classe è il bean che viene ricevuto dal webService attraverso il webMethod
 */

@XmlRootElement(name="RequestManagerFlightPlan")
@XmlAccessorType(XmlAccessType.FIELD)
public class RequestFlightPlan extends Request{

	@XmlElement(name = "destinations")
	private String destinations;

	@XmlElement(name = "departure")
	private String departure;

	@XmlElement(name = "dateDeparture")
	private String dateDeparture;
   
	@XmlElement(name = "timeDeparture")
        private String timeDeparture;
	
	@XmlElement(name = "nowArriving")
	private String nowArriving;

	@XmlElement(name = "idSapr")
	private int idSapr;
	
	@XmlElement(name = "idNote")
	private int idNote;
	
	@XmlElement(name = "idDevice")
	private int idDevice;
	
	@XmlElement(name = "pilotLicense")
	private String pilotLicense;

    public RequestFlightPlan() {
		super();
		// TODO Auto-generated constructor stub
    }
    public RequestFlightPlan(String destinations, String departure, String dateDeparture, String timeDeparture, String nowArriving, int idSapr, int idNote, int idDevice,Request.operation op) {
        this.destinations = destinations;
        this.departure = departure;
        this.dateDeparture = dateDeparture;
        this.timeDeparture = timeDeparture;
        this.nowArriving = nowArriving;
        this.idSapr = idSapr;
        this.idNote = idNote;
        this.idDevice = idDevice;
        this.op=op;
    }

    public String getDestinations() {
        return destinations;
    }

    public void setDestinations(String destinations) {
        this.destinations = destinations;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDateDeparture() {
        return dateDeparture;
    }

    public void setDateDeparture(String dateDeparture) {
        this.dateDeparture = dateDeparture;
    }

    public String getTimeDeparture() {
        return timeDeparture;
    }

    public void setTimeDeparture(String timeDeparture) {
        this.timeDeparture = timeDeparture;
    }

    public String getNowArriving() {
        return nowArriving;
    }

    public void setNowArriving(String nowArriving) {
        this.nowArriving = nowArriving;
    }

    public int getIdSapr() {
        return idSapr;
    }

    public void setIdSapr(int idSapr) {
        this.idSapr = idSapr;
    }

    public int getIdNote() {
        return idNote;
    }

    public void setIdNote(int idNote) {
        this.idNote = idNote;
    }

    public int getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(int idDevice) {
        this.idDevice = idDevice;
    }

    public String getPilotLicense() {
        return pilotLicense;
    }

    public void setPilotLicense(String pilotLicense) {
        this.pilotLicense = pilotLicense;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.destinations != null ? this.destinations.hashCode() : 0);
        hash = 53 * hash + (this.departure != null ? this.departure.hashCode() : 0);
        hash = 53 * hash + (this.dateDeparture != null ? this.dateDeparture.hashCode() : 0);
        hash = 53 * hash + (this.timeDeparture != null ? this.timeDeparture.hashCode() : 0);
        hash = 53 * hash + (this.nowArriving != null ? this.nowArriving.hashCode() : 0);
        hash = 53 * hash + this.idSapr;
        hash = 53 * hash + this.idNote;
        hash = 53 * hash + this.idDevice;
        hash = 53 * hash + (this.pilotLicense != null ? this.pilotLicense.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RequestFlightPlan other = (RequestFlightPlan) obj;
        if (this.idSapr != other.idSapr) {
            return false;
        }
        if (this.idNote != other.idNote) {
            return false;
        }
        if (this.idDevice != other.idDevice) {
            return false;
        }
        if ((this.destinations == null) ? (other.destinations != null) : !this.destinations.equals(other.destinations)) {
            return false;
        }
        if ((this.departure == null) ? (other.departure != null) : !this.departure.equals(other.departure)) {
            return false;
        }
        if ((this.dateDeparture == null) ? (other.dateDeparture != null) : !this.dateDeparture.equals(other.dateDeparture)) {
            return false;
        }
        if ((this.timeDeparture == null) ? (other.timeDeparture != null) : !this.timeDeparture.equals(other.timeDeparture)) {
            return false;
        }
        if ((this.nowArriving == null) ? (other.nowArriving != null) : !this.nowArriving.equals(other.nowArriving)) {
            return false;
        }
        if ((this.pilotLicense == null) ? (other.pilotLicense != null) : !this.pilotLicense.equals(other.pilotLicense)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RequestFlightPlan{" + "destinations=" + destinations + ", departure=" + departure + ", dateDeparture=" + dateDeparture + ", timeDeparture=" + timeDeparture + ", nowArriving=" + nowArriving + ", idSapr=" + idSapr + ", idNote=" + idNote + ", idDevice=" + idDevice + ", pilotLicense=" + pilotLicense +"op="+op+'}';
    }
           
    
}

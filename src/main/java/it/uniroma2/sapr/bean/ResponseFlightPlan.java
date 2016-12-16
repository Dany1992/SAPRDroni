/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.uniroma2.sapr.bean;
import it.uniroma2.sapr.pojo.Device;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe da passare al Web Client
 * @author pierfrancescotommasino
 */
@XmlRootElement(name="ResponseFlightPlan")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseFlightPlan {
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
	
	@XmlElement(name = "devices")
	private ArrayList<Integer> devices;
	
	@XmlElement(name = "pilotLicense")
	private String pilotLicense;

	
    public ResponseFlightPlan(String destinations, String departure, String dateDeparture, String timeDeparture, String nowArriving, int idSapr, int idNote, ArrayList<Integer> devices, String pilotLicense) {
        this.destinations = destinations;
        this.departure = departure;
        this.dateDeparture = dateDeparture;
        this.timeDeparture = timeDeparture;
        this.nowArriving = nowArriving;
        this.idSapr = idSapr;
        this.idNote = idNote;
        this.devices = devices;
        this.pilotLicense = pilotLicense;
    }
    
    public ResponseFlightPlan(){
    	
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

    public ArrayList<Integer> getDevices() {
        return devices;
    }

    public void setDevices(ArrayList<Integer> devices) {
        this.devices = devices;
    }

    public String getPilotLicense() {
        return pilotLicense;
    }

    public void setPilotLicense(String pilotLicense) {
        this.pilotLicense = pilotLicense;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + (this.destinations != null ? this.destinations.hashCode() : 0);
        hash = 73 * hash + (this.departure != null ? this.departure.hashCode() : 0);
        hash = 73 * hash + (this.dateDeparture != null ? this.dateDeparture.hashCode() : 0);
        hash = 73 * hash + (this.timeDeparture != null ? this.timeDeparture.hashCode() : 0);
        hash = 73 * hash + (this.nowArriving != null ? this.nowArriving.hashCode() : 0);
        hash = 73 * hash + this.idSapr;
        hash = 73 * hash + this.idNote;
        hash = 73 * hash + (this.devices != null ? this.devices.hashCode() : 0);
        hash = 73 * hash + (this.pilotLicense != null ? this.pilotLicense.hashCode() : 0);
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
        final ResponseFlightPlan other = (ResponseFlightPlan) obj;
        if (this.idSapr != other.idSapr) {
            return false;
        }
        if (this.idNote != other.idNote) {
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
        if (this.devices != other.devices && (this.devices == null || !this.devices.equals(other.devices))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResponseFlightPlan{" + "destinations=" + destinations + ", departure=" + departure + ", dateDeparture=" + dateDeparture + ", timeDeparture=" + timeDeparture + ", nowArriving=" + nowArriving + ", idSapr=" + idSapr + ", idNote=" + idNote + ", devices=" + devices + ", pilotLicense=" + pilotLicense + '}';
    }
    
 
        
}

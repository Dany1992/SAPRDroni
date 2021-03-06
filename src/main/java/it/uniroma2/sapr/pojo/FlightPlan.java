/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.uniroma2.sapr.pojo;

import java.util.ArrayList;

/**
 * Questa classe è l'oggetto che viene creato prelevando i dati dalla RequestFlightPlain.
 * Tale oggetto viene utilizzato per le operazioni CRUD sul db;
 * @author pierfrancescotommasino
 * @mail tommasinofrancesco@hotmail.it
 */
public class FlightPlan{
    private String destination;
    private String departure;
    private String dateDeparture;
    private String timeDeparture;
    private String nowArriving;
    private int idSapr;
    private int idNote;
    private String pilotLicense;
    private ArrayList<Integer> devices;

    public FlightPlan(String destination, String departure, String dateDeparture, String timeDeparture, String nowArriving, int idSapr, int idNote,String pilotLicense, ArrayList<Integer> devices) {
        this.destination = destination;
        this.departure = departure;
        this.dateDeparture = dateDeparture;
        this.timeDeparture = timeDeparture;
        this.nowArriving = nowArriving;
        this.idSapr = idSapr;
        this.idNote = idNote;
        this.pilotLicense = pilotLicense;
        this.devices = devices;
    } 

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
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


    public String getPilotLicense() {
        return pilotLicense;
    }

    public void setPilotLicense(String pilotLicense) {
        this.pilotLicense = pilotLicense;
    }

    public ArrayList<Integer> getDevices() {
        return devices;
    }

    public void setDevices(ArrayList<Integer> devices) {
        this.devices = devices;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.destination != null ? this.destination.hashCode() : 0);
        hash = 89 * hash + (this.departure != null ? this.departure.hashCode() : 0);
        hash = 89 * hash + (this.dateDeparture != null ? this.dateDeparture.hashCode() : 0);
        hash = 89 * hash + (this.timeDeparture != null ? this.timeDeparture.hashCode() : 0);
        hash = 89 * hash + (this.nowArriving != null ? this.nowArriving.hashCode() : 0);
        hash = 89 * hash + this.idSapr;
        hash = 89 * hash + this.idNote;
        hash = 89 * hash + (this.pilotLicense != null ? this.pilotLicense.hashCode() : 0);
        hash = 89 * hash + (this.devices != null ? this.devices.hashCode() : 0);
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
        final FlightPlan other = (FlightPlan) obj;
        if (this.idSapr != other.idSapr) {
            return false;
        }
        if (this.idNote != other.idNote) {
            return false;
        }
        if ((this.destination == null) ? (other.destination != null) : !this.destination.equals(other.destination)) {
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
        return "FlightPlan{" + "destination=" + destination + ", departure=" + departure + ", dateDeparture=" + dateDeparture + ", timeDeparture=" + timeDeparture + ", nowArriving=" + nowArriving + ", idSapr=" + idSapr + ", idNote=" + idNote + ", pilotLicense=" + pilotLicense + ", devices=" + devices + '}';
    }


    
}


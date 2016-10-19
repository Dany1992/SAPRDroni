
package it.uniroma2.sapr.pojo;

import java.util.ArrayList;

/**
 * Questa classe è l'oggetto che viene creato prelevando i dati dalla RequestDevice.
 * Questo oggetto serve per le operazioni che faremo nel DB
 * 
 * @author dario
 */
public class Device {
    private int idDevice;
    private String model;
    private String type;
    private int weight;
    private String producer;
    private String pilotLicense;
    private ArrayList<CheckElement> checkDevice;
    private int active;

    public Device(int idDevice, String model, String type, int weight, String producer, String pilotLicense, ArrayList<CheckElement> checkDevice, int active) {
        this.idDevice = idDevice;
        this.model = model;
        this.type = type;
        this.weight = weight;
        this.producer = producer;
        this.pilotLicense = pilotLicense;
        this.checkDevice = checkDevice;
        this.active = active;
    }

    public int getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(int idDevice) {
        this.idDevice = idDevice;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getPilotLicense() {
        return pilotLicense;
    }

    public void setPilotLicense(String pilotLicense) {
        this.pilotLicense = pilotLicense;
    }

    public ArrayList<CheckElement> getCheckDevice() {
        return checkDevice;
    }

    public void setCheckDevice(ArrayList<CheckElement> checkDevice) {
        this.checkDevice = checkDevice;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.idDevice;
        hash = 67 * hash + (this.model != null ? this.model.hashCode() : 0);
        hash = 67 * hash + (this.type != null ? this.type.hashCode() : 0);
        hash = 67 * hash + this.weight;
        hash = 67 * hash + (this.producer != null ? this.producer.hashCode() : 0);
        hash = 67 * hash + (this.pilotLicense != null ? this.pilotLicense.hashCode() : 0);
        hash = 67 * hash + (this.checkDevice != null ? this.checkDevice.hashCode() : 0);
        hash = 67 * hash + this.active;
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
        final Device other = (Device) obj;
        if (this.idDevice != other.idDevice) {
            return false;
        }
        if (this.weight != other.weight) {
            return false;
        }
        if (this.active != other.active) {
            return false;
        }
        if ((this.model == null) ? (other.model != null) : !this.model.equals(other.model)) {
            return false;
        }
        if ((this.type == null) ? (other.type != null) : !this.type.equals(other.type)) {
            return false;
        }
        if ((this.producer == null) ? (other.producer != null) : !this.producer.equals(other.producer)) {
            return false;
        }
        if ((this.pilotLicense == null) ? (other.pilotLicense != null) : !this.pilotLicense.equals(other.pilotLicense)) {
            return false;
        }
        if (this.checkDevice != other.checkDevice && (this.checkDevice == null || !this.checkDevice.equals(other.checkDevice))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Device{" + "idDevice=" + idDevice + ", model=" + model + ", type=" + type + ", weight=" + weight + ", producer=" + producer + ", pilotLicense=" + pilotLicense + ", checkDevice=" + checkDevice + ", active=" + active + '}';
    }

    
 }

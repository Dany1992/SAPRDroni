
package it.uniroma2.sapr.bean;

import it.uniroma2.sapr.pojo.CheckElement;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Questa è la classe da passare al Web Client
 * @author dario
 */

@XmlRootElement(name="ResponseDevice")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseDevice {
    @XmlElement(name = "idDevice")
    private int idDevice;
    
    @XmlElement(name = "model")
    private String model;
    
    @XmlElement(name = "type")
    private String type;
    
    @XmlElement(name = "weight")
    private int weight;
    
    @XmlElement(name = "producer")
    private String producer;
    
    @XmlElement(name = "pilotLicense")
    private String pilotLicense;

    @XmlElement(name = "checkDevice")
    private ArrayList<CheckElement> checkDevice;

    public ResponseDevice(int idDevice, String model, String type, int weight, String producer, String pilotLicense, ArrayList<CheckElement> checkDevice) {
        this.idDevice = idDevice;
        this.model = model;
        this.type = type;
        this.weight = weight;
        this.producer = producer;
        this.pilotLicense = pilotLicense;
        this.checkDevice = checkDevice;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.idDevice;
        hash = 97 * hash + (this.model != null ? this.model.hashCode() : 0);
        hash = 97 * hash + (this.type != null ? this.type.hashCode() : 0);
        hash = 97 * hash + this.weight;
        hash = 97 * hash + (this.producer != null ? this.producer.hashCode() : 0);
        hash = 97 * hash + (this.pilotLicense != null ? this.pilotLicense.hashCode() : 0);
        hash = 97 * hash + (this.checkDevice != null ? this.checkDevice.hashCode() : 0);
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
        final ResponseDevice other = (ResponseDevice) obj;
        if (this.idDevice != other.idDevice) {
            return false;
        }
        if (this.weight != other.weight) {
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
        return "ResponseDevice{" + "idDevice=" + idDevice + ", model=" + model + ", type=" + type + ", weight=" + weight + ", producer=" + producer + ", pilotLicense=" + pilotLicense + ", checkDevice=" + checkDevice + '}';
    }
    
}

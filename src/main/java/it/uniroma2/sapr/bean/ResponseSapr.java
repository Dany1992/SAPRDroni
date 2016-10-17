package it.uniroma2.sapr.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="ResponseSapr")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseSapr {
    @XmlElement(name = "ID_SAPR")
    private int idSapr;
    
    @XmlElement(name = "MODEL_SAPR")
    private String model;
    
    @XmlElement(name = "PRODUCER")
    private String producer;
    
    @XmlElement(name = "WEIGHT_SAPR")
    private int weight;
    
    @XmlElement(name = "HEAVY_WEIGHT")
    private int heavyweight;
    
    @XmlElement(name = "BATTERY")
    private String battery;
    
    @XmlElement(name = "MAX_DISTANCE")
    private int maxDistance;
    
    @XmlElement(name = "MAX_HEIGHT")
    private int maxHeight;
    
    @XmlElement(name = "PILOTLICENSE")
    private String pilotLicense;

    public ResponseSapr(int idSapr, String model, String producer, int weight, int heavyweight, String battery, int maxDistance, int maxHeight, String pilotLicense) {
        this.idSapr = idSapr;
        this.model = model;
        this.producer = producer;
        this.weight = weight;
        this.heavyweight = heavyweight;
        this.battery = battery;
        this.maxDistance = maxDistance;
        this.maxHeight = maxHeight;
        this.pilotLicense = pilotLicense;
    }
    
    public ResponseSapr() {
		super();
		// TODO Auto-generated constructor stub
	}


    public int getIdSapr() {
		return idSapr;
	}



	public void setIdSapr(int idSapr) {
		this.idSapr = idSapr;
	}



	public String getModel() {
		return model;
	}



	public void setModel(String model) {
		this.model = model;
	}



	public String getProducer() {
		return producer;
	}



	public void setProducer(String producer) {
		this.producer = producer;
	}



	public int getWeight() {
		return weight;
	}



	public void setWeight(int weight) {
		this.weight = weight;
	}



	public int getHeavyweight() {
		return heavyweight;
	}



	public void setHeavyweight(int heavyweight) {
		this.heavyweight = heavyweight;
	}



	public String getBattery() {
		return battery;
	}



	public void setBattery(String battery) {
		this.battery = battery;
	}



	public int getMaxDistance() {
		return maxDistance;
	}



	public void setMaxDistance(int maxDistance) {
		this.maxDistance = maxDistance;
	}



	public int getMaxHeight() {
		return maxHeight;
	}



	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
	}



	public String getPilotLicense() {
		return pilotLicense;
	}



	public void setPilotLicense(String pilotLicense) {
		this.pilotLicense = pilotLicense;
	}



	@Override
    public int hashCode() {
        int hash = 9;
        hash = 52 * hash + this.idSapr;
        hash = 52 * hash + (this.model != null ? this.model.hashCode() : 0);
        hash = 52 * hash + (this.battery != null ? this.battery.hashCode() : 0);
        hash = 52 * hash + this.weight;
        hash = 52 * hash + this.heavyweight;
        hash = 52 * hash + this.maxDistance;
        hash = 52 * hash + this.maxHeight;
        hash = 52 * hash + (this.producer != null ? this.producer.hashCode() : 0);
        hash = 52 * hash + (this.pilotLicense != null ? this.pilotLicense.hashCode() : 0);
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
        final ResponseSapr other = (ResponseSapr) obj;
        
        if (this.idSapr != other.idSapr) {
            return false;
        }
        if (this.weight != other.weight) {
            return false;
        }
        if ((this.model == null) ? (other.model != null) : !this.model.equals(other.model)) {
            return false;
        }
        if ((this.battery == null) ? (other.battery != null) : !this.battery.equals(other.battery)) {
            return false;
        }
        if (this.heavyweight != other.heavyweight) {
            return false;
        }
        if (this.maxDistance != other.maxDistance) {
            return false;
        }
        if (this.maxHeight != other.maxHeight) {
            return false;
        }
        if ((this.producer == null) ? (other.producer != null) : !this.producer.equals(other.producer)) {
            return false;
        }
        if ((this.pilotLicense == null) ? (other.pilotLicense != null) : !this.pilotLicense.equals(other.pilotLicense)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResponseSapr{" + "idSapr=" + idSapr + ", model=" + model + ", producer=" + producer + ", weight=" + weight + ", heavyweight=" + heavyweight + ", maxDistance=" + maxDistance + ", maxHeight=" + maxHeight + ", battery=" + battery +",  pilotLicense=" + pilotLicense + '}';
    }
    
    
}
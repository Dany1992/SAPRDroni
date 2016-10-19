package it.uniroma2.sapr.bean;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import it.uniroma2.sapr.pojo.CheckElement;

@XmlRootElement(name="RequestManagerSAPR")
@XmlAccessorType(XmlAccessType.FIELD)
public class RequestSAPR extends Request {
	
	@XmlElement(name = "idSapr",nillable = false)
	private long idSapr;
	
	@XmlElement(name = "model",nillable = false)
	private String model;
	
	@XmlElement(name = "producer",nillable = false)
	private String producer;
	
	@XmlElement(name = "weight",nillable = false)
	private int weight;
	
	@XmlElement(name = "heavyweight",nillable = false)
	private int heavyweight;
	
	@XmlElement(name = "battery",nillable = false)
	private String battery;
	
	@XmlElement(name = "maxDistance",nillable = false)
	private int maxDistance;
	
	@XmlElement(name = "maxHeight",nillable = false)
	private int maxHeight;
	
	@XmlElement(name = "pilotLicense",nillable = false)
	private String pilotLicense;
	
	@XmlElement(name = "checkSapr",nillable = false)
	private ArrayList<CheckElement> checkSapr;
	
	@XmlElement(name = "active",nillable = false)
	private int active;

	public RequestSAPR() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RequestSAPR(long idSapr, String model, String producer, int weight, int heavyweight, String battery,
			int maxDistance, int maxHeight, String pilotLicense, ArrayList<CheckElement> checkSapr, int active, operation op) {
		super();
		this.idSapr = idSapr;
		this.model = model;
		this.producer = producer;
		this.weight = weight;
		this.heavyweight = heavyweight;
		this.battery = battery;
		this.maxDistance = maxDistance;
		this.maxHeight = maxHeight;
		this.pilotLicense = pilotLicense;
		this.checkSapr = checkSapr;
		this.active = active;
		this.op = op;
	}

	public long getIdSapr() {
		return idSapr;
	}

	public void setIdSapr(long idSapr) {
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
	
	public ArrayList<CheckElement> getCheckSapr() {
		return checkSapr;
	}

	public void setCheckSapr(ArrayList<CheckElement> checkSapr) {
		this.checkSapr = checkSapr;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + active;
		result = prime * result + ((battery == null) ? 0 : battery.hashCode());
		result = prime * result + ((checkSapr == null) ? 0 : checkSapr.hashCode());
		result = prime * result + heavyweight;
		result = prime * result + (int) (idSapr ^ (idSapr >>> 32));
		result = prime * result + maxDistance;
		result = prime * result + maxHeight;
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((pilotLicense == null) ? 0 : pilotLicense.hashCode());
		result = prime * result + ((producer == null) ? 0 : producer.hashCode());
		result = prime * result + weight;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		RequestSAPR other = (RequestSAPR) obj;
		if (active != other.active)
			return false;
		if (battery == null) {
			if (other.battery != null)
				return false;
		} else if (!battery.equals(other.battery))
			return false;
		if (checkSapr == null) {
			if (other.checkSapr != null)
				return false;
		} else if (!checkSapr.equals(other.checkSapr))
			return false;
		if (heavyweight != other.heavyweight)
			return false;
		if (idSapr != other.idSapr)
			return false;
		if (maxDistance != other.maxDistance)
			return false;
		if (maxHeight != other.maxHeight)
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (pilotLicense == null) {
			if (other.pilotLicense != null)
				return false;
		} else if (!pilotLicense.equals(other.pilotLicense))
			return false;
		if (producer == null) {
			if (other.producer != null)
				return false;
		} else if (!producer.equals(other.producer))
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RequestSapr [idSapr=" + idSapr + ", model=" + model + ", producer=" + producer + ", weight=" + weight
				+ ", heavyweight=" + heavyweight + ", battery=" + battery + ", maxDistance=" + maxDistance
				+ ", maxHeight=" + maxHeight + ", pilotLicense=" + pilotLicense + ", active=" + active + ", checkSapr=" + checkSapr
				+ "]";
	}
	
}

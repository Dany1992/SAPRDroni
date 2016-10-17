package it.uniroma2.sapr.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="RequestManagerSAPR")
@XmlAccessorType(XmlAccessType.FIELD)
public class RequestSAPR extends Request {
	
	@XmlElement(name = "ID_SAPR",nillable = false)
	private long idSapr;
	
	@XmlElement(name = "MODEL_SAPR",nillable = false)
	private String model;
	
	@XmlElement(name = "PRODUCER_SAPR",nillable = false)
	private String producer;
	
	@XmlElement(name = "WEIGHT_SAPR",nillable = false)
	private int weight;
	
	@XmlElement(name = "HEAVYWEIGHT_SAPR",nillable = false)
	private int heavyweight;
	
	@XmlElement(name = "BATTERY_SAPR",nillable = false)
	private String battery;
	
	@XmlElement(name = "MAX_DISTANCE",nillable = false)
	private int maxDistance;
	
	@XmlElement(name = "MAX_HEIGHT",nillable = false)
	private int maxHeight;
	
	@XmlElement(name = "PILOT_LICENSE",nillable = false)
	private String pilotLicense;

	public RequestSAPR() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RequestSAPR(long idSapr, String model, String producer, int weight, int heavyweight, String battery,
			int maxDistance, int maxHeight, String pilotLicense, operation op) {
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

	@Override
	public int hashCode() {
		final int prime = 52;
		int result = super.hashCode();
		result = prime * result + ((battery == null) ? 0 : battery.hashCode());
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
		if (battery == null) {
			if (other.battery != null)
				return false;
		} else if (!battery.equals(other.battery))
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
		return "RequestSAPR [idSapr=" + idSapr + ", model=" + model + ", producer=" + producer + ", weight=" + weight
				+ ", heavyweight=" + heavyweight + ", battery=" + battery + ", maxDistance=" + maxDistance
				+ ", maxHeight=" + maxHeight + ", pilotLicense=" + pilotLicense + ", op=" + op + "]";
	}	
	
	
}

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
	private String produce;
	
	@XmlElement(name = "WEIGHT_SAPR",nillable = false)
	private int weight;
	
	@XmlElement(name = "WEIGHTHEAVY_SAPR",nillable = false)
	private int heavyWeight;
	
	@XmlElement(name = "BATTERY_SAPR",nillable = false)
	private String battery;
	
	@XmlElement(name = "MAX_DISTANCE",nillable = false)
	private int maxDistance;
	
	@XmlElement(name = "MAX_HEIGHT",nillable = false)
	private int maxHeight;
	
	@XmlElement(name = "OWNER_SAPR",nillable = false)
	private String owner;

	public RequestSAPR() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RequestSAPR(long idSapr, String model, String produce, int weight, int heavyWeight, String battery,
			int maxDistance, int maxHeight, String owner, operation op) {
		super();
		this.idSapr = idSapr;
		this.model = model;
		this.produce = produce;
		this.weight = weight;
		this.heavyWeight = heavyWeight;
		this.battery = battery;
		this.maxDistance = maxDistance;
		this.maxHeight = maxHeight;
		this.owner = owner;
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

	public String getProduce() {
		return produce;
	}

	public void setProduce(String produce) {
		this.produce = produce;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getHeavyWeight() {
		return heavyWeight;
	}

	public void setHeavyWeight(int heavyWeight) {
		this.heavyWeight = heavyWeight;
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

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((battery == null) ? 0 : battery.hashCode());
		result = prime * result + heavyWeight;
		result = prime * result + (int) (idSapr ^ (idSapr >>> 32));
		result = prime * result + maxDistance;
		result = prime * result + maxHeight;
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + ((produce == null) ? 0 : produce.hashCode());
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
		if (heavyWeight != other.heavyWeight)
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
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (produce == null) {
			if (other.produce != null)
				return false;
		} else if (!produce.equals(other.produce))
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RequestSAPR [idSapr=" + idSapr + ", model=" + model + ", produce=" + produce + ", weight=" + weight
				+ ", heavyWeight=" + heavyWeight + ", battery=" + battery + ", maxDistance=" + maxDistance
				+ ", maxHeight=" + maxHeight + ", owner=" + owner + ", op=" + op + "]";
	}	
	
	
}

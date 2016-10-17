<<<<<<< HEAD
package it.uniroma2.sapr.pojo;

/**
 * Questa classe è l'oggetto che viene creato prelevando i dati dalla RequestSapr.
 * Tale oggetto viene utilizzazto poi per tutte le operazioni che si vogliono fare sul db
 */

public class Sapr {
    private int idSapr;
    private String model;
    private String producer;
    private int weight;
    private int heavyweight;
    private String battery;
    private int maxDistance;
    private int maxHeight;
    private String pilotLicense;

    public Sapr(int idSapr, String model, String producer, int weight, int heavyweight, String battery, int maxDistance, int maxHeight, String pilotLicense) {
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
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		 final Sapr other = (Sapr) obj;
		
		if (this.idSapr != other.idSapr)
			return false;
		
		if (this.model == null) {
			if (other.model != null)
				return false;
		} else if (!this.model.equals(other.model))
			return false;
		
		if (this.producer == null) {
			if (other.producer != null)
				return false;
		} else if (!this.producer.equals(other.producer))
			return false;
		
		if (this.weight != other.weight)
			return false;
		
		if (this.heavyweight != other.heavyweight)
			return false;
		
		if (this.battery == null) {
			if (other.battery != null)
				return false;
		} else if (!this.battery.equals(other.battery))
			return false;
		
		if (this.pilotLicense == null) {
			if (other.pilotLicense != null)
				return false;
		} else if (!this.pilotLicense.equals(other.pilotLicense))
			return false;
		
		if (this.maxDistance != other.maxDistance)
			return false;
		
		if (this.maxHeight != other.maxHeight)
			return false;
		
		return true;
	}

    @Override
    public int hashCode() {
        int result = 9;
        
        result = 52 * result + this.idSapr;
		result = 52 * result + ((model == null) ? 0 : model.hashCode());
		result = 52 * result + ((producer == null) ? 0 : producer.hashCode());
		result = 52 * result + this.weight;
		result = 52 * result + this.heavyweight;
		result = 52 * result + ((battery == null) ? 0 : battery.hashCode());
		result = 52 * result + this.maxDistance;
		result = 52 * result + this.maxHeight;
		result = 52 * result + ((pilotLicense == null) ? 0 : pilotLicense.hashCode());
		
		return result;
    }
    
    @Override
	public String toString() {
		return "Sapr [idSapr=" + idSapr + ", model=" + model + ", producer=" + producer + ", weight=" + weight
				+ ", pilotLicense=" + pilotLicense + ", heavyweight=" + heavyweight + ", battery=" + battery
				+ ", maxDistance=" + maxDistance + ", maxHeight=" + maxHeight + "]";
	}
}
=======
package it.uniroma2.sapr.pojo;

public class Sapr {
    private int idSapr;

    public Sapr(int idSapr) {
        this.idSapr = idSapr;
    }

    public int getIdSapr() {
        return idSapr;
    }

    public void setIdSapr(int idSapr) {
        this.idSapr = idSapr;
    }
    
}
>>>>>>> dindiBranch

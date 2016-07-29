package it.uniroma2.sapr.pojo;

/**
 * Created by Tiziano on 28/07/16.
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sapr)) return false;

        Sapr sapr = (Sapr) o;

        if (getIdSapr() != sapr.getIdSapr()) return false;
        if (getWeight() != sapr.getWeight()) return false;
        if (getHeavyweight() != sapr.getHeavyweight()) return false;
        if (getMaxDistance() != sapr.getMaxDistance()) return false;
        if (getMaxHeight() != sapr.getMaxHeight()) return false;
        if (!getModel().equals(sapr.getModel())) return false;
        if (!getProducer().equals(sapr.getProducer())) return false;
        if (!getBattery().equals(sapr.getBattery())) return false;
        return getPilotLicense().equals(sapr.getPilotLicense());

    }

    @Override
    public int hashCode() {
        int result = getIdSapr();
        result = 31 * result + getModel().hashCode();
        result = 31 * result + getProducer().hashCode();
        result = 31 * result + getWeight();
        result = 31 * result + getHeavyweight();
        result = 31 * result + getBattery().hashCode();
        result = 31 * result + getMaxDistance();
        result = 31 * result + getMaxHeight();
        result = 31 * result + getPilotLicense().hashCode();
        return result;
    }
}

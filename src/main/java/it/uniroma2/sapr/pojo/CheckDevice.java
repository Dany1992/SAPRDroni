package it.uniroma2.sapr.pojo;

import java.util.ArrayList;

/**
 *
 * @author dario
 */
public class CheckDevice {
    private int idDevice;
    private ArrayList<String> value;

    public CheckDevice() {
        super();
    }

    public CheckDevice(int idDevice, ArrayList<String> value) {
        this.idDevice = idDevice;
        this.value = value;
    }

    public int getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(int idDevice) {
        this.idDevice = idDevice;
    }

    public ArrayList<String> getValue() {
        return value;
    }

    public void setValue(ArrayList<String> value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + this.idDevice;
        hash = 31 * hash + (this.value != null ? this.value.hashCode() : 0);
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
        final CheckDevice other = (CheckDevice) obj;
        if (this.idDevice != other.idDevice) {
            return false;
        }
        if (this.value != other.value && (this.value == null || !this.value.equals(other.value))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CheckDevice{" + "idDevice=" + idDevice + ", value=" + value + '}';
    }

    
}

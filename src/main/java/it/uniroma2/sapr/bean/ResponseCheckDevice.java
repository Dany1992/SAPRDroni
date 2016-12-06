package it.uniroma2.sapr.bean;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dario
 */

@XmlRootElement(name="ResponseCheckElement")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseCheckDevice {
    @XmlElement(name = "idDevice", nillable = false)
    private int idDevice;
    @XmlElement(name = "values", nillable = false)
    private ArrayList<String> value;

    public ResponseCheckDevice(){
        super();
    }    

    public ResponseCheckDevice(int idDevice, ArrayList<String> value) {
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
        int hash = 5;
        hash = 17 * hash + this.idDevice;
        hash = 17 * hash + (this.value != null ? this.value.hashCode() : 0);
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
        final ResponseCheckDevice other = (ResponseCheckDevice) obj;
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
        return "ResponseCheckDevice{" + "idDevice=" + idDevice + ", value=" + value + '}';
    }

    
}

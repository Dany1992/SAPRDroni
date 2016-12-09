package it.uniroma2.sapr.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ResponseListPilots")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseListPilots extends Response {
	
	@XmlElement(name = "pilots")
	List<ResponsePilot> pilots;
	
	public ResponseListPilots() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResponseListPilots(int errorCode, String errorMessage) {
		super(errorCode, errorMessage);
		// TODO Auto-generated constructor stub
	}

	public ResponseListPilots(int errorCode, String errorMessage, List<ResponsePilot> pilots) {
		super(errorCode, errorMessage);
		this.pilots = pilots;
	}

	public List<ResponsePilot> getPilots() {
		return pilots;
	}

	public void setPilots(List<ResponsePilot> pilots) {
		this.pilots = pilots;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((pilots == null) ? 0 : pilots.hashCode());
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
		ResponseListPilots other = (ResponseListPilots) obj;
		if (pilots == null) {
			if (other.pilots != null)
				return false;
		} else if (!pilots.equals(other.pilots))
			return false;
		return true;
	}
	
}

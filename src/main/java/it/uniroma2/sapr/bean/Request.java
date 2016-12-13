package it.uniroma2.sapr.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Request {
	
	@XmlElement(name = "operation")
	protected operation op;
        @XmlElement(name = "opzione")
        protected opzione opz;
        
	public enum opzione{
		ENABLED,DISABLED,ALL
	}

	public opzione getOpz() {
		return opz;
	}
        
	public void setOpz(opzione opz) {
		this.opz = opz;
	}
        
	public enum operation{
		ADD,DELETE,UPDATE,ENABLE
	}
	
	@XmlElement(name = "opzione")
	protected operation opz;
	
	public enum opzione{
		ENABLED,DISABLED,ALL
	}
	
	public operation getOpz() {
		return opz;
	}

	public void setOpz(operation opz) {
		this.opz = opz;
	}

	public operation getOp() {
		return op;
	}

	public void setOp(operation op) {
		this.op = op;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((op == null) ? 0 : op.hashCode());
		result = prime * result + ((opz == null) ? 0 : opz.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		if (op != other.op)
			return false;
		if (opz != other.opz)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Request [op=" + op + "opz=" + opz +  "]";
	}

}

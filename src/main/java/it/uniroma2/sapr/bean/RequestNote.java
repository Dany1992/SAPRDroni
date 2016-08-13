package it.uniroma2.sapr.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Created by Tiziano on 28/07/16.
 */
@XmlRootElement(name="requestManagerNote")
@XmlAccessorType(XmlAccessType.FIELD)
public class RequestNote extends Request{
    @XmlElement(name = "ID_NOTE")
    private int idNote;
    @XmlElement(name = "TEXT_NOTE", nillable = false)
    private String textNote;
    @XmlElement(name = "DATE_NOTE", nillable = false)
    private String date;


    public RequestNote() {
		super();
	}

	public RequestNote(int idNote, String textNote, String date, operation op) {
        super();
        this.idNote = idNote;
        this.textNote = textNote;
        this.date = date;
        this.op = op;
    }

    public int getIdNote() {
        return idNote;
    }

    public void setIdNote(int idNote) {
        this.idNote = idNote;
    }

    public String getTextNote() {
        return textNote;
    }

    public void setTextNote(String textNote) {
        this.textNote = textNote;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequestNote)) return false;
        if (!super.equals(o)) return false;

        RequestNote that = (RequestNote) o;

        if (getIdNote() != that.getIdNote()) return false;
        if (!getTextNote().equals(that.getTextNote())) return false;
        return getDate().equals(that.getDate());

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getIdNote();
        result = 31 * result + getTextNote().hashCode();
        result = 31 * result + getDate().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "RequestNote{" +
                "idNote=" + idNote +
                ", textNote='" + textNote + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}

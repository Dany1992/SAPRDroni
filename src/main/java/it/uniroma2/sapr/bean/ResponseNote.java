/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.uniroma2.sapr.bean;

/**
 *
 * @author Tiziano
 */

import java.sql.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="RequestManagerNote")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseNote {
    @XmlElement(name = "idNote")
    private int idNote;
    @XmlElement(name = "textNote")
    private String textNote;
    @XmlElement(name = "date")
    private String date;

    public ResponseNote(int idNote, String textNote, String date) {
        this.idNote = idNote;
        this.textNote = textNote;
        this.date = date;
    }

    public ResponseNote() {
        super();
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
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.idNote;
        hash = 37 * hash + (this.textNote != null ? this.textNote.hashCode() : 0);
        hash = 37 * hash + (this.date != null ? this.date.hashCode() : 0);
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
        final ResponseNote other = (ResponseNote) obj;
        if (this.idNote != other.idNote) {
            return false;
        }
        if ((this.textNote == null) ? (other.textNote != null) : !this.textNote.equals(other.textNote)) {
            return false;
        }
        if ((this.date == null) ? (other.date != null) : !this.date.equals(other.date)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResponseNote{" + "idNote=" + idNote + ", textNote=" + textNote + ", date=" + date + '}';
    }
    
    
}

package it.uniroma2.sapr.pojo;

/**
 * Created by Tiziano on 28/07/16.
 */
public class Note {
    private int idNote;
    private String textNote;
    private String date;

    public Note(int idNote, String textNote, String date) {
        this.idNote = idNote;
        this.textNote = textNote;
        this.date = date;
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
    public String toString() {
        return "Note{" +
                "idNote=" + idNote +
                ", textNote='" + textNote + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}

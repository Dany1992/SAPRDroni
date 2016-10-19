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

    public Note(String textNote, String date){
        this.textNote = textNote;
        this.date = date;
    }

    public Note() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Note)) return false;

        Note note = (Note) o;

        if (getIdNote() != note.getIdNote()) return false;
        if (!getTextNote().equals(note.getTextNote())) return false;
        return getDate().equals(note.getDate());

    }

    @Override
    public int hashCode() {
        int result = getIdNote();
        result = 31 * result + getTextNote().hashCode();
        result = 31 * result + getDate().hashCode();
        return result;
    }
}

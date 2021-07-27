package patient.registration.model;

public class Patient {
    private long patientId;
    private String givenName;
    private String familyName;
    private String note;

    public Patient() {
    }

    public Patient(long patientId, String givenName, String familyName, String note) {
        this.patientId = patientId;
        this.givenName = givenName;
        this.familyName = familyName;
        this.note = note;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Patient: ID = " + patientId +
                ", givenName = " + givenName +
                ", familyName = " + familyName +
                ", note = " + note;
    }
}

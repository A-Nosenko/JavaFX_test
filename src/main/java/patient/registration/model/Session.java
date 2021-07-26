package patient.registration.model;

import java.util.Date;

public class Session {
    private long sessionId;
    private long fkPatient;
    private SessionType sessionType;
    private Date startDate;
    private int duration;
    private byte efficiency;

    public Session() {
    }

    public Session(long sessionId, long fkPatient, SessionType sessionType, Date startDate, int duration, byte efficiency) {
        this.sessionId = sessionId;
        this.fkPatient = fkPatient;
        this.sessionType = sessionType;
        this.startDate = startDate;
        this.duration = duration;
        this.efficiency = efficiency;
    }

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public long getFkPatient() {
        return fkPatient;
    }

    public void setFkPatient(long fkPatient) {
        this.fkPatient = fkPatient;
    }

    public SessionType getSessionType() {
        return sessionType;
    }

    public void setSessionType(SessionType sessionType) {
        this.sessionType = sessionType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public byte getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(byte efficiency) {
        this.efficiency = efficiency;
    }
}

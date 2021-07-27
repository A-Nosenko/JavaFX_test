package patient.registration.model;

import patient.registration.utils.DateUtils;

import java.util.Date;

public class Session {
    private long sessionId;
    private long fkPatient;
    private SessionType sessionType;
    private Date startDate;
    private int duration;
    private double efficiency;

    public Session() {
    }

    public Session(long sessionId, long fkPatient, SessionType sessionType, Date startDate, int duration, double efficiency) {
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

    public double getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(double efficiency) {
        this.efficiency = efficiency;
    }

    @Override
    public String toString() {
        return "Session: ID = " + sessionId +
                ", fkPatient = " + fkPatient +
                ", sessionType = " + sessionType +
                ", startDate = " + DateUtils.convertDateToText(startDate) +
                ", duration = " + duration +
                ", efficiency = " + efficiency;
    }
}

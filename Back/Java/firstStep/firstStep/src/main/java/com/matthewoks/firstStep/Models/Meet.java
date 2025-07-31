package com.matthewoks.firstStep.Models;

public abstract class Meet {
    private int id;
    private String meetName;
public Meet(){}

    public Meet(int id, String meetName) {
    super();
        this.id = id;
        this.meetName = meetName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMeetName() {
        return meetName;
    }

    @Override
    public String toString() {
        return "Meet{" +
                "id=" + id +
                ", meetName='" + meetName + '\'' +
                '}';
    }

    public void setMeetName(String meetName) {
        this.meetName = meetName;
    }
}

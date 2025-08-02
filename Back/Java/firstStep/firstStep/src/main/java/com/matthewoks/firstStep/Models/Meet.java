package com.matthewoks.firstStep.Models;

public abstract class Meet {
    private int Id;
    private String color;
    private String meetName;
public Meet(){}

    public Meet(int id,String color, String meetName) {
    super();
        this.Id=id;
        this.meetName = meetName;
        this.color = color;
    }
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMeetName() {
        return meetName;
    }

    @Override
    public String toString() {
        return "Meet{" +
                "color=" + color +
                ", meetName='" + meetName + '\'' +
                '}';
    }

    public void setMeetName(String meetName) {
        this.meetName = meetName;
    }
}

package com.matthewoks.firstStep.Models;

import java.util.List;

public class Exercise extends PlannedAction {
    private int duration;
    private int repetitions;
    private int sets;
    private int restTime;
    private String executionMode;
    private List<Equipment> equipments;
    private int intensityLevel;

    public Exercise() {

    }

    public Exercise(Long id, String name, String description, String color, String imageUrl, int duration, int repetitions, int sets, int restTime, String executionMode, List<Equipment> equipments, int intensityLevel) {
        super(id, name, description, color, imageUrl);
        this.duration = duration;
        this.repetitions = repetitions;
        this.sets = sets;
        this.restTime = restTime;
        this.executionMode = executionMode;
        this.equipments = equipments;
        this.intensityLevel = intensityLevel;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getRestTime() {
        return restTime;
    }

    public void setRestTime(int restTime) {
        this.restTime = restTime;
    }

    public String getExecutionMode() {
        return executionMode;
    }

    public void setExecutionMode(String executionMode) {
        this.executionMode = executionMode;
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
    }

    public int getIntensityLevel() {
        return intensityLevel;
    }

    public void setIntensityLevel(int intensityLevel) {
        this.intensityLevel = intensityLevel;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "duration=" + duration +
                ", repetitions=" + repetitions +
                ", sets=" + sets +
                ", restTime=" + restTime +
                ", executionMode='" + executionMode + '\'' +
                ", equipments=" + equipments +
                ", intensityLevel=" + intensityLevel +
                "} " + super.toString();
    }
}

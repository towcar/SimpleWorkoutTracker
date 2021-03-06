package com.carsonskjerdal.app.workouttracker;


/**
 * Created by Carson on 2017-09-15.
 * <p>
 * Feel free to use code just give credit please :)
 */

public class Workout {
    private int ID;
    private String title, weight, reps;
    private int icon;

    public Workout() {
    }

    public Workout(int ID, String title, String weight, String reps, int icon) {
        this.ID = ID;
        this.title = title;
        this.weight = weight;
        this.reps = reps;
        this.icon = icon;
    }

    public Workout(String title, String weight, String reps, int icon) {

        this.title = title;
        this.weight = weight;
        this.reps = reps;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getReps() {
        return reps;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }


}


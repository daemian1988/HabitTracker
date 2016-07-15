package com.example.android.habittracker;

/**
 * Created by Daemian on 6/7/2016.
 */

public class HabitClass {

    private int id;
    private String habitName;
    private int habitNoMove;

    public HabitClass() {

    }

    public HabitClass(int id, String habitName, int habitNoMove) {
        this.id = id;
        this.habitName = habitName;
        this.habitNoMove = habitNoMove;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHabitName() {
        return habitName;
    }

    public void setHabitName(String habitName) {
        this.habitName = habitName;
    }

    public int getHabitNoMove() {
        return habitNoMove;
    }

    public void setHabitNoMove(int habitNoMove) {
        this.habitNoMove = habitNoMove;
    }


}

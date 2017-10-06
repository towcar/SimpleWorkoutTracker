package com.carsonskjerdal.app.workouttracker;

import android.content.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carson on 2017-10-05.
 * <p>
 * Feel free to use code just give credit please :)
 */

public class WorkoutStorage {

    private static List<Workout> bicepList = new ArrayList<>();
    private static List<Workout> shouldersList = new ArrayList<>();
    private static List<Workout> legsList = new ArrayList<>();
    private static List<Workout> coreList = new ArrayList<>();
    private static List<Workout> chestList = new ArrayList<>();
    private static List<Workout> backList = new ArrayList<>();
    private static Workout workout;

    public WorkoutStorage() {

    }

    private static void buildWorkout() {

       /* int drawIcon = R.drawable.cworkouticon;

        workout = new Workout("Arm Pulls", "25", "8/8/8", drawIcon);
        bicepList.add(workout);
        workout = new Workout("Lined Reverse Triceps", "15", "8/8/8", drawIcon);
        bicepList.add(workout);
        workout = new Workout("Should Tricep Extensions", "20", "8/8/8", drawIcon);
        bicepList.add(workout);
        workout = new Workout("Tricep Rip Pull", "40", "8/8/8", drawIcon);
        bicepList.add(workout);
        workout = new Workout("Bicep Pull Up", "42.5", "8/8/8", drawIcon);
        bicepList.add(workout);
        workout = new Workout("Cable Curl", "20", "8/8/8", drawIcon);
        bicepList.add(workout);
        workout = new Workout("Hammer Curl", "20", "8/8/8", drawIcon);
        bicepList.add(workout);
        workout = new Workout("Dual Arm Pulls", "10", "8/8/8", drawIcon);
        bicepList.add(workout);*/
    }

    public static List<Workout> getList(String title) {
        //buildWorkout();
        switch (title) {

            case "bicep": {
                return bicepList;

            }
            case "legs": {
                return legsList;

            }
            case "shoulders": {
                return shouldersList;

            }
            case "core": {
                return coreList;

            }
            case "chest": {
                return chestList;
            }
            case "back": {
                return backList;

            }
        }

        return bicepList;

    }


    public static void addWorkout(String title, String category, String weight, Context context) {

        int drawIcon = R.drawable.cworkouticon;

        workout = new Workout(title, weight, "8/8/8", drawIcon);

        switch (category) {

            case "Biceps & Triceps": {
                drawIcon = R.drawable.cworkouticon;
                workout.setIcon(drawIcon);
                bicepList.add(workout);
                saveFile(context, bicepList);

            }
            case "Back": {
                drawIcon = R.drawable.cworkouticon;
                workout.setIcon(drawIcon);
                backList.add(workout);
                saveFile(context, backList);

            }
            case "Core": {
                drawIcon = R.drawable.cworkouticon;
                workout.setIcon(drawIcon);
                coreList.add(workout);
                saveFile(context, coreList);
            }
            case "Shoulders": {
                drawIcon = R.drawable.cworkouticon;
                workout.setIcon(drawIcon);
                shouldersList.add(workout);
                saveFile(context, shouldersList);
            }
            case "Chest": {
                drawIcon = R.drawable.chesticon;
                workout.setIcon(drawIcon);
                chestList.add(workout);
                saveFile(context, chestList);
            }
            case "Legs": {
                drawIcon = R.drawable.legicon;
                workout.setIcon(drawIcon);
                legsList.add(workout);
                saveFile(context,legsList);
            }
        }
    }

    private static void saveFile(Context context, List<Workout> category) {
        //update saved data with new data.
        File directory = new File(context.getFilesDir().getAbsolutePath()
                + File.separator + "serialization");
        if (!directory.exists()) directory.mkdirs();

        String filename = "data.txt";
        ObjectOutput out = null;

        try {
            out = new ObjectOutputStream(new FileOutputStream(directory
                    + File.separator + filename));
            out.writeObject(category);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}



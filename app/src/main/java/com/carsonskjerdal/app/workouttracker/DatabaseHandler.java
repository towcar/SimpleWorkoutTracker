package com.carsonskjerdal.app.workouttracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * Created by Carson on 2017-10-05.
 * <p>
 * Feel free to use code just give credit please :)
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "workoutListManager";

    // Contacts table name
    private static final String TABLE_WORKOUTS = "workouts";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_WEIGHT = "weight";
    private static final String KEY_REPS = "reps";
    private static final String KEY_ICON = "icon";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_WORKOUT_TABLE = "CREATE TABLE " + TABLE_WORKOUTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_WEIGHT + " TEXT,"
                + KEY_REPS + " TEXT,"
                + KEY_ICON + " TEXT" + ");";
        db.execSQL(CREATE_WORKOUT_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORKOUTS);

        // Create tables again
        onCreate(db);
    }


    // Adding new contact
    public void addWorkout(Workout workout) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, workout.getTitle()); // Contact Name
        values.put(KEY_WEIGHT, workout.getWeight()); // Contact Weight
        values.put(KEY_REPS, workout.getReps()); // Contact Reps
        values.put(KEY_ICON, workout.getIcon()); // Contact Icon

        // Inserting Row
        db.insert(TABLE_WORKOUTS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    public Workout getWorkout(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_WORKOUTS, new String[] { KEY_ID,
                        KEY_NAME, KEY_WEIGHT, KEY_REPS, KEY_ICON }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Workout workout;
        //ensure the cursor != null as above check proves.
        assert cursor != null;
        int count = cursor.getCount();

        if (count > 0) {

            workout = new Workout(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), Integer.parseInt(cursor.getString(4)));
            cursor.close();
            // return contact
            return workout;
        }
        workout = new Workout(0, "", "", "", 0);
        return workout;
    }

    // Getting All Contacts
    public List<Workout> getAllWorkouts() {
        List<Workout> contactList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_WORKOUTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Workout workout = new Workout();
                workout.setID(Integer.parseInt(cursor.getString(0)));
                workout.setTitle(cursor.getString(1));
                workout.setWeight(cursor.getString(2));
                workout.setReps(cursor.getString(3));
                workout.setIcon(parseInt(cursor.getString(4)));
                // Adding workout to list
                contactList.add(workout);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // Getting All Contacts
    public List<Workout> getWorkoutList(int drawIcon) {
        List<Workout> contactList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_WORKOUTS;

        int icon = drawIcon;


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);



        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                //if the icons match then add to list.
                if (Integer.parseInt(cursor.getString(4)) == icon) {
                    Log.e("comparing","" + icon + " & " + Integer.parseInt(cursor.getString(4)));
                    Workout workout = new Workout();
                    workout.setID(Integer.parseInt(cursor.getString(0)));
                    workout.setTitle(cursor.getString(1));
                    workout.setWeight(cursor.getString(2));
                    workout.setReps(cursor.getString(3));
                    workout.setIcon(parseInt(cursor.getString(4)));
                    // Adding workout to list
                    contactList.add(workout);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }


    // Updating single workout
    public int updateWorkout(Workout workout) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, workout.getID());
        values.put(KEY_NAME, workout.getTitle());
        values.put(KEY_WEIGHT, workout.getWeight());
        values.put(KEY_REPS, workout.getReps());
        values.put(KEY_ICON, workout.getIcon());

        // updating row
        return db.update(TABLE_WORKOUTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(workout.getID()) });
    }

    // Deleting single contact
    public void deleteWorkout(Workout workout) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_WORKOUTS, KEY_ID + " = ?",
                new String[] { String.valueOf(workout.getID()) });
        db.close();
    }

    public int getWorkoutCount() {
        String countQuery = "SELECT  * FROM " + TABLE_WORKOUTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

}
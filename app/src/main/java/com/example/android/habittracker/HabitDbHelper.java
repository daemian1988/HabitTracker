package com.example.android.habittracker;

/**
 * Created by Daemian on 6/7/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.R.attr.id;

public class HabitDbHelper extends SQLiteOpenHelper {

    public HabitDbHelper(Context context) {
        super(context, HabitContract.DB_NAME, null, HabitContract.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String TEXT_TYPE = " TEXT";
        final String COMMA_SEP = ",";
        final String INTEGER_TYPE = " INTEGER";

        String createTable = "CREATE TABLE " + HabitContract.HabitEntry.TABLE_NAME + " ( " +
                HabitContract.HabitEntry.COLUMN_NAME_ENTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                HabitContract.HabitEntry.COLUMN_NAME_HABIT_NAME + TEXT_TYPE + COMMA_SEP +
                HabitContract.HabitEntry.COLUMN_NAME_MOVE_NO + INTEGER_TYPE + ");";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + HabitContract.HabitEntry.TABLE_NAME);
        onCreate(db);
    }

    // code to add the new habit
    public void addContact(HabitClass habit) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitContract.HabitEntry.COLUMN_NAME_HABIT_NAME, habit.getHabitName()); // Contact Name
        values.put(HabitContract.HabitEntry.COLUMN_NAME_MOVE_NO, habit.getHabitNoMove()); // Contact Phone

        // Inserting Row
        db.insert(HabitContract.HabitEntry.TABLE_NAME, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single habit
    public HabitClass getHabit(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(HabitContract.HabitEntry.TABLE_NAME, new String[]{HabitContract.HabitEntry.COLUMN_NAME_ENTRY_ID,
                        HabitContract.HabitEntry.COLUMN_NAME_HABIT_NAME, HabitContract.HabitEntry.COLUMN_NAME_MOVE_NO},
                HabitContract.HabitEntry.COLUMN_NAME_ENTRY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        HabitClass habit = new HabitClass(cursor.getInt(0),
                cursor.getString(1), cursor.getInt(2));

        return habit;
    }

    // code to get the single habit
    public Cursor getHabit() {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select "+HabitContract.HabitEntry.COLUMN_NAME_HABIT_NAME+" FROM "+HabitContract.HabitEntry.TABLE_NAME+";";

        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }

    // code to update the single habit
    public int updateHabit(HabitClass habit) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitContract.HabitEntry.COLUMN_NAME_HABIT_NAME, habit.getHabitName()); // Contact Name
        values.put(HabitContract.HabitEntry.COLUMN_NAME_MOVE_NO, habit.getHabitNoMove()); // Contact Phone


        // updating row
        return db.update(HabitContract.HabitEntry.TABLE_NAME, values, HabitContract.HabitEntry.COLUMN_NAME_ENTRY_ID + " = ?",
                new String[]{String.valueOf(habit.getId())});
    }


    public void deleteDB(SQLiteDatabase db) {

        db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + HabitContract.HabitEntry.TABLE_NAME); //delete all rows in a table
        db.close();

    }
}

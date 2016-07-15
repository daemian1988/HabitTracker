package com.example.android.habittracker;

import android.provider.BaseColumns;

/**
 * Created by Daemian on 6/7/2016.
 */

public class HabitContract {

    public static final String DB_NAME = "habitContract.db";
    public static final int DB_VERSION = 1;

    public HabitContract() {
    }

    /* Inner class that defines the table contents */
    public class HabitEntry implements BaseColumns {
        public static final String TABLE_NAME = "habitTable";
        public static final String COLUMN_NAME_ENTRY_ID = "habitID";
        public static final String COLUMN_NAME_HABIT_NAME = "habitName";
        public static final String COLUMN_NAME_MOVE_NO = "habitMove";
    }

}

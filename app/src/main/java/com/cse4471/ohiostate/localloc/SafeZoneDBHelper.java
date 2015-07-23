package com.cse4471.ohiostate.localloc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Yalith on 7/18/2015.
 */
public class SafeZoneDBHelper extends SQLiteOpenHelper {

    private static final String TYPE_VARCHAR = " VARCHAR";
    private static final String COMMA = ",";

    private static final String SQL_CREATE_BLUETOOTH =
            "CREATE TABLE " + DataContract.BluetoothTable.TABLE_NAME + " ("
            + DataContract.BluetoothTable.COLUMN_MAC + TYPE_VARCHAR
                    + "PRIMARY KEY" + COMMA + DataContract.BluetoothTable
                    .COLUMN_DEVICE_ID + TYPE_VARCHAR + COMMA + " )";

    private static final String SQL_CREATE_WIFI =
            "CREATE TABLE " + DataContract.WifiTable.TABLE_NAME + " ("
                    + DataContract.WifiTable.COLUMN_SSID + TYPE_VARCHAR
                    + "PRIMARY KEY" + COMMA + " )";

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SafeZone.db";

    public SafeZoneDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_BLUETOOTH);
        db.execSQL(SQL_CREATE_WIFI);
    }

}


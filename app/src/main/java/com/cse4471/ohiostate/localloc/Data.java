package com.cse4471.ohiostate.localloc;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.view.View;

/**
 * Created by Yalith on 7/18/2015.
 */
public final class Data {
    // To prevent someone from accidentally instantiating this class,
    // give it an empty constructor.
    public Data() {}



    public void AddToBluetooth(String mac, String deviceID) {
        SafeZoneDBHelper DBHelper = new SafeZoneDBHelper(context);

        SQLiteDatabase db = DBHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DataContract.BluetoothTable.COLUMN_MAC, mac);
        values.put(DataContract.BluetoothTable.COLUMN_DEVICE_ID, deviceID);

        long RowID = db.insert(
                DataContract.BluetoothTable.TABLE_NAME,
                null,
                values);

        db.close();
    }

    public void AddToWiFi (String ssid) {
        SafeZoneDBHelper DBHelper = new SafeZoneDBHelper(context);

        SQLiteDatabase db = DBHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DataContract.WifiTable.COLUMN_SSID, ssid);

        long RowID = db.insert(
                DataContract.WifiTable.TABLE_NAME,
                null,
                values);

        db.close();
    }

    public void RemoveFromBluetooth(String mac) {
        SafeZoneDBHelper DBHelper = new SafeZoneDBHelper(context);

        SQLiteDatabase db = DBHelper.getWritableDatabase();

        String[] selectionArgs = {mac};

        int deleted = db.delete(
                DataContract.BluetoothTable.TABLE_NAME,
                DataContract.BluetoothTable.COLUMN_MAC,
                selectionArgs);

        db.close();
    }

    public void RemoveFromWifi(String ssid) {
        SafeZoneDBHelper DBHelper = new SafeZoneDBHelper(context);

        SQLiteDatabase db = DBHelper.getWritableDatabase();

        String[] selectionArgs = {ssid};

        int deleted = db.delete(
                DataContract.WifiTable.TABLE_NAME,
                DataContract.WifiTable.COLUMN_SSID,
                selectionArgs);

        db.close();
    }


}

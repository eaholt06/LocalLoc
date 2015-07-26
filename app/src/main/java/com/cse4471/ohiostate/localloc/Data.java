package com.cse4471.ohiostate.localloc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Yalith on 7/18/2015.
*/
public final class Data {
    // To prevent someone from accidentally instantiating this class,
    // give it an empty constructor.
    public Data() {}

    private Context context;

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

        int deleted = db.delete(
                DataContract.BluetoothTable.TABLE_NAME,
                DataContract.BluetoothTable.COLUMN_MAC + "='" + mac + "'",
                null);

        db.close();
    }

    public void RemoveFromWifi(String ssid) {
        SafeZoneDBHelper DBHelper = new SafeZoneDBHelper(context);

        SQLiteDatabase db = DBHelper.getWritableDatabase();

        int deleted = db.delete(
                DataContract.WifiTable.TABLE_NAME,
                DataContract.WifiTable.COLUMN_SSID + "='" + ssid + "'",
                null);

        db.close();
    }

    public void UpdateBluetooth(String mac, String newDeviceID) {
        SafeZoneDBHelper DBHelper = new SafeZoneDBHelper(context);

        SQLiteDatabase db = DBHelper.getWritableDatabase();

        String[] selectionArgs = {mac};

        ContentValues values = new ContentValues();
        values.put(DataContract.BluetoothTable.COLUMN_DEVICE_ID, newDeviceID);

        int count = db.update(
                DataContract.BluetoothTable.TABLE_NAME,
                values,
                DataContract.BluetoothTable.COLUMN_MAC + "='" + mac + "'",
                null);

        db.close();
    }

    public Map ListBluetooth() {
        SafeZoneDBHelper DBHelper = new SafeZoneDBHelper(context);

        SQLiteDatabase db = DBHelper.getReadableDatabase();

        String[] projection = {
                DataContract.BluetoothTable.COLUMN_MAC,
                DataContract.BluetoothTable.COLUMN_DEVICE_ID};

        Cursor c = db.query(
                DataContract.BluetoothTable.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                DataContract.BluetoothTable.COLUMN_DEVICE_ID);

        LinkedHashMap bluetoothList = new LinkedHashMap();
        String mac, deviceID;
        c.moveToFirst();
        while(c.moveToNext()) {
            mac = c.getString(0);
            deviceID = c.getString(1);
            bluetoothList.put(mac,deviceID);
        }

        c.close();

        return bluetoothList;

    }

    public ArrayList ListWifi() {
        SafeZoneDBHelper DBHelper = new SafeZoneDBHelper(context);

        SQLiteDatabase db = DBHelper.getReadableDatabase();

        String[] projection = {
                DataContract.WifiTable.COLUMN_SSID};

        Cursor c = db.query(
                DataContract.BluetoothTable.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                DataContract.WifiTable.COLUMN_SSID);

        ArrayList wifiList = new ArrayList();
        String ssid;
        c.moveToFirst();
        while(c.moveToNext()) {
            ssid = c.getString(0);
            wifiList.add(ssid);
        }

        c.close();

        return wifiList;
    }

    public boolean IsInBluetooth(String mac) {
        SafeZoneDBHelper DBHelper = new SafeZoneDBHelper(context);

        SQLiteDatabase db = DBHelper.getReadableDatabase();

        boolean isInDB = false;

        String[] projection = {
                DataContract.BluetoothTable.COLUMN_MAC};

        Cursor c = db.query(
                DataContract.BluetoothTable.TABLE_NAME,
                projection,
                DataContract.BluetoothTable.COLUMN_MAC + "='" + mac + "'",
                null,
                null,
                null,
                null);

        if (c.getCount() > 0) {
            isInDB = true;
        }

        return isInDB;
    }

    public boolean IsInWifi(String ssid) {
        SafeZoneDBHelper DBHelper = new SafeZoneDBHelper(context);

        SQLiteDatabase db = DBHelper.getReadableDatabase();

        boolean isInDB = false;

        String[] projection = {
                DataContract.WifiTable.COLUMN_SSID};

        Cursor c = db.query(
                DataContract.WifiTable.TABLE_NAME,
                projection,
                DataContract.WifiTable.COLUMN_SSID + "='" + ssid + "'",
                null,
                null,
                null,
                null);

        if (c.getCount() > 0) {
            isInDB = true;
        }
        return isInDB;
    }
}

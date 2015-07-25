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

    /**
     *
     * @param mac - Mac address of the newly connected Bluetooth device
     * @param deviceID - Newly connected device ID name
     * @return 1 if device was successfully added, 0 otherwise
     * @requires the mac address is not already in the database
     * @ensures the new BLuetooth device is in the database
     *
     * This method adds a new Bluetooth connection to the database.
     */
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

    /**
     *
     * @param ssid - The ssid of the Wifi connection to be added
     * @return 1 if device was successfully added, 0 otherwise
     * @requires the ssid is not already in the database
     * @ensures the new Wifi connection is in the database
     *
     * This method adds a new Bluetooth connection to the database.
     */
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

    /**
     *
     * @param mac - Mac address of the Bluetooth commection to remove
     * @return 1 if device was successfully revmoed, 0 otherwise
     * @requires the mac address is in the database
     * @ensures the BLuetooth device is removed from the database
     *
     * This method removes a Bluetooth connection from the database.
     */
    public void RemoveFromBluetooth(String mac) {
        SafeZoneDBHelper DBHelper = new SafeZoneDBHelper(context);

        SQLiteDatabase db = DBHelper.getWritableDatabase();

        int deleted = db.delete(
                DataContract.BluetoothTable.TABLE_NAME,
                DataContract.BluetoothTable.COLUMN_MAC + "='" + mac + "'",
                null);

        db.close();
    }

    /**
     *
     * @param ssid - ssid of the Wifi commection to remove
     * @return 1 if device was successfully revmoed, 0 otherwise
     * @requires the ssid is in the database
     * @ensures the Wifi device is removed from the database
     *
     * This method removes a Wifi connection from the database.
     */
    public void RemoveFromWifi(String ssid) {
        SafeZoneDBHelper DBHelper = new SafeZoneDBHelper(context);

        SQLiteDatabase db = DBHelper.getWritableDatabase();

        int deleted = db.delete(
                DataContract.WifiTable.TABLE_NAME,
                DataContract.WifiTable.COLUMN_SSID + "='" + ssid + "'",
                null);

        db.close();
    }

    /**
     *
     * @param mac - Mac address of the Bluetooth commection to update
     * @param deviceID - The new name of the Bluetooth device
     * @return 1 if device was successfully revmoed, 0 otherwise
     * @requires the mac address is in the database
     * @ensures the BLuetooth deviceID is updated in the database
     *
     * This method updates the ID of  a Bluetooth connection in the database.
     */
    public void UpdateBluetooth(String mac, String deviceID) {
        SafeZoneDBHelper DBHelper = new SafeZoneDBHelper(context);

        SQLiteDatabase db = DBHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DataContract.BluetoothTable.COLUMN_DEVICE_ID, deviceID);

        int count = db.update(
                DataContract.BluetoothTable.TABLE_NAME,
                values,
                DataContract.BluetoothTable.COLUMN_MAC + "='" + mac + "'",
                null);

        db.close();
    }

    /**
     *
     * @return a map containing mac address, device ID pairs of all
     * Bluetooth connections in the database
     *
     * This method returns a map of <mac address, device ID> pairs of all
     * the BLuetooth connections in the database.
     */
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
        db.close();
        return bluetoothList;

    }

    /**
     *
     * @return an arraylist containing the ssid of all wifi
     * connections in the database
     *
     * This method returns an arraylist of all the wifi
     * connections in the database.
     */
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
        db.close();
        return wifiList;
    }

    /**
     *
     * @param mac - the mac address of the Bluetooth connection
     * @return true if the mac address is in the database, false if not
     *
     *
     */
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

        c.close();
        db.close();
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

        c.close();
        db.close();
        return isInDB;
    }
}

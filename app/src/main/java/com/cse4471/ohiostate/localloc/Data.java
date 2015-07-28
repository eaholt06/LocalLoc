package com.cse4471.ohiostate.localloc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.ArrayMap;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Jessica Gillespie
 * @version 07262015
 *
 * This class has methods to run the sql database for storing safe zones.
 *
*/
public final class Data {
    // To prevent someone from accidentally instantiating this class,
    // give it an empty constructor.
    public Data() {}

    private Context context;

    /**
     * @param title - Title of the newly connected Bluetooth device safe zone
     * @param mac - Mac address of the newly connected Bluetooth device
     * @param deviceID - Newly connected device ID name
     * @return rowID if device was successfully added, -1 otherwise
     * @requires the mac address is not already in the database
     * @ensures the new BLuetooth device is in the database
     *
     * This method adds a new Bluetooth connection to the database.
     */
    public long AddToBluetooth(String title, String mac, String deviceID) {
        SafeZoneDBHelper DBHelper = new SafeZoneDBHelper(context);

        SQLiteDatabase db = DBHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DataContract.BluetoothTable.COLUMN_TITLE, title);
        values.put(DataContract.BluetoothTable.COLUMN_MAC, mac);
        values.put(DataContract.BluetoothTable.COLUMN_DEVICE_ID, deviceID);

        long rowID = db.insert(
                DataContract.BluetoothTable.TABLE_NAME,
                null,
                values);

        db.close();

        return rowID;
    }

    /**
     * @param title - Title of the newly connected Bluetooth device safe zone
     * @param ssid - The ssid of the Wifi connection to be added
     * @return rowID if device was successfully added, -1 otherwise
     * @requires the ssid is not already in the database
     * @ensures the new Wifi connection is in the database
     *
     * This method adds a new Bluetooth connection to the database.
     */
    public long AddToWiFi (String title, String ssid) {
        SafeZoneDBHelper DBHelper = new SafeZoneDBHelper(context);

        SQLiteDatabase db = DBHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DataContract.BluetoothTable.COLUMN_TITLE, title);
        values.put(DataContract.WifiTable.COLUMN_SSID, ssid);

        long rowID = db.insert(
                DataContract.WifiTable.TABLE_NAME,
                null,
                values);

        db.close();

        return rowID;
    }

    /**
     *
     * @param mac - Mac address of the Bluetooth connection to remove
     * @return the number of rows deleted from the database
     * @requires the mac address is in the database
     * @ensures the Bluetooth device is removed from the database
     *
     * This method removes a Bluetooth connection from the database.
     */
    public int RemoveFromBluetooth(String mac) {
        SafeZoneDBHelper DBHelper = new SafeZoneDBHelper(context);

        SQLiteDatabase db = DBHelper.getWritableDatabase();

        int deleted = db.delete(
                DataContract.BluetoothTable.TABLE_NAME,
                DataContract.BluetoothTable.COLUMN_MAC + "='" + mac + "'",
                null);

        db.close();

        return deleted;
    }

    /**
     *
     * @param ssid - ssid of the Wifi commection to remove
     * @return the number of rows deleted from the database
     * @requires the ssid is in the database
     * @ensures the Wifi device is removed from the database
     *
     * This method removes a Wifi connection from the database.
     */
    public int RemoveFromWifi(String ssid) {
        SafeZoneDBHelper DBHelper = new SafeZoneDBHelper(context);

        SQLiteDatabase db = DBHelper.getWritableDatabase();

        int deleted = db.delete(
                DataContract.WifiTable.TABLE_NAME,
                DataContract.WifiTable.COLUMN_SSID + "='" + ssid + "'",
                null);

        db.close();

        return deleted;
    }

    /**
     *
     * @param mac - Mac address of the Bluetooth commection to update
     * @param deviceID - The new name of the Bluetooth device
     * @return the number of rows updates
     * @requires the mac address is in the database
     * @ensures the BLuetooth deviceID is updated in the database
     *
     * This method updates the ID of  a Bluetooth connection in the database.
     */
    public int UpdateBluetooth(String mac, String deviceID) {
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

        return count;
    }

    /**
     *
     * @return a map containing mac address, device ID pairs of all
     * Bluetooth connections in the database
     *
     * This method returns a map of <mac address, device ID> pairs of all
     * the BLuetooth connections in the database.
     */
    public ArrayMap<String, ArrayList<String>> ListBluetooth() {
        SafeZoneDBHelper DBHelper = new SafeZoneDBHelper(context);

        SQLiteDatabase db = DBHelper.getReadableDatabase();

        String[] projection = {
                DataContract.BluetoothTable.COLUMN_TITLE,
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

        ArrayMap<String, ArrayList<String>> bluetoothList = new ArrayMap<String, ArrayList<String>>();



        //LinkedHashMap<String,String> bluetoothList = new LinkedHashMap<>();
        String title, mac, deviceID;
        c.moveToFirst();
        while(c.moveToNext()) {
            title = c.getString(0);
            mac = c.getString(1);
            deviceID = c.getString(2);
            ArrayList<String> tempArrList = new ArrayList<String>();
            tempArrList.add(title);
            tempArrList.add(deviceID);
            bluetoothList.put(mac,tempArrList);
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
    public ArrayMap<String, String> ListWifi() {
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

        ArrayMap<String, String> wifiList = new ArrayMap<>();
        String title, ssid;
        c.moveToFirst();
        while(c.moveToNext()) {
            title = c.getString(0);
            ssid = c.getString(1);
            wifiList.put(ssid, title);
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
     *This method will search the database for the given mac address
     *and return true if the address is found in the databse, false
     *otherwise.
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

    /**
     *
     * @param ssid - the ssid of the wifi connection
     * @return true if the ssid is in the database, false if not
     *
     *This method will search the database for the given ssid
     *and return true if the ssid is found in the databse, false
     *otherwise.
     *
     */
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

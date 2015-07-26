package com.cse4471.ohiostate.localloc;


import android.content.Context;


public class ZoneChecker {


    public static String ssid;
    public static String bluetoothID;
    public static String bluetoothMAC;
    public static boolean safeZone;
    public static WiFi wifi;
    public static Bluetooth bt;
    //public static GeoLocation geo;


    /**
     * Creator of initial representation.
     */
    private void createNewRep(Context context) {
        wifi = new WiFi();
        bt = new Bluetooth(context);
        //geo = new GeoLocation(context);
        ssid = "Not Connected";
        bluetoothID = "Not Connected";
        bluetoothMAC = null;
        safeZone = false;

    }

    /**
     * Default constructor.
     */
    public ZoneChecker (Context context) {

        this.createNewRep(context);
    }


    public static boolean updateZone(Context context) {

        wifi.checkWIFI(context);
        ssid = wifi.ssid;

        bluetoothID = bt.bluetoothID;
        bluetoothMAC = bt.bluetoothMAC;

        //compare bt to Bluetooth Info
        boolean btSafe = false;
        //compare wifi to wifi list
        boolean wifiSafe = false;
        //compare geo to geo list
        //boolean geoSafe = false;

        return btSafe || wifiSafe;
    }
}
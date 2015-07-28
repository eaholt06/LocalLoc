package com.cse4471.ohiostate.localloc;


import android.content.Context;


public class ZoneChecker {


    public static String ssid;
    public static String bluetoothID;
    public static String bluetoothMAC;
    public static boolean safeZone;
    public static WiFi wifi;
    public static Bluetooth bt;
    public static Data db;
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
        db = new Data();

    }

    /**
     * Default constructor.
     */
    public ZoneChecker (Context context) {

        this.createNewRep(context);
    }


    public static void updateZone(Context context) {


        wifi.checkWIFI(context);
        ssid = wifi.ssid;

        bluetoothID = bt.bluetoothID;
        bluetoothMAC = bt.bluetoothMAC;

    }

    public static boolean checkZone() {

        return db.IsInBluetooth(bluetoothMAC) || db.IsInWifi(ssid);
    }

}
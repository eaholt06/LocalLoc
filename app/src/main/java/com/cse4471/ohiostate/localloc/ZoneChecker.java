package com.cse4471.ohiostate.localloc;


import android.content.Context;


public class ZoneChecker {


    public String ssid;
    public String bluetoothID;
    public String bluetoothMAC;
    public boolean safeZone;
    private WiFi wifi;
    private Bluetooth bt;
    private GeoLocation geo;


    /**
     * Creator of initial representation.
     */
    private void createNewRep(Context context) {
        this.wifi = new WiFi();
        this.bt = new Bluetooth(context);
        this.geo = new GeoLocation(context);
        this.ssid = "Not Connected";
        this.bluetoothID = "Not Connected";
        this.bluetoothMAC = null;
        this.safeZone = false;

    }

    /**
     * Default constructor.
     */
    public ZoneChecker (Context context) {

        this.createNewRep(context);
    }


    public final boolean updateZone(Context context) {

        this.wifi.checkWIFI(context);
        this.wifi.ssid = this.ssid;

        this.bluetoothID = this.bt.bluetoothID;
        this.bluetoothMAC = this.bt.bluetoothMAC;

        //compare bt to Bluetooth Info
        boolean btSafe = false;
        //compare wifi to wifi list
        boolean wifiSafe = false;
        //compare geo to geo list
        boolean geoSafe = false;

        return btSafe || wifiSafe || geoSafe;
    }
}
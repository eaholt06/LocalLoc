package com.cse4471.ohiostate.localloc;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.util.List;

public class ZoneChecker extends Activity {

    public boolean wifiConnection;
    public boolean bluetootheConnection;
    public String ssid;
    public List<BluetoothDevice> bluetoothID;
    public boolean safeZone;
    private Context context;

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {

        this.wifiConnection = false;
        this.ssid = "Not Connected";
        this.bluetootheConnection = false;
        this.bluetoothID = null;
        this.safeZone = false;
    }

    /**
     * Default constructor.
     */
    public ZoneChecker() {

        this.createNewRep();
    }


    public final ZoneChecker newInstance(Context context) {


        try {
            return this.getClass().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError("Cannot construct object of type "
                    + this.getClass());
        }

    }


    public final void reset() {
        this.createNewRep();
    }

    public final void updateConnectionsInfo(Context context) {

        //WI-FI Information
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (wifi.isWifiEnabled()) {
            WifiInfo wifiInfo = wifi.getConnectionInfo();
            if (wifiInfo != null) {
                NetworkInfo.DetailedState state = WifiInfo.getDetailedStateOf(wifiInfo
                        .getSupplicantState());
                if (state == NetworkInfo.DetailedState.CONNECTED || state == NetworkInfo
                    .DetailedState.OBTAINING_IPADDR) {
                    this.ssid = wifiInfo.getSSID();
                    this.wifiConnection = true;
                }
            }
        } else {
            this.ssid = "Not Connected";
        }

        //Bluetooth Information
        BluetoothManager bluetooth = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        this.bluetoothID = bluetooth.getConnectedDevices(2);
        if (this.bluetoothID != null){
            BluetoothDevice test = this.bluetoothID.get(1);
                    test.getName();
            this.bluetootheConnection = true;
        }
/*
        //Geo Location Information
        LocationManager loc = (LocationManager) context.getSystemService(Context
                .LOCATION_SERVICE);
        loc.getLastKnownLocation();
*/
    }

    public final void updateZone() {

        if (this.bluetootheConnection){
            //check againse Jessica's class for bluetooth connectionc
        } else if (this.wifiConnection) {
            //check againse Jessica's class for wifi connections
        } else {
            //check againse Jessica's class for geo location
        }
    }


    public final void updateSecurityProfile() {
        //create listeners and call Andrew's class
    }
}
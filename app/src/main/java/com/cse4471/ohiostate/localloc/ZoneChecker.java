package com.cse4471.ohiostate.localloc;


import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;


public class ZoneChecker {


    public String ssid;
    public String bluetoothID;
    public boolean safeZone;
    private boolean intentCreated;


    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.ssid = "Not Connected";
        this.bluetoothID = "Not Connected";
        this.safeZone = false;
        this.intentCreated = false;
    }


    /**
     * Default constructor.
     */
    public ZoneChecker () {

        this.createNewRep();
    }


    public final ZoneChecker newInstance() {


        try {
            return this.getClass().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError("Cannot construct object of type "
                    + this.getClass());
        }

    }


    /* (Complete)
    Returns the name of the currently connected Wi-Fi Network, or 'Not Connected' if there is no
    Wi-Fi connection.
     */
    public final void checkWIFI(Context context) {

        //Getting the Wi-Fi Service from the context
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

        //Default return value
        this.ssid = "Not Connected";
        if (wifi.isWifiEnabled()) {

            //Getting information about the Wi-Fi connection
            WifiInfo wifiInfo = wifi.getConnectionInfo();
            if (wifiInfo != null) {
                NetworkInfo.DetailedState state = WifiInfo.getDetailedStateOf(wifiInfo
                        .getSupplicantState());
                if (state == NetworkInfo.DetailedState.CONNECTED || state == NetworkInfo
                        .DetailedState.OBTAINING_IPADDR) {
                    this.ssid = wifiInfo.getSSID();
                }
            }
        }
    }


    /* (Complete)
    Listens for changes in Bluetooth connections, and then adds them to this.bluetoothID
     */
    public final BroadcastReceiver btReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

            if (BluetoothDevice.ACTION_ACL_CONNECTED.equals(action)) {
                updateBT(device.getAddress());

            } else if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action)) {
                updateBT("Not Connected");
            }
        }
    };


    private void updateBT (String btMAC){
        this.bluetoothID = btMAC;
    }


    /* (not started)
    Returns the location of the device.
     */
    public final void checkGeo(Context context) {

    }


    public final boolean updateZone(Context context) {

        //checking current wifi status
        checkWIFI(context);
        String ssid = this.ssid;

        //creating Intents in the context to track Bluetooth connection changes
        if (!this.intentCreated){
            IntentFilter filter1 = new IntentFilter(BluetoothDevice.ACTION_ACL_CONNECTED);
            IntentFilter filter2 = new IntentFilter(BluetoothDevice.ACTION_ACL_DISCONNECTED);
            context.registerReceiver(btReceiver, filter1);
            context.registerReceiver(btReceiver, filter2);
            this.intentCreated = true;
        }

        //Bluetooth status auto updates
        String btMAC = this.bluetoothID;

        //geo location info

        //compare bt to Bluetooth Info
        boolean btSafe = false;
        //compare wifi to wifi list
        boolean wifiSafe = false;
        //compare geo to geo list
        boolean geoSafe = false;

        return btSafe || wifiSafe || geoSafe;
    }
}
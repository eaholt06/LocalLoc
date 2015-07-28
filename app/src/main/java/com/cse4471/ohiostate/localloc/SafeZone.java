package com.cse4471.ohiostate.localloc;

import android.content.Context;
import android.location.Location;

import java.util.UUID;
/**
 * Created by Liz on 7/15/2015.
 */
public class SafeZone {
    private UUID mId;
    private String mTitle;
    private String mZoneType;
    private String mDeviceId;
    private String mMacId;

    public SafeZone()


    {
        // Generate unique identifier
        mId = UUID.randomUUID();
    }

    public void setTitle(String Title) {

        this.mTitle = Title;
    }

    public String getTitle() {

        return this.mTitle;
    }

    public void setZoneType(String zoneType) {

        this.mZoneType = zoneType;
    }

    public String getZoneType() {

        return this.mZoneType;
    }

    public String getDeviceId() {

        return mDeviceId;
    }

    public String getMacId() {
        return this.mMacId;
    }

    public boolean setDeviceId(Context context) {
        ZoneChecker.updateZone(context);
        if (this.getZoneType().equals("WiFi")) {
            if (!ZoneChecker.ssid.equals("Not Connected")) {
                this.mDeviceId = ZoneChecker.ssid;
                return true;
            }
        } else if (this.getZoneType().equals("Bluetooth")) {
            if (!ZoneChecker.bluetoothID.equals("Not Connected")) {
                this.mDeviceId = ZoneChecker.bluetoothID;
                this.mMacId = ZoneChecker.bluetoothMAC;
                return true;
            }
        }
        return false;
    }

    public void setDeviceId(String DeviceId, String MacId) {
        if(this.getZoneType().equalsIgnoreCase("Bluetooth")){
            this.mDeviceId = DeviceId;
            this.mMacId = MacId;
        }else if(this.getZoneType().equalsIgnoreCase("Wifi")){
            this.mDeviceId = DeviceId;
        }

    }
}

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
    private Location mGeo;
    public ZoneChecker zone = new ZoneChecker();

    public SafeZone()


    {
        // Generate unique identifier
        mId = UUID.randomUUID();
    }

    public void setTitle(String Title) {
        mTitle = Title;
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

    public void setDeviceId(Context context, String DeviceId) {

        zone.updateZone(context);

        if (this.getZoneType() == "Wifi") {
            if (zone.ssid != "Not Connected") {
                this.mDeviceId = zone.ssid;
            } else {
                this.mDeviceId = DeviceId;
            }
        } else if (this.getZoneType() == "Bluetooth") {
            if (zone.bluetoothID != "Not Connected") {
                this.mDeviceId = zone.bluetoothID;
            } else {
                this.mDeviceId = DeviceId;
            }
        }
    }

    public Location getGeo() {
        return mGeo;
    }

    public void setGeo(Location geo) {

        this.mGeo = geo;
    }
}

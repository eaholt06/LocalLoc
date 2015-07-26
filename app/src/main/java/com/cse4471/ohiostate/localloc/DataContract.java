package com.cse4471.ohiostate.localloc;

import android.provider.BaseColumns;

/**
 * @author Jessica Gillespie
 * @version 07262015
 *
 * This class defines variables for the safe zone database
 * tables and columns
 *
 */
public class DataContract {
    //The following inner classes define each table in the database
    public static abstract class BluetoothTable implements BaseColumns {
        public static final String TABLE_NAME = "BLUETOOTH";
        public static final String COLUMN_MAC = "Mac";
        public static final String COLUMN_DEVICE_ID = "DeviceID";
    }

    public static abstract class WifiTable implements BaseColumns {
        public static final String TABLE_NAME = "WIFI";
        public static final String COLUMN_SSID = "ssid";
    }
}

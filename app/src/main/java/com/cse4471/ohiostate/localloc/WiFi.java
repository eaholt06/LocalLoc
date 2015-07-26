package com.cse4471.ohiostate.localloc;

import android.content.Context;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

/**
 * Provides the WiFi servces for the app
 */
public class WiFi {

	public String ssid;

	/**
	 * Creator of initial representation.
	 */
	private void createNewRep() {
		this.ssid = "Not Connected";
	}


	/**
	 * Default constructor.
	 */
	public WiFi () {

		this.createNewRep();
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
}

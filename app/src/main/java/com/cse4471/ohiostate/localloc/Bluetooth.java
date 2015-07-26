package com.cse4471.ohiostate.localloc;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * Provides the Bluetooth servces for the app
 */
public class Bluetooth {

	public String bluetoothID;
	public String bluetoothMAC;

	/**
	 * Creator of initial representation.
	 */
	private void createNewRep(Context context) {
		this.bluetoothID = "Not Connected";
		this.bluetoothMAC = "Not Connected";
		IntentFilter filter1 = new IntentFilter(BluetoothDevice.ACTION_ACL_CONNECTED);
		IntentFilter filter2 = new IntentFilter(BluetoothDevice.ACTION_ACL_DISCONNECTED);
		context.registerReceiver(btReceiver, filter1);
		context.registerReceiver(btReceiver, filter2);
	}


	/**
	 * Default constructor.
	 */
	public Bluetooth (Context context) {

		this.createNewRep(context);
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
				updateBT(device.getAddress(), device.getName());

			} else if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action)) {
				updateBT("Not Connected", "Not Connected");
			}
		}
	};


	private void updateBT (String btMAC, String btID){

		this.bluetoothID = btID;
		this.bluetoothMAC = btMAC;
	}
}

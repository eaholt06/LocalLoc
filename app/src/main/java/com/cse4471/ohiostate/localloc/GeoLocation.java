package com.cse4471.ohiostate.localloc;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.List;
/**
 * Provides the GeoFencing servces for the app
 */
public class GeoLocation {

	public GoogleApiClient apiClient;
	public ArrayList<Geofence> safeGeos;


	/**
	 * Creator of initial representation.
	 */
	private void createNewRep(Context context) {

		this.safeGeos = new ArrayList<Geofence>();

		this.apiClient = new GoogleApiClient.Builder(context)
				.addConnectionCallbacks((GoogleApiClient.ConnectionCallbacks) context)
				.addOnConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) context)
				.addApi(LocationServices.API)
				.build();
	}


	/**
	 * Default constructor.
	 */
	public GeoLocation (Context context) {

		this.createNewRep(context);
	}


	/*
		Addcs new geofences to the list
	 */
	public final void buildGeo() {
		Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(
				this.apiClient);
		this.safeGeos.add(new Geofence.Builder()
				// Set the request ID of the geofence. This is a string to identify this geofence.
				.setRequestId(//get this from the user)
				.setCircularRegion(
						lastLocation.getLatitude(),
						lastLocation.getLongitude(),
						50
				)
				.setExpirationDuration(- 1)
				.setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER |
						Geofence.GEOFENCE_TRANSITION_EXIT)
				.build());
	}

	private GeofencingRequest getGeofencingRequest() {
		GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
		builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);
		builder.addGeofences(this.safeGeos);
		return builder.build();
	}

	private PendingIntent getGeofencePendingIntent(Context context) {
		// Reuse the PendingIntent if we already have it.
		if (mGeofencePendingIntent != null) {
			return mGeofencePendingIntent;
		}
		Intent intent = new Intent(context, GeofenceTransitionsIntentService.class);
		// We use FLAG_UPDATE_CURRENT so that we get the same pending intent back when
		// calling addGeofences() and removeGeofences().
		return PendingIntent.getService(this, 0, intent, PendingIntent.
				FLAG_UPDATE_CURRENT);
	}

	protected void onHandleIntent (Intent intent) {

		GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
		if (geofencingEvent.hasError()) {
			String errorMessage = GeofenceErrorMessages.getErrorString(this,
					geofencingEvent.getErrorCode());
			Log.e(TAG, errorMessage);
			return;
		}

		// Get the transition type.
		int geofenceTransition = geofencingEvent.getGeofenceTransition();

		// Test that the reported transition was of interest.
		if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER ||
				geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT) {

			// Get the geofences that were triggered. A single event can trigger
			// multiple geofences.
			List triggeringGeofences = geofencingEvent.getTriggeringGeofences();

			// Get the transition details as a String.
			String geofenceTransitionDetails = getGeofenceTransitionDetails(
					this,
					geofenceTransition,
					triggeringGeofences
			);

			// Send notification and log the transition details.
			sendNotification(geofenceTransitionDetails);
			Log.i(TAG, geofenceTransitionDetails);
		} else {
			// Log the error.
			Log.e(TAG, getString(R.string.geofence_transition_invalid_type,
					geofenceTransition));
		}
	}
}

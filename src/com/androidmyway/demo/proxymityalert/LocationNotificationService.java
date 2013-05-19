package com.androidmyway.demo.proxymityalert;

import java.util.ArrayList;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.os.IBinder;
import android.util.Log;

/**
 * A service running when having activities bind to it. It fetch news from
 * server and send broadcast The minDistance parameter can also be used to
 * control the frequency of location updates. If it is greater than 0 then the
 * location provider will only send your application an update when the location
 * has changed by at least minDistance meters, AND at least minTime milliseconds
 * have passed. However it is more difficult for location providers to save
 * power using the minDistance parameter, so minTime should be the primary tool
 * to conserving battery life.
 * 
 * @author long-nguyen
 * 
 */
public class LocationNotificationService extends Service {

	public static final String TAG = LocationNotificationService.class.getSimpleName();

	public static final int FAV_NOTIFICATION_ID = 3;
	public static final String INTENT_NOTIFICATION_NEAR_FAV = "intent_notification_near_fav";
    private static final long POINT_RADIUS = 200; // in Meters
    private static final long PROX_ALERT_EXPIRATION = -1; // It will never expire
    public static final String PROX_ALERT_INTENT = "com.androidmyway.demo.ProximityAlert";
    private LocationManager locationManager;
    private ArrayList<ProximityIntentReceiver> receivers=new ArrayList<ProximityIntentReceiver>();
	@Override
	public void onCreate() {
		super.onCreate();
		receivers.clear();
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		for(Spot p:SpotsList.mSpots){
        	addProximityAlert(p);
        }
	}
	private void addProximityAlert(Spot p) {
	       Intent intent = new Intent(PROX_ALERT_INTENT+p.name);
	       intent.putExtra("Locationname", p.name);
	       PendingIntent proximityIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
	       locationManager.addProximityAlert(
	    		   p.loc.getLatitude(), // the latitude of the central point of the alert region
	    		   p.loc.getLongitude(), // the longitude of the central point of the alert region
	              POINT_RADIUS, // the radius of the central point of the alert region, in meters
	              PROX_ALERT_EXPIRATION, // time for this proximity alert, in milliseconds, or -1 to indicate no                           expiration
	              proximityIntent // will be used to generate an Intent to fire when entry to or exit from the alert region is detected
	       );
	       IntentFilter filter = new IntentFilter(PROX_ALERT_INTENT+p.name); 
	       ProximityIntentReceiver rcv=new ProximityIntentReceiver();
	       receivers.add(rcv);
	       registerReceiver(rcv, filter);
	    }

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "Service destroyed");
		for(ProximityIntentReceiver rcv:receivers) unregisterReceiver(rcv);
	}

	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(TAG, "service Started : ");
		super.onStartCommand(intent, flags, startId);
		return Service.START_STICKY;
	}

}

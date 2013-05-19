package com.androidmyway.demo.proxymityalert;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ProximityAlertActivity extends Activity {
//    private static final long POINT_RADIUS = 200; // in Meters
//    private static final long PROX_ALERT_EXPIRATION = -1; // It will never expire
//    public static final String PROX_ALERT_INTENT = "com.androidmyway.demo.ProximityAlert";
//    private LocationManager locationManager;
//    ProximityIntentReceiver receiver;
    @Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_proxymity);
//		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        startService(new Intent(this,LocationNotificationService.class));
//        for(Spot p:SpotsList.mSpots){
//        	addProximityAlert(p);
//        }
    }

//    private void addProximityAlert(Spot p) {
//       Intent intent = new Intent(PROX_ALERT_INTENT+p.name);
//       intent.putExtra("Locationname", p.name);
//       PendingIntent proximityIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
//       locationManager.addProximityAlert(
//    		   p.loc.getLatitude(), // the latitude of the central point of the alert region
//    		   p.loc.getLongitude(), // the longitude of the central point of the alert region
//              POINT_RADIUS, // the radius of the central point of the alert region, in meters
//              PROX_ALERT_EXPIRATION, // time for this proximity alert, in milliseconds, or -1 to indicate no                           expiration
//              proximityIntent // will be used to generate an Intent to fire when entry to or exit from the alert region is detected
//       );
//       IntentFilter filter = new IntentFilter(PROX_ALERT_INTENT+p.name); 
//       registerReceiver(new ProximityIntentReceiver(), filter);
//
//    }
}

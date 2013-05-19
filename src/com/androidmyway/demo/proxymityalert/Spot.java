package com.androidmyway.demo.proxymityalert;

import android.location.Location;

class Spot {
	public String name;
	public Location loc;

	public Spot(String n, float lat, float ln) {
		name = n;
		loc = new Location("");
		loc.setLatitude(lat);
		loc.setLongitude(ln);
	}
}

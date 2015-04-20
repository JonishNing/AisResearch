package Model;

import java.sql.Timestamp;

public class AisRecord {
	
	private String mmsi;
	private Timestamp gpstime;
	private double longitude;
	private double latitude;
	private double direction;//航向
	private int period;//记录的时间 1.白天 2.夜间
	
	public String getMmsi() {
		return mmsi;
	}
	public void setMmsi(String mmsi) {
		this.mmsi = mmsi;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getDirection() {
		return direction;
	}
	public void setDirection(double direction) {
		this.direction = direction;
	}
	public Timestamp getGpstime() {
		return gpstime;
	}
	public void setGpstime(Timestamp gpstime) {
		this.gpstime = gpstime;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public int getPeriod(){
		return period;
	}
	
	

}

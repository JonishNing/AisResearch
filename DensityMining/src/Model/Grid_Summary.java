package Model;

import java.math.BigInteger;
import java.util.Date;

import Commons.DataAnalysis;
import Commons.TimeSwitch;

public class Grid_Summary {

	private int longitude;
	private int latitude;
	private int time;
	//private BigInteger GPSTime;
	private int period;
	//private Date date;

	public Grid_Summary() {
		super();
	}

	public Grid_Summary( int longitude,int latitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}


	public Grid_Summary(int longitude, int latitude, int time) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
		this.time = time;
		
	}
	public Grid_Summary(int longitude, int latitude, int time,int period) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
		this.time = time;
		this.period=period;
		
	}
	
	public int getLatitude() {
		return latitude;
	}

	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}

	public int getLongitude() {
		return longitude;
	}

	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}
	
	
//	public int getGPSTime() {
//		return GPSTime;
//	}
//
//	public void setGPSTime(int GPSTime) {
//		this.GPSTime = GPSTime;
//	}
//	public int getPeriod(Grid_Summary Summary,TimeSwitch Switch,DataAnalysis Analysis)
//		{
//		int period;
//		period=Analysis.judge_dayornight(Summary.getLongitude(), Summary.getLatitude(), Switch.getLocalTime(Summary.getGPSTime(), Summary.getLongitude()));
//		return period;
//		}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + latitude;
		result = prime * result + longitude;
		return result;
	}

	@Override
	public String toString() {
		return "[longitude=" + longitude + ", latitude=" + latitude + ", time=" + time + ",period="+period+"]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grid_Summary other = (Grid_Summary) obj;
		if (latitude != other.latitude)
			return false;
		if (longitude != other.longitude)
			return false;
		return true;
	}

	
	

	

}

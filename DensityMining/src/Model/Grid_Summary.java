package Model;

import java.util.Date;

public class Grid_Summary {

	private int longitude;
	private int latitude;
	private int time;
	private int GPSTime;
	//private Date date;

	public Grid_Summary() {
		super();
	}

	public Grid_Summary(int latitude, int longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Grid_Summary(int latitude, int longitude, int time) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.time = time;
	}

	public Grid_Summary(int longitude, int latitude, int time, int GPSTime) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
		this.time = time;
		this.GPSTime = GPSTime;
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
	
	public int getGPSTime() {
		return GPSTime;
	}

	public void setGPSTime(int GPSTime) {
		this.GPSTime = GPSTime;
	}

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
		return "Grid [longitude=" + longitude + ", longitude=" + latitude + ", time=" + time + "]";
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

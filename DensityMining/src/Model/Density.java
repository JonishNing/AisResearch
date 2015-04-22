package Model;

public class Density {
	
	private int Longitude;
	private int Latitude;
	private int Day_Vessel_Count;
	private int Night_Vessel_Count;
	public Density(){
		this.Day_Vessel_Count = 0;
		this.Night_Vessel_Count = 0;
	}
	public int getDay_Count() {
		return Day_Vessel_Count;
	}
	public void setDay_Count(int day_Count) {
		Day_Vessel_Count = day_Count;
	}
	public int getNight_Count() {
		return Night_Vessel_Count;
	}
	public void setNight_Count(int night_Count) {
		Night_Vessel_Count = night_Count;
	}
	public int getLongitude() {
		return Longitude;
	}
	public void setLongitude(int longitude) {
		Longitude = longitude;
	}
	public int getLatitude() {
		return Latitude;
	}
	public void setLatitude(int latitude) {
		Latitude = latitude;
	}
	public void addNightCout(){
		this.Night_Vessel_Count = this.Night_Vessel_Count + 1;
	}
	public void addDayCout(){
		this.Day_Vessel_Count = this.Day_Vessel_Count + 1;
	}
	
	

}

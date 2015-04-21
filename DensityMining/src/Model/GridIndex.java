package Model;

public class GridIndex {
	
	private int upper_left_longitude;
	private int upper_right_latitude;
	private int lower_right_longtitude;
	private int lower_right_latitude;
	
	
	public GridIndex(int upper_left_longitude, int upper_right_latitude) {
		super();
		this.upper_left_longitude = upper_left_longitude;
		this.upper_right_latitude = upper_right_latitude;
		this.lower_right_longtitude = upper_left_longitude + 3000;
		this.lower_right_latitude = upper_right_latitude + 3000;
	}
	
	public int getUpper_left_longitude() {
		return upper_left_longitude;
	}
	public void setUpper_left_longitude(int upper_left_longitude) {
		this.upper_left_longitude = upper_left_longitude;
	}
	public int getUpper_left_latitude() {
		return upper_right_latitude;
	}
	public void setUpper_left_latitude(int upper_right_latitude) {
		this.upper_right_latitude = upper_right_latitude;
	}
	public int getLower_right_longtitude() {
		return upper_right_latitude + 3000;
	}
	public void setLower_right_longtitude(int lower_right_longtitude) {
		this.lower_right_longtitude = lower_right_longtitude;
	}
	public int getLower_right_latitude() {
		return upper_right_latitude + 3000;
	}
	public void setLower_right_latitude(int lower_right_latitude) {
		this.lower_right_latitude = lower_right_latitude;
	}
	

	
		
	

}

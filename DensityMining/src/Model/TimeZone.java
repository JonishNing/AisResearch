package Model;


public class TimeZone {
	
	private double longitude;
	private int timezone;
	
	public TimeZone(double longitude){
		this.longitude = longitude;
		this.timezone  = this.getTimezone();
	}
	
	//获取时区，东正西负
	public int getTimezone(){
		double longitude = this.longitude;
		int timezone = 0;

		timezone = (int)(longitude/(15*6000000));		

		return timezone;
	}


}

package Commons;

import java.sql.Timestamp;

public class TimeSwitch {
	

	/**
	 * 获取当地时间
	 * @param BeijingTimestamp //北京时间
	 * @param longitude
	 * @return
	 */
	public Timestamp getLocalTime(Timestamp BeijingTimestamp , Double longitude){
		Timestamp currentTimestamp  = BeijingTimestamp;
		if(longitude>180||longitude<-180){
			System.out.println("longitude is error");
			return null;
		}
		int timezone = getTimezone(longitude);
		int zoneinterval = 8 - timezone;
		int hour = BeijingTimestamp.getHours();
		int day  = BeijingTimestamp.getDay();
		int currenthour = hour - zoneinterval;
		int currentday = day;
		if(currenthour>=0){
			currentTimestamp.setHours(currenthour);
		}
		else if(currenthour < 0){
			currenthour = currenthour + 24;
			currentTimestamp.setHours(currenthour);
			currentday = currentday - 1;
			currentTimestamp.setDate(day);
		}				
		return currentTimestamp;
	}
	
	/**
	 * 获取时区(东正西负)
	 * @param longitude
	 * @return
	 */
	public int getTimezone(double longitude){
		int timezone = 0;
		//四舍五入求时区
		timezone = Integer.parseInt(new java.text.DecimalFormat("0").format(longitude/15));		
		return timezone;
	}
	
	public static void main(String[] args){
		TimeSwitch timeSwitch = new TimeSwitch();
		Timestamp tm = new Timestamp(115, 4, 14, 15, 30, 25, 0);
		double longitude = 100;
		int timezone = timeSwitch.getTimezone(longitude);
		Timestamp currenttm = timeSwitch.getLocalTime(tm, longitude);
		try {
			String bjtm = tm.toString();
			System.out.println("北京时间："+bjtm);
			String time = currenttm.toString();			
			System.out.println(timezone+"区时间："+time);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print("null timestamp!");
		}
		


		
	}
	
	


}

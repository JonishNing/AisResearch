package Commons;

import java.sql.Timestamp;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;

public class TimeSwitch {
	

	/**
	 * 根据时区获取当地时间
	 * @param BeijingTimestamp //北京时间
	 * @param longitude
	 * @return
	 */
	public Date getLocalTime(Date BeijingTimeDate ,int longitude){
		Date currentDate  = BeijingTimeDate;
		if(longitude>180*600000||longitude<-180*600000){
			System.out.println("longitude is error");
			return null;
		}
		int timezone = getTimezone(longitude);
		int zoneinterval = 8 - timezone;
		int hour = BeijingTimeDate.getHours();
		int day  = BeijingTimeDate.getDay();
		int currenthour = hour - zoneinterval;
		int currentday = day;
		if(currenthour>=0){
			currentDate.setHours(currenthour);
		}
		else if(currenthour < 0){
			currenthour = currenthour + 24;
			currentDate.setHours(currenthour);
			currentday = currentday - 1;
			currentDate.setDate(day);
		}				
		return currentDate;
	}
	
	/**
	 * 获取时区(东正西负)
	 * @param longitude
	 * @return
	 */
	public int getTimezone(int longitude){
		int timezone = 0;
		//四舍五入求时区
		timezone = Integer.parseInt(new java.text.DecimalFormat("0").format(longitude/600000/15));		
		return timezone;
	}
	
	/**
	 * 根据年月获得GPSTime（开始）
	 * @param year
	 * @param month
	 * @return
	 */
	public int getStartGPStime(int year , int month){
		DateTime startDateTime = new DateTime(year,month,1,0,0,0); 
		DateTime endDateTime = new DateTime(year, month+1, 1, 0, 0, 0);
		Date startDate = startDateTime.toDate();
		Date endDate = endDateTime.toDate();
		int StartGPSTime =(int)startDate.getTime(); 
		return StartGPSTime; 
	}
	
	/**
	 * 根据年月获得GPSTime（结束）
	 * @param year
	 * @param month
	 * @return
	 */
	public int getEndGPStime(int year , int month){
		month = month + 1;
		DateTime endDateTime = new DateTime(year, month, 1, 0, 0, 0);
		Date endDate = endDateTime.toDate();
		int EndGPStime =(int)endDate.getTime(); 
		return EndGPStime; 
	}
	
	/**
	 * 根据GPS时间获得当天的点钟
	 * @param GPSTime
	 * @return
	 */
	public int GPStime2Hour(int GPSTime){
		DateTime dateTime = new DateTime(1970,1,1,0,0,0);
		DateTime currentdateTime = dateTime.plusSeconds(GPSTime);
		int hour_of_day = currentdateTime.getHourOfDay();
		return hour_of_day;
	}
	
	
	
	public static void main(String[] args){
		TimeSwitch timeSwitch = new TimeSwitch();
		Date tm = new Timestamp(115, 4, 14, 15, 30, 25, 0);
		int longitude = 100*600000;
		int timezone = timeSwitch.getTimezone(longitude);
		Date currenttm = timeSwitch.getLocalTime(tm, longitude);		
		int Gps = 1388505626;
		int hour = timeSwitch.GPStime2Hour(Gps);
		System.out.println(hour);
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

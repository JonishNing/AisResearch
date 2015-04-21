package Commons;

import java.sql.Timestamp;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;

public class TimeSwitch {
	

	/**
	 * ����ʱ����ȡ����ʱ��
	 * @param BeijingTimestamp //����ʱ��
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
	 * ��ȡʱ��(��������)
	 * @param longitude
	 * @return
	 */
	public int getTimezone(int longitude){
		int timezone = 0;
		//����������ʱ��
		timezone = Integer.parseInt(new java.text.DecimalFormat("0").format(longitude/600000/15));		
		return timezone;
	}
	
	/**
	 * �������»��GPSTime����ʼ��
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
	 * �������»��GPSTime��������
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
	 * ����GPSʱ���õ���ĵ���
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
			System.out.println("����ʱ�䣺"+bjtm);
			String time = currenttm.toString();			
			System.out.println(timezone+"��ʱ�䣺"+time);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print("null timestamp!");
		}
		


		
	}
	
	


}

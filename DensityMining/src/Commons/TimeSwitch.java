package Commons;

import java.sql.Timestamp;
import java.util.Date;

import org.joda.time.DateTime;


public class TimeSwitch {
	

	/**
	 * ����ʱ����ȡ����ʱ��
	 * @param BeijingTimestamp //����ʱ��
	 * @param longitude
	 * @return
	 */
	public int getLocalTime(int GPSTime ,int longitude){		
		Date currentDate  = GPS2Date(GPSTime);
		if(longitude>180*600000||longitude<-180*600000){
			System.out.println("longitude is error");
			return 0;
		}
		int timezone = getTimezone(longitude);
		int zoneinterval = 8 - timezone;
		int hour = currentDate.getHours();
		int day_of_month  = currentDate.getDate();
		int currenthour = hour - zoneinterval;
		int currentday = day_of_month;
		if(currenthour >= 0 && currenthour <= 24){
			currentDate.setHours(currenthour);
		}
		else if(currenthour > 24){
			currenthour = currentday - 24;
			currentDate.setHours(currenthour);
			currentday = currentday + 1;
			currentDate.setDate(currentday);
		}
		else if(currenthour < 0){
			currenthour = currenthour + 24;
			currentDate.setHours(currenthour);
			currentday = currentday - 1;
			currentDate.setDate(day_of_month);
		}				
		return (int)(currentDate.getTime()/1000);
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
		Date startDate = startDateTime.toDate();
		int StartGPSTime =(int)(startDate.getTime()/1000); 
		return StartGPSTime; 
	}
	
	/**
	 * �������»��GPSTime��������
	 * @param year
	 * @param month
	 * @return
	 */
	public int getEndGPStime(int year , int month){
		int nextmonth = month + 1;
		int nextyear = year;
		if(month == 12){
			nextmonth = 1;
			nextyear = year + 1;
		}
		DateTime endDateTime = new DateTime(nextyear, nextmonth, 1, 0, 0, 0);
		Date endDate = endDateTime.toDate();
		int EndGPStime =(int)(endDate.getTime()/1000); 
		return EndGPStime; 
	}
	
	/**
	 * ����GPSʱ���õ����X����
	 * @param GPSTime
	 * @return
	 */
	public int GPStime2Hour(int GPSTime){
		DateTime dateTime = new DateTime(1970,1,1,8,0,0);
		DateTime currentdateTime = dateTime.plusSeconds(GPSTime);
		int hour_of_day = currentdateTime.getHourOfDay();
		return hour_of_day;
	}
	
	/**
	 * GPSת��ΪDate
	 * @param GPSTime
	 * @return
	 */
	public Date GPS2Date(int GPSTime){
		DateTime dateTime = new DateTime(1970,1,1,8,0,0);
		DateTime currentdateTime = dateTime.plusSeconds(GPSTime);
		return currentdateTime.toDate();
		
	}
	
	/**
	 * ��ø��µ�����
	 * @param year
	 * @param month
	 * @return
	 */
	public int getDaysofMonth(int year , int month){
		int days = 0;
		int start = getStartGPStime(year, month);
		int end = getEndGPStime(year, month);
		days = (int)((end - start)/(60*60*24));
		return days;
	}
	
	
	
	public static void main(String[] args){
		TimeSwitch timeSwitch = new TimeSwitch();
//		Timestamp tm = new Timestamp(115, 4, 14, 15, 30, 25, 0);
		int tm = 1388505761;
//		System.out.println("tm = " + tm.toString());
//		System.out.println("hours = " + tm.getHours());
//		System.out.println("Days = " + tm.getDate());
//		System.out.println("tm = " + tm.toString());
		int longitude = 114*600000;
		int timezone = timeSwitch.getTimezone(longitude);
//		System.out.println("tm = " + tm.toString());
		int currenttm = timeSwitch.getLocalTime(tm, longitude);
		System.out.println("tm = " + tm);
//		System.out.println(tm.toString());
		System.out.println("currenttm ="+currenttm +" ");
		int Gps = 1388505626;
		int hour = timeSwitch.GPStime2Hour(Gps);
		System.out.println(hour);
		try {
			String bjtm = tm + "";
			System.out.println("����ʱ�䣺"+bjtm);
			String time = currenttm + "";			
			System.out.println(timezone+"��ʱ�䣺"+time);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print("null timestamp!");
		}
		int days = timeSwitch.getDaysofMonth(2012, 4);
		System.out.println("2013.04 have"+days);
		int gpsstart = timeSwitch.getStartGPStime(2014, 1);
		int gpsend = timeSwitch.getEndGPStime(2014, 1);
		System.out.println("gpsstart ="+gpsstart + " gpsend = "+gpsend );
		
		Date date = timeSwitch.GPS2Date(1388505600);
		System.out.println(date.toString());


		
	}
	
	


}

package Commons;

import java.sql.Timestamp;

public class TimeSwitch {
	

	/**
	 * ��ȡ����ʱ��
	 * @param BeijingTimestamp //����ʱ��
	 * @param longitude
	 * @return
	 */
	public Timestamp getLocalTime(Timestamp BeijingTimestamp ,int longitude){
		Timestamp currentTimestamp  = BeijingTimestamp;
		if(longitude>180*600000||longitude<-180*600000){
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
	
	
	public static void main(String[] args){
		TimeSwitch timeSwitch = new TimeSwitch();
		Timestamp tm = new Timestamp(115, 4, 14, 15, 30, 25, 0);
		int longitude = 100*600000;
		int timezone = timeSwitch.getTimezone(longitude);
		Timestamp currenttm = timeSwitch.getLocalTime(tm, longitude);
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

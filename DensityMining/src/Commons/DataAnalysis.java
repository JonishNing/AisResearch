package Commons;



import java.sql.ResultSet;

import Commons.TimeSwitch;

import org.omg.CORBA.PUBLIC_MEMBER;

import Model.AisRecord;
import Model.Grid;
import Model.GridIndex;

public class DataAnalysis {
	
	public static int GridInterval = 3000;//Grid���
	public static final int DAY_PERIOD = 1;//����
	public static final int NIGHT_PERIOD = 2;//��ҹ
	
	/**
	 * ��ú�����
	 * @param longitude
	 * @return
	 */
	public int getLinenum(int longitude){
		int linenum = 0;
		linenum = longitude/GridInterval;		
		return linenum;
	}
	
	/**
	 * ���������
	 * @param latitude
	 * @return
	 */
	public int getRownum(int latitude){
		int rownum;
		rownum = latitude/GridInterval;
		return rownum;
	}
	
	/**
	 * �жϰ����ҹ��
	 * @param longitude
	 * @param latitude
	 * @param GPSTime
	 * @return
	 */
	public int judge_dayornight(int longitude , int latitude, int GPSTime){
		int period = 0;
		TimeSwitch ts = new TimeSwitch();
		int hour =  ts.GPStime2Hour(GPSTime);
		if(hour > 6 && hour <20){
			period = DAY_PERIOD;
		}
		else {
			period = NIGHT_PERIOD;
		}
		return period;
	}
	
	/**
	 * ������ڸ��ӵ����Ͼ�γ��
	 * @param longitude
	 * @param latitude
	 * @return
	 */
	public GridIndex getGridIndex(int longitude, int latitude){
		GridIndex gI = new GridIndex();
		gI.setUpper_left_longitude(longitude - longitude%3000);
		gI.setUpper_right_latitude(latitude - latitude%3000);
		return gI;
		
	}
	
	

}

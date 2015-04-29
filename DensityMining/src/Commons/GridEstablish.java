package Commons;

import java.util.HashMap;
import java.util.Map;

import Model.Grid;


public class GridEstablish {
	
	/**
	 * �����ݿ��й���ȫ�����
	 * @param interval
	 * @param year
	 * @param month
	 */
	public static void EstablishAllGridsInDB(int interval , int year , int month){
		DatabaseConnection dc = new DatabaseConnection();
		double in = ((double)interval / 600000);
		String sql = "";
		for(double i = -180 + in ; i < 180 ; i = i + in){
			for(double j = -90 ; j < 90 ; j = j + in){
				int UpperLeftLongitude = (int)(i * 600000);
				int UpperLeftLatitude = (int)(j * 600000);
				int LowerRightLongitude = UpperLeftLongitude + 12000;
				int LowerRightLatitude = UpperLeftLatitude + 12000;
				sql = "insert into l1_world_grids_latest(Upper_Left_Longitude,Upper_Left_Latitude,Lower_Right_Longitude,"
					+ "Lower_Right_Latitude,Year,Month,CrossingTimeSum,DynamicDataCout,Mean_AIS_Update_Interval,"
					+ "Sigama2_AIS_Update_Interval,Day_Vessel_Avg_Count,Night_Vessel_Avg_Count,Udt_Time)"
					+ "values(" + UpperLeftLongitude + "," + UpperLeftLatitude + "," 
					+ LowerRightLongitude + "," + LowerRightLatitude + ","+ year + "," + month + "," + "0,0,0,0,0,0,now())";
				System.out.println(sql);
				dc.executeUpdate(sql);
			}
		}
	}
	
	/**
	 * �ڹ���ȫ�����ӵ�Map
	 * @param interval
	 * @return
	 */
	public static Map<String, Grid> EstablishAllGridsInMap(int interval , int year , int month){
		//�洢ÿ�����ӵ�ͳ����Ϣ
		Map<String, Grid> map = new HashMap<String, Grid>();
		Grid grid = new Grid();
		double in = ((double)interval / 600000);
		for(double i = -180 + in ; i < 180 ; i = i + in){
			for(double j = -90 ; j < 90 ; j = j + in){
				int UpperLeftLongitude = (int)(i * 600000);
				int UpperLeftLatitude = (int)(j * 600000);
				String str = (UpperLeftLongitude + "," ) + UpperLeftLatitude;
				grid.setUpper_Left_Longitude(UpperLeftLongitude);
				grid.setUpper_Left_Latitude(UpperLeftLatitude);
				grid.setLower_Right_Longitude(UpperLeftLongitude + interval);
				grid.setLower_Right_Latitude(UpperLeftLatitude - interval);
				grid.setYear(year);
				grid.setMonth(month);
				map.put(str, grid);
			}
		}		
		return map;
	}
	public static void main(String[] args){
		
		GridEstablish.EstablishAllGridsInDB(600000, 2014, 1);
	}

}

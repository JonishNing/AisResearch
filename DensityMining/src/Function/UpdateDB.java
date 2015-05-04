package Function;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import Commons.DatabaseConnection;
import Model.Grid;

public class UpdateDB {
	
	public static void update2DB(Map<String, Grid> map){
		DatabaseConnection dc = new DatabaseConnection();
		for(String s:map.keySet()){
			Grid g = map.get(s);
			int Upper_Left_Longitude = g.getUpper_Left_Longitude();
			int Upper_Left_Latitude = g.getUpper_Left_Latitude();
			int CrosingTimeSum = g.getCrossingTimeSum();
			int DynamicDataCout = g.getDynamicDataCout();
			int Mean_AIS_Update_Interval = g.getMean_AIS_Update_Interval();
			int Sigama2_AIS_Update_Interval = g.getSigama2_AIS_Update_Interval();
			int Day_Vessel_Avg_Count = g.getDay_Vessel_Avg_Count();
			int Night_Vessel_Avg_Count = g.getNight_Vessel_Avg_Count();
			String sql = "update l1_world_grids_latest set CrossingTimeSum=" + CrosingTimeSum + " , DynamicDataCout=" + DynamicDataCout
						+ " , Mean_AIS_Update_Interval=" + Mean_AIS_Update_Interval + " , Sigama2_AIS_Update_Interval=" + Sigama2_AIS_Update_Interval
						+ " , Day_Vessel_Avg_Count=" +  Day_Vessel_Avg_Count + " , Night_Vessel_Avg_Count=" + Night_Vessel_Avg_Count + ", Udt_Time" + new Date();
			sql = sql + "where Upper_Left_Longitude=" + Upper_Left_Longitude + "and Upper_Left_Latitude=" + Upper_Left_Latitude;
			dc.executeUpdate(sql);
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

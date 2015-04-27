package Function;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import Commons.DataAnalysis;
import Commons.DataFetch;
import Commons.DatabaseConnection;
import Commons.GridCountFunction;
import Commons.TimeSwitch;
import Model.AisDynamicRecord;
import Model.GridIndex;
import Model.Grid_Summary;
import Model.Route_Segment;

public class Analysis {
	
	/**
	 * 处理航段数据
	 * @param year
	 * @param month
	 */
	public static void AnalysisRoute_SegmentByMonth(int year , int month){
		DataFetch df = new DataFetch();
		DataAnalysis da = new DataAnalysis();
		TimeSwitch ts = new TimeSwitch();
		DatabaseConnection dc = new DatabaseConnection();
		//获得一个月内的所有航段记录
		ArrayList<Route_Segment> rsList = df.getRoute_SegmentList(year, month);
		long starttime = System.currentTimeMillis();
		for(int i = 0; i<200;i++){

			
			routeDataGridCount(dc, rsList, i, year, month);
			
		}
		long end = System.currentTimeMillis();
		long time =( end - starttime )/1000;
		System.out.println(time);

	}
	
	/**
	 * 处理动态Ais数据
	 * @param year
	 * @param month
	 */
	public static void AnalysisAisByMonth(int year , int month){
		TimeSwitch ts = new TimeSwitch();
		DataFetch df = new DataFetch();
		DataAnalysis da = new DataAnalysis();
		DatabaseConnection dc = new DatabaseConnection();
		//获得一个月内的所有原始Ais数据记录
		int longitude = 0;
		int latitude = 0;
		int GPSTime = 0;
		int period = 0;
		//获得该月的天数
		int days_of_month = ts.getDaysofMonth(year, month);
		String sql = "update l1_world_grids_recent set ";
		ArrayList<AisDynamicRecord> aisList = df.getAisDynamicRecordsbyMonth(year, month," ");
		for(Iterator<AisDynamicRecord> iterator = aisList.iterator(); iterator.hasNext();){
			AisDynamicRecord ais = iterator.next();
			longitude = ais.getDRLONGITUDE();
			latitude = ais.getDRLATITUDE();
			GridIndex gi = da.getGridIndex(longitude, latitude);
			int UL_Longitude = gi.getUpper_left_longitude();
			int UL_Latitude = gi.getUpper_left_latitude();
			String sql0 = sql + "DynamicDataCout = DynamicDataCout + 1 " + "where Upper_Left_Longitude="+UL_Longitude+" and Upper_Left_Latitude="+UL_Latitude;
			
			GPSTime = ais.getDRGPSTIME();
			period = da.judge_dayornight(longitude, latitude, GPSTime);			
			if (period == DataAnalysis.DAY_PERIOD) {
				//更新白天字段
//				String sql0 = sql + "DynamicDataCout = DynamicDataCout + 1 "; 
//				sql0 = sql0 + "Day_DynamicDataCout = Day_DynamicDataCout + 1 ";
				sql0 = sql0 + "where Upper_Left_Longitude="+UL_Longitude+" and Upper_Left_Latitude="+UL_Latitude;
				dc.executeUpdate(sql0);
			}
			else if(period == DataAnalysis.NIGHT_PERIOD){
				//更新 黑夜字段
//				String sql0 = sql + "DynamicDataCout = DynamicDataCout + 1 "; 
//				sql0 = sql0 + "Night_DynamicDataCout = Night_DynamicDataCout + 1 ";
				sql0 = sql0 + "where Upper_Left_Longitude="+UL_Longitude+" and Upper_Left_Latitude="+UL_Latitude;
				dc.executeUpdate(sql0);
			}
			
		}
	}

	/**
	 * 
	 * @param db
	 * @param rsList
	 * @param i
	 * @param year
	 * @param month
	 */
	private static void routeDataGridCount(DatabaseConnection db, ArrayList<Route_Segment> rsList, int i, int year, int month) {
		Route_Segment r_s = rsList.get(i);
		GridCountFunction gcf = new GridCountFunction();
		HashSet<Grid_Summary> set = gcf.Grid_SummaryCount(r_s);
		System.out.println(i + "," + "set size = " + set.size());
		Iterator<Grid_Summary> it = set.iterator();
		while(it.hasNext()) {
			Grid_Summary grid = it.next();
			boolean flag = true;
			int Upper_Left_Longitude = grid.getLongitude();
			int Upper_Left_Latitude = grid.getLatitude();
			int Lower_Right_Longitude = Upper_Left_Longitude + 3000;
			int Lower_Right_Latitude = Upper_Left_Latitude + 3000;
			int time = grid.getTime();
			String sql = "select Upper_Left_Longitude,Upper_Left_Latitude from l1_world_grids_history";
			ResultSet rs = db.executeQuery(sql);
			try {
				while(rs.next()) {
					if(Upper_Left_Longitude == rs.getInt("Upper_Left_Longitude") && Upper_Left_Latitude == rs.getInt("Upper_Left_Latitude")) {
						flag = false;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(flag) {
				String sql2 = "insert into l1_world_grids_history(Upper_Left_Longitude,Upper_Left_Latitude,Lower_Right_Longitude,"
						+ "Lower_Right_Latitude,Year,Month,CrossingTimeSum,DynamicDataCout,Mean_AIS_Update_Interval,"
						+ "Sigama2_Upper_AIS_Update_Interval,Day_Vessel_Avg_Count,Night_Vessel_Avg_Count,Day_Vessel_Stddev_Count,"
						+ "Night_Vessel_Stddev_Count,Udt_Time)" + "values(" + Upper_Left_Longitude + "," + Upper_Left_Latitude + "," 
						+ Lower_Right_Longitude + "," + Lower_Right_Latitude + ","+ year + "," + month + "," + time + ",0,0,0,0,0,0,0,now())";
				db.executeUpdate(sql2);
			} else {
				String sql3 = "update l1_world_grids_history set CrossingTimeSum=CrossingTimeSum+" + time + " where Upper_Left_Longitude=" 
						+ Upper_Left_Longitude + "and Upper_Left_Latitude=" + Upper_Left_Latitude;
				db.executeUpdate(sql3);
			}
		}
	}
}

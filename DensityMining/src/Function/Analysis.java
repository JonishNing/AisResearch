package Function;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import Commons.DataAnalysis;
import Commons.DataFetch;
import Commons.DatabaseConnection;
import Commons.GridCountFunction;
import Commons.TimeSwitch;
import Model.AisDynamicRecord;
import Model.Grid;
import Model.GridIndex;
import Model.Grid_Summary;
import Model.Route_Segment;

public class Analysis {
	
	/**
	 * 处理动态Ais数据
	 * @param year
	 * @param month
	 */
	public static Map<String, Grid> AnalysisAisByMonth(int year , int month , int interval , ArrayList<AisDynamicRecord> aisList , Map<String, Grid> map){
		TimeSwitch ts = new TimeSwitch();
		DataAnalysis da = new DataAnalysis();
		GridCountFunction gf = new GridCountFunction();
//		//获得该月的天数
//		int days_of_month = ts.getDaysofMonth(year, month);
//		int Sigama2_AIS_Update_Interval = 0;
		//获取格子信息的List
		ArrayList<Grid_Summary> grid_s = gf.Grid_SummaryCount(aisList, interval);
		//将原始的DynamicDataCount加入Map
		for(Iterator<AisDynamicRecord> iterator = aisList.iterator(); iterator.hasNext();){
			AisDynamicRecord ais = iterator.next();
			int longitude = ais.getDRLONGITUDE();
			int latitude = ais.getDRLATITUDE();
			GridIndex gi = da.getGridIndex(longitude, latitude);
			int UL_Longitude = gi.getUpper_left_longitude();
			int UL_Latitude = gi.getUpper_left_latitude();
			String str = UL_Longitude + "," + UL_Latitude;
//			//记录当前船只的发包数
//			int current_count = 0;
			for(String s: map.keySet()){
				if(str.equals(s)){
					
					Grid grid = map.get(s);
					int count = grid.getDynamicDataCout();
					grid.setDynamicDataCout(++count);
//					current_count++;
					map.put(s, grid);
					break;
				}
			}
//			int current_sigma = current_count / days_of_month;
//			if(Sigama2_AIS_Update_Interval < current_sigma){
//				Sigama2_AIS_Update_Interval = current_count;
//			}
		}	
		//处理经过的格子信息填入Map
		for(Iterator<Grid_Summary> iterator2 = grid_s.iterator(); iterator2.hasNext();){
			Grid_Summary gs = iterator2.next();
			int UL_Longitude2 = gs.getLongitude();
			int UL_Latitude2 = gs.getLatitude();
			String str2 = UL_Longitude2 + "," + UL_Latitude2;
			int day_or_night = gs.getPeriod();
			int time = gs.getTime();
			//插入统计信息
			for(String s: map.keySet()){
				if(str2.equals(s)){						
					Grid grid = map.get(s);
					int CrossingTimeSum = grid.getCrossingTimeSum() + time;
					int Day_Vessel_Count = grid.getDay_Vessel_Count();
					int Night_Vessel_Count = grid.getNight_Vessel_Count();
					if(day_or_night == DataAnalysis.DAY_PERIOD){
						grid.setCrossingTimeSum(CrossingTimeSum);
						grid.setDay_Vessel_Count(++Day_Vessel_Count);
						map.put(s, grid);
						break;
					}
					else if(day_or_night == DataAnalysis.NIGHT_PERIOD){
						grid.setCrossingTimeSum(CrossingTimeSum);	
						grid.setNight_Vessel_Count(++Night_Vessel_Count);
						map.put(s, grid);
						break;
					}					
				}	
			}
		}			
		return map;
	}

	/**
	 * 处理中间结果得到填表信息
	 * @param year
	 * @param month
	 * @param map
	 * @return
	 */
	public static Map<String, Grid> GetSumOfAis(int year , int month , Map<String, Grid> map){
		TimeSwitch ts = new TimeSwitch();
		//获得该月的天数
		int days_of_month = ts.getDaysofMonth(year, month);
		for(String s:map.keySet()){
			Grid grid = map.get(s);
			if(grid.getDay_Vessel_Count() == 0 &&grid.getNight_Vessel_Count() == 0){
				continue;					}
				int Mean_AIS_Update_Interval = grid.getCrossingTimeSum() / grid.getDynamicDataCout();
				int Day_Vessel_Avg_Count = grid.getDay_Vessel_Count() / days_of_month;
				int Night_Vessel_Avg_Count = grid.getNight_Vessel_Count() / days_of_month;
				grid.setMean_AIS_Update_Interval(Mean_AIS_Update_Interval);
				grid.setDay_Vessel_Avg_Count(Day_Vessel_Avg_Count);
				grid.setNight_Vessel_Avg_Count(Night_Vessel_Avg_Count);
				map.put(s, grid);
			}
		return map;
	}
	
	
	/**
	 * 处理航段数据(Pass)
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

			
//			routeDataGridCount(dc, rsList, i, year, month);
			
		}
		long end = System.currentTimeMillis();
		long time =( end - starttime )/1000;
		System.out.println(time);

	}
	

	/**
	 * (pass 只看思想)
	 * @param db
	 * @param rsList
	 * @param i
	 * @param year
	 * @param month
	 */
//	private static void routeDataGridCount(DatabaseConnection db, ArrayList<Route_Segment> rsList, int i, int year, int month) {
//		Route_Segment r_s = rsList.get(i);
//		GridCountFunction gcf = new GridCountFunction();
//		HashSet<Grid_Summary> set = gcf.Grid_SummaryCount(r_s);
//		System.out.println(i + "," + "set size = " + set.size());
//		Iterator<Grid_Summary> it = set.iterator();
//		while(it.hasNext()) {
//			Grid_Summary grid = it.next();
//			boolean flag = true;
//			int Upper_Left_Longitude = grid.getLongitude();
//			int Upper_Left_Latitude = grid.getLatitude();
//			int Lower_Right_Longitude = Upper_Left_Longitude + 3000;
//			int Lower_Right_Latitude = Upper_Left_Latitude + 3000;
//			int time = grid.getTime();
//			String sql = "select Upper_Left_Longitude,Upper_Left_Latitude from l1_world_grids_history";
//			ResultSet rs = db.executeQuery(sql);
//			try {
//				while(rs.next()) {
//					if(Upper_Left_Longitude == rs.getInt("Upper_Left_Longitude") && Upper_Left_Latitude == rs.getInt("Upper_Left_Latitude")) {
//						flag = false;
//					}
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			if(flag) {
//				String sql2 = "insert into l1_world_grids_history(Upper_Left_Longitude,Upper_Left_Latitude,Lower_Right_Longitude,"
//						+ "Lower_Right_Latitude,Year,Month,CrossingTimeSum,DynamicDataCout,Mean_AIS_Update_Interval,"
//						+ "Sigama2_Upper_AIS_Update_Interval,Day_Vessel_Avg_Count,Night_Vessel_Avg_Count,Day_Vessel_Stddev_Count,"
//						+ "Night_Vessel_Stddev_Count,Udt_Time)" + "values(" + Upper_Left_Longitude + "," + Upper_Left_Latitude + "," 
//						+ Lower_Right_Longitude + "," + Lower_Right_Latitude + ","+ year + "," + month + "," + time + ",0,0,0,0,0,0,0,now())";
//				db.executeUpdate(sql2);
//			} else {
//				String sql3 = "update l1_world_grids_history set CrossingTimeSum=CrossingTimeSum+" + time + " where Upper_Left_Longitude=" 
//						+ Upper_Left_Longitude + "and Upper_Left_Latitude=" + Upper_Left_Latitude;
//				db.executeUpdate(sql3);
//			}
//		}
//	}
}

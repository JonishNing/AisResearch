package Commons;

public class GridEstablish {
	
	/**
	 * 构建全球格子
	 * @param interval
	 * @param year
	 * @param month
	 */
	public static void EstablishAllGrids(int interval , int year , int month){
		DatabaseConnection dc = new DatabaseConnection();
		double in = ((double)interval / 600000);
		int maxLongitude = 180 * 600000;
		int maxLatitude = 90 * 600000;
		String sql = "";
		for(double i = -180.02 ; i < 180 ; i = i + in){
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
//				System.out.println(sql);
				dc.executeUpdate(sql);
			}
		}
	}
	
	public static void main(String[] args){
		
		GridEstablish.EstablishAllGrids(800000, 2013, 11);
	}

}

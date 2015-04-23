package Function;

import java.util.ArrayList;
import java.util.Iterator;

import Commons.DataAnalysis;
import Commons.DataFetch;
import Commons.DatabaseConnection;
import Commons.TimeSwitch;
import Model.AisDynamicRecord;
import Model.GridIndex;
import Model.Route_Segment;

public class Analysis {
	
	/**
	 * ����������
	 * @param year
	 * @param month
	 */
	public void AnalysisByMonth(int year , int month){
		DataFetch df = new DataFetch();
		DataAnalysis da = new DataAnalysis();
		TimeSwitch ts = new TimeSwitch();
		DatabaseConnection dc = new DatabaseConnection();
		//���һ�����ڵ����к��μ�¼
		ArrayList<Route_Segment> rsList = df.getRoute_SegmentList(year, month);
		for(int i = 0; i<rsList.size();i++){
			Route_Segment r_s = rsList.get(i);
			//�����ʼ�����ֹ��ľ�γ��
			int current_start_longtitude = r_s.getStart_Latitude();
			int current_end_longitude = r_s.getEnd_Longitude();
			int current_start_latitude = r_s.getStart_Latitude();
			int current_end_latitude = r_s.getEnd_Latitude();
			//��ø�����¼�������ĸ��ӵ����ϽǾ�γ�Ⱥ���ÿ��������ͣ����ʱ�䲢���µ����ݿ�
			{
				//WHY
			}
			
		}


	}
	
	/**
	 * ����̬Ais����
	 * @param year
	 * @param month
	 */
	public void AnalysisAisByMonth(int year , int month){
		TimeSwitch ts = new TimeSwitch();
		DataFetch df = new DataFetch();
		DataAnalysis da = new DataAnalysis();
		DatabaseConnection dc = new DatabaseConnection();
		//���һ�����ڵ�����ԭʼAis���ݼ�¼
		int longitude = 0;
		int latitude = 0;
		int GPSTime = 0;
		int period = 0;
		//��ø��µ�����
		int days_of_month = ts.getDaysofMonth(year, month);
		String sql = "update l1_world_grids_recent set ";
		ArrayList<AisDynamicRecord> aisList = df.getAisDynamicRecordsbyMonth(year, month);
		for(Iterator<AisDynamicRecord> iterator = aisList.iterator(); iterator.hasNext();){
			AisDynamicRecord ais = iterator.next();
			longitude = ais.getDRLONGITUDE();
			latitude = ais.getDRLATITUDE();
			GridIndex gi = da.getGridIndex(longitude, latitude);
			int UL_Longitude = gi.getUpper_left_longitude();
			int UL_Latitude = gi.getUpper_left_latitude();
			GPSTime = ais.getDRGPSTIME();
			period = da.judge_dayornight(longitude, latitude, GPSTime);			
			if (period == DataAnalysis.DAY_PERIOD) {
				//���°����ֶ�
				String sql0 = sql + "DynamicDataCout = DynamicDataCout + 1 "; 
//				sql0 = sql0 + "Day_DynamicDataCout = Day_DynamicDataCout + 1 ";
				sql0 = sql0 + "where Upper_Left_Longitude="+UL_Longitude+" and Upper_Left_Latitude="+UL_Latitude;
				dc.executeUpdate(sql0);
			}
			else if(period == DataAnalysis.NIGHT_PERIOD){
				//���� ��ҹ�ֶ�
				String sql0 = sql + "DynamicDataCout = DynamicDataCout + 1 "; 
//				sql0 = sql0 + "Night_DynamicDataCout = Night_DynamicDataCout + 1 ";
				sql0 = sql0 + "where Upper_Left_Longitude="+UL_Longitude+" and Upper_Left_Latitude="+UL_Latitude;
				dc.executeUpdate(sql0);
			}
			
		}
	}
	public static void main(String[] args){

	}
}

package Function;

import java.util.ArrayList;

import Commons.DataAnalysis;
import Commons.DataFetch;
import Model.GridIndex;
import Model.Route_Segment;

public class Analysis {
	
	public static void main(String[] args){
		DataFetch df = new DataFetch();
		DataAnalysis da = new DataAnalysis();
		//���һ�����ڵ����к��μ�¼
		ArrayList<Route_Segment> rsList = df.getRoute_SegmentList(2013, 11);
		for(int i = 0; i<rsList.size();i++){
			Route_Segment r_s = rsList.get(i);
			//�����ʼ�����ֹ��ľ�γ��
			int current_start_longtitude = r_s.getStart_Latitude();
			int current_end_longitude = r_s.getEnd_Longitude();
			int current_start_latitude = r_s.getStart_Latitude();
			int current_end_latitude = r_s.getEnd_Latitude();
			//��ø�����¼�������ĸ��Ӻ���ÿ��������ͣ����ʱ��
			{
				//WHY
			}
			//�����ʼ�����ֹ��ľ�γ�����ڵĸ�����Ϣ
			GridIndex StartGridIndex = da.getGridIndex(current_start_longtitude, current_start_latitude);
			GridIndex endGridIndex = da.getGridIndex(current_end_longitude, current_end_latitude);
		}

	}
}

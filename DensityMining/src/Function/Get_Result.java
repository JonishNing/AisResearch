package Function;

import java.util.ArrayList;

import Commons.DataAnalysis;
import Commons.DataFetch;
import Commons.DatabaseConnection;
import Commons.GridCountFunction;
import Commons.TimeSwitch;
import Model.AisDynamicRecord;

public class Get_Result {

	public static void main(String[] args){
		
		int year = 2013;
		int month = 11;
//		TimeSwitch ts = new TimeSwitch();
//		DataAnalysis da = new DataAnalysis();
//		DataFetch df = new DataFetch();
//		DatabaseConnection dc = new DatabaseConnection();
//		GridCountFunction gf = new GridCountFunction();
		
		//������
		Analysis.AnalysisRoute_SegmentByMonth(year, month);
		
		//����ԭʼ����
		Analysis.AnalysisAisByMonth(year, month);
		
		
		
	}
}

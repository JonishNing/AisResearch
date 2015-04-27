package Function;

import java.util.ArrayList;

import Commons.DataAnalysis;
import Commons.DataFetch;
import Commons.DatabaseConnection;
import Commons.GridCountFunction;
import Commons.GridEstablish;
import Commons.TimeSwitch;
import Model.AisDynamicRecord;

public class Get_Result {

	public static void main(String[] args){
		int interval = 12000;
		int year = 2013;
		int month = 11;

		
		//构建格子		
		GridEstablish.EstablishAllGrids(interval, year, month);
	
		//处理原始数据
		Analysis.AnalysisAisByMonth(year, month);
		
		
		
	}
}

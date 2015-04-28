package Function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Commons.DataAnalysis;
import Commons.DataFetch;
import Commons.DatabaseConnection;
import Commons.GridCountFunction;
import Commons.GridEstablish;
import Commons.TimeSwitch;
import Model.AisDynamicRecord;
import Model.Grid;
import Model.GridIndex;

public class Get_Result {

	public static void main(String[] args){
		int interval = 600000;
		int year = 2014;
		int month = 1;
		String[] MMSI = {"413359060","574001180","412009000","413442000","412060070"};
		//存储每个格子的统计信息
		Map<String, Grid> map = GridEstablish.EstablishAllGridsInMap(interval,year,month);		
		//在数据库中构建格子	
		GridEstablish.EstablishAllGridsInDB(interval, year, month);
		//依次处理每个船的信息
		for(int i = 0 ; i <MMSI.length ; i++){
			//获取数据
			ArrayList<AisDynamicRecord> aisList = DataFetch.getAisDynamicRecordsbyMonth(year, month, MMSI[i]);
		
			//原始处理
			map = Analysis.AnalysisAisByMonth(year, month,aisList,map);
		}

		//将结果插入数据库
		UpdateDB.update2DB(map);
		
	}
}

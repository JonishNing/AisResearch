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
		//�洢ÿ�����ӵ�ͳ����Ϣ
		Map<String, Grid> map = GridEstablish.EstablishAllGridsInMap(interval,year,month);		
		//�����ݿ��й�������	
		GridEstablish.EstablishAllGridsInDB(interval, year, month);
		//���δ���ÿ��������Ϣ
		for(int i = 0 ; i <MMSI.length ; i++){
			//��ȡ����
			ArrayList<AisDynamicRecord> aisList = DataFetch.getAisDynamicRecordsbyMonth(year, month, MMSI[i]);
		
			//ԭʼ����
			map = Analysis.AnalysisAisByMonth(year, month,aisList,map);
		}

		//������������ݿ�
		UpdateDB.update2DB(map);
		
	}
}

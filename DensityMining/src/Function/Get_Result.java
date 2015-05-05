package Function;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;

import Commons.DataAnalysis;
import Commons.DataFetch;
import Commons.DatabaseConnection;
import Commons.GridCountFunction;
import Commons.GridEstablish;
import Commons.TimeSwitch;
import Model.AisDynamicRecord;
import Model.Grid;
import Model.GridIndex;
import Model.Grid_Summary;
import Model.Route_Segment;

public class Get_Result 
{

	public static void main(String[] args)
	{
		int interval = 600000;
		int year = 2014;
		int month = 1;
		
		long start = System.currentTimeMillis();
		
		//����MMSI�ļ���
		String[] MMSI = {"413359060","574001180","412009000","413442000","412060070"};
		double start_of_gridmap = System.currentTimeMillis();
		//�洢ÿ�����ӵ�ͳ����Ϣ
		Map<String, Grid> map = GridEstablish.EstablishAllGridsInMap(interval,year,month);		
		double end_of_gridmap = System.currentTimeMillis();
		System.out.println("Time for Establish the MapGrid is" + ((end_of_gridmap - start_of_gridmap)/1000) + "s");
		System.out.println();
		//�����ݿ��й�������
		double start_of_griddb = System.currentTimeMillis();
		GridEstablish.EstablishAllGridsInDB(interval, year, month);
		double end_of_griddb = System.currentTimeMillis();
		System.out.println("Time for Establish the MapGrid in DB is" + ((end_of_griddb - start_of_griddb)/1000) + "s");
		System.out.println();
		//���δ���ÿ��������Ϣ
		for(int i = 0 ; i <MMSI.length ; i++)
		{
			//��ȡ����
			double start_of_MMSI = System.currentTimeMillis();
			ArrayList<AisDynamicRecord> aisList = DataFetch.getAisDynamicRecordsbyMonth(year, month, MMSI[i]);
			double end_of_MMSI = System.currentTimeMillis();
			System.out.println("Time for Get " + MMSI[i] + " Ship is" + ((end_of_MMSI - start_of_MMSI)/1000) + "s" );
			//ԭʼ����
			double start_of_analysisais = System.currentTimeMillis();
			map = Analysis.AnalysisAisByMonth(year, month,interval, aisList,map);
			double end_of_anlysisais = System.currentTimeMillis();
			System.out.println("Time for analysis " + MMSI[i] + " Ship is" + ((end_of_anlysisais - start_of_analysisais)/1000) + "s");
			System.out.println();
		}
		//�õ�ͳ�ƽ��
		double start_of_getSUM = System.currentTimeMillis();
		map = Analysis.GetSumOfAis(year, month, map);
		double end_of_getSUM = System.currentTimeMillis();
		System.out.println("Time for getSUM is " + ((end_of_getSUM - start_of_getSUM)/1000) + "s");
		System.out.println();
		//������������ݿ�
		double start_of_updatedb = System.currentTimeMillis();
		UpdateDB.update2DB(map);
		double end_of_updatedb = System.currentTimeMillis();
		System.out.println("Time for updateDB is " + ((end_of_updatedb - start_of_updatedb)/1000) + "s");
		System.out.println();
		long end = System.currentTimeMillis();
		System.out.println("The whole time is " + ((end - start)/1000) + "s");
		System.out.println();
	}
}

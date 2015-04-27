package Commons;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import Model.AisDynamicRecord;
import Model.Route_Segment;

public class DataFetch {
	
	//原始Ais数据部分
	List<AisDynamicRecord> AISDYNAMICList = new ArrayList<AisDynamicRecord>();
	String sql1 = "select DRGPSTIME,DRTERMINALCODE,DRLATITUDE,DRLONGITUDE from aisdynamiclog";
	int GpsTime = 0;
	String TerminalCode = new String();
	int Latitude = 0;
	int Longitude = 0;
	int Speed = 0;
	int Status = 0;
	int rot = 0;
	String Message = new String();
	int Ais_Source = 0;
	
	/**
	 * 获取一个月内某条船（MMSI）的所有原始数据
	 * @param year
	 * @param month
	 * @return
	 */
	public ArrayList<AisDynamicRecord> getAisDynamicRecordsbyMonth(int year , int month, String mmsi){
		TimeSwitch ts = new TimeSwitch();
		int start  = ts.getStartGPStime(year, month);
		int end = ts.getEndGPStime(year, month);
		sql1 = sql1 + " where DRTERMINALCODE ='"+mmsi+"' and DRGPSTIME>"+start+" and "+" DRGPSTIME<"+end;
		sql1 = sql1 + " order by DRGPSTIME ASC";
		System.out.println(sql1);
		DatabaseConnection db = new DatabaseConnection();
		ResultSet rs=db.executeQuery(sql1);
		
		try {
			while(rs.next()){
				AisDynamicRecord ais = new AisDynamicRecord();
				GpsTime = rs.getBigDecimal(1).intValue();
				TerminalCode = rs.getString(2);
//				RecordType = rs.getBigDecimal(3).intValue();
//				Commtype = rs.getBigDecimal(4).intValue();
//				RcvTime = rs.getBigDecimal(5).intValue();
				Latitude = rs.getBigDecimal(3).intValue();
				Longitude = rs.getBigDecimal(4).intValue();
//				Direction = rs.getBigDecimal(8).intValue();
//				TrueHeading = rs.getBigDecimal(9).intValue();
//				Speed = rs.getBigDecimal(10).intValue();
//				Status = rs.getBigDecimal(11).intValue();
//				rot = rs.getBigDecimal(12).intValue();
//				Message = "unknown";
//				Ais_Source = rs.getBigDecimal(14).intValue();
				ais.setDRGPSTIME(GpsTime);
				ais.setDRTERMINALCODE(TerminalCode);
//				ais.setDRRECORDTYPE(RecordType);
//				ais.setDRCOMMTYPE(Commtype);
//				ais.setDRRCVTIME(RcvTime);
				ais.setDRLATITUDE(Latitude);
				ais.setDRLONGITUDE(Longitude);
//				ais.setDRDIRECTION(Direction);
//				ais.setDRTRUEHEADING(TrueHeading);
//				ais.setDRSPEED(Speed);
//				ais.setDRSTATUS(Status);
//				ais.setDRROT(rot);
//				ais.setDRMESSAGE(Message);
//				ais.setAIS_SOURCE(Ais_Source);
				AISDYNAMICList.add(ais);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return (ArrayList<AisDynamicRecord>)AISDYNAMICList;
	}
	
	//航段信息部分
	List<Route_Segment> route_Segment_List = new ArrayList<Route_Segment>();
	String sql2 = "select ID,Ship_ID,Start_Longitude,Start_Latitude,Start_Datetime,End_Longitude,End_Latitude,End_Date_Time,Dynamic_Data_Count from l1_0_route_segment ";
	int ID = 0;
	int Ship_ID = 0;
	int Start_Longitude = 0;
	int Start_Latitude = 0;
	Timestamp Start_Datetime = new Timestamp(0);
	int End_Longitude = 0;
	int End_Latitude = 0;
	Timestamp End_Date_Time = new Timestamp(0);
	int Dynamic_Data_Count = 0;
	
	public ArrayList<Route_Segment> getRoute_SegmentList(int year , int month){
		DateTime startDateTime = new DateTime(year,month,1,0,0,0); 
		DateTime endDateTime = new DateTime(year, month+1, 1, 0, 0, 0);
		Date startDate = startDateTime.toDate();
		Date endDate = endDateTime.toDate();
		DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String start = sdf.format(startDate);
		String end = sdf.format(endDate);
		sql2 = sql2 + " where End_Date_Time>'"+start+"' and End_Date_Time<'"+end+"'";
		sql2 = sql2 + "order by Ship_ID";
		System.out.println(sql2);
		DatabaseConnection db = new DatabaseConnection();
		ResultSet rs=db.executeQuery(sql2);
		
		try {
			while(rs.next()){
				Route_Segment route_Segment = new Route_Segment();
				ID = rs.getInt("ID");
				Ship_ID = rs.getInt("Ship_ID");
				Start_Longitude = rs.getInt("Start_Longitude");
				Start_Latitude = rs.getInt("Start_Latitude");
				Start_Datetime = rs.getTimestamp("Start_Datetime");
				End_Longitude = rs.getInt("End_Longitude");
				End_Latitude = rs.getInt("End_Latitude");
				End_Date_Time = rs.getTimestamp("End_Date_Time");
				Dynamic_Data_Count = rs.getInt("Dynamic_Data_Count");
				route_Segment.setID(ID);
				route_Segment.setShip_ID(Ship_ID);
				route_Segment.setStart_Longitude(Start_Longitude);
				route_Segment.setStart_Latitude(Start_Latitude);
				route_Segment.setStart_Datetime(Start_Datetime);
				route_Segment.setEnd_Longitude(End_Longitude);
				route_Segment.setEnd_Latitude(End_Latitude);
				route_Segment.setEnd_Date_Time(End_Date_Time);
				route_Segment.setDynamic_Data_Count(Dynamic_Data_Count);
				route_Segment_List.add(route_Segment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (ArrayList<Route_Segment>)route_Segment_List;
	}


	
	public static void main(String args[]){
		
		DataFetch df = new DataFetch();
		ArrayList<AisDynamicRecord> aislist = (ArrayList<AisDynamicRecord>)df.getAisDynamicRecordsbyMonth(2013, 4,"413359060");
		System.out.println("arraylist.size()="+aislist.size());
		ArrayList<Route_Segment> rlist = (ArrayList<Route_Segment>)df.getRoute_SegmentList(2013, 4);
		System.out.println("rlist.size()=" + rlist.size());
		
	}
}

package Commons;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.AisDynamicRecord;

public class DataFetch {
	
	
	ArrayList<AisDynamicRecord> AISDYNAMICList = new ArrayList<AisDynamicRecord>();
	String sql = "select * from aisdynamiclog";
	int GpsTime = 0;
	String TerminalCode = new String();
	int RecordType = 0;
	int Commtype = 0;
	int RcvTime = 0;
	int Latitude = 0;
	int Longitude = 0;
	int Direction = 0;
	int TrueHeading = 0;
	int Speed = 0;
	int Status = 0;
	int rot = 0;
	String Message = new String();
	int Ais_Source = 0;
	
	public List<AisDynamicRecord> getAisDynamicRecords(){
		
		DatabaseConnection db = new DatabaseConnection();
		ResultSet rs=db.executeQuery(sql);
		
		try {
			while(rs.next()){
				AisDynamicRecord ais = new AisDynamicRecord();
				GpsTime = rs.getBigDecimal(1).intValue();
				TerminalCode = rs.getString(2);
				RecordType = rs.getBigDecimal(3).intValue();
				Commtype = rs.getBigDecimal(4).intValue();
				RcvTime = rs.getBigDecimal(5).intValue();
				Latitude = rs.getBigDecimal(6).intValue();
				Longitude = rs.getBigDecimal(7).intValue();
				Direction = rs.getBigDecimal(8).intValue();
				TrueHeading = rs.getBigDecimal(9).intValue();
				Speed = rs.getBigDecimal(10).intValue();
				Status = rs.getBigDecimal(11).intValue();
				rot = rs.getBigDecimal(12).intValue();
				Message = "unknown";
				Ais_Source = rs.getBigDecimal(14).intValue();
				ais.setDRGPSTIME(GpsTime);
				ais.setDRTERMINALCODE(TerminalCode);
				ais.setDRRECORDTYPE(RecordType);
				ais.setDRCOMMTYPE(Commtype);
				ais.setDRRCVTIME(RcvTime);
				ais.setDRLATITUDE(Latitude);
				ais.setDRLONGITUDE(Longitude);
				ais.setDRDIRECTION(Direction);
				ais.setDRTRUEHEADING(TrueHeading);
				ais.setDRSPEED(Speed);
				ais.setDRSTATUS(Status);
				ais.setDRROT(rot);
				ais.setDRMESSAGE(Message);
				ais.setAIS_SOURCE(Ais_Source);
				AISDYNAMICList.add(ais);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return AISDYNAMICList;
	}


	
	public static void main(String args[]){
		
		DataFetch df = new DataFetch();
		ArrayList<AisDynamicRecord> aislist = (ArrayList<AisDynamicRecord>)df.getAisDynamicRecords();
		System.out.println("arraylist.size()="+aislist.size());
	}
}

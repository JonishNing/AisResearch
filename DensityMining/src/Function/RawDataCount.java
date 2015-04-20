package Function;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Commons.DatabaseConnection;
import Model.AisRecord;
import Model.Grid;

public class RawDataCount {
	
	public ResultSet getRawdataBymonth(int month , int year){
		//确定起始时间
		Date startDate = new Date();
		Date endDate = new Date();
		startDate.setMonth(month);
		startDate.setYear(year);
		endDate.setMonth(month);
		endDate.setYear(year);
		
		String sql = "select * from aisdynamiclog where DRGPSTIME>="+startDate+" DRGPSTIME<="+endDate;
		DatabaseConnection db= new DatabaseConnection();
		ResultSet rs = db.executeQuery(sql);
		return rs;
	}
	
	public Map<Grid, AisRecord> setStore(){
		
		
		return null;
	}
	
	public static void main(String[] args){
		RawDataCount rd = new RawDataCount();
		ResultSet rsResultSet = rd.getRawdata();
		int id = 0;
		try {
			while(rsResultSet.next()){
				id = rsResultSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("id = "+ id);
	}

}

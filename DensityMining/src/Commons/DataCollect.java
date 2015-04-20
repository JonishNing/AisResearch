package Commons;



import java.sql.ResultSet;

import org.omg.CORBA.PUBLIC_MEMBER;

import Model.AisRecord;

public class DataCollect {
	
	public static int GridInterval = 3000;//Grid¿ç¶È
	
	
	public int getLinenum(int longitude){
		int linenum = 0;
		linenum = longitude/GridInterval;		
		return linenum;
	}
	
	public int getRownum(int latitude){
		int rownum;
		rownum = latitude/GridInterval;
		return rownum;
	}
		
	

}

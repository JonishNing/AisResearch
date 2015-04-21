package Model;

import java.sql.Timestamp;

public class Route_Segment {
	
	private int ID;
	private int Ship_ID;
	private int Start_Longitude;
	private int Start_Latitude;
	private Timestamp Start_Datetime;
	private int End_Longitude;
	private int End_Latitude;
	private Timestamp End_Date_Time;
	private int Dynamic_Data_Count;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getShip_ID() {
		return Ship_ID;
	}
	public void setShip_ID(int ship_ID) {
		Ship_ID = ship_ID;
	}
	public int getStart_Longitude() {
		return Start_Longitude;
	}
	public void setStart_Longitude(int start_Longitude) {
		Start_Longitude = start_Longitude;
	}
	public int getStart_Latitude() {
		return Start_Latitude;
	}
	public void setStart_Latitude(int start_Latitude) {
		Start_Latitude = start_Latitude;
	}
	public Timestamp getStart_Datetime() {
		return Start_Datetime;
	}
	public void setStart_Datetime(Timestamp start_Datetime) {
		Start_Datetime = start_Datetime;
	}
	public int getEnd_Longitude() {
		return End_Longitude;
	}
	public void setEnd_Longitude(int end_Longitude) {
		End_Longitude = end_Longitude;
	}
	public int getEnd_Latitude() {
		return End_Latitude;
	}
	public void setEnd_Latitude(int end_Latitude) {
		End_Latitude = end_Latitude;
	}
	public Timestamp getEnd_Date_Time() {
		return End_Date_Time;
	}
	public void setEnd_Date_Time(Timestamp end_Date_Time) {
		End_Date_Time = end_Date_Time;
	}
	public int getDynamic_Data_Count() {
		return Dynamic_Data_Count;
	}
	public void setDynamic_Data_Count(int dynamic_Data_Count) {
		Dynamic_Data_Count = dynamic_Data_Count;
	}
	
	
	

}

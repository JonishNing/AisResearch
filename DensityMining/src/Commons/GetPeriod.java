package Commons;

import java.sql.Timestamp;

import Model.Grid_Summary;
import Commons.TimeSwitch;
import Commons.DataAnalysis;

public class GetPeriod
{
	public int getPeriod(Grid_Summary Summary,TimeSwitch Switch,DataAnalysis Analysis){
		int period;
		period=Analysis.judge_dayornight(Summary.getLongitude(), Summary.getLatitude(), Switch.getLocalTime(Summary.getGPSTime(), Summary.getLongitude()));
		return period;
	}
	
	public static void main(String [] args)
	{
		GetPeriod Period=new GetPeriod();
		Grid_Summary Summary=new Grid_Summary(1500000,200000,10000,468562301);
		TimeSwitch Switch=new TimeSwitch();
		System.out.println(Switch.getLocalTime(Summary.getGPSTime(), Summary.getLongitude()));
		System.out.println(new Timestamp(468533501));
		DataAnalysis Analysis=new DataAnalysis();
		//System.out.println(Analysis.judge_dayornight(Summary.getLongitude(), Summary.getLatitude(), Switch.getLocalTime(Summary.getGPSTime(), Summary.getLongitude())));
		//System.out.println(Summary.getGPSTime());
		System.out.println(Period.getPeriod(Summary, Switch, Analysis));
		
		
	}
}

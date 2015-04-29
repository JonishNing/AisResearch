package Commons;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

import Model.Grid;
import Model.Grid_Summary;
import Model.Route_Segment;

public class GridCountFunction {

	public static void main(String[] args) {
		Grid_Summary a = new Grid_Summary(1500,2000,1000);
		Grid_Summary b = new Grid_Summary(7000,8000,2000);
		Route_Segment route = new Route_Segment();
		route.setStart_Longitude(73512262);
		route.setStart_Latitude(18620531);
		route.setStart_Datetime(new Timestamp(1000));
		route.setEnd_Longitude(73508431);
		route.setEnd_Latitude(18615667);
		route.setEnd_Date_Time(new Timestamp(2000));
		HashSet<Grid_Summary> set = new HashSet<Grid_Summary>();
		GridCountFunction gc = new GridCountFunction();
		set = gc.Grid_SummaryCount(route);
		System.out.println("set size :" + set.size());
		Iterator<Grid_Summary> it = set.iterator();
		while(it.hasNext()) {
			Grid_Summary g = it.next();
			System.out.println(g);
		}
	}
	public HashSet<Grid_Summary> Grid_SummaryCount(Route_Segment route_segment) {
		HashSet<Grid_Summary> set = new HashSet<Grid_Summary>();
		ArrayList<Grid_Summary> list = new ArrayList<Grid_Summary>();
		int start_Longitude = route_segment.getStart_Longitude();
		int start_Latitude = route_segment.getStart_Latitude();
		int end_Longitude = route_segment.getEnd_Longitude();
		int end_Latitude = route_segment.getEnd_Latitude();
		int start_Time = (int)route_segment.getStart_Datetime().getTime();
		int end_Time = (int)route_segment.getEnd_Date_Time().getTime();
		Date date = route_segment.getStart_Datetime();
		int timeInterval = 0;
		if (start_Longitude > end_Longitude) {
			int x = start_Longitude;
			start_Longitude = end_Longitude;
			end_Longitude = x;
			int y = start_Latitude;
			start_Latitude = end_Latitude;
			end_Latitude = y;
			int t = start_Time;
			start_Time = end_Time;
			end_Time = t;
		}
		for (int x = start_Longitude; x <= end_Longitude ; x += 400) {
			Grid_Summary Grid_Summary = new Grid_Summary();
			int y = lineFunction(start_Longitude, start_Latitude, end_Longitude, end_Latitude, x);
			y = (int)(Math.floor(y) / 3000) * 3000 + 3000;
			int xx = (int)(x / 3000 ) * 3000;
			Grid_Summary.setLongitude(xx);                                                                                     
			Grid_Summary.setLatitude(y);
//			Grid_Summary.setDate(date);
			list.add(Grid_Summary);
			set.add(Grid_Summary);
		}
		int size = list.size();
		int count = 1;
		timeInterval = Math.abs(end_Time - start_Time);
		Iterator<Grid_Summary> it = set.iterator();
		int i = 0;
		while(i < list.size()) {
			i = 0;
			count = 1;
			for(int j=i+1; j<list.size(); j++) {
				if(list.get(i).equals(list.get(j))) {
					count ++;
				} else {
					break;
				}
			}
			if(it.hasNext()) { 
				int time = timeInterval * count / size;
				it.next().setTime(time);
			}
			for(int k=i; k<count; k++) {
				Grid_Summary l = list.remove(0);
			}
		}
		return set;
	}

	private int lineFunction(int start_Longitude, int start_Latitude, int end_Longitude, int end_Latitude, int x) {
		int y = 0;
		y = (x - start_Longitude) * (end_Latitude - start_Latitude) / (end_Longitude - start_Longitude) + start_Latitude;
		return y;
	}
}

package Commons;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import Model.Grid_Summary;

public class GridCountFunction {

	public static void main(String[] args) {
		Grid_Summary a = new Grid_Summary(1500,2000,1000);
		Grid_Summary b = new Grid_Summary(7000,8000,2000);
		HashSet<Grid_Summary> set = new HashSet<Grid_Summary>();
		GridCountFunction gc = new GridCountFunction();
		set = gc.Grid_SummaryCount(a, b);
		System.out.println("set size :" + set.size());
		Iterator<Grid_Summary> it = set.iterator();
		while(it.hasNext()) {
			Grid_Summary g = it.next();
			System.out.println(g);
		}
	}
	public HashSet<Grid_Summary> Grid_SummaryCount(Grid_Summary a, Grid_Summary b) {

		HashSet<Grid_Summary> set = new HashSet<Grid_Summary>();
		ArrayList<Grid_Summary> list = new ArrayList<Grid_Summary>();
		double x1 = a.getX();
		double y1 = a.getY();
		double x2 = b.getX();
		double y2 = b.getY();
		int time1 = a.getTime();
		int time2 = b.getTime();
		int timeInterval = 0;
		
		
		if (x1 > x2) {
			double x = x1;
			x1 = x2;
			x2 = x;
			double y = y1;
			y1 = y2;
			y2 = y;
			int t = time1;
			time1 = time2;
			time2 = t;
		}
		for (double x = x1; x <= x2 ; x += 400) {
			Grid_Summary Grid_Summary = new Grid_Summary();
			double y = lineFunction(x1, y1, x2, y2, x);
			y = (int)(Math.floor(y) / 3000) * 3000 + 3000;
			double xx = (int)(x / 3000 ) * 3000;
			Grid_Summary.setX(xx);                                                                                        
			Grid_Summary.setY(y);
			list.add(Grid_Summary);
			set.add(Grid_Summary);
		}
		int size = list.size();
		int count = 1;
		timeInterval = time2 - time1;
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

	private double lineFunction(double x1, double y1, double x2, double y2, double x) {
		double y = 0.0;
		y = (x - x1) * (y2 - y1) / (x2 - x1) + y1;
		return y;
	}
}

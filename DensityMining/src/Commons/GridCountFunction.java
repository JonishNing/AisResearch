package Commons;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

import Function.Analysis;
import Model.AisDynamicRecord;
import Model.Grid_Summary;
import Model.Route_Segment;

public class GridCountFunction
{

	public static void main(String[] args)
	{
		AisDynamicRecord a = new AisDynamicRecord(1388505626,12107237,66098388);
		AisDynamicRecord b = new AisDynamicRecord(1388507564,12086775,66046279);
		AisDynamicRecord c = new AisDynamicRecord(1388510847,12009373,66029932);
		AisDynamicRecord d = new AisDynamicRecord(1388512345,11992552,66018439);
		AisDynamicRecord e = new AisDynamicRecord(1388513060,11992597,66018576);
		ArrayList<AisDynamicRecord> aisList = new ArrayList<AisDynamicRecord>();
		aisList.add(a);
		aisList.add(b);
		aisList.add(c);
		aisList.add(d);
		aisList.add(e);
		// aisList.add(null);
		// route.setStart_Longitude(73512262);
		// route.setStart_Latitude(18620531);
		// route.setStart_Datetime(new Timestamp(1000));
		// route.setEnd_Longitude(73508431);
		// route.setEnd_Latitude(18615667);
		// route.setEnd_Date_Time(new Timestamp(2000));
		ArrayList<Grid_Summary> set = new ArrayList<Grid_Summary>();
		GridCountFunction gc = new GridCountFunction();
		set = gc.Grid_SummaryCount(aisList, 3000);
		System.out.println("set size :" + set.size());
		Iterator<Grid_Summary> it = set.iterator();
		while (it.hasNext())
		{
			Grid_Summary g = it.next();
			System.out.println(g);
		}
	}

	/**
	 * 根据航道信息返回经过的格子
	 * 
	 * @param route_segment
	 *            航道
	 * @return
	 */
	public ArrayList<Grid_Summary> Grid_SummaryCount(ArrayList<AisDynamicRecord> aisList, int interval)
	{
		ArrayList<Grid_Summary> result = new ArrayList<Grid_Summary>();

		for (int j = 0; j < aisList.size() - 1; j++)
		{
			LinkedHashSet<Grid_Summary> set = new LinkedHashSet<Grid_Summary>();
			ArrayList<Grid_Summary> list = new ArrayList<Grid_Summary>();
			int precision = 400;
			int start_Longitude = aisList.get(j).getDRLONGITUDE();// 起始经度
			int start_Latitude = aisList.get(j).getDRLATITUDE();// 起始纬度
			int end_Longitude = aisList.get(j + 1).getDRLONGITUDE();// 终点经度
			int end_Latitude = aisList.get(j + 1).getDRLATITUDE();// 终点纬度
			int start_Time = aisList.get(j).getDRGPSTIME();// 起始时间
			int end_Time = aisList.get(j + 1).getDRGPSTIME();// 终止时间
			int timeInterval = 0;// 时间间隔
			// 以经度最小的点作为起始点
			if (start_Longitude > end_Longitude)
			{
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
			// 由起始经度到终止经度，每次递增precision的经度
			for (int x = start_Longitude; x <= end_Longitude; x += precision)
			{
				Grid_Summary Grid_Summary = new Grid_Summary();
				int y = lineFunction(start_Longitude, start_Latitude,
						end_Longitude, end_Latitude, x);// 计算该经度下的纬度
				y = (int) (Math.floor(y) / interval) * interval + interval;// 取左上角纬度
				int xx = (int) (x / interval) * interval;// 取左上角经度
				Grid_Summary.setLongitude(xx);
				Grid_Summary.setLatitude(y);
				list.add(Grid_Summary);
				set.add(Grid_Summary);
			}
			int size = list.size();// 包括重复的格子的总数
			int count = 1;// 重复格子的数量
			timeInterval = Math.abs(end_Time - start_Time);
			Iterator<Grid_Summary> it = set.iterator();
			int m = 0;
			// 根据格子重复数量比计算船在每个格子中停留的时间
			while (m < list.size())
			{
				m = 0;
				count = 1;
				for (int n = m + 1; n < list.size(); n++)
				{
					if (list.get(m).equals(list.get(n)))
					{
						count++;
					}
					else
					{
						break;
					}
				}
				if (it.hasNext())
				{
					int time = timeInterval * count / size;
					it.next().setTime(time);
				}
				for (int k = m; k < count; k++)
				{
					Grid_Summary l = list.remove(0);
				}
			}
			ArrayList<Grid_Summary> list1 = new ArrayList<Grid_Summary>(set);
			int time1 = 0;
			for (int h = 0; h < list1.size(); h++)
			{
				time1 = list1.get(0).getTime();
				if (h == 0)
				{
					list1.get(h).setPeriod(
							DataAnalysis.judge_dayornight(list1.get(h)
									.getLongitude(),
									list1.get(h).getLatitude(), TimeSwitch
											.getLocalTime(start_Time, list1
													.get(h).getLongitude())));
				}
				else
				{
					int h1 = h;
					while (h1 > 0)
					{
						time1 += list1.get(h1).getTime();
						h1--;
					}
					list1.get(h).setPeriod(
							DataAnalysis.judge_dayornight(list1.get(h)
									.getLongitude(),
									list1.get(h).getLatitude(), TimeSwitch
											.getLocalTime(time1, list1.get(h)
													.getLongitude())));
				}

			}
			//LinkedHashSet<Grid_Summary> set1 = new LinkedHashSet<Grid_Summary>(list1);
			result.addAll(set);
		}
		System.out.println(result);
		return result;
		
	}

	/**
	 * 直线方程
	 * 
	 * @param start_Longitude
	 * @param start_Latitude
	 * @param end_Longitude
	 * @param end_Latitude
	 * @param x
	 * @return
	 */
	private int lineFunction(int start_Longitude, int start_Latitude,
			int end_Longitude, int end_Latitude, int x)
	{
		int y = 0;
		y = (x - start_Longitude) * (end_Latitude - start_Latitude)
				/ (end_Longitude - start_Longitude) + start_Latitude;
		return y;
	}
}

package Model;


public class Grid {
	
	private int Upper_Left_Longitude;//�������꾭��
	private int Upper_Left_Latitude;//��������γ��
	private int Lower_Right_Longitude;
	private int Lower_Right_Latitude;
	private int Year;
	private int Month;
	private int CrossingTimeSum;//����´��й����դ������д���դ�����߹�����ʱ��
	private int DynamicDataCout;//����´��й����դ������д���դ���ڷ��͵Ķ�̬���ݵ�����
	private int Mean_AIS_Update_Interval;//ƽ��AIS���¼������λ����
	private int Sigama2_AIS_Update_Interval;//���󲿷ִ�ֻ��AIS���¼����С�ڸ�ֵ����λ���롣
	private int Day_Vessel_Avg_Count;//һ����������ڹ����grid��Ĵ�ֻ��������
									 //����·ݡ���γ�ȣ����ǲ�ͬ��������ʱ�䲻һ��������
	private int Night_Vessel_Avg_Count;//һ��ҹ������ڹ����grid��Ĵ�ֻ��������
	private int Day_Vessel_Stddev_Count;//��ֻ��׼��
	private int Night_Vessel_Stddev_Count;//��ֻ��׼��
	
	public int getUpper_Left_Longitude() {
		return Upper_Left_Longitude;
	}
	public void setUpper_Left_Longitude(int upper_Left_Longitude) {
		Upper_Left_Longitude = upper_Left_Longitude;
	}
	public int getUpper_Left_Latitude() {
		return Upper_Left_Latitude;
	}
	public void setUpper_Left_Latitude(int upper_Left_Latitude) {
		Upper_Left_Latitude = upper_Left_Latitude;
	}
	public int getLower_Right_Longitude() {
		return Lower_Right_Longitude;
	}
	public void setLower_Right_Longitude(int lower_Right_Longitude) {
		Lower_Right_Longitude = lower_Right_Longitude;
	}
	public int getLower_Right_Latitude() {
		return Lower_Right_Latitude;
	}
	public void setLower_Right_Latitude(int lower_Right_Latitude) {
		Lower_Right_Latitude = lower_Right_Latitude;
	}
	public int getYear() {
		return Year;
	}
	public void setYear(int year) {
		Year = year;
	}
	public int getMonth() {
		return Month;
	}
	public void setMonth(int month) {
		Month = month;
	}
	public int getCrossingTimeSum() {
		return CrossingTimeSum;
	}
	public void setCrossingTimeSum(int crossingTimeSum) {
		CrossingTimeSum = crossingTimeSum;
	}
	public int getDynamicDataCout() {
		return DynamicDataCout;
	}
	public void setDynamicDataCout(int dynamicDataCout) {
		DynamicDataCout = dynamicDataCout;
	}
	public int getMean_AIS_Update_Interval() {
		return Mean_AIS_Update_Interval;
	}
	public void setMean_AIS_Update_Interval(int mean_AIS_Update_Interval) {
		Mean_AIS_Update_Interval = mean_AIS_Update_Interval;
	}
	public int getSigama2_AIS_Update_Interval() {
		return Sigama2_AIS_Update_Interval;
	}
	public void setSigama2_AIS_Update_Interval(int sigama2_AIS_Update_Interval) {
		Sigama2_AIS_Update_Interval = sigama2_AIS_Update_Interval;
	}
	public int getDay_Vessel_Avg_Count() {
		return Day_Vessel_Avg_Count;
	}
	public void setDay_Vessel_Avg_Count(int day_Vessel_Avg_Count) {
		Day_Vessel_Avg_Count = day_Vessel_Avg_Count;
	}
	public int getNight_Vessel_Avg_Count() {
		return Night_Vessel_Avg_Count;
	}
	public void setNight_Vessel_Avg_Count(int night_Vessel_Avg_Count) {
		Night_Vessel_Avg_Count = night_Vessel_Avg_Count;
	}
	public int getDay_Vessel_Stddev_Count() {
		return Day_Vessel_Stddev_Count;
	}
	public void setDay_Vessel_Stddev_Count(int day_Vessel_Stddev_Count) {
		Day_Vessel_Stddev_Count = day_Vessel_Stddev_Count;
	}
	public int getNight_Vessel_Stddev_Count() {
		return Night_Vessel_Stddev_Count;
	}
	public void setNight_Vessel_Stddev_Count(int night_Vessel_Stddev_Count) {
		Night_Vessel_Stddev_Count = night_Vessel_Stddev_Count;
	}
	
	
	

}

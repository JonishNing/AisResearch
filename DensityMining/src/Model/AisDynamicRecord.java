package Model;

public class AisDynamicRecord {
	
	private int DRGPSTIME;	//GPS时间
	private String DRTERMINALCODE;	//MMSI
	private int DRRECORDTYPE;	
	private int DRCOMMTYPE;	
	private int DRRCVTIME;	
	private int DRLATITUDE;	//纬度
	private int DRLONGITUDE;	//经度
	private int DRDIRECTION;
	private int DRTRUEHEADING;	
	private int DRSPEED;	
	private int DRSTATUS;
	private int DRROT;	
	private String DRMESSAGE;	
	private int AIS_SOURCE;
	
	public AisDynamicRecord()
	{
	}
	public AisDynamicRecord(int DRGPSTIME, int DRLATITUDE,int DRLONGITUDE)
	{
		this.DRGPSTIME=DRGPSTIME;
		this.DRLATITUDE=DRLATITUDE;
		this.DRLONGITUDE=DRLONGITUDE;
		
	}
	public int getDRGPSTIME() {
		return DRGPSTIME;
	}
	public void setDRGPSTIME(int dRGPSTIME) {
		DRGPSTIME = dRGPSTIME;
	}
	public String getDRTERMINALCODE() {
		return DRTERMINALCODE;
	}
	public void setDRTERMINALCODE(String dRTERMINALCODE) {
		DRTERMINALCODE = dRTERMINALCODE;
	}
	public int getDRRECORDTYPE() {
		return DRRECORDTYPE;
	}
	public void setDRRECORDTYPE(int dRRECORDTYPE) {
		DRRECORDTYPE = dRRECORDTYPE;
	}
	public int getDRCOMMTYPE() {
		return DRCOMMTYPE;
	}
	public void setDRCOMMTYPE(int dRCOMMTYPE) {
		DRCOMMTYPE = dRCOMMTYPE;
	}
	public int getDRRCVTIME() {
		return DRRCVTIME;
	}
	public void setDRRCVTIME(int dRRCVTIME) {
		DRRCVTIME = dRRCVTIME;
	}
	public int getDRLATITUDE() {
		return DRLATITUDE;
	}
	public void setDRLATITUDE(int dRLATITUDE) {
		DRLATITUDE = dRLATITUDE;
	}
	public int getDRLONGITUDE() {
		return DRLONGITUDE;
	}
	public void setDRLONGITUDE(int dRLONGITUDE) {
		DRLONGITUDE = dRLONGITUDE;
	}
	public int getDRDIRECTION() {
		return DRDIRECTION;
	}
	public void setDRDIRECTION(int dRDIRECTION) {
		DRDIRECTION = dRDIRECTION;
	}
	public int getDRTRUEHEADING() {
		return DRTRUEHEADING;
	}
	public void setDRTRUEHEADING(int dRTRUEHEADING) {
		DRTRUEHEADING = dRTRUEHEADING;
	}
	public int getDRSPEED() {
		return DRSPEED;
	}
	public void setDRSPEED(int dRSPEED) {
		DRSPEED = dRSPEED;
	}
	public int getDRSTATUS() {
		return DRSTATUS;
	}
	public void setDRSTATUS(int dRSTATUS) {
		DRSTATUS = dRSTATUS;
	}
	public int getDRROT() {
		return DRROT;
	}
	public void setDRROT(int dRROT) {
		DRROT = dRROT;
	}
	public String getDRMESSAGE() {
		return DRMESSAGE;
	}
	public void setDRMESSAGE(String dRMESSAGE) {
		DRMESSAGE = dRMESSAGE;
	}
	public int getAIS_SOURCE() {
		return AIS_SOURCE;
	}
	public void setAIS_SOURCE(int aIS_SOURCE) {
		AIS_SOURCE = aIS_SOURCE;
	}
	
	

}

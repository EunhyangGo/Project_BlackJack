package com.biz.blackjack.vo;

public class ShapeVO {

	String CLUB;
	String DAIMOND;
	String SPADE;
	String HEART;
	public String getCLUB() {
		return CLUB;
	}
	public void setCLUB(String cLUB) {
		CLUB = cLUB;
	}
	public String getDAIMOND() {
		return DAIMOND;
	}
	public void setDAIMOND(String dAIMOND) {
		DAIMOND = dAIMOND;
	}
	public String getSPADE() {
		return SPADE;
	}
	public void setSPADE(String sPADE) {
		SPADE = sPADE;
	}
	public String getHEART() {
		return HEART;
	}
	public void setHEART(String hEART) {
		HEART = hEART;
	}
	@Override
	public String toString() {
		return "ShapeVO [CLUB=" + CLUB + ", DAIMOND=" + DAIMOND + ", SPADE=" + SPADE + ", HEART=" + HEART + "]";
	}
	
	
	
}

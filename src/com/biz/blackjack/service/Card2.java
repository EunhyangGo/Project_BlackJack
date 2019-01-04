package com.biz.blackjack.service;

import com.biz.blackjack.vo.NumVO;
import com.biz.blackjack.vo.ShapeVO;

public class Card2 {
	
	private ShapeVO sv;
	private NumVO nv;
	

	public Card2(ShapeVO sv, NumVO nv) {
		// TODO Auto-generated constructor stub
		this.sv = sv;
		this.nv = nv;
	}

	

	@Override
	public String toString() {
		return "Card2 [sv=" + sv + ", nv=" + nv + "]";
	}



	public ShapeVO getSv() {
		return sv;
	}



	public void setSv(ShapeVO sv) {
		this.sv = sv;
	}



	public NumVO getNv() {
		return nv;
	}



	public void setNv(NumVO nv) {
		this.nv = nv;
	}

	
}

package com.biz.blackjack.exec;

import com.biz.blackjack.service.BlackJackService;
import com.biz.blackjack.vo.NumVO;
import com.biz.blackjack.vo.ShapeVO;

public class BlackJackExec02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		BlackJackService bs = new BlackJackService();
		
		bs.CreateCard();
		bs.shuffle();

	}

}

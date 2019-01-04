package com.biz.blackjack.service;

import com.biz.blackjack.vo.NumVO;
import com.biz.blackjack.vo.ShapeVO;

public class Card {
	
	private Suit suit;
	private Value value;
	
	public Card(Suit suit, Value value) {
		this.value = value;
		this.suit = suit;
		
	}

	@Override
	public String toString() {
		return "Card [suit=" + suit + ", value=" + value + "]";
	}

	public Value getValue() {
		return this.value;
	}
}

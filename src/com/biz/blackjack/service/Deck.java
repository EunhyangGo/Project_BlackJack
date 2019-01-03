package com.biz.blackjack.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {

	private ArrayList<Card> cards;
	
	
	public Deck() {
		this.cards = new ArrayList<Card>();
	}
	public void createFullDeck() {
		// 카드 만들기
		for(Suit cardSuit : Suit.values()) {
			for(Value cardValue : Value.values()) {
				// 새로운 카드 섞어서 만들든걸 추가하기
				this.cards.add(new Card(cardSuit, cardValue));
				// Card클래스에서 Card 메서드에 매개변수를 cardSuit(club,diamond,spade,heart)가 담겨있고
				// cardValue(2~ACE까지)부터 에 담아 
				// ArrayList<Card>에 추가한다.
				// 그러면 cards에는 지금 무작위의 카드가 섞여있다.
			}
		}
	}
	public void shuffle() {
		ArrayList<Card> tmpDeck = new ArrayList<Card>();
		// use Random
		Random random = new Random();
		int randomCardIndex = 0;
		int originalSize = this.cards.size();
		for(int i = 0 ; i< originalSize; i++) {
			//Generate Random Index rand.nextInt((max-min) +1) + min
			randomCardIndex = random.nextInt((this.cards.size()-1 -0) +1) +0;
			tmpDeck.add(this.cards.get(randomCardIndex));
			// remove from original deck
			this.cards.remove(randomCardIndex);
		}
		this.cards = tmpDeck;
	}
	public String toString() {
		String cardListOutput = "";
		for(Card aCard : this.cards) {
			cardListOutput += "\n" + aCard.toString();
			
		}
		return cardListOutput;
	}

	public void removeCard(int i) {
		this.cards.remove(i);
	}
	public Card getCard(int i) {
		return this.cards.get(i);
	}
	public void addCard(Card addCard) {
		this.cards.add(addCard);
	}
	// Draws from the deck
	public void draw(Deck comingForm) {
		this.cards.add(comingForm.getCard(0));
		comingForm.removeCard(0);
	}
	public int deckSize() {
		return this.cards.size();
	}
	// Return total value of cards in deck
	public int cardsValue() {
		int totalValue =0;
		int aces = 0;
		
		for(Card aCard : this.cards) {
			switch(aCard.getValue()) {
			case TWO : totalValue +=2; break;
			case  THREE : totalValue += 3; break;
			case  FOUR : totalValue += 4; break;
			case  FIVE : totalValue += 5; break;
			case  SIX : totalValue += 6; break;
			case  SEVEN : totalValue += 7; break;
			case  EIGHT : totalValue += 8; break;
			case  NINE : totalValue += 9; break;
			case  TEN : totalValue += 10; break;
			case  JACK : totalValue += 10; break;
			case  QUEEN : totalValue += 10; break;
			case  KING : totalValue += 10; break;
			case  ACE : aces += 1; break;
			
			}
		}
		for(int i =0; i<aces; i++) {
			if(totalValue > 10) {
				totalValue += 1;
			}else {
				totalValue +=11;
			}
		}
		return totalValue;
	}
}

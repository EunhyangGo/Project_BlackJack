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
		// 랜덤 숫자를 만들기 위해 random사용
		Random random = new Random();
		int randomCardIndex = 0;
		int originalSize = this.cards.size();
		// 만약 카드가 얻는게 적어진다면 더이상 일하지 않을것이다
		// originalSize변수에 cards에 들어있는 값을 넣어주고
		for(int i = 0 ; i< originalSize; i++) {
			// 그만큼 for문으로 돌려서 randomCard 넘버를 만들어서 
			// 0부터 51까지 근데 오리지널카드에서 삭제를 해야하기때문에
			// 최종적으로 original deck은 비게 될 것이고 temCard에는 가득차게 될 것
			// rand.nextInt((max-min)+1)min
			randomCardIndex = random.nextInt((this.cards.size()-1 -0) +1) +0;
			tmpDeck.add(this.cards.get(randomCardIndex));

			// originalSize에서 꺼내오기 randomcard를
			// 그리고 tmpCard에 저장.
			this.cards.remove(randomCardIndex);
			// 그리고 randomCard를 삭제해준다.
		}
		this.cards = tmpDeck;
		// tmpcard에는 랜덤의 카드들이 들어가있고 그 카드들이 다시 cards에 들어가잇다.
	}
	// sysout 출력해줄 메서드
	public String toString() {
		String cardListOutput = "";
		for(Card aCard : this.cards) {
			cardListOutput += "\n" + aCard.toString();
			
		}
		return cardListOutput;
	}

	// 카드 삭제 메서드
	public void removeCard(int i) {
		this.cards.remove(i);
	}
	// 카드를 얻을때 사용하는 메서드
	public Card getCard(int i) {
		return this.cards.get(i);
	}
	public void addCard(Card addCard) {
		this.cards.add(addCard);//cards에 Card2 추가
	}
	// Draws from the deck
	public void draw(Deck comingForm) {
		this.cards.add(comingForm.getCard(0));
		comingForm.removeCard(0);
	}
	public int deckSize() {
		return this.cards.size();
	}
	
	public void moveAllToDeck(Deck moveTO) {
		int thisDecksize = this.cards.size();
		
		// put cards into moveTo deck
		for(int i = 0 ; i < thisDecksize; i++) {
			moveTO.addCard(this.getCard(i));
		}
		for(int i = 0 ; i <thisDecksize; i++) {
			this.removeCard(0);
		}
	}

	// 카드값 지정해주는 메서드
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

package com.biz.blackjack.exec;

import java.util.Scanner;

import com.biz.blackjack.service.Deck;

public class BlackJackExec01 {

	public static void main(String[] args) {
	System.out.println("블랙잭 게임을 시작합니다.");
	
	// Create our playing deck
	Deck playingDeck = new Deck(); // 
	playingDeck.createFullDeck();
	playingDeck.shuffle();
	
	// Create a deck for the player
	Deck playerDeck = new Deck();
	Deck dealerDeck = new Deck();
	
	double playerMoney = 100.00;
	
	Scanner userInput = new Scanner(System.in);
	
	// Game Loop
	while(playerMoney > 0) {
		//play on!
		//Take the players bet
		System.out.println("당신은 " + playerMoney + ", 이 있습니다. 얼마를 배팅하시겠습니까?");
		double playerBet = userInput.nextDouble();
		if(playerBet > playerMoney) {
			System.out.println("가지고 있는 돈보다 더 많이 배팅할 수 없습니다. byebye");
			break;
		}
		boolean endRound = false;
		// Start Dealing
		// player gets two cards
		
		playerDeck.draw(playingDeck);
		playerDeck.draw(playingDeck);
		
		// Dealer gets two cards
		dealerDeck.draw(playingDeck);
		dealerDeck.draw(playingDeck);
		
		while(true) {
			System.out.println("your hand: ");
			System.out.print(playerDeck.toString());
			System.out.println("your deck is valued at :" + playerDeck.cardsValue());
			
			// Display Dealer hand
			System.out.println("Dealer Hand " + dealerDeck.getCard(0).toString() 
					+" and [Hidden]");
			
			// What does the player want to do?
			System.out.println("Would you like to (1) Hit or (2) Stand?");
		
			int response = userInput.nextInt();
			
			// They Hit
			if(response == 1) {
				playerDeck.draw(playingDeck);
				System.out.println("you draw a : " + playerDeck.getCard(playerDeck.deckSize()-1.toString));
				// Bust if >21
				if(playerDeck.cardsValue()>21) {
					System.out.println("Bust. Currently calued at " + playerDeck.cardsValue());
					playerMoney -= playerBet;
					endRound = true;
					
				}
			}
		if(response == 2) {
			break;
		}
		
		}
		//Reaveal Dealer Cards
		System.out.println("Dealer Cards : " + dealerDeck.toString());
		// See if dealer has more point than player
		if(dealerDeck.cardsValue() > playerDeck.cardsValue() && endRound == false) {
			System.out.println("Dealer beats you !");
			playerMoney -= playerBet;
			endRound = true;
			
		}
		// Dealer Draws at 16, stand at 17
		while((dealerDeck.cardsValue() <17) && endRound == false) {
			dealerDeck.draw(playingDeck);
			System.out.println("Dealer Draws " + dealerDeck.getCard(dealerDeck.deckSize()-1).toString());
			
		}
	}
	System.out.println("게임 끝! 가지고 있는 돈이 없습니다.");
	
}
}

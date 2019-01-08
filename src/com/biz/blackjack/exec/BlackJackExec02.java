package com.biz.blackjack.exec;

import java.util.Scanner;

import com.biz.blackjack.service.BlackJackService;

public class BlackJackExec02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		Scanner scan = new Scanner(System.in);
		
		
		BlackJackService playingDeck = new BlackJackService();
		playingDeck.CreateCard();
		playingDeck.shuffle();
		
		
		BlackJackService player = new BlackJackService();
		BlackJackService dealer = new BlackJackService();
		
		int playerMoney = 10000;
		while(playerMoney > 0) {
			System.out.println("당신은" + playerMoney + "원이 있습니다."
					+ "얼마를 배팅하시겠습니까?");
			int playerBet = scan.nextInt();
			if(playerBet > playerMoney) {
				System.out.println("가지고 있는 돈보다 더 많이 배팅할 수 없습니다.");
			break;
			}
			boolean endRound = false;
			
			// player가 두개의 카드 갖기
			player.draw(playingDeck);
			player.draw(playingDeck);
			
			// 딜러가 두개의 카드 갖기 
			dealer.draw(playingDeck);
			dealer.draw(playingDeck);
			
			while(true) {
				System.out.println("당신이 소지한 카드입니다");
				System.out.println(player.toString());
				System.out.println("당신이 가지고 있는 카드 합은 :" + player.cardsValue());
				
				System.out.println("딜러가 소지한 카드입니다: "
						+ dealer.getCard(0).toString()
						+ " 그리고 [?]");
				
				System.out.println("계속하시려면 (1), 멈추시려면 (2)를 눌러주세요");
				
				int response = scan.nextInt();
				
				// 만약 계속 하려면(카드 한장을 더 받으려면)
				if(response == 1) {
					player.draw(playingDeck);
					System.out.println("당신이 받은 카드는" + player.getCard(player.deckSize()-1).toString());
					
					// 숫자가 초과해서 카드를 더이상 받을 수 없을때
					if(player.cardsValue()>21) {
						System.out.println("당신은 카드를 더이상 받을 수 없습니다."
								+ player.cardsValue());
						playerMoney -= playerBet;
						endRound = true;
						
						
					}
				}
				if(response ==2) {
					break;
				}
				
			}
			// 딜러가 가지고 있는 카드.
			System.out.println("딜러가 소지하고 있는 카드는:" + dealer.toString() + "입니다");
					if(dealer.cardsValue() > player.cardsValue() && endRound == false) {
				System.out.println("딜러가 이겼습니다!");
				playerMoney -= playerBet;
				endRound = true;
			}
			// 딜러가 16,17일때
			 while((dealer.cardsValue() < 17 ) && endRound == false){
				 dealer.draw(playingDeck);
				 System.out.println("딜러가 받은 카드는 " + dealer.getCard(dealer.deckSize()-1).toString());
				 
			 }
			 // 딜러가 가지고 있는 카드 보여주기.
			System.out.println("딜러가 가지고 있는 카드는 " + dealer.cardsValue());
			if((dealer.cardsValue()>21) && endRound == false) {
				System.out.println("당신이 이겼습니다!");
				playerMoney += playerBet;
				endRound = true;
				
			}
			if((player.cardsValue() == dealer.cardsValue()) && endRound == false) {
				System.out.println("계속 진행합니다");
				endRound = true;
				
			}
			
			if(player.cardsValue() > dealer.cardsValue() && endRound == false ){
				System.out.println("당신이 이겼습니다!");
				endRound = true;
			}
			
			player.moveAllToDeck(playingDeck);
			dealer.moveAllToDeck(playingDeck);
			System.out.println("게임이 끝났습니다.");
		}
		System.out.println("게임 끝! 가지고 있는 돈이 없습니다.");
				
		
		
	}

}
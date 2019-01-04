/* 
 * [게임 규칙]
 * 
 * 공통
 *  - 유저와 딜러의 1:1 대결
 *  - 52장의 트럼프 카드를 사용(13장(A~K)씩 4묶음_
 *    ※ 문양(스페이드, 다이아몬드, 하트, 클로버)의 의미는 없음
 *  - 최초 기본 카드는 각각 2장을 배분
 *  - 'J, Q, K'는 10점으로 계산
 *  - 'A'는 '1점' 또는 '11점'으로 계산되는데,
 *    '점수 > 21점 == 1점'으로 계산, '점수 <= 21점 == 11점'으로 계산
 * 
 * 유저
 *  - 기본 카드를 받고 난 후 유저는 승패가 결정되기 전까지, 'Hit'or'Stay'를 결정해야 함
 *  - 'Hit'결정 시 유저는 카드 1장을 뽑음 
 *    'Hit'는 4번만 가능. 총 6개의 카드를 가질 수 있음
 *  - 'Stay'결정 시 각자의 카들를 모두 공개하고, 점수를 비교하여 승패를 결정
 * 
 * 딜러
 *  - 딜러의 첫 번째 카드는 유저에게 보여주지 않음
 *    ※ '?'로 표시
 *  - 유저가 'Hit'or'Stay'를 결정하면, 각 점수를 비교함
 *    딜러의 점수가 '21점 이하'이고, 유저의 점수보다 낮을 경우 카드 1장을 뽑음
 *    ※ 게임 진행 중 1회만 뽑을 수 있음
 *
 * 승패
 * 1.유저 승리
 *  - 유저의 기본 카드 2장이 블랙잭(A, K)이고, 딜러는 해당되지 않을 경우
 *  - 각자 점수가 '21점 이하'이고, 유저의 점수가 더 높을 경우
 *  - 유저의 점수가 '21점 이하'이고, 딜러의 점수는 '21점을 초과'할 경우
 *  
 * 2.유저 패배
 *  - 딜러의 기본 카드 2장이 블랙잭(A, K)이고, 유저는 해당되지 않을 경우
 *  - 각자 점수가 '21점 이하'이고, 딜러의 점수가 더 높을 경우
 *  - 유저의 점수가 '21점을 초과'할 경우
 *  
 * 3.무승부
 *  - 각자 기본 카드 2장이 블랙잭(A, K)일 경우 
 *  - 각자 점수가 동일한 경우 
 */

package com.biz.blackjack.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.biz.blackjack.vo.NumVO;
import com.biz.blackjack.vo.ShapeVO;

public class BlackJackService {
	List<ShapeVO> shapeList;
	List<NumVO> numList;
	List<Card2> cards;
	String player ;
	String dealer;
	Scanner scan;
	
	public BlackJackService() {
		this.shapeList = new ArrayList<ShapeVO>();
		this.numList = new ArrayList<NumVO>();
		this.cards = new ArrayList<Card2>();
		this.player = player;
		this.dealer = dealer;
		scan = new Scanner(System.in);
	}
	
	// 카드를 만드는 메서드
	public void CreateCard() {
		for(ShapeVO sv : shapeList) {
			for(NumVO nv : numList) {
				// 모양이 들어있는 카드와, 숫자가 들어있는 카드를 서로 섞어준다.
				this.cards.add(new Card2(sv,nv));
			
			}
		}
	}

	// 만들어진 카드를 섞어주는 메서드
	public void shuffle() {
		List<Card2> tmpCard = new ArrayList<Card2>();
		Random random = new Random();
		int randomCard = 0;
		int originalSize = this.cards.size();
		for(int i =0 ; i <originalSize; i++) {
			randomCard = random.nextInt((this.cards.size()-1-0) +1) +0;
			tmpCard.add(this.cards.get(randomCard));
			this.cards.remove(randomCard);
			
		}
		this.cards = tmpCard;

	}
	public String toString() {
		String cardListOutPut = "";
		for(Card2 aCard : this.cards) {
			cardListOutPut += "\n" +aCard.toString();
		}
		return cardListOutPut;
	}
	public void removeCard(int i) {
		this.cards.remove(i);
		
	}
	public Card2 getCard(int i) {
		return this.cards.get(i);
	}
	public void addCard(Card2 addCard) {
		// TODO Auto-generated method stub
	this.cards.add(addCard);	
	}
	
	public void draw(Deck comingForm) {
		this.cards.add(comingForm.getCard(0));
		comingForm.removeCard(0);
	}
	public int deckSize() {
		return this.cards.size();
	}
	public void moveAllToDeck(Deck moveTO) {
		int thisDeckSize = this.cards.size();
		
		// put cards into moveTO deck
		for(int i = 0; i < thisDeckSize; i++) {
			moveTO.addCard(this.getCard(i));
		}
		for(int i = 0; i<thisDeckSize; i++) {
			this.removeCard(0);
		}
	}
	public int cardsValue() {
		int totalValue =0;
		int aces = 0;
		
		
		NumVO vo = new NumVO();
		for(int i =0; i< vo.getACE(); i++) {
			if(totalValue > 10 ) {
				totalValue += 1 ;
			}else {
				totalValue +=11;
			}

		}
		return totalValue;
	}
	public void Betting() {
		
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
			
			
		}
		
		
		
	}
}

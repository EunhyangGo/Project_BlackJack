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
	Scanner scan;
	
	public BlackJackService() {
		this.shapeList = new ArrayList<ShapeVO>();
		this.numList = new ArrayList<NumVO>();
		this.cards = new ArrayList<Card2>();
		scan = new Scanner(System.in);
	}
	
	// 카드를 만드는 메서드
	public void CreateCard() {
		for(ShapeVO sv : shapeList) {
			for(NumVO nv : numList) {
				// 모양이 들어있는 카드와, 숫자가 들어있는 카드를 서로 섞어준다.
				this.cards.add(new Card2(sv,nv)); //cards에 추가해주기
			
			}
		}
	}

	// 만들어진 카드를 섞어주는 메서드
	// 랜덤으로 카드를 섞고 한장의 카드를 선택하기 위해 만들었음.
	// 카드가 tmpcard에 들어가서 그 안에서 카드가 섞일것 그리고 셋팅한다

	public void shuffle() {
		List<Card2> tmpCard = new ArrayList<Card2>();
		Random random = new Random();
		// 랜덤 숫자를 만들기 위해 random사용
		int randomCard = 0;
		int originalSize = this.cards.size(); 
		// 만약 카드가 얻는게 적어진다면 더이상 일하지 않을것이다
		// originalSize변수에 cards에 들어있는 값을 넣어주고
		for(int i =0 ; i <originalSize; i++) {
			// 그만큼 for문으로 돌려서 randomCard 넘버를 만들어서 
			// 0부터 51까지 근데 오리지널카드에서 삭제를 해야하기때문에
			
			// 최종적으로 original deck은 비게 될 것이고 temCard에는 가득차게 될 것
			// rand.nextInt((max-min)+1)min
			randomCard = random.nextInt((this.cards.size()-1-0) +1) +0;
			// originalSize에서 꺼내오기 randomcard를
			// 그리고 tmpCard에 저장.
			tmpCard.add(this.cards.get(randomCard));
			// 그리고 randomCard를 삭제해준다.
			this.cards.remove(randomCard);
			
		}
		this.cards = tmpCard; // cards에 tmpDeck 저장
		// tmpcard에는 랜덤의 카드들이 들어가있고 그 카드들이 다시 cards에 들어가잇다.

	}
	// sysout 출력해줄 메서드
	public String toString() {
		String cardListOutPut = "";
		for(Card2 aCard : this.cards) {
			cardListOutPut += "\n" +aCard.toString();
		}
		return cardListOutPut;
	}
	// 카드 삭제 메서드
	public void removeCard(int i) {
		this.cards.remove(i);
		
	}
	
	
	// 처리가 필요할때 사용할 것.
	
	public Card2 getCard(int i) {
		return this.cards.get(i);
	}
	public void addCard(Card2 addCard) {
		// TODO Auto-generated method stub
	this.cards.add(addCard);	
	} //cards에 Card2 추가
	
	public void draw(BlackJackService comingForm) {
		this.cards.add(comingForm.getCard(0));
		// blackjackservice에 첫번째값 가져오기.
		// 후에 카드를 더 추가할때 만약 카드가 끝나면 그때 List끝에 메서드에 추가할때 사용할 수 있다. 
		comingForm.removeCard(0);
	}
	// Decksize 리턴해주기.
	public int deckSize() {
		
		return this.cards.size();
	}
	
	public void moveAllToDeck(BlackJackService moveTO) {
		int thisDeckSize = this.cards.size();
		
		// put cards into moveTO deck
		for(int i = 0; i < thisDeckSize; i++) {
			moveTO.addCard(this.getCard(i));
		}
		for(int i = 0; i<thisDeckSize; i++) {
			this.removeCard(0);
		}
	}
	// totalvalue 값 리턴해주기
	public int cardsValue() {
		int totalValue =0;
		int aces = 0;
		
	
		// ACE카드 값 때에 따라 매겨주기
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

package tycoon02;

import java.util.Random;

public class People {
	int buyCount;
	int waitTime;
	
	People() {
		Random random = new Random();
		
		int max = 3;
		int min = 1;
		buyCount =  random.nextInt((max - min) + 1) + min;
		waitTime = 10;
	}

	public int getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(int buyCount) {
		this.buyCount = buyCount;
	}

	public int getWaitTime() {
		return waitTime;
	}

	// 손님이 기다릴 수 있는 시간
	public int minusWaitTime() {	
		return waitTime--;		
	}
	
	public String createOrder() {
		return Integer.toString(buyCount) + "개 주세요~";		
	}
	
}

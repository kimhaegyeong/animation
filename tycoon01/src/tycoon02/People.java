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

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}
	
}

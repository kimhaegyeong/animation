package tycoon01;

public class Bungubbang01 {
	// 익은 정도
	private state front;
	private state back;
	private int sum;
	
	// enum 사용
	public enum state {
		RARE(10), MEDIUM(25), BEST(50), MEDIUM_WELLDONE(25), WELLDONE(10);
		private int value;

		private state(int value) {
			this.value = value;
		}
	}

	public void setFront(int time) {
		this.front = checkState(time);
	}	

	public void setBack(int time) {
		this.back = checkState(time);
	}

	public int getSum() {
		return front.value + back.value;
	}	
	
	private state checkState(int time) {
		if (time == 0 && time == 1) {
			return state.RARE;
		} else if (time == 2 && time == 3) {
			return state.MEDIUM;
		} else if (time == 4 && time == 5) {
			return state.BEST;
		} else if (time == 6 && time == 7) {
			return state.MEDIUM_WELLDONE;
		} else  {
			return state.WELLDONE;
		} 		
	}
}

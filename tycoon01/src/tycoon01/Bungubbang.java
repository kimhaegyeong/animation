package tycoon01;

import javax.swing.ImageIcon;

public class Bungubbang implements Cloneable {
	// 익은 정도
	private static state front;
	private static state back;
	private int sum;
	
	// enum 사용
	public enum state {
		RARE(10), MEDIUM(25), BEST(50), MEDIUM_WELLDONE(25), WELLDONE(10);
		private int value;

		private state(int value) {
			this.value = value;
		}
	}

	Bungubbang() {
		front = null;
		back = null;
	}	
	
	public state getFront() {
		return front;
	}

	public void setFront(state front) {
		this.front = front;
	}

	public state getBack() {
		return back;
	}

	public void setBack(state back) {
		this.back = back;
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
	
	state checkState(int time) {	
		if (time < 2) {
			return state.RARE;
		} else if (time >= 2 && time <= 3) {
			return state.MEDIUM;
		} else if (time >= 4 && time <= 5) {
			return state.BEST;
		} else if (time >= 6 && time <= 7) {
			return state.MEDIUM_WELLDONE;
		} else if (time > 7) {
			return state.WELLDONE;
		}
		return null;
	}

	@Override
	protected Bungubbang clone() throws CloneNotSupportedException {

		return (Bungubbang)super.clone();
	}
	
}

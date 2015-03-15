package windowBuilder06;

public class ParkingLot {
	private int[] liftA, liftB, liftC;
	private boolean closedDoor;
	private Plate[] plates;

	private Plate[][] state; // 지하 주차장의 현재 상태, 맵

	ParkingLot() {
		liftA = new int[] { 7, 2 };
		liftB = new int[] { 7, 9 };
		liftC = new int[] { 7, 16 };

		closedDoor = true;

		plates = new Plate[40];
		state = new Plate[10][19];

		for (int i = 0; i < plates.length; i++) {
			plates[i] = new Plate();
		}

		// 초기 plate 위치
		for (int i = 0; i < plates.length; i++) {
			plates[i].setRow( i / 8 + 3);
			
			if ( (i % 8) < 4 ) {
				plates[i].setColumn(i % 8 + 4);
			} else if ( (i % 8) >= 4 ) {
				plates[i].setColumn(i % 8 + 7);
			} 
			
		}
		
		updateState();
	}

	void updateState() {
		for (int i = 0; i < plates.length; i++) {
			state[plates[i].getRow()][plates[i].getColumn()] = plates[i];
		}
	}

	public Plate[][] getState() {
		return state;
	}

	public void setState(Plate[][] state) {
		this.state = state;
	}	
	
	public int[] getLiftA() {
		return liftA;
	}

	public void setLiftA(int[] liftA) {
		this.liftA = liftA;
	}

	public int[] getLiftB() {
		return liftB;
	}

	public void setLiftB(int[] liftB) {
		this.liftB = liftB;
	}

	public int[] getLiftC() {
		return liftC;
	}

	public void setLiftC(int[] liftC) {
		this.liftC = liftC;
	}



	class Plate {
		int row;
		int column;
		int carNo;

		Plate() {
		}

		public int getRow() {
			return row;
		}

		public void setRow(int row) {
			this.row = row;
		}

		public int getColumn() {
			return column;
		}

		public void setColumn(int column) {
			this.column = column;
		}

	}
}

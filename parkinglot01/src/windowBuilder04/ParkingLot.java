package windowBuilder04;

public class ParkingLot {	
	private boolean[][] plateLoation;
	
	ParkingLot() {
		//plateLoation = new boolean[5][11];
		plateLoation = new boolean[][] 	{
					{false, true, true, true, true, false, true, true, true, true, false},
					{false, true, true, true, true, false, true, true, true, true, false},
					{false, true, true, true, true, false, true, true, true, true, false},
					{false, true, true, true, true, false, true, true, true, true, false},
					{false, true, true, true, true, false, true, true, true, true, false}
				};		
	}

	public boolean[][] getPlateLoation() {
		return plateLoation;
	}

	public void setPlateLoation(boolean[][] plateLoation) {
		this.plateLoation = plateLoation;
	}
	
	
	
	
}

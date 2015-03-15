package windowBuilder05;

public class ParkingLot {	
	private boolean[][] plateLoation;

	ParkingLot() {
		//plateLoation = new boolean[10][19];
		plateLoation = new boolean[][] 	{
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
				{false, false, false, false, true, true, true, true, false, false, false, true, true, true, true, false, false, false, false, false },
				{false, false, false, false, true, true, true, true, false, false, false, true, true, true, true, false, false, false, false, false },
				{false, false, false, false, true, true, true, true, false, false, false, true, true, true, true, false, false, false, false, false },
				{false, false, false, false, true, true, true, true, false, false, false, true, true, true, true, false, false, false, false, false },
				{false, false, false, false, true, true, true, true, false, false, false, true, true, true, true, false, false, false, false, false },
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }
	
		};		
	}

	public boolean[][] getPlateLoation() {
		return plateLoation;
	}

	public void setPlateLoation(boolean[][] plateLoation) {
		this.plateLoation = plateLoation;
	}
	
	
	
	
}

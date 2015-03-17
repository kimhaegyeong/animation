// 최단경로 알고리즘 :
/*
 findEmptyPlate()에 ArrayList 사용
 step1 : liftB 를 최단 경로 이동
 step2 : plate 를 최단 경로 이동
 step3 : plate 1F 으로 올리기
 */

package windowBuilder11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class ParkingLot {
	private int[] liftA, liftB, liftC;
	private boolean closedDoor;
	// private Plate[] plates;

	private Plate[][] state; // 지하 주차장의 현재 상태, 맵

	ParkingLot() {
		liftA = new int[] { 7, 2 };
		liftB = new int[] { 7, 9 };
		liftC = new int[] { 7, 16 };

		closedDoor = true;

		state = new Plate[10][19];
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[0].length; j++) {
				state[i][j] = new Plate(i, j);
				state[i][j].row = i;
				state[i][j].column = j;
			}
		}
		// 초기 plate 위치
		for (int i = 0; i < 40; i++) {
			if ((i % 8) < 4) {
				state[(int) i / 8 + 3][i % 8 + 4].setNo(i);
			} else if ((i % 8) >= 4) {
				state[(int) i / 8 + 3][i % 8 + 7].setNo(i);
			}

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

	public void setLiftB(int row, int column) {
		this.liftB[0] = row;
		this.liftB[1] = column;
	}

	public int[] getLiftC() {
		return liftC;
	}

	public void setLiftC(int[] liftC) {
		this.liftC = liftC;
	}

	public Plate findEmptyPlate() {
		int minRouteCount = Integer.MAX_VALUE;
		Plate minRouteLocation = null;
		int inf = Integer.MAX_VALUE;

		int[][] weightA = new int[10][19];
		int[][] weightC = new int[10][19];
		int[][] weightTotal = new int[10][19];

		for(int row[]: weightA)
		    Arrays.fill(row, 0);
		
		for(int row[]: weightC)
		    Arrays.fill(row, 0);

		// liftA를 기준으로 가중치 구하기
		for (int i = 3; i < 8; i++) {
			for (int j = 2; j < 8; j++) {
				weightA[i][j] = Math.abs(liftA[0] - i) + Math.abs(liftA[1] - j);
			}
		}
		// liftC를 기준으로 가중치 구하기
		for (int i = 3; i < 8; i++) {
			for (int j = 10; j < 17; j++) {
				weightC[i][j] = Math.abs(liftC[0] - i) + Math.abs(liftC[1] - j);
			}
		}

		// liftB를 기준으로 가중치 구하기
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 17; j++) {
				if ((i == 1 || i == 2) && j != 9) {
					weightTotal[i][j] = inf;
				} else if ((i > 2 && i < 8)
						&& (j < 2 || j == 3 && j == 8 || j == 10 || j == 15 || j > 16)) {
					weightTotal[i][j] = inf;
				} else {
					// step1
					weightTotal[i][j] = Math.abs(liftB[0] - i)
							+ Math.abs(liftB[1] - j);
					
					// step2
					if ((getState()[i][j + 1].getCarNo() != null  && j < 7) || 
							(getState()[i][j].getCarNo() != null  && j == 7)) {						
						weightTotal[i][j] += weightA[i][j] + 7;
					}/* else if (getState()[i][j - 1].getCarNo() != null && j > 12) {
						weightTotal[i][j] += weightC[i][j] + 7;
					}*/
				}
			}
		}
		
		System.out.println(weightTotal[6][7] + " " + weightTotal[7][6] + " " + weightTotal[7][7]);

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 19; j++) {
				if (state[i][j].getNo() != null
						&& state[i][j].getCarNo() == null) {
					if (weightTotal[i][j] < minRouteCount) {
						minRouteCount = weightTotal[i][j];
						minRouteLocation = getState()[i][j];
					}
				}
			}
			
		}

		System.out.println("minRouteLocation : " + minRouteLocation.getRow() + " "
				+ minRouteLocation.getColumn());
		return minRouteLocation;
	}

	public Plate findEmptySpace(Integer carNo) {
		ArrayList<Plate> emptySpace = new ArrayList<Plate>();

		int routeCount;
		int minRouteCount = 0;
		Plate minRouteSpace = new Plate();

		for (int i = 3; i < 8; i++) {
			for (int j = 4; j < 15; j++) {
				if (j < 8 || j > 10)
					if (state[i][j].getNo() == null) {
						emptySpace.add(state[i][j]);
					}
			}
		}

		// 빈 주차 공간 출력
		Iterator<Plate> iterator = emptySpace.iterator();
		System.out.println("**** START - emptySpace ****");
		while (iterator.hasNext()) {
			Plate temp = iterator.next();
			System.out.println("{ " + temp.getRow() + ", " + temp.getColumn()
					+ " }");
		}
		System.out.println("**** END - emptySpace ****");

		// 최단경로의 빈공간 찾기
		for (int i = 0; i < emptySpace.size(); i++) {
			routeCount = emptySpace.get(i).getRow() - liftB[0];

			if (routeCount < minRouteCount || i == 0) {
				minRouteCount = routeCount;
				minRouteSpace = emptySpace.get(i);
			}
		}

		System.out.print("최단 경로 빈공간 : ");
		System.out.println("{ " + minRouteSpace.getRow() + ", "
				+ minRouteSpace.getColumn() + " }");

		// 빈공간에 집어 넣기
		state[minRouteSpace.getRow()][minRouteSpace.getColumn()]
				.setCarNo(carNo);
		state[minRouteSpace.getRow()][minRouteSpace.getColumn()]
				.setNo(state[1][9].getNo());
		
		return minRouteSpace;
	}

	class Plate implements Cloneable {
		private Integer no;
		private int row;
		private int column;
		private Integer carNo;

		public Plate(int row, int column) {
			this.row = row;
			this.column = column;
		}

		public Plate() {
			// TODO Auto-generated constructor stub
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

		public Integer getCarNo() {
			return carNo;
		}

		public void setCarNo(Integer carNo) {
			this.carNo = carNo;
		}

		public Integer getNo() {
			return no;
		}

		public void setNo(Integer no) {
			this.no = no;
		}

		public void clear() {
			no = null;
			carNo = null;
		}

		@Override
		protected Plate clone() throws CloneNotSupportedException {
			return (Plate) super.clone();
		}
	}
}

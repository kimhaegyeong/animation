// 빈 플레이트 찾기
/*
 발췌 : 
 c++ 자료구조론 - Horowitz 외.. P312
 최소-비용신장 트리
 1. 그래프 내에 있는 간선드란을 사용해야 한다.
 2. 정확하게 n-1개의 간선만을 사용해야 한다.
 3. 사이클을 생성하는 간선을 사용해서는 안된다.
 
 Kruskal 알고리즘
 - 한번에 하나씩 간선을 추가하면서 최소 신장 트리를 구축한다.
 - 포함할 간선을 비용의 크기 순으로 선택해간다.
 */

/*
 findEmptyPlate()에 ArrayList 사용
 step1 : liftB 를 최단 경로 이동
 */
package windowBuilder07;

import java.util.ArrayList;
import java.util.Iterator;

public class ParkingLot02 {
	private int[] liftA, liftB, liftC;
	private boolean closedDoor;
	private Plate[] plates;

	private Plate[][] state; // 지하 주차장의 현재 상태, 맵

	ParkingLot02() {
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

	public void findEmptyPlate() {
		ArrayList<Integer> emptyPlateNo = new ArrayList<Integer>();	
		ArrayList<Integer> temp = new ArrayList<Integer>();	

		int num = 0;
		int routeCount = 0;
		int minRouteCount = 0;
		int minRoutePlateNo = 0; 
		
		
	/*	for (int i = 0; i < plates.length; i++) {
			if (plates[i].getCarNo() == null) {	
				emptyPlateNo.add(i);
				num++;
			}
		}
		*/
	
		emptyPlateNo.add(8);
		emptyPlateNo.add(2);
		emptyPlateNo.add(20);
		emptyPlateNo.add(32);
		emptyPlateNo.add(14);

		
		// 비어있는 plate 출력
		/*
		for (Integer temp : emptyPlateNO)
			System.out.println(temp);
		*/
				
		for (int i = 0; i < emptyPlateNo.size(); i++) {
			routeCount = Math.abs(plates[emptyPlateNo.get(i)].getRow() - liftB[0]);
		
			if ( routeCount < minRouteCount ) { 
				minRouteCount = routeCount;
				minRoutePlateNo = emptyPlateNo.get(i);
				temp = null;
				temp.add(emptyPlateNo.get(i));
			} else if ( routeCount == minRouteCount ) { 
				temp.add(emptyPlateNo.get(i));
			}

			
			System.out.println(emptyPlateNo.get(i) + " " + routeCount);
		} 
	
		System.out.println("------ 최단 경로 -----");
		for (Integer minRoute : temp) {
			System.out.println(minRoute);
		}
		
		
		
	}
	

	class Plate {
		private int row;
		private int column;
		private Integer carNo;

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

		public Integer getCarNo() {
			return carNo;
		}

		public void setCarNo(Integer carNo) {
			this.carNo = carNo;
		}
		
	}



	
}

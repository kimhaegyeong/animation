package windowBuilder11;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import windowBuilder11.ParkingLot.Plate;

public class ParkinglotView extends JFrame {
	private static JTable table;
	private boolean closedDoor = true;
	private JTextField textField;
	private ParkingLot parkingLot;
	private int[] car = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ParkinglotView frame = new ParkinglotView();

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ParkinglotView() {
		setTitle("지하주차장 시뮬레이션");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 800, 600);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(12, 47, 776, 309);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblf = new JLabel("1F");
		lblf.setFont(new Font("Lucida Bright", Font.BOLD, 15));
		lblf.setBounds(12, 40, 46, 25);
		panel_1.add(lblf);

		JLabel lblB = new JLabel("B2");
		lblB.setFont(new Font("Lucida Bright", Font.BOLD, 15));
		lblB.setBounds(12, 90, 46, 25);
		panel_1.add(lblB);

		JLabel lblB_1 = new JLabel("B3");
		lblB_1.setFont(new Font("Lucida Bright", Font.BOLD, 15));
		lblB_1.setBounds(12, 120, 46, 25);
		panel_1.add(lblB_1);

		JLabel lblB_2 = new JLabel("B4");
		lblB_2.setFont(new Font("Lucida Bright", Font.BOLD, 15));
		lblB_2.setBounds(12, 150, 46, 25);
		panel_1.add(lblB_2);

		JLabel lblB_3 = new JLabel("B5");
		lblB_3.setFont(new Font("Lucida Bright", Font.BOLD, 15));
		lblB_3.setBounds(12, 180, 46, 25);
		panel_1.add(lblB_3);

		JLabel lblB_4 = new JLabel("B6");
		lblB_4.setFont(new Font("Lucida Bright", Font.BOLD, 15));
		lblB_4.setBounds(12, 210, 46, 25);
		panel_1.add(lblB_4);

		/************************************************************************************/
		final Color color = UIManager.getColor("Table.gridColor");

		DefaultTableModel dm = new DefaultTableModel(10, 19) {
			public void setValueAt(Object obj, int row, int col) {
				if (obj instanceof MyData) {
					super.setValueAt(obj, row, col);
				} else {
					MyData myData = null;
					Object oldObject = getValueAt(row, col);
					if (oldObject == null) {
						myData = new MyData(obj, new LinesBorder(color, 0));
					} else if (oldObject instanceof MyData) {
						myData = (MyData) oldObject;
					} else {
						System.out.println("error");
						return;
					}
					myData.setObject(obj);
					super.setValueAt(myData, row, col);
				}
			}
		};

		table = new JTable(dm);
		table.setCellSelectionEnabled(true);
		table.setBounds(40, 25, 722, 241);
		table.setRowHeight(30);
		table.setRowHeight(0, 10);
		table.setRowHeight(2, 25);
		table.setRowHeight(8, 15);
		table.setRowHeight(9, 15);

		table.getColumnModel().getColumn(0).setPreferredWidth(5);
		table.getColumnModel().getColumn(1).setPreferredWidth(5);
		table.getColumnModel().getColumn(3).setPreferredWidth(5);
		table.getColumnModel().getColumn(8).setPreferredWidth(5);
		table.getColumnModel().getColumn(10).setPreferredWidth(5);
		table.getColumnModel().getColumn(15).setPreferredWidth(5);
		table.getColumnModel().getColumn(17).setPreferredWidth(5);
		table.getColumnModel().getColumn(18).setPreferredWidth(5);

		// 셀마다 배경색 지정
		table.setDefaultRenderer(Object.class, new CustomTableCellRenderer());

		// plate 렌더링
		parkingLot = new ParkingLot();

		for (int i = 0; i < parkingLot.getState().length; i++) {
			for (int j = 0; j < parkingLot.getState()[0].length; j++) {
				if (parkingLot.getState()[i][j].getNo() != null) {
					BorderColor plateColor = new BorderColor(table, i, j,
							Color.blue, new Insets(0, 0, 3, 0));
				}
			}
		}

		// lift 렌더링
		BorderColor liftARendering = new BorderColor(table,
				parkingLot.getLiftA()[0] + 1, parkingLot.getLiftA()[1],
				Color.red, new Insets(15, 0, 0, 0));
		BorderColor Renderingring = new BorderColor(table,
				parkingLot.getLiftB()[0] + 1, parkingLot.getLiftB()[1],
				Color.red, new Insets(15, 0, 0, 0));
		BorderColor liftCColor = new BorderColor(table,
				parkingLot.getLiftC()[0] + 1, parkingLot.getLiftC()[1],
				Color.red, new Insets(15, 0, 0, 0));

		// door 렌더링
		BorderColor BorderColor1 = new BorderColor(table, 0, 8, Color.blue,
				new Insets(3, 3, 0, 3));
		BorderColor BorderColor2 = new BorderColor(table, 1, 8, Color.blue,
				new Insets(0, 3, 3, 3));

		panel_1.add(table);

		/**************************************************************************************/

		JLabel lblA = new JLabel("A");
		lblA.setFont(new Font("Dialog", Font.BOLD, 15));
		lblA.setBounds(90, 280, 56, 14);
		panel_1.add(lblA);

		JLabel lblB_5 = new JLabel("B");
		lblB_5.setFont(new Font("Dialog", Font.BOLD, 15));
		lblB_5.setBounds(400, 280, 56, 14);
		panel_1.add(lblB_5);

		JLabel lblC = new JLabel("C");
		lblC.setFont(new Font("Dialog", Font.BOLD, 15));
		lblC.setBounds(700, 280, 56, 14);
		panel_1.add(lblC);

		JLabel label = new JLabel("지하주차장 시뮬레이션");
		label.setFont(new Font("Dialog", Font.BOLD, 20));
		label.setBounds(12, 12, 267, 26);
		panel.add(label);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 365, 776, 223);
		panel.add(panel_2);
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder("Control Box"));

		JLabel lblDoor = new JLabel("Door");
		lblDoor.setFont(new Font("Dialog", Font.BOLD, 12));
		lblDoor.setBounds(12, 46, 60, 15);
		panel_2.add(lblDoor);

		JButton btnOpen = new JButton("Open");
		btnOpen.addActionListener(new DoorOpenListener());
		btnOpen.setBounds(85, 35, 99, 30);
		panel_2.add(btnOpen);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new DoorCloseListener());
		btnClose.setBounds(205, 35, 99, 30);
		panel_2.add(btnClose);

		JLabel label_1 = new JLabel("자동차 번호");
		label_1.setBounds(12, 84, 75, 15);
		panel_2.add(label_1);

		textField = new JTextField();
		textField.setBounds(85, 77, 223, 29);
		panel_2.add(textField);
		textField.setColumns(10);

		JLabel lblParking = new JLabel("주차");
		lblParking.setBounds(12, 120, 60, 15);
		panel_2.add(lblParking);

		JButton btnOut = new JButton("입고");
		btnOut.addActionListener(new InputCarListener());
		btnOut.setBounds(85, 120, 99, 30);
		panel_2.add(btnOut);

		JButton btnIn = new JButton("출고");
		btnIn.addActionListener(new DoorCloseListener());
		btnIn.setBounds(205, 120, 99, 30);
		panel_2.add(btnIn);

		JLabel lblCar = new JLabel("Car");
		lblCar.setBounds(12, 180, 60, 15);
		panel_2.add(lblCar);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new AddCar());
		btnAdd.setBounds(85, 180, 99, 30);
		panel_2.add(btnAdd);

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new DoorCloseListener());
		btnRemove.setBounds(205, 180, 99, 30);
		panel_2.add(btnRemove);

	}

	// inner class
	public class CustomTableCellRenderer extends DefaultTableCellRenderer {
		protected Border noFocusBorder;

		protected Border columnBorder;

		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			Component cell = super.getTableCellRendererComponent(table, value,
					isSelected, hasFocus, row, column);

			if (isSelected) {
				setForeground(table.getSelectionForeground());
				setBackground(table.getSelectionBackground());
			} else {
				if (row > 2 && row < 9 && column > 0 && column < 4) { // A
					cell.setBackground(Color.white);
				} else if ((row > 1 && row < 9 && column > 7 && column < 11)
						|| row == 1 && column > 8 && column < 11) { // B
					cell.setBackground(Color.white);
				} else if (row > 2 && row < 9 && column > 14 && column < 18) { // C
					cell.setBackground(Color.white);
				} else if (row >= 0 && row < 2 && column >= 0 && column < 8) { // 지상
					cell.setBackground(Color.white);
				} else if (closedDoor == true && row >= 0 && row < 2
						&& column == 8) { // door
											// close
					cell.setBackground(new Color(0x3F3F3F));
				} else if (closedDoor == false && row == 0 && row < 2
						&& column == 8) { // door open
					cell.setBackground(new Color(0x3F3F3F));
				} else if (closedDoor == false && row == 1 && row < 2
						&& column == 8) { // door open
					cell.setBackground(Color.white);
				} else if (row > 2 && row < 8 && column > 3 && column < 8) { // 주차
																				// 공간
					cell.setBackground(new Color(0xC0C0C0));
				} else if (row > 2 && row < 8 && column > 10 && column < 15) { // 주차
																				// 공간
					cell.setBackground(new Color(0xC0C0C0));
				} else
					cell.setBackground(new Color(0x777777));

				// car 렌더링
				if (car != null && row == car[0] && column == car[1]) {
					cell.setBackground(Color.yellow);
				}

			}
			setFont(table.getFont());

			if (hasFocus) {
				setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
				if (table.isCellEditable(row, column)) {
					setForeground(UIManager
							.getColor("Table.focusCellForeground"));
					setBackground(UIManager
							.getColor("Table.focusCellBackground"));
				}
			} else {
				if (value instanceof CellBorder) {
					Border border = ((CellBorder) value).getBorder();
					setBorder(border);
				} else {
					if (columnBorder != null) {
						setBorder(columnBorder);
					} else {
						setBorder(noFocusBorder);
					}
				}
			}

			if (parkingLot.getState()[row][column].getCarNo() != null) {
				setText(parkingLot.getState()[row][column].getCarNo()
						.toString());
			} else {
				setText("");
			}

			return cell;
		}
	}

	class BorderColor {
		JTable table;
		Color color = UIManager.getColor("Table.gridColor");

		BorderColor(JTable table, int row, int column, Color color, Insets inset) {
			this.table = table;
			setCellBorder(true, false, row, column, color, inset);
		}

		// 특정 위치에 밑줄 가능
		private void setCellBorder(boolean isReplace, boolean isBlock, int row,
				int column, Color color, Insets insets) {
			boolean isTop, isLeft, isBottom, isRight;

			MyData myData = new MyData("", new LinesBorder(color, 0));

			LinesBorder border = (LinesBorder) myData.getBorder();

			if (isBlock) {
				Insets tmp = new Insets(0, 0, 0, 0);
				// if (isTop)
				tmp.top = Math.max(tmp.top, insets.top);
				// if (isLeft)
				tmp.left = Math.max(tmp.left, insets.left);
				// if (isBottom)
				tmp.bottom = Math.max(tmp.bottom, insets.bottom);
				// if (isRight)
				tmp.right = Math.max(tmp.right, insets.right);
				border.append(tmp, isReplace);
			} else {
				border.append(insets, isReplace);
			}

			table.setValueAt(myData, row, column);

			table.clearSelection();
			table.revalidate();
			table.repaint();
		}

	}

	class DoorOpenListener extends DefaultTableCellRenderer implements
			ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// System.out.println(parkingLot.getState()[1][9].getNo());

			if (parkingLot.getState()[1][9].getNo() == null) {
				// 경고창
				JOptionPane.showMessageDialog(null, "문을 열 수 없습니다.");
			} else {
				closedDoor = false;

				// 셀마다 배경색 지정
				table.setDefaultRenderer(Object.class,
						new CustomTableCellRenderer());

				BorderColor BorderColor1 = new BorderColor(table, 0, 8,
						Color.blue, new Insets(3, 3, 3, 3));
				BorderColor BorderColor2 = new BorderColor(table, 1, 8,
						Color.blue, new Insets(0, 0, 0, 0));
				table.repaint();
			}
		}
	}

	class DoorCloseListener extends DefaultTableCellRenderer implements
			ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// System.out.println("door close");

			closedDoor = true;

			// 셀마다 배경색 지정
			table.setDefaultRenderer(Object.class,
					new CustomTableCellRenderer());

			BorderColor BorderColor1 = new BorderColor(table, 0, 8, Color.blue,
					new Insets(3, 3, 0, 3));
			BorderColor BorderColor2 = new BorderColor(table, 1, 8, Color.blue,
					new Insets(0, 3, 3, 3));

			table.repaint();

		}
	}

	class InputCarListener extends DefaultTableCellRenderer implements
			ActionListener {
		Plate minRouteSpace;
		String inputText;

		@Override
		public void actionPerformed(ActionEvent e) {
			inputText = textField.getText().toString();

			if (inputText.equals("")) {
				JOptionPane.showMessageDialog(null, "자동차 번호를 입력하세요");
			} else if (inputText.length() != 4) {
				JOptionPane.showMessageDialog(null, "자동차 번호는 4자리이어야 합니다.");
			} else if (!inputText.matches("\\d{4}")) {
				JOptionPane.showMessageDialog(null, "숫자만 입력하세요.");
			} else if (closedDoor) {
				JOptionPane.showMessageDialog(null, "문이 닫혀 있습니다.");
			} else {
				parkingLot.getState()[1][9].setCarNo(Integer
						.parseInt(inputText));

				minRouteSpace = parkingLot.findEmptySpace(Integer
						.parseInt(inputText));

				// liftB 옮기기
				MoveLift moveLiftB = new MoveLift(minRouteSpace);
				moveLiftB.setTimer();

				// plate 옮기기
				/*MovePlate movePlate = new MovePlate(parkingLot.getLiftB(),
						minRouteSpace);
				movePlate.setTimer();
*/
				table.setDefaultRenderer(Object.class,
						new CustomTableCellRenderer());
				table.repaint();
			}
			/*
			 * System.out.println("inputText : " + inputText);
			 * System.out.println("inputCarNo :" +
			 * parkingLot.getState()[1][9].getCarNo());
			 */
		}

	}

	class AddCar extends DefaultTableCellRenderer implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			car = new int[] { 1, 5 };

			table.setDefaultRenderer(Object.class,
					new CustomTableCellRenderer());
			table.repaint();

			Plate minRoutePlate = parkingLot.findEmptyPlate();
			// liftB 옮기기
			MoveLift moveLiftB = new MoveLift(minRoutePlate);
			System.out.println(">>>> moveLiftB " + parkingLot.getLiftB()[0]);
			moveLiftB.setTimer();

			// plate를 liftB로 옮기기		
			MovePlate movePlate = new MovePlate(minRoutePlate,
					parkingLot.getLiftB());
			System.out.println(">>>> movePlate " + parkingLot.getLiftB()[0]);
			movePlate.setTimer();

			// F1 층으로 올라가기
			MoveLift gotoF1Lift = new MoveLift("F1");
			System.out.println(">>>> gotoF1Lift");
			gotoF1Lift.setTimer();

		}
	}

	class MoveLift {
		private Timer timer;
		private int[] departLocation;
		private boolean empty = true;
		Integer minRoutePlateNo;

		public MoveLift(Plate minRoutePlate) {
			// this.minRoutePlateNo = minRoutePlateNo;
			departLocation = new int[] { minRoutePlate.getRow(),
					minRoutePlate.getColumn() };
			this.minRoutePlateNo = minRoutePlate.getNo();
		}

		public MoveLift(String F1) {
			departLocation = new int[] { 1, 9 };
		}

		public void setTimer() {
			MovePlate movePlate = new MovePlate();

			ActionListener action = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					int i = parkingLot.getLiftB()[0];
					
					int temp = i;

					if (parkingLot.getState()[parkingLot.getLiftB()[0]][parkingLot
							.getLiftB()[1]].getNo() != null) {
						empty = false;
					}

					if (i > departLocation[0]
							&& (parkingLot.getLiftB()[0] - departLocation[0]) > 0) {
						liftRendering(temp + 1, parkingLot.getLiftB()[1],
								new Insets(0, 0, 0, 0));
						temp = i - 1;
						liftRendering(temp + 1, parkingLot.getLiftB()[1],
								new Insets(15, 0, 0, 0));
					} else if (i < departLocation[0]
							&& (departLocation[0] - parkingLot.getLiftB()[0]) > 0) {
						if (i == parkingLot.getLiftB()[0]) {
							liftRendering(temp - 1, parkingLot.getLiftB()[1],
									new Insets(0, 0, 0, 0));
						}
						liftRendering(temp, parkingLot.getLiftB()[1],
								new Insets(0, 0, 0, 0));
						temp = i + 1;
						liftRendering(temp + 1, parkingLot.getLiftB()[1],
								new Insets(15, 0, 0, 0));
					} 
					parkingLot.setLiftB(temp, parkingLot.getLiftB()[1]);

					if (temp == departLocation[0]) {
						System.out.println("temp" +temp);
						parkingLot.setLiftB(temp, parkingLot.getLiftB()[1]);
						timer.stop();
					}

					if (empty == false) {
						movePlate.PlateRendering(parkingLot.getLiftB()[0],
								parkingLot.getLiftB()[1],
								new Insets(0, 0, 3, 0));

						try {
							parkingLot.getState()[temp][parkingLot.getLiftB()[1]] = (Plate) parkingLot
									.getState()[i][parkingLot.getLiftB()[1]]
									.clone();
						} catch (CloneNotSupportedException e) {
							e.printStackTrace();
						}
						parkingLot.getState()[i][parkingLot.getLiftB()[1]]
								.clear();

					}
				}
			};

			timer = new Timer(500, action);
			timer.setInitialDelay(0);
			timer.start();
		}

		void liftRendering(int row, int column, Insets inset) {
			BorderColor Renderingring = new BorderColor(table, row, column,
					Color.red, inset);
		}
	}

	class MovePlate {
		private Timer timer;
		int[] seletedPlate;
		int[] moveingPlate;
		//Object minRoutePlate;
		Object departLocation;
		Object arriveLocation;

		int[] temp;

		public MovePlate() {

		}

		public MovePlate(Plate departLocation, int[] arriveLocation) {
			this.departLocation = departLocation;

			seletedPlate = new int[] { departLocation.getRow(),
					departLocation.getColumn() };

			moveingPlate = new int[] { arriveLocation[0], arriveLocation[1] };
		}

		public MovePlate(int[] departLocation, Plate arriveLocation) {
			this.departLocation = departLocation;

			seletedPlate = new int[] { departLocation[0], departLocation[1] };

			moveingPlate = new int[] { arriveLocation.getRow(),
					arriveLocation.getColumn() };
			
		}

		public void setTimer() {
			System.out.println("seletedPlate[1] : " + seletedPlate[1]);
			System.out.println("parkingLot.getLiftB() : " + parkingLot.getLiftB()[0] + ", " +
							parkingLot.getLiftB()[1]);
			
			ActionListener action = new ActionListener() {
				
				int i = parkingLot.getLiftB()[1];

				@Override
				public void actionPerformed(ActionEvent event) {
					if (moveingPlate[1] == seletedPlate[1]) {
						timer.stop();
					} else {
						temp = moveingPlate.clone();						

						if (seletedPlate[1] - temp[1] < 0) {
							moveingPlate[1]--;
							if (moveingPlate[1] == 3 || moveingPlate[1] == 8 || moveingPlate[1] == 10 || moveingPlate[1] == 15) {
								moveingPlate[1]--;
							}
						} else if (seletedPlate[1] - temp[1] > 0) {
							moveingPlate[1]++;
							if (moveingPlate[1] == 3 || moveingPlate[1] == 8 || moveingPlate[1] == 10 || moveingPlate[1] == 15) {
								moveingPlate[1]++;
							}
						}
						System.out.println(temp[1] + ">> " + moveingPlate[1]); 

						PlateRendering(moveingPlate[0], moveingPlate[1],
								new Insets(0, 0, 0, 0));
						
						PlateRendering(temp[0], temp[1],
								new Insets(0, 0, 3, 0));

						// depp copy - clone()
						try {
							parkingLot.getState()[temp[0]][temp[1]] = (Plate) parkingLot
									.getState()[moveingPlate[0]][moveingPlate[1]]
									.clone();
						} catch (CloneNotSupportedException e) {
							e.printStackTrace();
						}
						parkingLot.getState()[moveingPlate[0]][moveingPlate[1]]
								.clear();

					}
				}
			};
			
			timer = new Timer(500, action);
			timer.setInitialDelay(0);
			timer.start();
		}

		void PlateRendering(int row, int column, Insets inset) {
			BorderColor Rendering = new BorderColor(table, row, column,
					Color.blue, inset);
		}
	}

}

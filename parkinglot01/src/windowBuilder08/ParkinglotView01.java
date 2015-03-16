package windowBuilder08;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ParkinglotView01 extends JFrame {
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
					ParkinglotView01 frame = new ParkinglotView01();

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ParkinglotView01() {
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

		// plate 색깔
		parkingLot = new ParkingLot();

		for (int i = 0; i < parkingLot.getState().length; i++) {
			for (int j = 0; j < parkingLot.getState()[0].length; j++) {
				if (parkingLot.getState()[i][j] != null) {
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
		btnOut.addActionListener(new DoorOpenListener());
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
				} else if (row > 2 && row < 8 && column > 3 && column < 8) {
					cell.setBackground(new Color(0xC0C0C0));
				} else if (row > 2 && row < 8 && column > 10 && column < 15) {
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
			setText((value == null) ? "" : value.toString());

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
			System.out.println("door open");
			closedDoor = false;

			// 셀마다 배경색 지정
			table.setDefaultRenderer(Object.class,
					new CustomTableCellRenderer());

			BorderColor BorderColor1 = new BorderColor(table, 0, 8, Color.blue,
					new Insets(3, 3, 3, 3));
			BorderColor BorderColor2 = new BorderColor(table, 1, 8, Color.blue,
					new Insets(0, 0, 0, 0));
			table.repaint();
		}
	}

	class DoorCloseListener extends DefaultTableCellRenderer implements
			ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("door close");

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

	class AddCar extends DefaultTableCellRenderer implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			car = new int[] { 1, 5 };

			table.setDefaultRenderer(Object.class,
					new CustomTableCellRenderer());
			table.repaint();

			int minRoutePlateNo = parkingLot.findEmptyPlate();
			
			// liftB 이동
			//System.out.println(parkingLot.getPlates()[minRoutePlateNo].getRow());
			for (int i = parkingLot.getLiftB()[0]; i >= parkingLot.getPlates()[minRoutePlateNo].getRow(); i--) {
				liftRendering(parkingLot.getLiftB(), new Insets(0, 0, 0, 0));
				parkingLot.setLiftB(i, 9);					
				liftRendering(parkingLot.getLiftB(), new Insets(15, 0, 0, 0));				
				System.out.println(i);
			}
			
			

			
			table.repaint();

		}
		
		void liftRendering (int[] lift, Insets inset) {
			BorderColor Renderingring = new BorderColor(table,
					lift[0] + 1, lift[1], Color.red, inset);
		}
	}
}

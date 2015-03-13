// 테이블 색 변경
package windowBuilder02;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JTextField;

public class parkinglotView02 extends JFrame {
	private JTable table;
	private boolean closedDoor = true;
	private JTextField textField;
	private ParkingLot parkingLot;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					parkinglotView02 frame = new parkinglotView02();

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
	public parkinglotView02() {
		parkingLot = new ParkingLot();
		
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

		table = new JTable(10, 19);
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

		panel_1.add(table);

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
		btnAdd.addActionListener(new DoorOpenListener());
		btnAdd.setBounds(85, 180, 99, 30);
		panel_2.add(btnAdd);

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new DoorCloseListener());
		btnRemove.setBounds(205, 180, 99, 30);
		panel_2.add(btnRemove);
		
	}

	// inner class
	public class CustomTableCellRenderer extends DefaultTableCellRenderer {
		public Component getTableCellRendererComponent(JTable table,
				Object obj, boolean isSelected, boolean hasFocus, int row,
				int column) {
			Component cell = super.getTableCellRendererComponent(table, obj,
					isSelected, hasFocus, row, column);

			/*
			 * if ((row == 0 && column == 8) || (row == 1 && column == 8)) {
			 * cell.setBackground(new Color(0x0066FF)); }
			 */

			if (row > 2 && row < 9 && column > 0 && column < 4) { // A
				cell.setBackground(Color.white);
			} else if ((row > 1 && row < 9 && column > 7 && column < 11)
					|| row == 1 && column > 8 && column < 11) { // B
				cell.setBackground(Color.white);
			} else if (row > 2 && row < 9 && column > 14 && column < 18) { // C
				cell.setBackground(Color.white);
			} else if (row >= 0 && row < 2 && column >= 0 && column < 8) { // 지상
				cell.setBackground(Color.white);
			} else if (closedDoor == true && row >= 0 && row < 2 && column == 8 ) {	// door close			
				cell.setBackground(Color.gray);
			} else if (closedDoor == false && row == 0 && row < 2 && column == 8) {  // door open
					cell.setBackground(Color.gray);
			} else if (closedDoor == false && row == 1 && row < 2 && column == 8) {  // door open
				cell.setBackground(Color.white);
			}else if (row > 2 && row < 8  && column > 3 && column < 8) {
				cell.setBackground(new Color(0xFAFAFA));
			} else if (row > 2 && row < 8 && column > 10 && column < 15) {
				cell.setBackground(new Color(0xFAFAFA));
			} else
				cell.setBackground(new Color(0xC0C0C0));

			return cell;
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
			table.repaint();
		}
	}
}

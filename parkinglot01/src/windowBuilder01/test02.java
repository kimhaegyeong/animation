// 테이블 색 변경
package windowBuilder01;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JButton;

public class test02 extends JFrame {
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test02 frame = new test02();
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
	public test02() {
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
		lblDoor.setBounds(12, 46, 56, 14);
		panel_2.add(lblDoor);
		
		JButton btnOpen = new JButton("Open");
		btnOpen.setBounds(86, 41, 99, 24);
		panel_2.add(btnOpen);
		
		JButton btnClose = new JButton("Close");
		btnClose.setBounds(205, 41, 99, 24);
		panel_2.add(btnClose);
	}

	// inner class
	public class CustomTableCellRenderer extends DefaultTableCellRenderer {
		public Component getTableCellRendererComponent(JTable table,
				Object obj, boolean isSelected, boolean hasFocus, int row,
				int column) {
			Component cell = super.getTableCellRendererComponent(table, obj,
					isSelected, hasFocus, row, column);

			/*if ((row == 0 && column == 8) || (row == 1 && column == 8)) {
				cell.setBackground(new Color(0x0066FF));
			} */
			if (row > 1) {
				if (row > 2 && row < 9 && column > 0 && column < 4 ) {
					cell.setBackground(Color.white);
				} else if (row >= 0 && row < 9 && column > 7 && column < 11 ) {
					cell.setBackground(Color.white);
				}  else if (row > 2 && row < 9 && column > 14 && column < 18 ) {
					cell.setBackground(Color.white);
				} else 
					cell.setBackground(new Color(0xC0C0C0));
			} 
			if (row <= 1 && column > 10) {
				cell.setBackground(new Color(0xC0C0C0));
			} 
			
			/*
			 * if (row < 1 && column > 10) { cell.setBackground(new
			 * Color(0xC0C0C0));
			 * 
			 * }
			 */

			return cell;
		}
	}
}

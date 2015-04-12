package tycoon01;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MoldView {

	private JFrame frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MoldView window = new MoldView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MoldView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 300, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel top_info = new JPanel();
		top_info.setBounds(0, 0, 300, 40);
		frame.getContentPane().add(top_info);
		top_info.setLayout(null);
		
		JTextField money_textField = new JTextField();
		money_textField.setText("0");
		money_textField.setBounds(5, 5, 50, 30);
		top_info.add(money_textField);
		money_textField.setColumns(10);
		
		JLabel money_label = new JLabel("원");
		money_label.setFont(new Font("나눔고딕", Font.BOLD, 12));
		money_label.setBounds(60, 5, 20, 30);
		top_info.add(money_label);
		
		JLabel heart_label = new JLabel("하트");
		heart_label.setFont(new Font("나눔고딕", Font.BOLD, 12));
		heart_label.setBounds(150, 5, 30, 30);
		top_info.add(heart_label);
		
		JTextField heart_textField = new JTextField();
		heart_textField.setText("5");
		heart_textField.setBounds(180, 5, 20, 30);
		top_info.add(heart_textField);
		heart_textField.setColumns(10);

		JLabel time_label = new JLabel("시간");
		time_label.setFont(new Font("나눔고딕", Font.BOLD, 12));
		time_label.setBounds(210, 5, 30, 30);
		top_info.add(time_label);
		
		JTextField time_textField = new JTextField();
		time_textField.setText("5");
		time_textField.setBounds(240, 5, 50, 30);
		top_info.add(time_textField);
		time_textField.setColumns(10);
		
		/* 손님 */
		JPanel seconed_p = new JPanel();
		seconed_p.setBounds(0, 40, 300, 120);
		frame.getContentPane().add(seconed_p);
		seconed_p.setLayout(null);
		
		JButton client1 = new JButton("client1");
		client1.setBounds(15, 15, 75, 96);
		seconed_p.add(client1);
		
		JButton client2 = new JButton("client2n");
		client2.setBounds(113, 15, 75, 96);
		seconed_p.add(client2);
		
		JButton client3 = new JButton("client3");
		client3.setBounds(210, 15, 75, 96);
		seconed_p.add(client3);

		/* 붕어빵 갯수 */
		JPanel third_p = new JPanel();
		third_p.setBounds(0, 160, 300, 40);
		frame.getContentPane().add(third_p);
		third_p.setLayout(null);
		
		JLabel num_label = new JLabel("개수");
		num_label.setFont(new Font("나눔고딕", Font.BOLD, 12));
		num_label.setBounds(5, 5, 30, 30);
		third_p.add(num_label);
		
		JTextField num_textField = new JTextField();
		num_textField.setText("5");
		num_textField.setBounds(40, 5, 50, 30);
		third_p.add(num_textField);
		num_textField.setColumns(10);
		
		/* 붕어빵 틀 */
		JPanel last_molds = new JPanel();
		last_molds.setBounds(0, 220, 300, 260);
		frame.getContentPane().add(last_molds);
		last_molds.setLayout(null);
		
		JButton mold01 = new JButton("mold01");
		mold01.setBackground(Color.BLACK);
		mold01.setBounds(15, 0, 60, 50);
		last_molds.add(mold01);
		
		JButton mold02 = new JButton("mold02");
		mold02.setBounds(85, 0, 60, 50);
		mold02.setIcon(new ImageIcon("img/rare.jpg"));
		last_molds.add(mold02);
		
		/* 이미지 아이콘 성공 */
		JButton mold03 = new JButton(new ImageIcon(this.getClass().getResource("rare.jpg")));
		mold03.setBounds(155, 0, 60, 50);
		last_molds.add(mold03);
		
		Icon i=new ImageIcon(this.getClass().getResource("rare.jpg"));
		JButton mold04 = new JButton();
		mold04.setBounds(225, 0, 60, 50);
		mold04.setIcon(i);
		last_molds.add(mold04);
		
		JButton mold05 = new JButton("mold05");
		mold05.setBounds(15, 60, 60, 50);
		last_molds.add(mold05);
		
		JButton mold06 = new JButton("mold06");
		mold06.setBounds(85, 60, 60, 50);
		last_molds.add(mold06);
		
		JButton mold07 = new JButton("mold07");
		mold07.setBounds(155, 60, 60, 50);
		last_molds.add(mold07);
		
		JButton mold08 = new JButton("mold08");
		mold08.setBounds(225, 60, 60, 50);
		last_molds.add(mold08);
		
		JButton mold09 = new JButton("mold09");
		mold09.setBounds(15, 120, 60, 50);
		last_molds.add(mold09);
		
		JButton mold10 = new JButton("mold10");
		mold10.setBounds(85, 120, 60, 50);
		last_molds.add(mold10);
		
		JButton mold11 = new JButton("mold11");
		mold11.setBounds(155, 120, 60, 50);
		last_molds.add(mold11);
		
		JButton mold12 = new JButton("mold12");
		mold12.setBounds(225, 120, 60, 50);
		last_molds.add(mold12);
		
		JButton mold13 = new JButton("mold13");
		mold13.setBounds(15, 180, 60, 50);
		last_molds.add(mold13);
		
		JButton mold14 = new JButton("mold14");
		mold14.setBounds(85, 180, 60, 50);
		last_molds.add(mold14);
		
		JButton mold15 = new JButton("mold15");
		mold15.setBounds(155, 180, 60, 50);
		last_molds.add(mold15);
		
		JButton mold16 = new JButton("mold16");
		mold16.setBounds(225, 180, 60, 50);
		last_molds.add(mold16);
	}	
}

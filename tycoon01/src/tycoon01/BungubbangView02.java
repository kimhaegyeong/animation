package tycoon01;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class BungubbangView02 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BungubbangView02 window = new BungubbangView02();
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
	public BungubbangView02() {
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

		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 255, 250));
		panel.setBounds(36, 69, 233, 60);
		frame.getContentPane().add(panel);

		JLabel label = new JLabel("붕어빵 타이쿤");
		label.setForeground(new Color(244, 164, 96));
		label.setFont(new Font("나눔고딕", Font.BOLD, 33));
		panel.add(label);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(36, 162, 233, 245);
		frame.getContentPane().add(panel_1);

		JButton button = new JButton("게임 시작하기");
		button.setFont(new Font("나눔고딕", Font.BOLD, 12));
		button.addActionListener(new StartGame());
		panel_1.add(button);
	}

	private class StartGame implements ActionListener {
		Bungubbang[] molds;
		Queue<Bungubbang> diplayBungubbang;
		int money;
		JTextField num_textField;

		StartGame() {
			money = 0;
		}

		public void actionPerformed(ActionEvent e) {
			// System.out.println("click");
			molds = new Bungubbang[16];
			diplayBungubbang = new LinkedList<Bungubbang>();

			frame.getContentPane().removeAll();

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
			time_textField.setText("60");
			time_textField.setBounds(240, 5, 50, 30);
			top_info.add(time_textField);
			time_textField.setColumns(10);

			/* 손님 */
			JPanel seconed_p = new JPanel();
			seconed_p.setBounds(0, 40, 300, 120);
			frame.getContentPane().add(seconed_p);
			seconed_p.setLayout(null);

			JButton client1 = new JButton();
			client1.setBounds(15, 15, 75, 96);
			seconed_p.add(client1);
						
			JButton client2 = new JButton();
			client2.setBounds(113, 15, 75, 96);
			seconed_p.add(client2);

			JButton client3 = new JButton();
			client3.setBounds(210, 15, 75, 96);
			seconed_p.add(client3);
			
			JLabel client1_label = new JLabel("5");
			client1_label.setFont(new Font("나눔고딕", Font.BOLD, 12));
			client1_label.setBounds(15, 15, 30, 30);
			seconed_p.add(client1_label);

			JLabel client2_label = new JLabel("5");
			client2_label.setFont(new Font("나눔고딕", Font.BOLD, 12));
			client2_label.setBounds(113, 15, 30, 30);
			seconed_p.add(client2_label);
			
			JLabel client3_label = new JLabel("5");
			client3_label.setFont(new Font("나눔고딕", Font.BOLD, 12));
			client3_label.setBounds(210, 15, 30, 30);
			seconed_p.add(client3_label);

			// 레이어드 시키기		
			seconed_p.setComponentZOrder(client1, 1);
			seconed_p.setComponentZOrder(client1_label, 0);
			
			seconed_p.setComponentZOrder(client2, 1);
			seconed_p.setComponentZOrder(client2_label, 0);
			
			seconed_p.setComponentZOrder(client3, 1);
			seconed_p.setComponentZOrder(client3_label, 0);
			
			/* 붕어빵 갯수 */
			JPanel third_p = new JPanel();
			third_p.setBounds(0, 160, 300, 40);
			frame.getContentPane().add(third_p);
			third_p.setLayout(null);

			JLabel num_label = new JLabel("개수");
			num_label.setFont(new Font("나눔고딕", Font.BOLD, 12));
			num_label.setBounds(5, 5, 30, 30);
			third_p.add(num_label);

			num_textField = new JTextField();
			num_textField.setText(Integer.toString(diplayBungubbang.size()));
			num_textField.setBounds(40, 5, 50, 30);
			third_p.add(num_textField);
			num_textField.setColumns(10);

			/* 붕어빵 틀 */
			JPanel last_molds = new JPanel();
			last_molds.setBounds(0, 220, 300, 260);
			frame.getContentPane().add(last_molds);
			last_molds.setLayout(null);

			JButton mold01 = new JButton();
			mold01.setBounds(15, 0, 60, 50);
			mold01.setBackground(Color.BLACK);
			mold01.addActionListener(new ClickMold(mold01, 0));
			last_molds.add(mold01);

			JButton mold02 = new JButton();
			mold02.setBounds(85, 0, 60, 50);
			mold02.setBackground(Color.BLACK);
			mold02.addActionListener(new ClickMold(mold02, 1));
			last_molds.add(mold02);

			JButton mold03 = new JButton();
			mold03.setBounds(155, 0, 60, 50);
			mold03.setBackground(Color.BLACK);
			mold03.addActionListener(new ClickMold(mold03, 2));
			last_molds.add(mold03);

			JButton mold04 = new JButton();
			mold04.setBounds(225, 0, 60, 50);
			mold04.setBackground(Color.BLACK);
			mold04.addActionListener(new ClickMold(mold04, 3));
			last_molds.add(mold04);

			JButton mold05 = new JButton();
			mold05.setBounds(15, 60, 60, 50);
			mold05.setBackground(Color.BLACK);
			mold05.addActionListener(new ClickMold(mold05, 4));
			last_molds.add(mold05);

			JButton mold06 = new JButton();
			mold06.setBounds(85, 60, 60, 50);
			mold06.setBackground(Color.BLACK);
			mold06.addActionListener(new ClickMold(mold06, 5));
			last_molds.add(mold06);

			JButton mold07 = new JButton();
			mold07.setBounds(155, 60, 60, 50);
			mold07.setBackground(Color.BLACK);
			mold07.addActionListener(new ClickMold(mold07, 6));
			last_molds.add(mold07);

			JButton mold08 = new JButton();
			mold08.setBounds(225, 60, 60, 50);
			mold08.setBackground(Color.BLACK);
			mold08.addActionListener(new ClickMold(mold08, 7));
			last_molds.add(mold08);

			JButton mold09 = new JButton();
			mold09.setBounds(15, 120, 60, 50);
			mold09.setBackground(Color.BLACK);
			mold09.addActionListener(new ClickMold(mold09, 8));
			last_molds.add(mold09);

			JButton mold10 = new JButton();
			mold10.setBounds(85, 120, 60, 50);
			mold10.setBackground(Color.BLACK);
			mold10.addActionListener(new ClickMold(mold10, 9));
			last_molds.add(mold10);

			JButton mold11 = new JButton();
			mold11.setBounds(155, 120, 60, 50);
			mold11.setBackground(Color.BLACK);
			mold11.addActionListener(new ClickMold(mold11, 10));
			last_molds.add(mold11);

			JButton mold12 = new JButton();
			mold12.setBounds(225, 120, 60, 50);
			mold12.setBackground(Color.BLACK);
			mold12.addActionListener(new ClickMold(mold12, 11));
			last_molds.add(mold12);

			JButton mold13 = new JButton();
			mold13.setBounds(15, 180, 60, 50);
			mold13.setBackground(Color.BLACK);
			mold13.addActionListener(new ClickMold(mold13, 12));
			last_molds.add(mold13);

			JButton mold14 = new JButton();
			mold14.setBounds(85, 180, 60, 50);
			mold14.setBackground(Color.BLACK);
			mold14.addActionListener(new ClickMold(mold14, 13));
			last_molds.add(mold14);

			JButton mold15 = new JButton();
			mold15.setBounds(155, 180, 60, 50);
			mold15.setBackground(Color.BLACK);
			mold15.addActionListener(new ClickMold(mold15, 14));
			last_molds.add(mold15);

			JButton mold16 = new JButton();
			mold16.setBounds(225, 180, 60, 50);
			mold16.setBackground(Color.BLACK);
			mold16.addActionListener(new ClickMold(mold16, 15));
			last_molds.add(mold16);

			frame.getContentPane().repaint();

			// 기본 옵션창
			JOptionPane.showMessageDialog(null, "Game Start");

			Timer timer = new javax.swing.Timer(1000, new ActionListener() {
				Integer count = 60;

				public void actionPerformed(ActionEvent e) {
					count--;
					time_textField.setText(count.toString());

					if (count == 0) {
						((Timer) e.getSource()).stop();
						JOptionPane.showMessageDialog(null, "Game End");
					}
				}
			});
			timer.start();
			
			// 손님 방문
			Random random = new Random();
			int result = random.nextInt(2);
			
			People people = new People();
			
			if (result == 0) {
				comePeople(client1);
				client1_label.setText(Integer.toString(people.getBuyCount()));
			} else if (result == 1) {
				comePeople(client2);
				client2_label.setText(Integer.toString(people.getBuyCount()));

			} else if (result == 2) {
				comePeople(client3);
				client3_label.setText(Integer.toString(people.getBuyCount()));

			}
		}
		
		public void comePeople(JButton client) {
			client.setIcon(new ImageIcon(this.getClass()
					.getResource("img/man_orgin.jpg")));
		}

		private class ClickMold implements ActionListener {
			JButton mold;
			int no;
			int clickCount;
			Integer count;
			Timer timer;

			public ClickMold(JButton mold) {
				this.mold = mold;
				clickCount = 0;
			}

			public ClickMold(JButton mold, int no) {
				this.mold = mold;
				this.no = no;
				clickCount = 0;
			}

			public void actionPerformed(ActionEvent e) {
				if (clickCount == 0) {
					molds[no] = new Bungubbang();
					clickCount++;
					count = 0;
					changeIcon();

				} else if (clickCount == 1) {
					timer.stop();
					molds[no].setFront(molds[no].checkState(count));
					count = 0;
					clickCount++;
					changeIcon();

				} else if (clickCount == 2) {
					timer.stop();
					molds[no].setBack(molds[no].checkState(count));
					clickCount = 0;

					try {
						diplayBungubbang.offer((Bungubbang) molds[no].clone());
					} catch (CloneNotSupportedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					num_textField.setText(Integer.toString(diplayBungubbang
							.size()));

					mold.setIcon(null);
					molds[no] = null;
				}

				// System.out.println(no + " " + molds[no].getFront() + " " +
				// molds[no].getBack() + " " + molds[no].getSum());
			}

			public void changeIcon() {
				timer = new javax.swing.Timer(1000, new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						if (count < 2) {
							mold.setIcon(new ImageIcon(this.getClass()
									.getResource("img/rare.jpg")));
						} else if (count >= 2 && count <= 3) {
							mold.setIcon(new ImageIcon(this.getClass()
									.getResource("img/medium.jpg")));
						} else if (count >= 4 && count <= 5) {
							mold.setIcon(new ImageIcon(this.getClass()
									.getResource("img/best.jpg")));
						} else if (count >= 6 && count <= 7) {
							mold.setIcon(new ImageIcon(this.getClass()
									.getResource("img/medium_well.jpg")));
						} else if (count > 7) {
							mold.setIcon(new ImageIcon(this.getClass()
									.getResource("img/well_done.jpg")));
						}

						mold.repaint();
						count++;
					}
				});
				timer.start();
			}
		}
	}

}

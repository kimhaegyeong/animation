package tycoon02;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class BungubbangView {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BungubbangView window = new BungubbangView();
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
	public BungubbangView() {
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

		// 배경 그리기

		try {
			frame.setContentPane(new ImagePanel(ImageIO.read(getClass()
					.getResource("img/background02.jpg"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 255, 250));
		panel.setBounds(36, 80, 233, 60);
		frame.getContentPane().add(panel);

		JLabel label = new JLabel("붕어빵 타이쿤");
		label.setForeground(new Color(244, 164, 96));
		label.setFont(new Font("나눔고딕", Font.BOLD, 33));
		panel.add(label);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(36, 240, 233, 245);
		// 배경이 투명하게
		panel_1.setOpaque(false);
		frame.getContentPane().add(panel_1);

		JButton button = new JButton("게임 시작하기");
		button.setBackground(new Color(255, 255, 153));
		button.setFont(new Font("나눔고딕", Font.BOLD, 20));
		button.setBorderPainted(false);

		// 버튼 내부의 텍스트 border 없음
		button.setFocusPainted(false);

		button.addActionListener(new StartGame());
		panel_1.add(button);

		JLabel maker = new JLabel("만든이 : schema9@gmail.com");
		maker.setForeground(Color.white);
		maker.setFont(new Font("나눔고딕", Font.BOLD, 10));
		panel_1.add(maker);
	}

	private class StartGame implements ActionListener {
		Bungubbang[] molds;
		Queue<Bungubbang> diplayBungubbang;
		int money;
		int heart;
		JTextField num_textField;
		JTextField money_textField;
		People[] waitingList;
		Timer timer;

		StartGame() {
			money = 0;
			heart = 5;
			waitingList = new People[3];
		}

		public void actionPerformed(ActionEvent e) {
			// System.out.println("click");
			molds = new Bungubbang[16];
			diplayBungubbang = new LinkedList<Bungubbang>();

			// 프레임 내부 콘텐츠 전부 삭제
			frame.getContentPane().removeAll();

			JPanel top_info = new JPanel();
			top_info.setBounds(0, 0, 300, 40);
			frame.getContentPane().add(top_info);
			top_info.setLayout(null);
			top_info.setOpaque(false);

			money_textField = new JTextField();
			money_textField.setText(Integer.toString(money));
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
			heart_textField.setText(Integer.toString(heart));
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
			seconed_p.setOpaque(false);

			JLabel client1_label = new JLabel();
			client1_label.setFont(new Font("나눔고딕", Font.BOLD, 12));
			client1_label.setBounds(15, 0, 80, 30);
			seconed_p.add(client1_label);

			JLabel client2_label = new JLabel();
			client2_label.setFont(new Font("나눔고딕", Font.BOLD, 12));
			client2_label.setBounds(113, 0, 80, 30);
			seconed_p.add(client2_label);

			JLabel client3_label = new JLabel();
			client3_label.setFont(new Font("나눔고딕", Font.BOLD, 12));
			client3_label.setBounds(210, 0, 80, 30);
			seconed_p.add(client3_label);
			
			JButton client1 = new JButton();
			client1.setBounds(15, 15, 75, 96);
			client1.addActionListener(new ClickClient(client1, client1_label, 0));

			// 버튼 배경색 투명화
			client1.setOpaque(false);
			client1.setContentAreaFilled(false);
			client1.setBorderPainted(false);

			seconed_p.add(client1);

			JButton client2 = new JButton();
			client2.setBounds(113, 15, 75, 96);
			client2.addActionListener(new ClickClient(client2, client2_label, 1));
			client2.setOpaque(false);
			client2.setContentAreaFilled(false);
			client2.setBorderPainted(false);

			seconed_p.add(client2);

			JButton client3 = new JButton();
			client3.setBounds(210, 15, 75, 96);
			client3.addActionListener(new ClickClient(client3, client3_label, 2));
			client3.setOpaque(false);
			client3.setContentAreaFilled(false);
			client3.setBorderPainted(false);

			seconed_p.add(client3);			

			// 레이어드 시키기
			seconed_p.setComponentZOrder(client1, 2);
			seconed_p.setComponentZOrder(client1_label, 0);

			seconed_p.setComponentZOrder(client2, 2);
			seconed_p.setComponentZOrder(client2_label, 0);

			seconed_p.setComponentZOrder(client3, 2);
			seconed_p.setComponentZOrder(client3_label, 0);

			/* 붕어빵 갯수 */
			JPanel third_p = new JPanel();
			third_p.setBounds(0, 160, 300, 40);
			frame.getContentPane().add(third_p);
			third_p.setLayout(null);
			third_p.setOpaque(false);

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
			last_molds.setBounds(0, 240, 300, 260);
			frame.getContentPane().add(last_molds);
			last_molds.setOpaque(false);
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

			timer = new javax.swing.Timer(1000, new ActionListener() {
				Integer count = 60;

				public void actionPerformed(ActionEvent e) {
					count--;
					time_textField.setText(count.toString());

					// 손님 방문
					// 횟수
					if (count % 8 == 3) {
						for (int i = 0; i < 3; i++) {
							if (waitingList[i] == null) {
								waitingList[i] = new People();

								if (i == 0) {
									comePeople(client1, client1_label);
									break;
								}
								if (i == 1) {
									comePeople(client2, client2_label);
									break;
								}
								if (i == 2) {
									comePeople(client3, client3_label);
									break;
								}
							}
						}
					}

					// 대기 시간에 따른 손님 반응
					for (int i = 0; i < 3; i++) {
						if (waitingList[i] != null) {
							waitingList[i].minusWaitTime();

							if (waitingList[i].getWaitTime() <= 2
									&& waitingList[i].getWaitTime() > 0) { // 짜증나는
																			// 상태
								if (i == 0)
									client1.setIcon(new ImageIcon(this
											.getClass().getResource(
													"img/man_bad.png")));
								if (i == 1)
									client2.setIcon(new ImageIcon(this
											.getClass().getResource(
													"img/man_bad.png")));
								if (i == 2)
									client3.setIcon(new ImageIcon(this
											.getClass().getResource(
													"img/man_bad.png")));
							} else if (waitingList[i].getWaitTime() <= 0) { // 참다가
																			// 가버림
								if (i == 0) {
									client1.setIcon(null);
									client1_label.setText(null);
									client1_label.setOpaque(false);
								}
								if (i == 1) {
									client2.setIcon(null);
									client2_label.setText(null);
									client2_label.setOpaque(false);
								}
								if (i == 2) {
									client3.setIcon(null);
									client3_label.setText(null);
									client3_label.setOpaque(false);
								}

								waitingList[i] = null;

								heart_textField.setText(Integer
										.toString(--heart));
							}
						}

						if (count == 0 || heart <= 0) {
							endGame();
						}

					}
				}
			});
			timer.start();
		}

		public void endGame() {
			timer.stop();

			frame.getContentPane().removeAll();
			frame.repaint();

			JPanel panel = new JPanel();
			panel.setBackground(new Color(245, 255, 250));
			panel.setBounds(36, 80, 240, 120);
			frame.getContentPane().add(panel);

			JLabel lblGameEnd = new JLabel("Game Over");
			lblGameEnd.setFont(new Font("Dialog", Font.BOLD, 32));
			lblGameEnd.setForeground(new Color(220, 20, 60));
			panel.add(lblGameEnd);

			JLabel text = new JLabel("총 " + money + "원\n을 벌었습니다.");
			text.setFont(new Font("Dialog", Font.BOLD, 15));
			text.setBounds(40, 40, 200, 20);
			panel.add(text);

			JPanel panel_1 = new JPanel();
			panel_1.setBounds(36, 240, 233, 245);
			// 배경이 투명하게
			panel_1.setOpaque(false);
			frame.getContentPane().add(panel_1);

			JButton button = new JButton("게임 시작하기");
			button.setBackground(new Color(255, 255, 153));
			button.setFont(new Font("나눔고딕", Font.BOLD, 20));
			button.setBorderPainted(false);

			// 버튼 내부의 텍스트 border 없음
			button.setFocusPainted(false);

			button.addActionListener(new StartGame());
			panel_1.add(button);

		}

		public void comePeople(JButton client, JLabel client_label) {
			client.setIcon(new ImageIcon(this.getClass().getResource(
					"img/man_orgin.png")));
			client_label.setText(waitingList[0].createOrder());
			client_label.setBackground(Color.white);
			client_label.setOpaque(true);

			System.out.println(waitingList[0].getBuyCount());
		}

		private class ClickClient implements ActionListener {
			int no;
			int sum;
			JButton client;
			JLabel client_label;
			int clientTimerCount = 0;
			
			ClickClient() {

			}

			ClickClient(int no) {
				this.no = no;
				sum = 0;
			}

			public ClickClient(JButton client, JLabel client_label, int no) {
				this.no = no;
				this.client = client;
				this.client_label = client_label;
				sum = 0;
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				Bungubbang temp;

				if (waitingList[no] != null) {
					int buyCount = waitingList[no].getBuyCount();

					if ((diplayBungubbang.size() - buyCount) >= 0) {
						for (int i = 0; i < buyCount; i++) {
							temp = diplayBungubbang.poll();
							sum += temp.getSum();

							System.out.println("sum>> " + sum);
						}
						
						// 손님반응을 표현해 주기위한 timer
						Timer clientTimer = new javax.swing.Timer(1000, new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								System.out.println("buyCount>>" + buyCount);
								
								if (clientTimerCount == 0) {
									// 손님의 반응
									if (sum >= (buyCount * 100) - 50 ) {				
										client.setIcon(new ImageIcon(this.getClass()
												.getResource("img/man_happy.jpg")));				// 행복
										client_label.setText("맛있어서 행복해요");
									} else if ( sum <= (buyCount * 100 / 2)) {
										client.setIcon(new ImageIcon(this.getClass()
												.getResource("img/man_bad.jpg")));					// 짜증 
										client_label.setText("맛없어요");
									} else {															// 소소
										client.setIcon(new ImageIcon(this.getClass()
												.getResource("img/man_orgin.jpg")));	
										client_label.setText("그냥 그래요");
									}	
									
									money += sum;
									money_textField.setText(Integer.toString(money));
									num_textField.setText(Integer.toString(diplayBungubbang
											.size()));
									
								} else if (clientTimerCount >= 3) {
									client.setIcon(null);
									client_label.setText(null);
									client_label.setOpaque(false);
									waitingList[no] = null;
									((Timer) e.getSource()).stop();
								}

								clientTimerCount++;
							}
						});
						clientTimer.start();						
					}
				}
			}
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
				if (clickCount == 0) { // 붓기
					molds[no] = new Bungubbang();
					clickCount++;
					count = 0;
					changeIcon();

				} else if (clickCount == 1) { // 앙금 넣기
					timer.stop();
					molds[no].setFront(molds[no].checkState(count--));
					count = 0;
					clickCount++;
					changeIcon();

				} else if (clickCount == 2) { // 뒤집기
					timer.stop();
					molds[no].setBack(molds[no].checkState(count--));
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

	// 배경
	class ImagePanel extends JComponent {
		private Image image;

		public ImagePanel(Image image) {
			this.image = image;
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image, 0, 0, this);
		}
	}

}

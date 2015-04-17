package tycoon03;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Timer;

public class People {
	private int buyCount;
	private Integer waitTime;
	private Timer timer;
	private boolean stayPeople;
	private boolean afterComuting;				// 계산 이후인가

	People() {
		Random random = new Random();

		int max = 3;
		int min = 1;
		buyCount = random.nextInt((max - min) + 1) + min;
		waitTime = 10;
		stayPeople = true;
		afterComuting = false;
	}

	public int getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(int buyCount) {
		this.buyCount = buyCount;
	}	

	public boolean isAfterComuting() {
		return afterComuting;
	}
	
	public boolean isStayPeople() {
		return stayPeople;
	}

	public String createOrder() {
		return Integer.toString(buyCount) + "개 주세요~";
	}

	public void startTimer(JButton client, JLabel client_label) {
		client.setIcon(new ImageIcon(this.getClass().getResource(
				"img/man_orgin.png")));
		client_label.setText(createOrder());
		client_label.setBackground(Color.white);
		client_label.setOpaque(true);

		System.out.println(getBuyCount());

		// 손님이 온 이후로의 시간
		timer = new javax.swing.Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (waitTime <= 2 && waitTime > 0) { // 짜증나는 // 상태
					client.setIcon(new ImageIcon(this.getClass().getResource(
							"img/man_bad.png")));
				} else if (waitTime <= 0) { // 참다가 // 가버림
					client.setIcon(null);
					client_label.setText(null);
					client_label.setOpaque(false);

					((Timer) e.getSource()).stop();
					stayPeople = false;
				}

				waitTime--;
			}
		});
		timer.start();
	}

	public void peopleResponse(JButton client, JLabel client_label, int sum) {
		timer.stop();
		waitTime = 2;

		timer = new javax.swing.Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 손님의 반응
				if (waitTime > 0) {
					if (sum >= (buyCount * 100) - 50) {
						client.setIcon(new ImageIcon(this.getClass()
								.getResource("img/man_happy.png"))); 			// 행복
						client_label.setText("고마워요!!");
					} else if (sum <= (buyCount * 100 / 2)) {
						client.setIcon(new ImageIcon(this.getClass()
								.getResource("img/man_bad.png"))); 			// 짜증
						client_label.setText("맛없어요");
					} else { 														// 소소
						client.setIcon(new ImageIcon(this.getClass()
								.getResource("img/man_orgin.png")));
						client_label.setText("그냥 그래요");
					}
				} else if (waitTime == 0) {
					client.setIcon(null);
					client_label.setText(null);
					client_label.setOpaque(false);
										
					((Timer) e.getSource()).stop();
					stayPeople = false;
					afterComuting = true;
				}
				
				waitTime--;
			}
		});
		timer.start();
	}
}

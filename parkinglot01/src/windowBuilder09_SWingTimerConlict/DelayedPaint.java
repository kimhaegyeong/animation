// 출처
// http://stackoverflow.com/questions/8193627/how-to-pause-java-swing-timer
// delay 있는 스윙 타이머

package windowBuilder09_SWingTimerConlict;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class DelayedPaint {
	 private CenterPanel center;
	    private JFrame frame;
	    private JPanel panel;

	    public static void main(String args[]) {
	        EventQueue.invokeLater(new Runnable() {

	            @Override
	            public void run() {
	                new DelayedPaint().createAndShowGUI();
	            }
	        });
	    }

	    void createAndShowGUI() {
	        frame = new JFrame("Delayed Paint");
	        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	        frame.setSize(400, 400);
	        panel = new JPanel(new BorderLayout());
	        frame.setContentPane(panel);
	        NorthPanel north = new NorthPanel();
	        panel.add(north, BorderLayout.NORTH);
	        center = new CenterPanel();
	        panel.add(center, BorderLayout.CENTER);
	        frame.setVisible(true);
	        north.startTimer();
	    }

	    class NorthPanel extends JPanel {

	        private JLabel lb;

	        public NorthPanel() {
	            lb = new JLabel("Good morning");
	            add(lb);
	        }

	        public void startTimer() {
	            ActionListener taskPerformer = new ActionListener() {

	                @Override
	                public void actionPerformed(ActionEvent evt) {
	                    setLayout(new FlowLayout(FlowLayout.LEFT));
	                    lb.setText("Left");
//	    timer.stop(); // Not needed if setRepeats(false).
	                    center.startTimer();
	                }
	            };
	            javax.swing.Timer timer = new javax.swing.Timer(2000, taskPerformer);
	            timer.setRepeats(false);
	            timer.start();
	        }
	    }

	    class CenterPanel extends JPanel {

	        private int icnt;
	        private Font boldFont = new Font("Dialog", Font.BOLD, 15);
	        private Properties centerProps;
	        private javax.swing.Timer timer;

	        public CenterPanel() {
	            centerProps = new Properties();
	            centerProps.setProperty("circle", "false");
	            centerProps.setProperty("lastString", "0");
	        }

	        @Override
	        public void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            Graphics2D g2 = (Graphics2D) g;
	            if (Boolean.valueOf(centerProps.getProperty("circle"))) {
	                Dimension dim = frame.getSize();
	                g2.draw(new Arc2D.Double(50, 25, dim.height - 100, dim.width - 100, 0, 360, Arc2D.OPEN));
	            }
	            int j = Integer.parseInt(centerProps.getProperty("lastString"));
	            if (j > 0) {
	                g2.setFont(boldFont);
	                for (int i = 1; i <= j; i++) {
	                    g2.drawString("" + (char) (i + 48), i * 10, 50);
	                }
	            }
	        }

	        public void startTimer() {
	            ActionListener taskPerformer = new ActionListener() {

	                @Override
	                public void actionPerformed(ActionEvent evt) {
	                    repaint(); // in paintComponent(...) icnt is already icnt+1.
	                    icnt++;
	                    if (icnt == 1) {
	                        centerProps.setProperty("circle", "true");
	                        timer.setDelay(500);
	                    } else if (icnt <= 10) {
	                        centerProps.setProperty("lastString", String.valueOf(icnt - 1));
	                        if (icnt == 10) {
	                            timer.stop();
	                        }
	                    }
	                }
	            };
	            timer = new javax.swing.Timer(2000, taskPerformer);
	            timer.start();
	        }
	    }
}

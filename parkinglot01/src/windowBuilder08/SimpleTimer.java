// 참조
// http://stackoverflow.com/questions/13503788/confusion-with-the-java-swing-timer
package windowBuilder08;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class SimpleTimer extends JFrame
{
    private JLabel label;
    private Timer timer;
    private int counter = 10; // the duration
    private int delay = 1000; // every 1 second
    private static final long serialVersionUID = 1L;

    public SimpleTimer()
    {
        super("Simple Timer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        label = new JLabel("Wait for " + counter + " sec", JLabel.CENTER);
        JPanel contentPane = (JPanel) getContentPane();
        contentPane.add(label, BorderLayout.CENTER);
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pack();

        ActionListener action = new ActionListener()
        {   
            @Override
            public void actionPerformed(ActionEvent event)
            {
                if(counter == 0)
                {
                    timer.stop();
                    label.setText("The time is up!");
                }
                else
                {
                    label.setText("Wait for " + counter + " sec");
                    counter--;
                }
            }
        };

        timer = new Timer(delay, action);
        timer.setInitialDelay(0);
        timer.start();

        setVisible(true);
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new SimpleTimer();
            }
        });
    }
}

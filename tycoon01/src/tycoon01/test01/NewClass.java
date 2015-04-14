// swing에서 z-index 주기
// 출처 : http://stackoverflow.com/questions/19388628/setting-component-z-order-results-to-illegal-position

// 겹치는 버튼
// 0이 제일 위에 있는 레이어
package tycoon01.test01;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class NewClass extends JFrame {
    public NewClass(){
        super("Test");
        setSize(200, 200);

        JPanel panel = new JPanel();
        setContentPane(panel);

        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");
        JButton button3 = new JButton("Button 3");

        button1.setBounds(10, 10, 100, 40);
        button2.setBounds(5, 5, 100, 30);
        button3.setBounds(15, 15, 150, 40);

        panel.setLayout(null);

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);

        panel.setComponentZOrder(button1, 1);
        panel.setComponentZOrder(button2, 0);
        panel.setComponentZOrder(button3, 2);

        // OR to swap z order of buttons, try below
        // panel.setComponentZOrder(button1, 0);
        // panel.setComponentZOrder(button2, 1);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String [] args){
        new NewClass();
    }
}
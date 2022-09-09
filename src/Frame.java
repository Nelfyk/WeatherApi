import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Frame extends JFrame {
    WeatherApi wa = new WeatherApi();
    JLabel label = new JLabel();
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JPanel panel4 = new JPanel();
    JPanel panel5 = new JPanel();
    TextField tf = new TextField();
    Button btn = new Button();
    ImageIcon icon;

    Frame() {
        label.setFont(new Font("Consolas", Font.BOLD, 24));
        label.setForeground(new Color(112,117,123));
        label.setBounds(50, 0, 300, 200);
        label.setPreferredSize(new Dimension(100,100));
        label.setVisible(true);
        label.setVerticalTextPosition(JLabel.CENTER);

        panel1.setPreferredSize(new Dimension(100,100)); // left
        panel2.setPreferredSize(new Dimension(100,40));  // top
        panel3.setPreferredSize(new Dimension(100,100)); // right
        panel4.setPreferredSize(new Dimension(100,40));  // bottom
        panel5.setPreferredSize(new Dimension(100,100)); // center
        panel1.setBackground(Color.ORANGE);
        panel2.setBackground(Color.BLACK);
        panel3.setBackground(Color.BLUE);
        panel4.setBackground(Color.RED);
        panel5.setBackground(Color.MAGENTA);

        tf.setBounds(50, 0, 150, 50);
        tf.setBackground(new Color(44,51,57));
        label.add(tf);

        btn.setBounds(100, 200, 100, 40);
        btn.setBackground(new Color(44,51,57));
        btn.addActionListener(e -> {

            label.setIcon(null);
            wa.setCITY(tf.getText());
            wa.getWeather();
            if (wa.getError() == 0) {
                label.setText(wa.getName() + " - " + wa.getTemp_c());
                try {
                    icon = new ImageIcon(new URL("https:" + wa.getIcon()));
                    label.setIcon(icon);
                } catch (Exception e3) {
                }
            } else
                label.setText("    INCORRECT CITY!");
            System.out.println(wa.getError());
        });
        this.setLayout(new BorderLayout());

        this.add(panel1,BorderLayout.WEST);
        this.add(panel2,BorderLayout.NORTH);
        this.add(panel3,BorderLayout.EAST);
        this.add(panel4,BorderLayout.SOUTH);
        this.add(panel5,BorderLayout.CENTER);
//        this.add(btn);
//        this.add(label);
//        this.getContentPane().setBackground(new Color(35,40,44));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700, 400);
//        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}

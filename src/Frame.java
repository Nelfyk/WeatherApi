import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Frame extends JFrame {
    WeatherApi wa = new WeatherApi();
    JLabel label = new JLabel();

    TextField tf = new TextField();
    Button btn = new Button();
    ImageIcon icon;

    Frame() {
        label.setFont(new Font("Consolas", Font.BOLD, 24));
        label.setForeground(Color.green);
        label.setBounds(50, 0, 300, 200);
        label.setPreferredSize(new Dimension(100, 100));
        label.setVisible(true);
        label.setVerticalTextPosition(JLabel.CENTER);

        tf.setBounds(65, 0, 150, 50);
        tf.setBackground(new Color(44, 51, 57));

        label.add(tf);

        btn.setBounds(88, 200, 100, 40);
        btn.setBackground(new Color(44, 51, 57));
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

        this.add(btn);
        this.add(label);
        this.getContentPane().setBackground(new Color(35, 40, 44));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 300);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}

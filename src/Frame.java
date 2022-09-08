import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    WeatherApi wa = new WeatherApi();
    JLabel label = new JLabel();
    TextField tf = new TextField();
    Button btn = new Button();

    Frame() {
        label.setFont(new Font("Consolas", Font.BOLD, 24));
        label.setForeground(Color.green);
        label.setBounds(100, 0, 300, 200);
        label.setVisible(true);

        tf.setBounds(0, 0, 150, 50);
        tf.setBackground(Color.black);
        label.add(tf);

        btn.setBounds(100, 200, 100, 40);
        btn.setBackground(Color.black);
        btn.addActionListener(e -> {

            wa.setCITY(tf.getText());
            wa.getWeather();
            if (wa.getError() == 200)
                label.setText(wa.getName() + " - " + wa.getTemp_c());
            else
                label.setText("INCORRECT CITY!");
            System.out.println(wa.getError());
        });

        this.add(btn);
        this.getContentPane().setBackground(Color.black);
        this.add(label);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 300);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setVisible(true);
    }
}

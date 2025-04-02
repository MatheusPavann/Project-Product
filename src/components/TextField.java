package components;

import javax.swing.*;
import java.awt.*;

public class TextField extends JPanel {

    private JLabel jlabel;
    private JTextField jTextField;

    public TextField(String label) {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        jlabel = new JLabel(label);
        jlabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        jlabel.setHorizontalAlignment(SwingConstants.LEFT);

        jTextField = new JTextField(20);
        jTextField.setPreferredSize(new Dimension(250, 30));
        jTextField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1),
                BorderFactory.createEmptyBorder(2, 15, 2, 15)
        ));

        add(jlabel, BorderLayout.NORTH);
        add(jTextField, BorderLayout.CENTER);
    }

    public String getText() {
        return jTextField.getText();
    }
}
package components;

import entities.Product;
import javax.swing.*;
import java.awt.*;

public class MainScreen extends JFrame {
    public MainScreen() {
        this.setLayout(new GridBagLayout());
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Gerenciador de Produtos");
        this.setLocationRelativeTo(null);

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.WHITE);
        panel1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        ScrollPanel scrollPanel = new ScrollPanel();

        scrollPanel.addCard(new Product("Tesla Model S", "Carro elétrico", 8120.00, 12, 123L));
        scrollPanel.addCard(new Product("Tesla Model S", "Carro elétrico", 8120.00, 12, 321L));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.3;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(panel1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.7;
        this.add(scrollPanel, gbc);

        this.setVisible(true);
    }
}
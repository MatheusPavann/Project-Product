package components;

import entities.Product;
import usecaseimpl.GetAllProductsUseCaseImpl;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainScreen extends JFrame {

    public MainScreen() {
        this.setLayout(new GridBagLayout());
        this.setSize(1920, 1080);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Gerenciador de Produtos");
        this.setLocationRelativeTo(null);

        FormPanel panel1 = new FormPanel();
        panel1.setBackground(Color.WHITE);
        panel1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        ScrollPanel scrollPanel = new ScrollPanel();
        populateScrollPanel(scrollPanel);


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

    private void populateScrollPanel(ScrollPanel scrollPanel) {
        GetAllProductsUseCaseImpl getAllProductsUseCase = new GetAllProductsUseCaseImpl();
        List<Product> products = getAllProductsUseCase.execute();
        products.forEach(scrollPanel::addCard);

    }
}
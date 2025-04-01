package components;

import entities.Product;
import usecaseimpl.DeleteProductUseCaseImpl;

import javax.swing.*;
import java.awt.*;

public class Card extends JPanel {
    public Card(Product product) {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        setPreferredSize(new Dimension(Integer.MAX_VALUE, 120));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        Label nameLabel = new Label(product.getName());
        Label descriptionLabel = new Label(product.getDescription());
        Label stockLabel = new Label(product.getStockQuantity().toString());
        Label codeLabel = new Label(product.getCode().toString());
        Label priceLabel = new Label(product.getPrice().toString());

        infoPanel.add(nameLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(descriptionLabel);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(priceLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(stockLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(codeLabel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        buttonPanel.setBackground(Color.WHITE);

        JButton changeButton = new JButton("Editar");
        JButton removeButton = new JButton("Remover");

        styleButton(changeButton, Color.BLUE);
        styleButton(removeButton, Color.RED);

        removeButton.addActionListener(event -> {
            boolean result = onDeleteButtonClick(product.getCode());
            Container parent = getParent();
            parent.remove(this);
            parent.revalidate();
            parent.repaint();
        });


        buttonPanel.add(changeButton);
        buttonPanel.add(removeButton);

        add(infoPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void styleButton(JButton button, Color bgColor) {
        button.setBackground(bgColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(3, 8, 3, 8)
        ));
    }

    private boolean onDeleteButtonClick(Long code) {
        try {
            DeleteProductUseCaseImpl deleteProductUseCase = new DeleteProductUseCaseImpl();
            deleteProductUseCase.execute(code);
            return true;
        }catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao remover produto");
            return false;
        }

    }
}

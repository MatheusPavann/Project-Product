package components;

import entities.Product;
import usecaseimpl.DeleteProductUseCaseImpl;
import usecaseimpl.UpdateProductUseCaseImpl;
import utils.ColorScheme;

import javax.swing.*;
import java.awt.*;

public class Card extends JPanel {


    public Card(Product product) {
        setLayout(new BorderLayout(10, 10));
        setBackground(ColorScheme.CARD_COLOR);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(ColorScheme.CARD_COLOR);
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

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        buttonPanel.setBackground(ColorScheme.CARD_COLOR);

        JButton changeButton = new JButton("Editar");
        JButton removeButton = new JButton("Remover");

        styleButton(changeButton, ColorScheme.EDIT_BUTTON_COLOR);
        styleButton(removeButton, ColorScheme.REMOVE_BUTTON_COLOR);

        removeButton.addActionListener(event -> {
            boolean result = onDeleteButtonClick(product.getCode());
            Container parent = getParent();
            parent.remove(parent);
            parent.revalidate();
            parent.repaint();
        });

        changeButton.addActionListener(event -> {
            JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(this);
            EditCardDialog editCardDialog = new EditCardDialog(
                    parent,
                    product
            );
            editCardDialog.setVisible(true);
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
            ScrollPanel.refresh();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao remover produto");
            return false;
        }
    }
}

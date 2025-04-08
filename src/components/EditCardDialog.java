package components;

import entities.Product;
import usecaseimpl.DeleteProductUseCaseImpl;
import usecaseimpl.UpdateProductUseCaseImpl;
import utils.ColorScheme;
import utils.ProductValidator;

import javax.swing.*;
import java.awt.*;

public class EditCardDialog extends JDialog {
    public EditCardDialog(Component parent, Product product) {
        setSize(400, 400);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField nameField = new JTextField(product.getName());
        JTextField descriptionField = new JTextField(product.getDescription());
        JTextField priceField = new JTextField(String.valueOf(product.getPrice()));
        JTextField quantityField = new JTextField(String.valueOf(product.getStockQuantity()));

        panel.add(new JLabel("Nome:"));
        panel.add(nameField);
        panel.add(Box.createVerticalStrut(10));

        panel.add(new JLabel("Descricao:"));
        panel.add(descriptionField);
        panel.add(Box.createVerticalStrut(10));

        panel.add(new JLabel("Preco:"));
        panel.add(priceField);
        panel.add(Box.createVerticalStrut(10));

        panel.add(new JLabel("Quantidade:"));
        panel.add(quantityField);
        panel.add(Box.createVerticalStrut(10));

        JButton saveButton = new JButton("Salvar");
        saveButton.setBackground(ColorScheme.ADD_BUTTON_COLOR);
        saveButton.setForeground(Color.WHITE);

        JButton deleteButton = new JButton("Deletar");
        deleteButton.setBackground(ColorScheme.REMOVE_BUTTON_COLOR);
        deleteButton.setForeground(Color.WHITE);

        saveButton.addActionListener(e -> {
            Product validateProduct = ProductValidator.validateAndCreateProduct(
                    nameField.getText(),
                    descriptionField.getText(),
                    priceField.getText(),
                    quantityField.getText(),
                    product.getCode().toString(),
                    this,
                    product.getId()
            );
            if(validateProduct != null) {
                UpdateProductUseCaseImpl updateProductUseCase = new UpdateProductUseCaseImpl();
                updateProductUseCase.execute(validateProduct);
                ScrollPanel.refresh();
                dispose();
            }
        });

        deleteButton.addActionListener(event-> {
            DeleteProductUseCaseImpl deleteProductUseCase = new DeleteProductUseCaseImpl();
            deleteProductUseCase.execute(product.getCode());
            ScrollPanel.refresh();
            dispose();
        });

        panel.add(saveButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(deleteButton);
        setContentPane(panel);
    }
}

package components;

import entities.Product;
import usecaseimpl.ProductExistsByCodeUseCaseImpl;
import usecaseimpl.RegisterProductUseCaseImpl;
import utils.ProductValidator;

import javax.swing.*;
import java.awt.*;

public class FormPanel extends JPanel {

    public FormPanel() {

        add(Box.createVerticalStrut(30));
        JLabel titleLabel = new JLabel("Cadastro De Produtos");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        add(titleLabel);


        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(300, 150));

        TextField name = new TextField("Nome: ");
        TextField description = new TextField("Descricao: ");
        TextField price = new TextField("Preco: ");
        TextField quantity = new TextField("Quantidade: ");
        TextField code = new TextField("Codigo: ");

        JButton addButton = new JButton("Adicionar");
        addButton.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        addButton.setFont(new Font("SansSerif", Font.BOLD, 16));

        addButton.addActionListener(event -> {
            Product product = ProductValidator.validateAndCreateProduct(
                    name.getText(),
                    description.getText(),
                    price.getText(),
                    quantity.getText(),
                    code.getText(),
                    this);

            if (product != null) {
                onAddButtonClick(product);
            }
        });

        name.setMaximumSize(new Dimension(550, 50));
        description.setMaximumSize(new Dimension(550, 50));
        price.setMaximumSize(new Dimension(550, 50));
        quantity.setMaximumSize(new Dimension(550, 50));
        code.setMaximumSize(new Dimension(550, 50));

        add(Box.createVerticalStrut(80));
        add(name);
        add(Box.createVerticalStrut(10));
        add(description);
        add(Box.createVerticalStrut(10));
        add(price);
        add(Box.createVerticalStrut(10));
        add(quantity);
        add(Box.createVerticalStrut(10));
        add(code);
        add(Box.createVerticalStrut(10));
        add(addButton);
    }



    private boolean onAddButtonClick(Product product) {
        try {
            RegisterProductUseCaseImpl registerProductUseCase = new RegisterProductUseCaseImpl();
            registerProductUseCase.execute(product);
            JOptionPane.showMessageDialog(this, "Produto Adicionado com sucesso!");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar produto");
            return false;
        }
    }
}
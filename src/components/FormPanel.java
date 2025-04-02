package components;

import entities.Product;
import usecaseimpl.RegisterProductUseCaseImpl;

import javax.swing.*;
import java.awt.*;

public class FormPanel extends JPanel {

    public FormPanel() {

        add(Box.createVerticalStrut(30));
        JLabel jLabel = new JLabel("Cadastro De Produtos");
        jLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        jLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        add(jLabel);


        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Define layout vertical
        setPreferredSize(new Dimension(300, 150)); // Define um tamanho fixo para o painel

        TextField name = new TextField("Nome: ");
        TextField description = new TextField("Descricao: ");
        TextField price = new TextField("Preco: ");
        TextField quantity = new TextField("Quantidade: ");
        TextField code = new TextField("Codigo: ");






        JButton jButton = new JButton("Adicionar");
        jButton.addActionListener(event -> {
            String productName = name.getText();
            String productDescription = description.getText();

            Double productPrice = 0.0;
            try{
                productPrice = Double.parseDouble(price.getText());
            }catch (NumberFormatException e){
                JOptionPane.showMessageDialog(this,"Valor Invalido");
            }
            Integer productQuantity = 0;
            try{
                productQuantity = Integer.parseInt(quantity.getText());
            }catch (NumberFormatException e){
                JOptionPane.showMessageDialog(this,"Valor Invalido");
            }
            Long productCode = 0L;
            try{
                productCode = Long.parseLong(code.getText());
            }catch (NumberFormatException e){
                JOptionPane.showMessageDialog(this,"Valor Invalido");
            }

            Product product = new Product(productName,productDescription,productPrice,productQuantity,productCode);
            onAddButtonClick(product);
        });
        add(jButton);

        name.setMaximumSize(new Dimension(550, 50)); // Define tamanho fixo para o campo de texto
        description.setMaximumSize(new Dimension(550, 50));
        price.setMaximumSize(new Dimension(550, 50)); // Define tamanho fixo para o campo de texto
        quantity.setMaximumSize(new Dimension(550, 50));
        code.setMaximumSize(new Dimension(550, 50));

        add(Box.createVerticalStrut(80));
        add(name);
        add(Box.createVerticalStrut(10)); // Espaço menor entre os campos
        add(description);
        add(Box.createVerticalStrut(10));
        add(price);
        add(Box.createVerticalStrut(10));
        add(quantity);
        add(Box.createVerticalStrut(10));
        add(code);
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

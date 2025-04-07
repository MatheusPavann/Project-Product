package components;

import entities.Product;
import usecaseimpl.FindProductByCodeImpl;
import utils.ColorScheme;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class SearchCardDialog extends JDialog {

    public SearchCardDialog(Component parent) {
        setSize(300, 150);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel label = new JLabel("Digite o código do produto:");
        JTextField codeField = new JTextField();
        JButton nextButton = new JButton("Próximo");
        nextButton.setBackground(ColorScheme.ADD_BUTTON_COLOR);

        nextButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        nextButton.addActionListener(e -> {
            try {
                long code = Long.parseLong(codeField.getText().trim());

                FindProductByCodeImpl finder = new FindProductByCodeImpl();
                Optional<Product> optionalProduct = finder.execute(code);

                if (optionalProduct.isPresent()) {
                    Product found = optionalProduct.get();
                    new EditCardDialog(parent, found).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Produto não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Código inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });


        panel.add(label);
        panel.add(Box.createVerticalStrut(10));
        panel.add(codeField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(nextButton);

        setContentPane(panel);
    }
}

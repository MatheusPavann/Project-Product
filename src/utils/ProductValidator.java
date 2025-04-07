package utils;

import components.FormPanel;
import entities.Product;
import usecaseimpl.FindProductByCodeImpl;
import usecaseimpl.ProductExistsByCodeUseCaseImpl;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductValidator {

    public static Product validateAndCreateProduct(String name, String description, String priceText, String quantityText, String codeText, Component panel, Long currentProductId) {
        List<String> errors = new ArrayList<>();
        Double price = null;
        Integer quantity = null;
        Long code = null;

        if (name.trim().isEmpty()) {
            errors.add("Nome é obrigatório.");
        }

        if (description.trim().isEmpty()) {
            errors.add("Descrição é obrigatória.");
        }

        try {
            price = Double.parseDouble(priceText.replace(",", "."));
            if (price <= 0) {
                errors.add("O preço deve ser maior que zero.");
            }
        } catch (NumberFormatException e) {
            errors.add("O preço precisa ser Número paizão");
        }

        try {
            quantity = Integer.parseInt(quantityText);
            if (quantity < 0) {
                errors.add("A quantidade não pode ser negativa.");
            }
        } catch (NumberFormatException e) {
            errors.add("A quantidade precisa ser Número paizão");
        }

        try {
            code = Long.parseLong(codeText);
            if (code <= 0) {
                errors.add("O código deve ser um número positivo.");
            } else {
                FindProductByCodeImpl findProductByCode = new FindProductByCodeImpl();
                Optional<Product> productOpt = findProductByCode.execute(code);

                if (productOpt.isPresent()) {
                    Product existingProduct = productOpt.get();
                    if (!existingProduct.getId().equals(currentProductId)) {
                        errors.add("O código já está sendo usado por outro produto.");
                    }
                }
            }
        } catch (NumberFormatException e) {
            errors.add("O código não pode ser vazio +e deve ter menos que 19 dígitos.");
        }


        if (!errors.isEmpty()) {
            JOptionPane.showMessageDialog(panel, String.join("\n", errors), "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        return new Product(name, description, price, quantity, code);
    }

}

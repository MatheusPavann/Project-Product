package utils;

import components.FormPanel;
import entities.Product;
import usecaseimpl.ProductExistsByCodeUseCaseImpl;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ProductValidator {

    public static Product validateAndCreateProduct(String name, String description, String priceText, String quantityText, String codeText, FormPanel panel) {
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
            errors.add("Preço inválido.");
        }

        try {
            quantity = Integer.parseInt(quantityText);
            if (quantity < 0) {
                errors.add("A quantidade não pode ser negativa.");
            }
        } catch (NumberFormatException e) {
            errors.add("Quantidade inválida.");
        }

        try {
            code = Long.parseLong(codeText);
            if (code <= 0) {
                errors.add("O código deve ser um número positivo.");
            }
            ProductExistsByCodeUseCaseImpl productExistsByCodeUseCase = new ProductExistsByCodeUseCaseImpl();
            if(productExistsByCodeUseCase.execute(code)) {
                errors.add("O código já existe em outro produto.");
            }
        } catch (NumberFormatException e) {
            errors.add("O codigo deve ter menos que 19 digitos");
        }

        if (!errors.isEmpty()) {
            JOptionPane.showMessageDialog(panel, String.join("\n", errors), "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        return new Product(name, description, price, quantity, code);
    }
}

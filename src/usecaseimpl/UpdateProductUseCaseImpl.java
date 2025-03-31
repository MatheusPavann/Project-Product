package usecaseimpl;

import entities.Product;
import persistence.DatabaseConnection;
import usecases.UpdateProductUseCase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateProductUseCaseImpl implements UpdateProductUseCase {

    private final String UPDATE_PRODUCT_QUERT = """
                UPDATE product p
                SET p.code = ?, p.name = ?, p.price = ?, p.stockQuantity = ?, p.description = ?
                WHERE p.code = ?
                AND (p.code = ? OR NOT EXISTS (
                    SELECT 1 FROM product WHERE code = ?
                ));
            """;


    @Override
    public void execute(Product product) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT_QUERT);

            statement.setLong(1, product.getCode());
            statement.setString(2, product.getName());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getStockQuantity());
            statement.setString(5, product.getDescription());
            statement.setLong(6, product.getCode());
            statement.setLong(7, product.getCode());

            int rowsAffected = statement.executeUpdate();

            if(rowsAffected != 1) {
                System.out.println("Falha ao atualizar os dados do produto");
            }
            System.out.println("Produto atualizado com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

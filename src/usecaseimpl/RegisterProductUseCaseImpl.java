package usecaseimpl;

import entities.Product;
import persistence.DatabaseConnection;
import usecases.RegisterProductUseCase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterProductUseCaseImpl implements RegisterProductUseCase {

    private static final String INSERT_PRODUCT_QUERY = "INSERT INTO product (name, description, price, stock_quantity) VALUES (?, ?, ?, ?)";

    @Override
    public void execute(Product product) {
        try(Connection connection = DatabaseConnection.getConnection()){
            PreparedStatement statement = connection.prepareStatement(INSERT_PRODUCT_QUERY);

            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getStockQuantity());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

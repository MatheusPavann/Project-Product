package usecaseimpl;

import entities.Product;
import persistence.DatabaseConnection;
import usecases.GetAllProductsUseCase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetAllProductsUseCaseImpl implements GetAllProductsUseCase {

    private final String SELECT_ALL_PRODUCTS_QUERY = "SELECT * FROM product";


    @Override
    public List<Product> execute() {
        List<Product> products = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_PRODUCTS_QUERY);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                Double price = resultSet.getDouble("price");
                int stockQuantity = resultSet.getInt("stock_quantity");

                Product product = new Product(id, name, description, price, stockQuantity);
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }
}

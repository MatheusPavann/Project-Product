package usecaseimpl;

import entities.Product;
import persistence.DatabaseConnection;
import usecases.FindProductByCode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class FindProductByCodeImpl implements FindProductByCode {

    private final String FIND_PRODUCT_BY_CODE_QUERY = "SELECT * FROM product WHERE code = ?";

    @Override
    public Optional<Product> execute(Long code) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_PRODUCT_BY_CODE_QUERY);
            statement.setLong(1, code);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Product product = new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("stock_quantity"),
                        resultSet.getLong("code")
                );
                return Optional.of(product);
            } else {
                return Optional.empty();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

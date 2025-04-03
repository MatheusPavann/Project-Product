package usecaseimpl;

import persistence.DatabaseConnection;
import usecases.ProductExistsByCodeUseCase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductExistsByCodeUseCaseImpl implements ProductExistsByCodeUseCase {

    private static final String QUERY_EXISTS_BY_CODE = "SELECT COUNT(*) FROM product WHERE code = ?";

    @Override
    public boolean execute(Long code) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_EXISTS_BY_CODE)) {

            statement.setLong(1, code);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

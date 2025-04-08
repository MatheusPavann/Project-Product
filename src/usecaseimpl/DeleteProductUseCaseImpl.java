package usecaseimpl;


import persistence.DatabaseConnection;
import sockets.PeerClient;
import usecases.DeleteProductUseCase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteProductUseCaseImpl implements DeleteProductUseCase {

    private final String DELETE_PRODUCT_QUERY = "DELETE FROM product WHERE code = ?";

    @Override
    public boolean execute(Long code) {
            try(Connection connection = DatabaseConnection.getConnection()) {
                PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_QUERY);
                statement.setLong(1, code);
                int rowsAffected = statement.executeUpdate();

                if(rowsAffected != 1){
                    PeerClient.send("localhost", 5001);
                    return true;
                }
                return false;

            } catch (SQLException e) {
                System.out.println("Erro ao conectar com o banco de dados");
            }
            return false;
    }
}

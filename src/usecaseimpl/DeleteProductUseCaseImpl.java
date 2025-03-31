package usecaseimpl;


import persistence.DatabaseConnection;
import usecases.DeleteProductUseCase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteProductUseCaseImpl implements DeleteProductUseCase {

    private final String DELETE_PRODUCT_QUERY = "DELETE FROM product WHERE code = ?";

    @Override
    public void execute(Long code) {
            try(Connection connection = DatabaseConnection.getConnection()) {
                PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_QUERY);
                int rowsAffected = statement.executeUpdate();

                if(rowsAffected != 1){
                    System.out.println("Erro ao deletar produto");
                }
                System.out.println("Produto deletado com sucesso");

            } catch (SQLException e) {
                System.out.println("Erro ao conectar com o banco de dados");
            }
    }
}

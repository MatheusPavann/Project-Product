import java.sql.Connection;
import java.sql.SQLException;

import static persistence.DatabaseConnection.getConnection;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("Conexão bem-sucedida com o PostgreSQL no Docker!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
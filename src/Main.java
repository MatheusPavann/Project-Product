import InterfaceMet.ProductManager;
import customExceptions.SelectionException;
import entities.Product;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static persistence.DatabaseConnection.getConnection;

public class Main {
    public static void main(String[] args) {
        List<Product> productList = new ArrayList<>();

        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("Conexão bem-sucedida com o PostgreSQL no Docker!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("\nCadastrar Produto: (1) ");
                System.out.println("Listar Produtos: (2) ");
                System.out.println("Atualizar Produto: (3) ");
                System.out.println("Excluir Produto: (4) ");
                System.out.println("Sair: (5) ");
                System.out.print("Escolha uma opção: ");

                int choice = sc.nextInt();
                sc.nextLine();

                try {
                    switch (choice) {
                        case 1:
                            cadastrarProduto(sc, productList);
                            break;
                        case 2:
                            listarProdutos(productList);
                            break;
                        case 3:
                            atualizarProduto();
                            break;
                        case 4:
                            excluirProduto(sc, productList);
                            break;
                        case 5:
                            System.out.println("Saindo...");
                            sc.close();
                            return;
                        default:
                            throw new SelectionException("Erro: Opção inválida! Escolha entre 1 e 5.\n");
                    }
                } catch (SelectionException e) {
                    System.out.println(e.getMessage());
                }
            } catch (Exception e) {
                System.out.println("Erro: Entrada inválida! Digite apenas números.");
                sc.nextLine(); // Limpa o buffer para evitar loop infinito
            }
        }
    }

    private static void cadastrarProduto(Scanner sc, List<Product> productList) {
        System.out.print("Id: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Nome: ");
        String name = sc.nextLine();

        System.out.print("Descrição: ");
        String description = sc.nextLine();

        System.out.print("Preço do Produto: ");
        double price = sc.nextDouble();

        System.out.print("Quantidade em Estoque: ");
        int stockQuantity = sc.nextInt();

        Product product = new Product(id, name, description, price, stockQuantity);
        productList.add(product);
        System.out.println("Produto cadastrado com sucesso!");
    }

    private static void listarProdutos(List<Product> productList) throws SelectionException {
        if (productList.isEmpty()) {
            throw new SelectionException("Erro: Nenhum produto cadastrado.");
        }
        System.out.println("\nLista de Produtos:");
        for (Product p : productList) {
            System.out.println(p);
        }
    }

    private static void atualizarProduto() throws SelectionException {
        throw new SelectionException("Erro: Funcionalidade ainda não implementada.");
    }

    private static void excluirProduto(Scanner sc, List<Product> productList) {
        System.out.print("Digite o Id do Produto que deseja Excluir: ");
        int idToDelete = sc.nextInt();
        ProductManager.productRemove(productList, idToDelete);
    }
}

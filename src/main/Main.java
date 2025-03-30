package main;

import exceptions.SelectionException;
import entities.Product;
import usecaseimpl.GetAllProductsUseCaseImpl;
import usecaseimpl.RegisterProductUseCaseImpl;
import usecases.GetAllProductsUseCase;
import usecases.RegisterProductUseCase;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        while (true) {
            try {
                System.out.println("\nCadastrar Produto: (1) ");
                System.out.println("Listar Produtos: (2)");
                System.out.println("Sair: (5) ");
                System.out.print("Escolha uma opção: ");

                int choice = sc.nextInt();
                sc.nextLine();

                try {
                    switch (choice) {
                        case 1 -> registerProduct(sc);
                        case 2 -> getAllProducts();
                        case 5 -> {
                            System.out.println("Saindo...");
                            sc.close();
                        }
                        default -> System.out.println("Entrada Invalida, Por Favor Selecione outra opção");

                    }
                } catch (SelectionException e) {
                    System.out.println(e.getMessage());
                }
            } catch (Exception e) {
                System.out.println("Erro: Entrada inválida! Digite apenas números.");
                e.printStackTrace();
                sc.nextLine();
            }
        }
    }

    private static void registerProduct(Scanner sc) {
        System.out.print("Nome: ");
        String name = sc.nextLine();

        System.out.print("Descrição: ");
        String description = sc.nextLine();

        System.out.print("Preço do Produto: ");
        Double price = sc.nextDouble();

        System.out.print("Quantidade em Estoque: ");
        Integer stockQuantity = sc.nextInt();

        RegisterProductUseCase registerProductUseCase = new RegisterProductUseCaseImpl();
        registerProductUseCase.execute(new Product(name,description,price,stockQuantity));

    }

    private static void getAllProducts() {
        GetAllProductsUseCase getAllProductsUseCase = new GetAllProductsUseCaseImpl();
        List<Product> products = getAllProductsUseCase.execute();
        products.forEach(product -> System.out.println(product.toString()));
    }

}

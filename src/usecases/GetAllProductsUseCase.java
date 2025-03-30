package usecases;

import entities.Product;

import java.util.List;

public interface GetAllProductsUseCase {
    List<Product> execute();
}

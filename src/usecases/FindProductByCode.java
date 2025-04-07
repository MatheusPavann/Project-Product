package usecases;

import entities.Product;

import java.util.Optional;

public interface FindProductByCode {
    Optional<Product> execute(Long code);
}

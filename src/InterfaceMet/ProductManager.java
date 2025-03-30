package InterfaceMet;


import entities.Product;
import java.util.List;


public class ProductManager{
    public static void productRemove(List<Product> productList, int idToDelete) {

        for (Product product : productList) {
        if (product.getId() == idToDelete) {
            productList.remove(product);
            System.out.println("Produto com ID " + idToDelete + " foi excluído.");
        }
    }

    }
}

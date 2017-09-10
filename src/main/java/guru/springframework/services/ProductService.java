package guru.springframework.services;

import guru.springframework.domain.Product;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by jt on 1/26/16.
 */
public interface ProductService {

    Set<Product> getProducts();

    Product findById(Integer id);

}

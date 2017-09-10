package guru.springframework.services;

import guru.springframework.domain.Product;
import guru.springframework.repositories.ProductRepository;
import guru.springframework.services.jms.JmsTextMessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by jt on 1/26/16.
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private JmsTextMessageService jmsTextMessageService;

    public ProductServiceImpl(ProductRepository productRepository, JmsTextMessageService jmsTextMessageService) {
        this.productRepository = productRepository;
        this.jmsTextMessageService = jmsTextMessageService;
    }

    @Override
    public Set<Product> getProducts() {

        Set<Product> productSet = new HashSet<>();
        productRepository.findAll().iterator().forEachRemaining(productSet::add);
        return productSet;
    }

    @Override
    public Product findById(Integer id) {

        Optional<Product> productOptional = productRepository.findById(id);

        jmsTextMessageService.sendTextMessage("Fetching Product ID: " + id );
        return productOptional.get();
    }

}

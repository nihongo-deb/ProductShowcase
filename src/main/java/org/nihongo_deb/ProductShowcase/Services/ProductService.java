package org.nihongo_deb.ProductShowcase.Services;

import org.nihongo_deb.ProductShowcase.Entities.Product;
import org.nihongo_deb.ProductShowcase.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @author KAWAIISHY
 * @project ProductShowcase
 * @created 16.08.2023
 */
@Service
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll(){
        return this.productRepository.findAll();
    }

    public List<Product> findByType(String type){
        return this.productRepository.findByType(type);
    }

    public List<Product> findByPriceBetween(Double p1, Double p2){
        return this.productRepository.findByPriceBetween(p1, p2);
    }

    @Transactional
    public void save(Product product){
        this.productRepository.save(product);
    }

    @Transactional
    public void update(UUID uuid, Product updatedProduct){
        updatedProduct.setUuid(uuid);
        this.productRepository.save(updatedProduct);
    }

    @Transactional
    public void delete(Product product){
        this.productRepository.delete(product);
    }
}

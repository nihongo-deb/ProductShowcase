package org.nihongo_deb.ProductShowcase.Services;

import org.nihongo_deb.ProductShowcase.DTO.Product.ProductDTO;
import org.nihongo_deb.ProductShowcase.DTO.Product.ProductFilterDTO;
import org.nihongo_deb.ProductShowcase.Entities.Product;
import org.nihongo_deb.ProductShowcase.Entities.Showcase;
import org.nihongo_deb.ProductShowcase.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public List<Product> findByShowcase(Showcase showcase){
        return this.productRepository.findByOwner(showcase);
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
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        this.productRepository.save(product);
    }

    @Transactional
    public void update(UUID uuid, Product updatedProduct){
        updatedProduct.setUuid(uuid);
        updatedProduct.setCreatedAt(this.productRepository.findById(uuid).get().getCreatedAt()); // TODO изменить логику назначения времени создания
        updatedProduct.setUpdatedAt(LocalDateTime.now());
        this.productRepository.save(updatedProduct);
    }

    @Transactional
    public void delete(UUID uuid){
        this.productRepository.deleteById(uuid);
    }

    public List<Product> findByFilterDTO(Showcase showcase, ProductFilterDTO filterDTO) {
        String type = filterDTO.getType();
        Double priceFrom = filterDTO.getPriceFrom();
        Double priceTo = filterDTO.getPriceTo();

        List<Product> products = findByShowcase(showcase);

        if (type != null)
            products = products
                    .stream()
                    .filter(p -> p.getType().equals(type))
                    .collect(Collectors.toList());
        if (priceFrom != null && priceTo != null)
            products = products
                    .stream()
                    .filter(p -> p.getPrice() >= priceFrom && p.getPrice() <= priceTo)
                    .collect(Collectors.toList());
        return products;
    }
}

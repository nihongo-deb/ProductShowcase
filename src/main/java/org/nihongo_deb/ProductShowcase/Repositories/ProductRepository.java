package org.nihongo_deb.ProductShowcase.Repositories;

import org.nihongo_deb.ProductShowcase.Entities.Product;
import org.nihongo_deb.ProductShowcase.Entities.Showcase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author KAWAIISHY
 * @project ProductShowcase
 * @created 16.08.2023
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findByOwner(Showcase showcase);
    List<Product> findByType(String type);
    List<Product> findByPriceBetween(Double p1, Double p2);

}

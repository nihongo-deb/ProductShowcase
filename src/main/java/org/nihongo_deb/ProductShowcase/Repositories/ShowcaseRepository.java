package org.nihongo_deb.ProductShowcase.Repositories;

import org.nihongo_deb.ProductShowcase.Entities.Showcase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author KAWAIISHY
 * @project ProductShowcase
 * @created 16.08.2023
 */
@Repository
public interface ShowcaseRepository extends JpaRepository<Showcase, UUID> {
    List<Showcase> findByType(String type);
    List<Showcase> findByAddress(String address);
    List<Showcase> findByCreatedAtBetween(LocalDateTime d1, LocalDateTime d2);
    List<Showcase> findByUpdatedAtBetween(LocalDateTime d1, LocalDateTime d2);
}

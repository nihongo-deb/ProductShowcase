package org.nihongo_deb.ProductShowcase.Services;

import org.nihongo_deb.ProductShowcase.Entities.Showcase;
import org.nihongo_deb.ProductShowcase.Repositories.ShowcaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author KAWAIISHY
 * @project ProductShowcase
 * @created 16.08.2023
 */
@Service
@Transactional(readOnly = true)
public class ShowcaseService {
    private final ShowcaseRepository showcaseRepository;

    @Autowired
    public ShowcaseService(ShowcaseRepository showcaseRepository) {
        this.showcaseRepository = showcaseRepository;
    }

    public List<Showcase> findAll() {
        return this.showcaseRepository.findAll();
    }

    public List<Showcase> findByType(String type) {
        return this.showcaseRepository.findByType(type);
    }

    public List<Showcase> findByAddress(String address) {
        return this.showcaseRepository.findByAddress(address);
    }

    public List<Showcase> findByCreatedAtBetween(LocalDateTime d1, LocalDateTime d2) {
        return this.showcaseRepository.findByCreatedAtBetween(d1, d2);
    }

    public List<Showcase> findByUpdatedAtBetween(LocalDateTime d1, LocalDateTime d2) {
        return this.showcaseRepository.findByUpdatedAtBetween(d1, d2);
    }

    @Transactional
    public void save(Showcase showcase){
        this.showcaseRepository.save(showcase);
    }

    @Transactional
    public void update(UUID uuid, Showcase updatedShowcase){
        updatedShowcase.setUuid(uuid);
        this.showcaseRepository.save(updatedShowcase);
    }

    @Transactional
    public void delete(Showcase showcase){
        this.showcaseRepository.delete(showcase);
    }
}

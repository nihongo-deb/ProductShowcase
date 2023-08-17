package org.nihongo_deb.ProductShowcase.Services;

import org.nihongo_deb.ProductShowcase.DTO.Showcase.ShowcaseFilterDTO;
import org.nihongo_deb.ProductShowcase.Entities.Showcase;
import org.nihongo_deb.ProductShowcase.Repositories.ShowcaseRepository;
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
public class ShowcaseService {
    private final ShowcaseRepository showcaseRepository;

    @Autowired
    public ShowcaseService(ShowcaseRepository showcaseRepository) {
        this.showcaseRepository = showcaseRepository;
    }

    public List<Showcase> findAll() {
        return this.showcaseRepository.findAll();
    }

    public Showcase findByUUID(UUID uuid){
        return this.showcaseRepository.findById(uuid).orElse(null);
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

    public List<Showcase> findByFilterDTO(ShowcaseFilterDTO showcaseDTO) {
        List<Showcase> filteredShowcases = findAll();

        String type = showcaseDTO.getType();
        String address = showcaseDTO.getAddress();
        LocalDateTime createdDateFrom = showcaseDTO.getCreatedDateFrom();
        LocalDateTime createdDateTo = showcaseDTO.getCreatedDateTo();
        LocalDateTime updatedDateFrom = showcaseDTO.getUpdatedDateFrom();
        LocalDateTime updatedDateTo = showcaseDTO.getUpdatedDateTo();

        if (type != null)
            filteredShowcases = filteredShowcases
                    .stream()
                    .filter(sh -> sh.getType().equals(type))
                    .collect(Collectors.toList());

        if (address != null)
            filteredShowcases = filteredShowcases
                    .stream()
                    .filter(sh -> sh.getAddress().equals(address))
                    .collect(Collectors.toList());

        if (createdDateFrom != null && createdDateTo != null)
            filteredShowcases = filteredShowcases
                    .stream()
                    .filter(sh -> sh.getCreatedAt().isAfter(createdDateFrom) && sh.getCreatedAt().isBefore(createdDateTo))
                    .collect(Collectors.toList());

        if (updatedDateFrom != null && updatedDateTo != null)
            filteredShowcases = filteredShowcases
                    .stream()
                    .filter(sh -> sh.getUpdatedAt().isAfter(updatedDateFrom) && sh.getUpdatedAt().isBefore(updatedDateTo))
                    .collect(Collectors.toList());

        return filteredShowcases;
    }

    @Transactional
    public void save(Showcase showcase){
        this.showcaseRepository.save(showcase);
    }

    @Transactional
    public void update(UUID uuid, Showcase updatedShowcase){
        updatedShowcase.setUuid(uuid);
        updatedShowcase.setCreatedAt(this.showcaseRepository.findById(uuid).get().getCreatedAt());
        updatedShowcase.setUpdatedAt(LocalDateTime.now());

        this.showcaseRepository.save(updatedShowcase);
    }

    @Transactional
    public void delete(UUID uuid){
        this.showcaseRepository.deleteById(uuid);
    }
}

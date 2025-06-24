package com.example.PlaningPoker.service;

import com.example.PlaningPoker.model.Organization;
import com.example.PlaningPoker.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrganizationService {
    @Autowired
    private OrganizationRepository repository;

    public List<Organization> getAll() {
        return repository.findAll();
    }

    public Organization getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organization not found"));
    }

    public Organization create(Organization org) {
        org.setCreatedAt(LocalDateTime.now());
        org.setModifiedAt(LocalDateTime.now());
        return repository.save(org);
    }

    public Organization update(String id, Organization updatedOrg) {
        Organization org = getById(id);
        org.setOrgName(updatedOrg.getOrgName());
        org.setModifiedAt(LocalDateTime.now());
        return repository.save(org);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }


}
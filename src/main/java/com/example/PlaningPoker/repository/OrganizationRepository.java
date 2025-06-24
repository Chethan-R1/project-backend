package com.example.PlaningPoker.repository;

import com.example.PlaningPoker.model.Organization;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrganizationRepository extends MongoRepository<Organization, String> {
}

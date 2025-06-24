package com.example.PlaningPoker.controller;

import com.example.PlaningPoker.model.Organization;
import com.example.PlaningPoker.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizations")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class OrganizationController {

    private final OrganizationService organizationService;

    @GetMapping
    public List<Organization> getAll() {
        return organizationService.getAll();
    }

    @GetMapping("/{id}")
    public Organization getById(@PathVariable String id) {
        return organizationService.getById(id);
    }

    @PostMapping
    public Organization create(@RequestBody Organization org) {
        return organizationService.create(org);
    }

    @PutMapping("/{id}")
    public Organization update(@PathVariable String id, @RequestBody Organization org) {
        return organizationService.update(id, org);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        organizationService.delete(id);
    }
}

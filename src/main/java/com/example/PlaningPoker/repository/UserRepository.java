package com.example.PlaningPoker.repository;

import com.example.PlaningPoker.model.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByName(String name);

    @Query("SELECT u FROM User u JOIN PlanningTableMember ptm ON ptm.user.userId = u.userId WHERE ptm.planningTable.id = :planTableId")
    List<User> findUsersByPlanningTableId(@Param("planTableId") String planTableId);


}

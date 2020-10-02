package com.example.taxi_app.repositories;

import com.example.taxi_app.models.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    @Transactional
    @Modifying
    @Query("delete from User u where u.id = ?1")
    void deleteById(UUID id);

    User findByEmail(String carNumber);
}

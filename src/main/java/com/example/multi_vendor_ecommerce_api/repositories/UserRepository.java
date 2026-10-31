package com.example.multi_vendor_ecommerce_api.repositories;

import com.example.multi_vendor_ecommerce_api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //this will find by username
    Optional<User> findByUsername(String username);
    Optional<User> findById(Long id);
}

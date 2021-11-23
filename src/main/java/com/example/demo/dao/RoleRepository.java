package com.example.demo.dao;

import java.util.Optional;

import com.example.demo.entities.Role;
import com.example.demo.enumerations.RoleName;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(RoleName roleName);
}
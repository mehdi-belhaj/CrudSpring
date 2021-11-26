package com.example.demo.dao;

import com.example.demo.entities.Candidate;
import com.example.demo.enumerations.ActivityArea;
import com.example.demo.enumerations.Poste;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    public Optional<Candidate> findByEmail(String email);

    public Optional<Candidate> findByUsername(String username);

    public Page<Candidate> findAll(Pageable pageable);

    @Query(" FROM Candidate c WHERE CONCAT(c.firstname,' ',c.lastname,' ',c.username,' ',c.email,' ',c.address) LIKE %?1%")
    Page<Candidate> search(String keyword, Pageable pageable);

    Page<Candidate> findByPoste(Poste poste, Pageable pageable);

    Page<Candidate> findByActivityArea(ActivityArea activityArea, Pageable pageable);


}
// Page<Candidate> findByPosteOrActivityArea(Poste poste, ActivityArea
// activityArea, Pageable pageable);
//Page<Candidate> findByFirstnameContainingIgnoreCaseOrLastnameContainingIgnoreCaseOrUsernameContainingIgnoreCase(
//            String firstName, String LastName, String Username, Pageable pageable);
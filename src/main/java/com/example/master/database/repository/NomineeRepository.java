package com.example.master.database.repository;

import com.example.master.database.bean.Nominee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NomineeRepository extends JpaRepository<Nominee, Integer> {
    List<Nominee> findNomineesByCity(String city);
    List<Nominee> findNomineesByState(String state);
    List<Nominee> findNomineesByParty(String party);
    void deleteNomineeByEmailId(String emailId);
    Nominee findNomineesByEmailId(String emailId);
}

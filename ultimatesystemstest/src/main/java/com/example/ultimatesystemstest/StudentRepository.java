package com.example.ultimatesystemstest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository {
    @Query("SELECT p FROM Student p WHERE " +
            "p.name LIKE CONCAT('%',:query, '%')" )
    List<Student> searchByName(String query);
    @Query("SELECT p FROM Student p WHERE " +
            "p.surname LIKE CONCAT('%',:query, '%')" )
    List<Student> searchBySurname(String query);
    @Query("SELECT u FROM Student  u WHERE u.id = ?1")
    public Student findById(long Id);

}

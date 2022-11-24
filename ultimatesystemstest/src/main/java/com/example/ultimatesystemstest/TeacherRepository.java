package com.example.ultimatesystemstest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository {
    @Query("SELECT p FROM Teacher p WHERE " +
            "p.name LIKE CONCAT('%',:query, '%')" )
    public  List<Teacher> searchByName(String query);
    @Query("SELECT p FROM Teacher p WHERE " +
            "p.surname LIKE CONCAT('%',:query, '%')" )
    public List<Teacher> searchBySurname(String query);
    @Query("SELECT u FROM Teacher  u WHERE u.id = ?1")
    public Teacher findById(long Id);

}

package com.example.ultimatesystemstest;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

@Data
@Table(name="teachers")
@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;
    @Column
    @NotEmpty
    @Size(min = 2, message = "students name should have at least 2 characters")
    private String name;
    @Column
    @NotEmpty
    @Size(min = 2, message = "students surname should have at least 2 characters")
    private String surname;
    @NotEmpty
    @Column
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;
    @NotEmpty
    @Column
    @Min(18)
    private int age;
    @Column
    @NotEmpty
    @Size(min = 2, message = "students field of study should have at least 2 characters")
    private String subject;
    @ManyToMany
    private Set<Student> students;
}

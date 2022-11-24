package com.example.ultimatesystemstest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
public class TeacherService {
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    StudentRepository studentRepository;
    public List<Teacher> getAllTeachersSortedAndPaged(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Teacher> pagedResult = teacherRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Teacher>();
        }
    }
    public List<Teacher> getAllTeachersPaged(Integer pageNo, Integer pageSize)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<Teacher> pagedResult = teacherRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Teacher>();
        }
    }
    public List<Teacher> getAllTeachersSorted(String sortBy)
    {
        Sort sortOrder = Sort.by(sortBy);

        List<Teacher> sortedResult = teacherRepository.findAll(sortOrder);

        if(sortedResult.isEmpty()) {
            return new ArrayList<Teacher>();
        } else {
            return sortedResult;
        }
    }
    public List<Teacher>searchTeacherByName(String query){
        List<Teacher> teachers =teacherRepository.searchByName(query);
        return teachers;
    }
    public List<Teacher>searchTeacherBySurname(String query){
        List<Teacher> teachers =teacherRepository.searchBySurname(query);
        return teachers;
    }
    public Teacher createTeacher(Teacher teacher){
        teacherRepository.save(teacher);
        return teacher;
    }
    @Transactional
    public void deleteTeacher(long teacherId){
        Teacher teacher= teacherRepository.findById(teacherId);
        teacher.getStudents().forEach(student -> student.getTeachers().remove(teacher));
        studentRepository.saveAll(teacher.getStudents());
        teacherRepository.delete(teacher);

    }
    @Transactional
    public void removeAllStudentsFromTeacher(long teacherId){
        Teacher teacher= teacherRepository.findById(teacherId);
        teacher.getStudents().forEach(student -> student.getTeachers().remove(teacher));
        studentRepository.saveAll(teacher.getStudents());
        teacherRepository.save(teacher);
    }
    @Transactional
    public void removeStudentFromTeacher(long teacherId,long studentId){
        Teacher teacher = teacherRepository.findById(teacherId);
        teacher.getStudents().remove(studentRepository.findById(studentId));
        teacherRepository.save(teacher);
    }
    @Transactional
    public void addStudentToTeacher(long teacherId,long studentId){
        Teacher teacher = teacherRepository.findById(teacherId);
        teacher.getStudents().add(studentRepository.findById(studentId));
        teacherRepository.save(teacher);
    }
    public Teacher modifyTeacher(Teacher teacher, String name, String surname, int age, String email, String subject){
        teacher.setAge(age);
        teacher.setName(name);
        teacher.setSurname(surname);
        teacher.setSubject(subject);
        teacher.setEmail(email);
        return teacher;
    }
}

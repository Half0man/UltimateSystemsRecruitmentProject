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
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    TeacherRepository teacherRepository;
    public List<Student> getAllStudentsSortedAndPaged(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Student> pagedResult = studentRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Student>();
        }
    }
    public List<Student> getAllStudentsPaged(Integer pageNo, Integer pageSize)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<Student> pagedResult = studentRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Student>();
        }
    }
    public List<Student> getAllStudentsSorted(String sortBy)
    {
        Sort sortOrder = Sort.by(sortBy);

        List<Student> sortedResult = studentRepository.findAll(sortOrder);

        if(sortedResult.isEmpty()) {
            return new ArrayList<Student>();
        } else {
            return sortedResult;
        }
    }
    public List<Student>searchStudentByName(String query){
        List<Student> students =studentRepository.searchByName(query);
        return students;
    }
    public List<Student>searchTeacherBySurname(String query){
        List<Student> students =studentRepository.searchBySurname(query);
        return students;
    }
    @Transactional
    public void deleteStudent(long studentId){
        Student student= studentRepository.findById(studentId);
        student.getTeachers().forEach(teacher -> teacher.getStudents().remove(student));
        teacherRepository.saveAll(student.getTeachers());
        studentRepository.delete(student);
    }
    public void removeStudentFromAllTeachers(long studentId){
        Student student= studentRepository.findById(studentId);
        student.getTeachers().forEach(teacher -> teacher.getStudents().remove(student));
        teacherRepository.saveAll(student.getTeachers());
        studentRepository.save(student);
    }
    public void removeStudentFromTeacher(long studentId,long teacherId){
        Student student = studentRepository.findById(studentId);
        student.getTeachers().remove(teacherRepository.findById(teacherId));
        teacherRepository.save(student);
    }
    public void crateStudent(Student student){
        studentRepository.save(student);
    }
    public void modifyStudent(Student student,String name,String surname,int age,String email,String fieldOfStudy){
        student.setAge(age);
        student.setName(name);
        student.setSurname(surname);
        student.setFieldOfStudy(fieldOfStudy);
        student.setEmail(email);
    }
    }

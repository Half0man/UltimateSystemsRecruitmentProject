package com.example.ultimatesystemstest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    private StudentService studentService;
    @GetMapping("/searchByName")
    public ResponseEntity<List<Student>> searchByName(@RequestParam("query")String query){
        return ResponseEntity.ok(studentService.searchStudentByName(query));
    }
    @GetMapping("/searchBySurname")
    public ResponseEntity<List<Student>> searchBySurname(@RequestParam("query")String query){
        return ResponseEntity.ok(studentService.searchStudentBySurname(query));
    }
    @GetMapping("/createStudent")
    public Student createStudent(@Valid @RequestBody Student student){
        return studentService.createStudent(student);
    }
    @GetMapping("/modifyStudent")
    public Student modifyStudent(@Valid Student student,String name,String surname,int age,String email,String fieldOfStudy ){
        return studentService.modifyStudent(student,name,surname,age,email, fieldOfStudy);
    }
    @GetMapping("/getAllStudentsPagedAndSorted")
    public ResponseEntity<List<Student>>getAllStudentsPagedAndSorted(@RequestParam(defaultValue = "0") Integer pageNo,
                                                                     @RequestParam(defaultValue = "10") Integer pageSize,
                                                                     @RequestParam(defaultValue = "id") String sortBy){
        List<Student> list = studentService.getAllStudentsSortedAndPaged(pageNo,pageSize,sortBy);
        return new ResponseEntity<>(list,new HttpHeaders(), HttpStatus.OK);
    }
    @GetMapping("/getAllStudentsPaged")
    public ResponseEntity<List<Student>>getAllStudentsPaged(@RequestParam(defaultValue = "0") Integer pageNo,
                                                            @RequestParam(defaultValue = "10") Integer pageSize){
        List<Student> list = studentService.getAllStudentsPaged(pageNo,pageSize);
        return new ResponseEntity<>(list,new HttpHeaders(), HttpStatus.OK);
    }
    @GetMapping("/getAllStudentsSorted")
    public ResponseEntity<List<Student>>getAllStudentsSorted(@RequestParam(defaultValue = "id") String sortBy){
        List<Student> list = studentService.getAllStudentsSorted(sortBy);
        return new ResponseEntity<>(list,new HttpHeaders(), HttpStatus.OK);
    }
    @GetMapping("/deleteStudent")
    public void deleteStudent(@RequestParam("id") long id){
        studentService.deleteStudent(id);
    }
    @GetMapping("/removeAllStudentsFromTeacher")
    public void removeAllTeachersFromStudent(@RequestParam("id") long id){
        studentService.removeStudentFromAllTeachers(id);
    }
    @GetMapping("/removeStudentFromTeacher")
    public void removeStudentFromTeacher(@RequestParam("studentId") long studentId,@RequestParam("teacherId") long teacherId){
        studentService.removeStudentFromTeacher(studentId,teacherId);
    }
}

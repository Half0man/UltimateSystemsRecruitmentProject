package com.example.ultimatesystemstest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/teacher")
@AllArgsConstructor
public class TeacherController {
    private TeacherService teacherService;
    @GetMapping("/searchByName")
    public ResponseEntity<List<Teacher>> searchByName(@RequestParam("query")String query){
        return ResponseEntity.ok(teacherService.searchTeacherByName(query));
    }
    @GetMapping("/searchBySurname")
    public ResponseEntity<List<Teacher>> searchBySurname(@RequestParam("query")String query){
        return ResponseEntity.ok(teacherService.searchTeacherBySurname(query));
    }
    @GetMapping("/createTeacher")
    public Teacher createTeacher(@Valid @RequestBody Teacher teacher){
        return teacherService.createTeacher(teacher);
    }
    @GetMapping("/modifyTeacher")
    public Teacher modifyTeacher(@Valid Teacher teacher,String name,String surname,int age,String email,String subject ){
        return teacherService.modifyTeacher(teacher,name,surname,age,email,subject);
    }
    @GetMapping("/getAllTeachersPagedAndSorted")
    public ResponseEntity<List<Teacher>>getAllTeachersPagedAndSorted(@RequestParam(defaultValue = "0") Integer pageNo,
                                                                     @RequestParam(defaultValue = "10") Integer pageSize,
                                                                     @RequestParam(defaultValue = "id") String sortBy){
        List<Teacher> list = teacherService.getAllTeachersSortedAndPaged(pageNo,pageSize,sortBy);
        return new ResponseEntity<>(list,new HttpHeaders(), HttpStatus.OK);
    }
    @GetMapping("/getAllTeachersPaged")
    public ResponseEntity<List<Teacher>>getAllTeachersPaged(@RequestParam(defaultValue = "0") Integer pageNo,
                                                                     @RequestParam(defaultValue = "10") Integer pageSize){
        List<Teacher> list = teacherService.getAllTeachersPaged(pageNo,pageSize);
        return new ResponseEntity<>(list,new HttpHeaders(), HttpStatus.OK);
    }
    @GetMapping("/getAllTeachersSorted")
    public ResponseEntity<List<Teacher>>getAllTeachersSorted(@RequestParam(defaultValue = "id") String sortBy){
        List<Teacher> list = teacherService.getAllTeachersSorted(sortBy);
        return new ResponseEntity<>(list,new HttpHeaders(), HttpStatus.OK);
    }
    @GetMapping("/deleteTeacher")
    public void deleteTeacher(@RequestParam("id") long id){
        teacherService.deleteTeacher(id);
    }
    @GetMapping("/removeAllStudentsFromTeacher")
    public void removeAllStudentsFromTeacher(@RequestParam("id") long id){
        teacherService.removeAllStudentsFromTeacher(id);
    }
    @GetMapping("/removeStudentFromTeacher")
    public void removeStudentFromTeacher(@RequestParam("teacherId") long teacherId,@RequestParam("studentId") long studentId){
        teacherService.removeStudentFromTeacher(teacherId,studentId);
    }

}

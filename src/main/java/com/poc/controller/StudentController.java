package com.poc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc.models.Student;
import com.poc.otherclasses.BaseResponse;
import com.poc.otherclasses.POCException;
import com.poc.security.AdminPermission;
import com.poc.security.CombinePermission;
import com.poc.service.StudentService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Students", description = "Student management APIs")
@RestController
@RequestMapping("/api/students")
public class StudentController {
	
	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

 
    @CombinePermission
    @PostMapping("/secure/add")
    public BaseResponse<String> addStudent(@RequestBody Student student) {
        try {
			return studentService.addStudent(student);
		} catch (POCException e) {
			logger.error("Exception occurred in StudentController addStudent", e);
		}
        return null;
    }

    @CombinePermission
    @PutMapping("/secure/update/{id}")
    public BaseResponse<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
         try {
			return studentService.updateStudent(id, student);
		} catch (POCException e) {
			logger.error("Exception occurred in StudentController updateStudent", e);
		}
         return null;
    }

    @AdminPermission
    @DeleteMapping("/secure/delete/{id}")
    public BaseResponse<String> deleteStudent(@PathVariable Long id) {
       try {
		return studentService.deleteStudent(id);
	} catch (POCException e) {
		logger.error("Exception occurred in StudentController deleteStudent", e);
	}
       return null;
    }

   
    @GetMapping("/all")
    public BaseResponse<List<Student>>  getAllStudents() {
        try {
			return studentService.getAllStudents();
		} catch (POCException e) {
			logger.error("Exception occurred in StudentController getAllStudents", e);
		}
        return null;
    }
}

package com.poc;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.poc.models.Student;
import com.poc.otherclasses.BaseResponse;
import com.poc.otherclasses.POCException;
import com.poc.service.StudentService;

@SpringBootTest
public class StudentServiceTests {

	@Autowired
	private StudentService studentService;

	@Test
	public void testAddStudent() {
		Student student = new Student();
		student.setName("John Doe");
		BaseResponse<String> savedStudent;
		try {
			savedStudent = studentService.addStudent(student);
			assertNotNull(savedStudent.getData());
		} catch (POCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
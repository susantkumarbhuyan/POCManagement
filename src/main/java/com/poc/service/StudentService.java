package com.poc.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.constants.ResponseConstants;
import com.poc.models.Student;
import com.poc.otherclasses.BaseResponse;
import com.poc.otherclasses.POCException;
import com.poc.repository.StudentRepository;

@Service
public class StudentService {

	private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

	@Autowired
	private StudentRepository studentRepository;

	public BaseResponse<String> addStudent(Student student) throws POCException {
		BaseResponse<String> response = new BaseResponse<>();
		try {
			if(student.getName().isBlank()  || student.getName().isEmpty()) {
				response.setStatusCode(ResponseConstants.NOT_FOUND);
				response.setStatusMessage(ResponseConstants.NO_DATAFOUND);
				response.setData("Name is Empty");
				return response;
			}
			response.setStatusCode(ResponseConstants.SUCCESS_CREATED);
			response.setStatusMessage(ResponseConstants.SUCESS_MESSAGE);
			studentRepository.save(student);
		} catch (Exception e) {
			logger.error("Exception occurred in StudentService addStudent", e);
		}
		return response;
	}

	public BaseResponse<Student> updateStudent(Long id, Student updatedStudent) throws POCException {
		BaseResponse<Student> response = new BaseResponse<>();
		try {
			Optional<Student> existingStudent = studentRepository.findById(id);
			if (existingStudent.isPresent()) {
				Student student = existingStudent.get();
				
				
				student.setName(updatedStudent.getName());
				student.setAddress(updatedStudent.getAddress());
				student.setAge(updatedStudent.getAge());
				student.setDocuments(updatedStudent.getDocuments());
				
				var user = studentRepository.save(student);
				response.setData(user);
				response.setStatusCode(ResponseConstants.SUCCESS_CREATED);
				response.setStatusMessage(ResponseConstants.SUCESS_MESSAGE);
			}
		} catch (Exception e) {
			logger.error("Exception occurred in StudentService updateStudent", e);
		}
		return response;
	}

	public BaseResponse<String> deleteStudent(Long id) throws POCException{
		BaseResponse<String> response = new BaseResponse<>();
		try {
			studentRepository.deleteById(id);
			response.setStatusCode(ResponseConstants.ACCEPT_DELETE);
			response.setStatusMessage(ResponseConstants.SUCESS_DELETE_MESSAGE);
		} catch (Exception e) {
			logger.error("Exception occurred in StudentService deleteStudent", e);
		}
		return response;
	}

	public BaseResponse<List<Student>> getAllStudents() throws POCException{
		BaseResponse<List<Student>> response = new BaseResponse<>();
		try {

			var studentList = studentRepository.findAll();
			if (!studentList.isEmpty()) {
				response.setData(studentList);
				response.setStatusCode(ResponseConstants.SUCCESS_CREATED);
				response.setStatusMessage(ResponseConstants.SUCESS_MESSAGE);
			} else {
				response.setStatusCode(ResponseConstants.NOT_FOUND);
				response.setStatusMessage(ResponseConstants.NO_DATAFOUND);
			}
			
			logger.debug("StudatedList", studentList.toString());

		} catch (Exception e) {
			logger.error("Exception occurred in StudentService getAllStudents", e);
		}
		return response;
	}
}

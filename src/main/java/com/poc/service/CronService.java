package com.poc.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.poc.models.Student;
import com.poc.otherclasses.BaseResponse;
import com.poc.otherclasses.POCException;

@Service
public class CronService {

	private static final Logger logger = LoggerFactory.getLogger(CronService.class);

	@Autowired
	private StudentService studentService;

//   @Scheduled(cron = "0 0/30 * * * ?")
	@Scheduled(cron = "*/30 * * * * *")
	public void updateEmployeeRecords() {
    	BaseResponse<List<Student>> students;
		try {
			students = studentService.getAllStudents();
			if(students.getData() != null && !students.getData().isEmpty()) {
				logger.info("User Info "+ students.getData().size());
			}else {
				logger.info("User Info", "empty");
			}
			
		} catch (POCException e) {
			logger.error("Error Occured in Cron Jobs",e );
		}
    }
}

package com.example.Student.Controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Student.Entity.Student;
import com.example.Student.Service.StudentService;
import com.example.Student.dto.StudentDTO;
import com.example.Student.dto.StudentDetailDTO;
import com.example.Student.response.responsegenerator;

import jakarta.validation.Valid;

 

@RestController
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	

	@GetMapping("/getAll")
	public ResponseEntity<?> getAllStudent() {
	    List<Student> response = studentService.fetchStudentList();
	    if(response!=null) {
	    return responsegenerator.successResponse("Data fetched successfully..!", response);
	}	
		 return responsegenerator.errorResponse("Data can't be fetched!...");

	}
	 
	 @GetMapping("/getById/{id}")
		public ResponseEntity<?> getStudentId(@PathVariable int id){
		 
		 Optional<Student> response =  studentService.getId(id);
		 if(response.isPresent()) {
		    return responsegenerator.successResponse("Data fetched successfully..!", response);}
		 else {
		 return responsegenerator.errorResponse("Student not found!...");}
				 }



	 @PostMapping("/create")
	 public ResponseEntity<?> addstudent(@Valid @RequestBody Student student) {
		 try {
		 Student response = studentService.addstudent(student);
		 if(response!=null) {
			    return responsegenerator.successResponse("Data added sucessfully!...", response);
			}else
		    return responsegenerator.errorResponse("data not added");
		 }catch(Exception e) {
			 return responsegenerator.errorResponse(e.getMessage());

		 }
		 }
	 
	 @PutMapping("/update")
	 public ResponseEntity<?> updateStudent(@Valid @RequestBody Student student) {
		 try {
		Student response =  studentService.updateStudent(student);
		if(response!=null) {
		    return responsegenerator.successResponse("Data updated successfully..!", response);

		}else
	    return responsegenerator.errorResponse("Student not found");
		}catch(Exception e) {
			return responsegenerator.errorResponse(e.getMessage());
		}
	 }

	 @DeleteMapping("/delete/{id}")
	    public ResponseEntity<?> deleteStudent(@PathVariable int id) {
		 try {
		 boolean response =studentService.deleteStudentById(id);
		 if(response!=false) {
			 return responsegenerator.successResponse("Data deleted successfully..!",response);	      
			 } else
		 return responsegenerator.errorResponse("data not in the table");
		 }catch(Exception e) {
			 return responsegenerator.errorResponse(e.getMessage());
		 }
	 } 
	 
	 @GetMapping("/studentdetails/{id}")
	 public ResponseEntity<?> getStudentDetailsById(@PathVariable int id){
		 try {
			 StudentDetailDTO response=studentService.getStudentDetails(id);
			 return responsegenerator.successResponse("Data fethed successfully!..", response);
		 }catch(Exception e) {
			 return responsegenerator.errorResponse(e.getMessage());
		 }
	 }
	 
	 @GetMapping("/studentquery/{id}")
		public ResponseEntity<?> getFullStudentDTO(@PathVariable int id) {
			try {
				List<StudentDTO> response = studentService.getFullStudentDTO(id);
				return responsegenerator.successResponse("Data fethed successfully!..", response);
			} catch (Exception e) {
				return responsegenerator.errorResponse(e.getMessage());
	 
				
}

}
}
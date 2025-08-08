package com.example.Student.Controller;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

 

@RestController
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	public StudentController(StudentService studentService) {
        this.studentService = studentService;
	}

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
			@GetMapping("/export")
			public void exportToExcel(HttpServletResponse response) throws IOException {
			    response.setContentType("application/octet-stream");

			    String headerKey = "Content-Disposition";
			    String headerValue = "attachment; filename=students.xlsx";
			    response.setHeader(headerKey, headerValue);

			    List<Student> studentList = studentService.getAllStudents();

			    XSSFWorkbook workbook = new XSSFWorkbook();
			    XSSFSheet sheet = workbook.createSheet("Students");

			    XSSFRow header = sheet.createRow(0);
			    header.createCell(0).setCellValue("Student ID");
			    header.createCell(1).setCellValue("DOB");
			    header.createCell(2).setCellValue("Course");
			    header.createCell(3).setCellValue("Gender");
			    header.createCell(4).setCellValue("Phone");

			    int rowCount = 1;
			    for (Student student : studentList) {
			        XSSFRow row = sheet.createRow(rowCount++);
			        row.createCell(0).setCellValue(student.getStudentId());
			        row.createCell(1).setCellValue(student.getDob());
			        row.createCell(2).setCellValue(student.getCourse());
			        row.createCell(3).setCellValue(student.getGender());
			        row.createCell(4).setCellValue(student.getPhNo());
			    }

			    workbook.write(response.getOutputStream());
			    workbook.close();
			

}
			
			@GetMapping("/pdf")
		    public List<Student> getAllStudents1() {
		        return studentService.getAllStudentsAndGeneratePdf();
		    }
		}



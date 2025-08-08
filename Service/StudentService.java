package com.example.Student.Service;

import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Student.Entity.Student;
import com.example.Student.Repository.Addressrepository;
import com.example.Student.Repository.Qualificationrepository;
import com.example.Student.Repository.Studentrepository;
import com.example.Student.dto.StudentDTO;
import com.example.Student.dto.StudentDetailDTO;
import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
@Service
public class StudentService {

	@Autowired
	private Studentrepository studentrepository;

	@Autowired
	public Addressrepository addressrepository;

	@Autowired
	public Qualificationrepository qualificationrepository;
	
	public Student saveDepartment(Student student) {
		return studentrepository.save(student);
	}

	public List<Student> fetchStudentList() {
		return studentrepository.findAll();
	}

	public Optional<Student> getId(int id) {
		return studentrepository.findById(id);
	}

	public Student addstudent(Student stud) {
		return studentrepository.save(stud);
	}

	public Student updateStudent(Student data) {
		Optional<Student> existingStudent = studentrepository.findById(data.getStudentId());
		if (existingStudent.isPresent()) {
			existingStudent.get().setCourse(data.getCourse());
			existingStudent.get().setDob(data.getDob());
			existingStudent.get().setPhNo(data.getPhNo());
			existingStudent.get().setGender(data.getGender());
			
			
			return studentrepository.saveAndFlush(existingStudent.get());
		}
		return null;

	}

	public boolean deleteStudentById(int id) {
		if (id < 0) {
			throw new RuntimeException("Invalid student ID: must be positive.");
		}
		if (studentrepository.existsById(id)) {
			studentrepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	public StudentDetailDTO getStudentDetails(int id) {
		Student student = studentrepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
		System.out.println("Student fetched = " + student);

		StudentDetailDTO dto = new StudentDetailDTO();
		dto.setStudentId(student.getStudentId());
		dto.setCourse(student.getCourse());
		dto.setDob(student.getDob());
		dto.setPhno(student.getPhNo());
		dto.setGender(student.getGender()); 
		dto.setAddress(addressrepository.findByStudentId(dto.getStudentId()));
		dto.setQualification(qualificationrepository.findByStudentId(dto.getStudentId()));
		return dto;
	}

	

	public List<StudentDTO> getFullStudentDTO(int id) {
		return studentrepository.getFullStudentDTO(id);
	}

	public List<Student> getAllStudents() {
        return studentrepository.findAll();
    }
	public List<Student> getAllStudentsAndGeneratePdf() {
        List<Student> students = studentrepository.findAll();

        if (!students.isEmpty()) {
            generatePdf(students, "C:\\fileupload\\StudentsData.pdf");
        }

        return students; 
    }
   private void generatePdf(List<Student> students, String filePath) {
        try {
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            Field[] fields = Student.class.getDeclaredFields();
            PdfPTable table = new PdfPTable(fields.length);

            for (Field field : fields) {
                field.setAccessible(true);
                table.addCell(new PdfPCell());
            }

            for (Student student : students) {
                for (Field field : fields) {
                    field.setAccessible(true);
                    Object value = field.get(student);
                    table.addCell(value != null ? value.toString() : "");
                }
            }

            document.add(table);
            document.close();
            System.out.println("PDF generated at: " + filePath);

        } catch (Exception e) {
            throw new RuntimeException("PDF generation failed", e);
        }
    }
}
	
	
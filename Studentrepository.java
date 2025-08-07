package com.example.Student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Studentrepository extends JpaRepository<Student,Integer>{

	@Query("SELECT s.studentId as studentId, s.dob as dob, s.course as course, s.gender as gender, s.phNo as phNo, " +
		       "a.addId as addId, a.doorNo as doorNo, a.streetName as streetName, a.pinCode as pinCode, a.state as state, " +
		       "q.qualId as qualId, q.universityName as universityName, q.yop as yop, q.cgpa as cgpa " +
		       "FROM Student s " +
		       "JOIN Address a ON a.studentId = s.studentId " +
		       "JOIN Qualification q ON q.studentId = s.studentId " +
		       "WHERE s.studentId = :id")
		List<StudentDTO> getFullStudentDTO(@Param("id") int id);
}
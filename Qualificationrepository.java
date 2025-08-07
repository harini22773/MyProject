package com.example.Student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public   interface Qualificationrepository extends JpaRepository<Qualification,Integer>{
	List<Qualification> findByStudentId(int studentId);
}


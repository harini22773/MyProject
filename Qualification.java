 package com.example.Student;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Qualification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qualId")
     private int qualId;


    
    @Column(name = "yop")
    private String yop;

    @Column(name = "university_name")
    private String universityName;
    
    @Column(name = "cgpa")
    private String cgpa;
    
   
  
    private int studentId;


	public int getStudent() {
		return studentId;
	}

	public void setStudent(int student) {
		this.studentId = student;
	}

	public int getQualId() {
		return qualId;
	}

	public void setQualId(int qualId) {
		this.qualId = qualId;
	}

	

	public String getYop() {
		return yop;
	}

	public void setYop(String yop) {
		this.yop = yop;
	}

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	public String getCgpa() {
		return cgpa;
	}

	public void setCgpa(String cgpa) {
		this.cgpa = cgpa;
	}
}


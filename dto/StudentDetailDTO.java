package com.example.Student.dto;

import java.util.List;

import com.example.Student.Entity.Address;
import com.example.Student.Entity.Qualification;

import lombok.Data;

@Data
public class StudentDetailDTO {
	private int studentId;
	private String Course;
	private String Dob;
	private String phno;
	private String gender;
	private List<Address> address;
	private List<Qualification> qualification;
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getCourse() {
		return Course;
	}
	public void setCourse(String course) {
		Course = course;
	}
	public String getDob() {
		return Dob;
	}
	public void setDob(String dob) {
		Dob = dob;
	}
	public String getPhno() {
		return phno;
	}
	public void setPhno(String phno) {
		this.phno = phno;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public List<Address> getAddress() {
		return address;
	}
	public void setAddress(List<Address> address) {
		this.address = address;
	}
	public List<Qualification> getQualification() {
		return qualification;
	}
		public void setQualification(List<Qualification> qualification) {
			this. qualification =  qualification;
	}

	
}
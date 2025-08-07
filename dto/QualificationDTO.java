package com.example.Student.dto;

public class QualificationDTO {
    private int qualId;
    private String yop;
    private String university_name;
    private String  cgpa;
    public QualificationDTO() {}
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
	public String getUniversity_name() {
		return university_name;
	}
	public void setUniversity_name(String university_name) {
		this.university_name = university_name;
	}
	public String getCgpa() {
		return cgpa;
	}
	public void setCgpa(String cgpa) {
		this.cgpa = cgpa;
	}
   

    // Getters and Setters
}

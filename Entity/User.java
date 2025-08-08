package com.example.Student.Entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "User")
public class User {
	@Column(name = "user_Name")
	private String userName;
	@Id
	@Column(name = "User_id")
	private String userId;
	
	@Column(name = "student_id")
	private int studentId;
	

	@Column(name = "user_password")
	private String userPassword;
	
	
	@Column(name = "profile_image_uuid")
	private String profileImageUuid;
	@Column(name = "profile_image_path")
	private String profileImagePath;

	public String getProfileImagePath() {
		return profileImagePath;
	}

	public void setProfileImagePath(String profileImagePath) {
		this.profileImagePath = profileImagePath;
	}

	public String getProfileImageUuid() {
	    return profileImageUuid;
	}

	public void setProfileImageUuid(String profileImageUuid) {
	    this.profileImageUuid = profileImageUuid;
	}
	



	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public int getStudentId() {
		return studentId;
	}


	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}


	public String getUserPassword() {
		return userPassword;
	}


	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
}
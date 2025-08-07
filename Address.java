package com.example.Student;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "add_id")
    private int addId;

    @NotNull(message="doorno cannot be null")
    @Column(name = "door_no")
    private String doorNo;

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "pincode")
    private String pinCode;

    @Column(name = "state")
    private String state;

   
    private int studentId;
    
    public int getStudent() {
		return studentId;
	}

	public void setStudent(int student) {
		this.studentId = student;
	}

    

    public int getAddId() {
        return addId;
    }

    public void setAddId(int addId) {
        this.addId = addId;
    }

    public String getDoorNo() {
        return doorNo;
    }

    public void setDoorNo(String doorNo) {
        this.doorNo = doorNo;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getPincode() {
        return pinCode;
    }

    public void setPincode(String pincode) {
        this.pinCode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

   
}

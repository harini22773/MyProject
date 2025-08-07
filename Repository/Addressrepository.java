package com.example.Student.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Student.Entity.Address;

@Repository
public interface Addressrepository extends JpaRepository<Address,Integer>{
	List<Address> findByStudentId(int studentId);
}

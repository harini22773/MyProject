package com.example.Student.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Student.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
	List<User> findByStudentId(int studentId);
Optional<User> findByUserName(String userName);
User findByUserNameAndUserPassword(String userName, String password);


}

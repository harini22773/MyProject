package com.example.Student;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
	List<User> findByStudentId(int studentId);
Optional<User> findByUserName(String userName);
User findByUserNameAndUserPassword(String userName, String password);


}

package com.example.Student.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Student.Entity.User;
import com.example.Student.Repository.UserRepository;
import com.example.Student.dto.UserDTO;


@Service
public class UserService implements UserDetailsService{

	@Autowired
    private UserRepository repo;
	

	
	public List<User> getAllUsers() {
        return repo.findAll();
    }
	
	public Optional<User> getUserById(int id){
		return repo.findById(id);
	}
	
	public User addUser(User user) {
	    return repo.save(user);
	}
	
	public User updateUser(User user) {
		      	    
		    return repo.saveAndFlush(user);
		   	}
	
	 public String deleteUser(int id) {
	        if (repo.existsById(id)) {
	            repo.deleteById(id);
	            return "User with ID " + id + " deleted successfully.";
	        } else {
	            return "User with ID " + id + " not found.";
	        }
	    }
	

	 public String userIdAndPassword(UserDTO user) {
		    Optional<User> existingUser = repo.findByUserName(user.getUserName());

		    if (existingUser.isPresent()) {
		        if (existingUser.get().getUserPassword().equals(user.getUserPassword())) {
		            return "Login Success";
		        } else {
		            return "Incorrect Password";
		        }
		    } else {
		        return "User not found. Please Sign-Up";
		    }
		}

		
	 public UserDTO loginRequest(String userName, String userPassword) {
	        Optional<User> userOpt = repo.findByUserName(userName);

	        if (userOpt.isPresent()) {
	            User user = userOpt.get();

	            
	            if (user.getUserPassword().equals(userPassword)) {
	               
	                UserDTO dto = new UserDTO();
	                dto.setUserName(user.getUserName());
	                dto.setUserPassword(user.getUserPassword());
	                return dto;
	            }
	        }
	        return null;
	    }
	 
	 @Override
	 public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			Optional<User> userOptional = repo.findByUserName(username);
			if (!userOptional.isPresent()) {
				throw new UsernameNotFoundException("Invalid username or password.");
			}
			User user = userOptional.get();
			return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getUserPassword(),
					getAuthority());
		}
	 private List<SimpleGrantedAuthority> getAuthority() {
			return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}

	 public User getUserDetails(String userName, String password) {
			return repo.findByUserNameAndUserPassword(userName,password);
		}

	 }
			


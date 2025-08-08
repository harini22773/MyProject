package com.example.Student.Service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

	 public User saveUser(User user) {
	        return repo.save(user);
	    }
	  // Upload image and store UUID in user
	    public String uploadProfileImage(String userId, MultipartFile file) throws IOException {
	        Optional<User> userOpt = repo.findByUserId(userId);
	        if (!userOpt.isPresent()) {
	            throw new RuntimeException("User not found");
	        }

	        User user = userOpt.get();

	        // Generate UUID
	        String uuid = UUID.randomUUID().toString();

	        // Save image in local folder (you can change this path)
	        String uploadDir = "C:\\fileupload/uploads/";
	        File dir = new File(uploadDir);
	        if (!dir.exists()) {
	            dir.mkdirs();
	        }
	        String filePath = uploadDir + uuid + "_" + file.getOriginalFilename();
	        file.transferTo(new File(filePath));

	        // Save UUID in database
	        user.setProfileImageUuid(uuid);
	        user.setProfileImagePath(filePath);
	        repo.save(user);
	        

	        return uuid;
	    }

	    // Get user by profile image UUID
	    public Optional<User> getUserByProfileUuid(String profileUuid) {
	        return repo.findByProfileImageUuid(profileUuid);
	    }
	}

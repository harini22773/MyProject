
package com.example.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.Student.Util.JWTUtil;
import com.example.Student.response.responsegenerator;



@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
    private UserService service;
	@Autowired
	private JWTUtil jwtUtil;
 
 @GetMapping("/getAllUser")
    public ResponseEntity<?> getAllUser() {
	 List<User> user = service.getAllUsers();
	 if (user != null && !user.isEmpty()) {
         return responsegenerator.successResponse("User fetched successfully", user);
     } else {
         return responsegenerator.errorResponse("No user found");
     }
 }
 
 @GetMapping("/{id}")
 public ResponseEntity<?> getUserById(@PathVariable int id){
	 Optional<User> user = service.getUserById(id);
	 if (user.isPresent()) {
         return responsegenerator.successResponse("User found", user.get());
     } else {
         return responsegenerator.errorResponse("User not found with ID: " + id);
     }

 }
 
 @PostMapping("/create")
 public ResponseEntity<?> addUser(@RequestBody User user) {
     try {
         User createdUser = service.addUser(user);

         if (createdUser != null) {
             return responsegenerator.successResponse("User added successfully", createdUser);
         } else {
             return responsegenerator.errorResponse("Failed to add user");
         }
     } catch (Exception e) {
         return responsegenerator.errorResponse("Unexpected error occurred: " + e.getMessage());
     }
 }

 
 @PutMapping("/update")
 	public ResponseEntity<?> updateUser(@RequestBody User request) {
	 		User updatedUser = service.updateUser(request);
	 		 if (updatedUser != null) {
	                return responsegenerator.successResponse("User updated successfully", updatedUser);
	            } else {
	                return responsegenerator.errorResponse("User not found");
	            }
	 	 }
 
 @DeleteMapping("/delete/{id}")
 	public ResponseEntity<?> deleteUser(@PathVariable int id) {
	           String result = service.deleteUser(id);

	            if (result.toLowerCase().contains("not found")) {
	                return responsegenerator.errorResponse(result);
	            }

	            return responsegenerator.successResponse(result, null);
 }
// @PostMapping("/login")
//	            public ResponseEntity<String> login(@RequestBody User user) {
//	                String response = service.login(user);
//	                if (response.startsWith("Login successful")) {
//	                    return ResponseEntity.ok(response);
//	                } else {
//	                    return ResponseEntity.status(401).body(response);
// }
	                
 @PostMapping("/loginDTO")
 
public ResponseEntity<?> loginDetails(@RequestBody UserDTO request) {
	  String response = service.userIdAndPassword(request);
	  if (response.equals("User not found. Please Sign-Up")) {
		  return responsegenerator.successResponse(response, request);
	  } else {
	      return responsegenerator.errorResponse(response);
	 }
	  
}
 @PostMapping("/login")
 public ResponseEntity<?> login(@RequestBody UserDTO loginRequest) {
         UserDTO user = service.loginRequest(loginRequest.getUserName(), loginRequest.getUserPassword());

         if (user != null) {
			 String token = jwtUtil.generateToken(user.getUserName());

            return responsegenerator.successResponse("Login Success", token);
         } else {
             return responsegenerator.errorResponse("Invalid username or password");
         }

 }
  
 
 @PostMapping("/validate")
	public ResponseEntity<?> loginDetails(@RequestBody User request){
		try {
			User response=service.getUserDetails(request.getUserName(),request.getUserPassword());
			if(response!=null) {
				String token=jwtUtil.generateToken(request.getUserName());
				Map<String,Object> result=new HashMap<String, Object>();
				result.put("token", token);
				result.put("user",response);
			return responsegenerator.successResponse("login successfull", result);
			}else {
				return responsegenerator.errorResponse("login failed");
			}
		}catch(Exception e)
		{
			
			return responsegenerator.errorResponse(e.getMessage());
		}
 }   
 }

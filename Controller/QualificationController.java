package com.example.Student.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Student.Entity.Qualification;
import com.example.Student.Service.QualificationService;
import com.example.Student.response.responsegenerator;

@RestController
@RequestMapping("/qualification")
public class QualificationController {

    @Autowired
    private QualificationService qualificationService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllQualifications() {
        try {
            List<Qualification> list = qualificationService.getAllQualification();
            if (!list.isEmpty()) {
                return responsegenerator.successResponse("Qualifications fetched successfully", list);
            } else {
                return responsegenerator.errorResponse("No qualifications found");
            }
        } catch (Exception e) {
            return responsegenerator.errorResponse("Error fetching qualifications: " + e.getMessage());
        }
    } 
      
    @GetMapping("/{id}")
    public ResponseEntity<?> getQualificationById(@PathVariable int id) {
        try {
            Optional<Qualification> qualification = qualificationService.getQualificationById(id);
            return qualification.isPresent() ?
                responsegenerator.successResponse("Qualification fetched successfully", qualification) :
                responsegenerator.errorResponse("Qualification not found with ID: " + id);
        } catch (Exception e) {
            return responsegenerator.errorResponse("Error fetching qualification: " + e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addQualification(@RequestBody Qualification qualification) {
        try {
            Qualification saved = qualificationService.addQualification(qualification);
            return responsegenerator.successResponse("Qualification added successfully", saved);
        } catch (Exception e) {
            return responsegenerator.errorResponse("Error adding qualification: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateQualification(@RequestBody Qualification qualification) {
        try {
            Qualification updated = qualificationService.updateQualification(qualification);
            if (updated != null) {
                return responsegenerator.successResponse("Qualification updated successfully", updated);
            } else {
                return responsegenerator.errorResponse("Qualification not found");
            }
        } catch (Exception e) {
            return responsegenerator.errorResponse("Error updating qualification: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQualification(@PathVariable int id) {
        try {
            String result = qualificationService.deleteQualification(id);
            return responsegenerator.successResponse(result, null);
        } catch (Exception e) {
            return responsegenerator.errorResponse("Error deleting qualification: " + e.getMessage());
        }
    }
}

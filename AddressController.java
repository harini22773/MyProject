package com.example.Student;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Student.response.responsegenerator;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllAddresses() {
        try {
            List<Address> list = addressService.getAllAddress();
            if (!list.isEmpty()) {
                return responsegenerator.successResponse("Addresses fetched successfully", list);
            } else {
                return responsegenerator.errorResponse("No address records found");
            }
        } catch (Exception e) {
            return responsegenerator.errorResponse("Error fetching addresses: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAddressById(@PathVariable int id) {
        try {
            Optional<Address> address = addressService.getAddressById(id);
            return address.isPresent() ?
                responsegenerator.successResponse("Address fetched successfully", address) :
                responsegenerator.errorResponse("Address not found with ID: " + id);
        } catch (Exception e) {
            return responsegenerator.errorResponse("Error fetching address: " + e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addAddress(@RequestBody Address address) {
        try {
            Address saved = addressService.addAddress(address);
            return responsegenerator.successResponse("Address added successfully", saved);
        } catch (Exception e) {
            return responsegenerator.errorResponse("Error adding address: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateAddress(@RequestBody Address address) {
        try {
            Address updated = addressService.updateAddress(address);
            if (updated != null) {
                return responsegenerator.successResponse("Address updated successfully", updated);
            } else {
                return responsegenerator.errorResponse("Address not found");
            }
        } catch (Exception e) {
            return responsegenerator.errorResponse("Error updating address: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable int id) {
        try {
            String result = addressService.deleteAddress(id);
            return responsegenerator.successResponse(result, null);
        } catch (Exception e) {
            return responsegenerator.errorResponse("Error deleting address: " + e.getMessage());
        }
    }
}

package com.example.Student;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private Addressrepository repo;

    public List<Address> getAllAddress() {
        return repo.findAll();
    }

    public Address addAddress(Address address) {
        return repo.save(address);
    }
    
    public Optional<Address> getAddressById(Integer id) {
        return repo.findById(id);
    }

    public String deleteAddress(int id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return "Address with ID " + id + " deleted successfully.";
        } else {
            return "Address with ID " + id + " not found.";
        }
    }

    public Address updateAddress(Address updatedAddress) {
        Optional<Address> existingAddress = repo.findById(updatedAddress.getAddId());

        if (existingAddress.isPresent()) {
            Address address = existingAddress.get();
            address.setDoorNo(updatedAddress.getDoorNo());
            address.setStreetName(updatedAddress.getStreetName());
            address.setPincode(updatedAddress.getPincode());
            address.setState(updatedAddress.getState());
            return repo.saveAndFlush(address);
        }
        return null;
    }
}

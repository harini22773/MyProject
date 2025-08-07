package com.example.Student.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Student.Entity.Qualification;
import com.example.Student.Repository.Qualificationrepository;

@Service
public class QualificationService {

    @Autowired
    private Qualificationrepository repo;

    public List<Qualification> getAllQualification() {
        return repo.findAll();
    }

    public Qualification addQualification(Qualification qualification) {
        return repo.save(qualification);
    }

    public Optional<Qualification> getQualificationById(int id) {
        return repo.findById(id);
    }

    public String deleteQualification(int id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return "Qualification with ID " + id + " deleted successfully.";
        } else {
            return "Qualification with ID " + id + " not found.";
        }
    }

    public Qualification updateQualification(Qualification updatedQualification) {
        Optional<Qualification> existing = repo.findById(updatedQualification.getQualId());

        if (existing.isPresent()) {
            Qualification qual = existing.get();
            qual.setYop(updatedQualification.getYop());
            qual.setUniversityName(updatedQualification.getUniversityName());
            qual.setCgpa(updatedQualification.getCgpa());
            return repo.saveAndFlush(qual);
        }
        return null;
    }
}

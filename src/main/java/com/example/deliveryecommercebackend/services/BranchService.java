package com.example.deliveryecommercebackend.services;


import com.example.deliveryecommercebackend.DTO.BranchDTO;
import com.example.deliveryecommercebackend.model.Branch;
import com.example.deliveryecommercebackend.repository.BranchRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepo;

    public HttpStatus createBranch(BranchDTO branchDTO) {

        var checkBranch = branchRepo.findById(branchDTO.getBranch_id()).get();

        if(checkBranch != null)
            return HttpStatus.BAD_REQUEST;

        Branch branch = new Branch();
        branch.setName(branchDTO.getName());
        branch.setAddress(branchDTO.getAddress());
        branch.setDes(branchDTO.getDes());
        var checkCreate = branchRepo.save(branch);

        if(checkCreate != null) {
            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }

    public List<Branch> getBranch() {
        List<Branch> listBranch = branchRepo.findAll();
        return listBranch;
    }

}

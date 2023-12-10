package com.example.deliveryecommercebackend.services;

import com.example.deliveryecommercebackend.DTO.CustomerBankDTO;
import com.example.deliveryecommercebackend.model.Bank;
import com.example.deliveryecommercebackend.model.CustomerBank;
import com.example.deliveryecommercebackend.model.user.User;
import com.example.deliveryecommercebackend.repository.BankRepository;
import com.example.deliveryecommercebackend.repository.CustomerBankRepository;
import com.example.deliveryecommercebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerBankService {
    @Autowired
    private CustomerBankRepository customerBankRepository;
    @Autowired
    private BankRepository bankRepository;
    @Autowired
    private UserRepository userRepository;
    public ResponseEntity<?> getBankByUserId(String userId) {
        try {
            List<CustomerBank> list = customerBankRepository.findBankListByUserId(userId);

            return ResponseEntity.ok().body(list);
        }catch (Exception ex) {
            System.out.println("Error from service - Error: " + ex.getMessage());
            return ResponseEntity.badRequest().body("Error: " + ex.getMessage());
        }
    }
    public ResponseEntity<?> addBankToCustomer(CustomerBankDTO customerBank) {
        try {
            //find delete bank account of customer by bank_name and user_id
            CustomerBank checkExists = customerBankRepository.findAccountDeleted(customerBank.getUser_id(),
                    customerBank.getBank_name(),
                    customerBank.getBank_number());
            if(checkExists != null){
                checkExists.set_deleted(false);
                return ResponseEntity.ok().body("Add bank to customer successfully");
            }

            Bank bank =  bankRepository.findBankByName(customerBank.getBank_name());
            if(bank == null) {
                return ResponseEntity.badRequest().body("Bank not found");
            }

            User user = userRepository.findUserById(customerBank.getUser_id());
            if(user == null) {
                return ResponseEntity.badRequest().body("User not found");
            }

            CustomerBank newCustomerBank = new CustomerBank(customerBank);

            var check = customerBankRepository.save(newCustomerBank);

            if(check.getId() == null) {
                return ResponseEntity.badRequest().body("Add bank to customer failed");
            }
            return ResponseEntity.ok().body("Add bank to customer successfully");
        }catch (Exception ex) {
            System.out.println("Error from customer bank service - Error: " + ex.getMessage());
            return ResponseEntity.badRequest().body("Error");
        }
    }
    public ResponseEntity<?> deleteBankAccount(CustomerBankDTO customerBank) {
        try {
            CustomerBank bank =  customerBankRepository.findAccountDeleted(customerBank.getUser_id(),
                    customerBank.getBank_name(),
                    customerBank.getBank_number());
            if(bank == null) {
                return ResponseEntity.badRequest().body("Bank account not found");
            }

            bank.set_deleted(true);
            var check = customerBankRepository.save(bank);
            if(check == null) {
                return ResponseEntity.ok().body("Delete bank to customer failed");
            }
            return ResponseEntity.ok().body("Delete bank to customer successfully");
        }catch (Exception ex) {
            System.out.println("Error from customer bank service - Error: " + ex.getMessage());
            return ResponseEntity.badRequest().body("Error");
        }
    }

}

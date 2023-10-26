package com.example.deliveryecommercebackend;

import com.example.deliveryecommercebackend.model.Bank;
import com.example.deliveryecommercebackend.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication

public class DeliveryEcommerceBackendApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DeliveryEcommerceBackendApplication.class, args);
    }

    @Autowired
    private BankRepository bankRepository;

    @Override
    public void run(String... args) throws Exception {
        Bank newBank = new Bank();
        newBank.setCode("bank1");
        newBank.setName("EXIMBANK");
        newBank.setState(0);
        bankRepository.save(newBank);

        Bank newBank1 = new Bank();
        newBank1.setCode("bank2");
        newBank1.setName("TPBANK");
        newBank1.setState(0);
        bankRepository.save(newBank1);


        Bank newBank2 = new Bank();
        newBank2.setCode("bank3");
        newBank2.setName("TECHCOMBANK");
        newBank2.setState(0);
        bankRepository.save(newBank2);


    }
}

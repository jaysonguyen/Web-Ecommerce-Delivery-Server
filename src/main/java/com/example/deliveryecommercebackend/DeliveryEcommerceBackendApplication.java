package com.example.deliveryecommercebackend;

import com.example.deliveryecommercebackend.repository.ActionRepository;
import com.example.deliveryecommercebackend.repository.BankRepository;
import com.example.deliveryecommercebackend.repository.ProductTypeRepository;
import com.example.deliveryecommercebackend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class DeliveryEcommerceBackendApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DeliveryEcommerceBackendApplication.class, args);
    }

    @Autowired
    private BankRepository bankRepository;
    @Autowired
    private ActionRepository actionRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Override
    public void run(String... args) throws Exception {
//            Bank newBank = new Bank();
//            newBank.setBank_id("bank1");
//            newBank.setName("EXIMBANK");
//            newBank.setState(false);
//            bankRepository.save(newBank);
//
//            Bank newBank1 = new Bank();
//            newBank1.setBank_id("bank2");
//            newBank1.setName("TPBANK");
//            newBank1.setState(false);
//            bankRepository.save(newBank1);
//
//            Bank newBank2 = new Bank();
//            newBank2.setBank_id("bank3");
//            newBank2.setName("TECHCOMBANK");
//            newBank2.setState(false);
//            bankRepository.save(newBank2);
//
//
//            Role roleAdmin = new Role();
//            roleAdmin.setRoleId(1);
//            roleAdmin.setDes("admin");
//            roleAdmin.setName("admin");
//            roleRepository.save(roleAdmin);
//
//            Role roleStaff = new Role();
//            roleStaff.setRoleId(3);
//            roleStaff.setDes("staff");
//            roleStaff.setName("staff");
//            roleRepository.save(roleStaff);
//
//
//            Role roleCustomer = new Role();
//            roleCustomer.setRoleId(2);
//            roleCustomer.setDes("customer");
//            roleCustomer.setName("customer");
//            roleRepository.save(roleCustomer);
//
//            Role roleShipper = new Role();
//            roleShipper.setRoleId(4);
//            roleShipper.setDes("shipper");
//            roleShipper.setName("shipper");
//            roleRepository.save(roleShipper);
//
//
//            Action action = new Action();
//            action.setAction_id(0);
//            action.setCode("0");
//            action.setName("New");
//            action.setDes("Waiting for sending");
//            action.setType("admin");
//            action.set_deleted(false);
//            action.setCreated(Date.valueOf(LocalDate.now()));
//
//            actionRepository.save(action);
//
//            Action action1 = new Action();
//            action1.setAction_id(1);
//            action1.setCode("1");
//            action1.setName("Waiting");
//            action1.setDes("Waiting for accept");
//            action1.setType("admin");
//            action1.set_deleted(false);
//            action1.setCreated(Date.valueOf(LocalDate.now()));
//
//            actionRepository.save(action1);
//
//            Action action2 = new Action();
//            action2.setAction_id(2);
//            action2.setCode("2");
//            action2.setName("Accept");
//            action2.setDes("Waiting for delivering");
//            action2.setType("admin");
//            action2.set_deleted(false);
//            action2.setCreated(Date.valueOf(LocalDate.now()));
//
//            actionRepository.save(action2);
//
//            Action action3 = new Action();
//            action3.setAction_id(3);
//            action3.setCode("3");
//            action3.setName("Delivering");
//            action3.setDes("On delivering");
//            action3.setType("admin");
//            action3.set_deleted(false);
//            action3.setCreated(Date.valueOf(LocalDate.now()));
//
//            actionRepository.save(action3);
//
//            Action action4 = new Action();
//            action4.setAction_id(4);
//            action4.setCode("4");
//            action4.setName("Waiting for return");
//            action4.setDes("Waiting for returning");
//            action4.setType("admin");
//            action4.set_deleted(false);
//            action4.setCreated(Date.valueOf(LocalDate.now()));
//
//            actionRepository.save(action4);
//
//            Action action5 = new Action();
//            action5.setAction_id(5);
//            action5.setCode("5");
//            action5.setName("Returning");
//            action5.setDes("On returning");
//            action5.setType("admin");
//            action5.set_deleted(false);
//            action5.setCreated(Date.valueOf(LocalDate.now()));
//
//            actionRepository.save(action5);
//
//            Action action6 = new Action();
//            action6.setAction_id(6);
//            action6.setCode("6");
//            action6.setName("Returning");
//            action6.setDes("On returning");
//            action6.setType("admin");
//            action6.set_deleted(false);
//            action6.setCreated(Date.valueOf(LocalDate.now()));
//
//            actionRepository.save(action6);
//
//            Action action7 = new Action();
//            action7.setAction_id(7);
//            action7.setCode("7");
//            action7.setName("Finished");
//            action7.setDes("Finished");
//            action7.setType("admin");
//            action7.set_deleted(false);
//            action7.setCreated(Date.valueOf(LocalDate.now()));
//
//            actionRepository.save(action7);
//
//
//            ProductType productType = new ProductType();
//            productType.setCode("type1");
//            productType.setName("type number one");
//            productType.setDes("type1 description");
//
//            productTypeRepository.save(productType);

    }
}

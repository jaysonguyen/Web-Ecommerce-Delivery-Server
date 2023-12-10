package com.example.deliveryecommercebackend.services;

import com.example.deliveryecommercebackend.DTO.*;
import com.example.deliveryecommercebackend.factory.user.*;
import com.example.deliveryecommercebackend.model.*;
import com.example.deliveryecommercebackend.model.user.Admin;
import com.example.deliveryecommercebackend.model.user.Store;
import com.example.deliveryecommercebackend.model.user.User;
import com.example.deliveryecommercebackend.repository.*;
import jakarta.transaction.Transactional;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
//@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    @Autowired
    private StoreRepository storeRepository;

    private BranchRepository branchRepo;

    @Autowired
    private AreaRepository areaRepo;

    @Autowired
    private ShippingAssignmentRepository shipRepo;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, BranchRepository branchRepo, ShippingAssignmentRepository shipRepo, AreaRepository areaRepo) {
        this.userRepository = userRepository;
        this.branchRepo = branchRepo;
        this.shipRepo = shipRepo;
        this.areaRepo = areaRepo;
        this.roleRepository = roleRepository;
    }

    public List<getUserListDTO> getAllUsers() {
        try {
            var staffList = userRepository.findNoneDeleteUser();
            List<getUserListDTO> res = new ArrayList<getUserListDTO>();
            for(User user : staffList){
                getUserListDTO temp = new getUserListDTO();
                temp.setData(user);
                res.add(temp);
            }

            return res;
        } catch(Exception ex) {
            System.out.printf("Get user failed - Error: " + ex);
            return Collections.emptyList();
        }
    }

    public ResponseEntity<?> getUserByCode(String code) {
        try {
            User user = userRepository.findUserByCode(code);
            return ResponseEntity.ok().body(new UserDTO(user));
        } catch(Exception ex) {
            System.out.printf("Get user failed - Error: " + ex);
            return ResponseEntity.badRequest().body("Error from services: " + ex.getMessage());
        }
    }

    public ResponseEntity<?> getUserByID(String id) {
        try {
            User user = userRepository.findUserById(id);
            return ResponseEntity.ok().body(new UserDTO(user));
        } catch(Exception ex) {
            System.out.printf("Get user failed - Error: " + ex.getMessage());
            return ResponseEntity.badRequest().body("Error from services");
        }
    }


    public ResponseEntity<?> getStoreByUser(String userId) {
        try {
            //find user
            User user = userRepository.findUserById(userId);
            if(user.getUser_id() == null) {
                return ResponseEntity.badRequest().body("User not found");
            }

            var storeList = storeRepository.findStoreByUser(user);
            List<StoreDisplayDTO> storeDisplayDTOS = new ArrayList<>();
            for(var item : storeList){
                StoreDisplayDTO temp = new StoreDisplayDTO(item);
                storeDisplayDTOS.add(temp);
            }

            return ResponseEntity.ok().body(storeDisplayDTOS);
        } catch (Exception ex) {
            System.out.printf("Error from services: " + ex.getMessage());
            return ResponseEntity.status(500).body("Error from server");
        }

    }

    public ResponseEntity<?> create_customer(UserCreateDTO userDTO){
        UserFactory userFactory = new CustomerFactory();
        User newUser = userFactory.createUser(userDTO);
        //create user first
        var check = userRepository.save(newUser);
        if(check.getUser_id() == null) {
            return ResponseEntity.badRequest().body("Create user failed");
        }

        //create store default
        if (newUser.getStores() != null && !newUser.getStores().isEmpty()) {
            Store defaultStore = newUser.getStores().get(0);
            var check_2 = storeRepository.save(defaultStore);
            if(check_2.getStore_id() == null) {
                return ResponseEntity.badRequest().body("Cannot create store by default");
            }
            return ResponseEntity.ok().body("Created successfully");
        } else {
            return ResponseEntity.badRequest().body("Cannot create default store");
        }
    }
    public ResponseEntity<?> create_staff(UserCreateDTO userDTO){
        //find branch
        Branch branch = branchRepo.findBranchByCode(userDTO.getBranch_code());
        if(branch == null) {
            return ResponseEntity.badRequest().body("Branch not found");
        }
        //attach branch to userDTO
        userDTO.setBranch(branch);
        UserFactory userFactory = new StaffFactory();
        User newUser = userFactory.createUser(userDTO);
        var check = userRepository.save(newUser);
        if(check.getUser_id() == null) {
            return ResponseEntity.badRequest().body("Create user failed");
        }
        return ResponseEntity.ok().body("Created successfully");
    }

    public ResponseEntity<?> createUser_v2(UserCreateDTO userDTO) {
        try {
            UserFactory userFactory;
            User newUser = null;

            //find role object
            Role role = roleRepository.findById(userDTO.getRole_id()).get();
            if(role == null){
                return ResponseEntity.badRequest().body("Role not found");
            }

            userDTO.setRole(role);
            switch(userDTO.getRole_id()){
                case 2 -> {
                    System.out.println("Create customer");
                    return create_customer(userDTO);
                }
                case 3 -> {
                    return create_staff(userDTO);
                }
                case 4 -> {
                    userFactory = new ShipperFactory();
                    newUser = userFactory.createUser(userDTO);
                }
                default -> {
                    userFactory = new AdminFactory();
                    newUser = userFactory.createUser(userDTO);
                }
            }
            if(newUser == null) {
                return ResponseEntity.badRequest().body("Create user failed, user got null");
            }
            var check = userRepository.save(newUser);
            if(check.getUser_id() == null) {
                return ResponseEntity.badRequest().body("Create user failed");
            }
            return ResponseEntity.ok().body("Created successfully");
        } catch(Exception ex) {
            System.out.printf("Create user failed - Error" + ex);
            return ResponseEntity.status(500).body("Server error: " + ex.getMessage());
        }
    }
    public ResponseEntity<?> createUser(UserCreateDTO userDTO) {
        Role role = roleRepository.findById(userDTO.getRole_id()).get();
        System.out.println(role.toString());

        var checkValidAccount = userRepository.findUserByAccount(userDTO.getAccount());
        var checkValidEmail = userRepository.findUsersByEmail(userDTO.getEmail());

        // existing user
        if(checkValidEmail != null) {
            return ResponseEntity.badRequest().body("Email is not valid");
        }
        if(checkValidAccount != null) {
            return ResponseEntity.badRequest().body("Account is not valid");
        }
        //remember change it later
        User newUser = new Admin();
        newUser.setDataCreate(userDTO);

        try {
            User checkSave = userRepository.save(newUser);
            if(checkSave.getUser_id() != null) {
                return ResponseEntity.ok().body("Insert account successfully");
            } else {
                return ResponseEntity.badRequest().body("Insert account failed");
            }
        } catch(Exception ex) {
            System.out.printf("Create user failed - Error" + ex);
            return ResponseEntity.status(500).body("Server error: " + ex.getMessage());
        }
    }

    public HttpStatus updateUserAdmin(UserCreateDTO userDto) {
        User user = userRepository.findUserByAccount(userDto.getAccount());
        user.setDataUpdated(userDto);
        try {
            var checkSave = userRepository.save(user);
            if (checkSave != null)
                return HttpStatus.OK;
        }catch (Exception ex) {
            System.out.printf("Error from service" + ex);
        }
        return HttpStatus.BAD_REQUEST;
    }

    public HttpStatus deleteUser(String account) {
        User user = userRepository.findUserById(account);
        user.set_delete(true);
        try {
            var checkUpdate = userRepository.save(user);
            if(checkUpdate == null) {
                return HttpStatus.CONFLICT;
            }
            return HttpStatus.OK;
        } catch (Exception ex) {
            System.out.printf("Error from service", ex);
            return HttpStatus.BAD_REQUEST;
        }

    }


//    STAFF
    public ResponseEntity<?> getStaff() {
        try {
            var roleStaff = roleRepository.findRoleByName("staff");

            if(roleStaff == null) {
                return ResponseEntity.badRequest().body("Not found role");
            }

            var staffList = userRepository.findUsersByRole(roleStaff);
            List<getUserListDTO> res = new ArrayList<getUserListDTO>();
            for(User user : staffList){
                getUserListDTO temp = new getUserListDTO();
                temp.setData(user);
                res.add(temp);
            }


            return ResponseEntity.ok().body(res);

        } catch (Exception ex) {
            System.out.println("Error from services" + ex);
            return ResponseEntity.badRequest().body("Error: " + ex);
        }
    }
    public ResponseEntity<?> getCustomer() {
        try {
            var roleStaff = roleRepository.findRoleByName("customer");

            if(roleStaff == null) {
                return ResponseEntity.badRequest().body("Not found role");
            }

            var staffList = userRepository.findUsersByRole(roleStaff);
            List<getUserListDTO> res = new ArrayList<getUserListDTO>();
            for(User user : staffList){
                getUserListDTO temp = new getUserListDTO();
                temp.setData(user);
                res.add(temp);
            }

            return ResponseEntity.ok().body(res);

        } catch (Exception ex) {
            System.out.println("Error from services" + ex);
            return ResponseEntity.badRequest().body("Error: " + ex);
        }
    }
    public ResponseEntity<?> getShipper() {
        try {
            var roleStaff = roleRepository.findRoleByName("shipper");
            if(roleStaff == null) {
                return ResponseEntity.badRequest().body("Not found role");
            }

            var staffList = userRepository.findUsersByRole(roleStaff);
            List<getUserListDTO> res = new ArrayList<getUserListDTO>();
            for(User user : staffList){
                getUserListDTO temp = new getUserListDTO();
                temp.setData(user);
                res.add(temp);
            }

            return ResponseEntity.ok().body(res);

        } catch (Exception ex) {
            System.out.println("Error from services" + ex);
            return ResponseEntity.badRequest().body("Error: " + ex);
        }
    }

    public ResponseEntity<?> getAssignmentShipperInfo(String branchID) {
        try {
            var branch = branchRepo.findById(branchID).get();
            var assignList = shipRepo.findShippingAssignmentByBranch(branch);

            return ResponseEntity.ok().body(assignList);
        }
        catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
            return ResponseEntity.badRequest().body("Error from server");
        }
    }

    public ResponseEntity<?> getShipperByBranch(String branchID) {
        try {

            var roleStaff = roleRepository.findRoleByName("shipper");

            if(roleStaff == null) {
                return ResponseEntity.badRequest().body("Not found role");
            }

            var branch = branchRepo.findById(branchID).get();
           var shipperList = userRepository.findShipperByBranch(branch, roleStaff);
            System.out.println(roleStaff.getName());

            List<getUserListDTO> res = new ArrayList<>();
            for(User user : shipperList){
                getUserListDTO temp = new getUserListDTO();
                temp.setData(user);
                res.add(temp);
            }

            return ResponseEntity.ok().body(res);

        }catch (Exception ex) {
            System.out.println("Error from services: " + ex);
            return ResponseEntity.badRequest().body("Error from services" + ex);
        }
    }

    public ResponseEntity<?> setAssignmentShipment(String area_code, String  branch_code, String user_code) {
        try {
            var branch = branchRepo.findBranchByCode(branch_code);
            var area = areaRepo.findByCode(area_code);
            var user = userRepository.findUsersByCode(user_code);
            var assignment = shipRepo.findShippingAssignmentByAreaAndAndBranch(branch, area);

            assignment.setUser(user);
            assignment.setStatus(true);

            var checkSave = shipRepo.save(assignment);
            if(checkSave != null) {
                return ResponseEntity.ok().body("Insert success");
            }
            return ResponseEntity.badRequest().body("Insert failed");

        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error from services, " + ex.getMessage());
        }
    }
    public ResponseEntity<?> deleteShipment(String area_code, String  branch_code) {
        try {
            var branch = branchRepo.findBranchByCode(branch_code);
            var area = areaRepo.findByCode(area_code);
            var assignment = shipRepo.findShippingAssignmentByAreaAndAndBranch(branch, area);

            assignment.setUser(null);
            assignment.setStatus(false);

            var checkSave = shipRepo.save(assignment);
            if(checkSave != null) {
                return ResponseEntity.ok().body("Delete success");
            }
            return ResponseEntity.badRequest().body("Insert failed");

        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error from services, " + ex.getMessage());
        }
    }
}

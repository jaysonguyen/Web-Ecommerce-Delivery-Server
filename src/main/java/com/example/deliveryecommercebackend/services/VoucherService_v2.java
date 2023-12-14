package com.example.deliveryecommercebackend.services;

import com.example.deliveryecommercebackend.DTO.VoucherDTO;
import com.example.deliveryecommercebackend.DTO.VoucherDisplayDTO;
import com.example.deliveryecommercebackend.exception.ResourceNotfoundException;
import com.example.deliveryecommercebackend.model.Voucher;
import com.example.deliveryecommercebackend.repository.VoucherRepository;
import com.example.deliveryecommercebackend.services.utils.ExcelUploadService;
import com.example.deliveryecommercebackend.template.EntityTemplate;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class VoucherService_v2 extends EntityTemplate<Voucher> {

    @Autowired
    private VoucherRepository voucherRepository;

    public VoucherService_v2(VoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

    @Override
    protected boolean validateInput(Voucher entity) {
        return true;
    }

    @Override
    protected boolean create(Voucher entity) {
        try {
            voucherRepository.save(entity);
            return true;
        } catch(Exception ex) {
            System.out.printf("Error from voucher service - Error" + ex.getMessage());
            return false;
        }
    }

    @Override
    protected boolean update(Voucher entity) {
        try {
            Voucher newVoucher = voucherRepository.findNoneDeleteVoucherByID(entity.getVoucher_id());
            if(newVoucher == null) {
                System.out.println("Voucher not found");
                return false;
            }
            newVoucher = new Voucher(entity);
            voucherRepository.save(newVoucher);
            return true;
        } catch(Exception ex) {
            System.out.printf("Error from voucher service - Error" + ex.getMessage());
            return false;
        }
    }

    @Override
    protected boolean delete(Voucher entity) {
        try {
            Voucher newVoucher = voucherRepository.findById(entity.getVoucher_id()).get();
            newVoucher.set_deleted(true);
            voucherRepository.save(newVoucher);
            return true;
        } catch(Exception ex) {
            System.out.printf("Error at voucher service - Error" + ex.getMessage());
            return false;
        }
    }

    public boolean duplicate_old(String id) {
        try {
            Voucher voucher = voucherRepository.findById(id).get();
            if(voucher.getVoucher_id() == null) {
                System.out.println("Error at voucher service ver2: Voucher not found");
                return false;
            }
            Voucher newVoucher = new Voucher(voucher);

            voucherRepository.save(newVoucher);
            return true;
        } catch(Exception ex) {
            System.out.printf("Error at voucher service - Error" + ex.getMessage());
            return false;
        }
    }

    public boolean duplicate(String id) {
        try {
            Voucher voucher = voucherRepository.findById(id).get();
            if(voucher.getVoucher_id() == null) {
                System.out.println("Error at voucher service ver2: Voucher not found");
                return false;
            }
            Voucher newVoucher = (Voucher) voucher.Clone();

            voucherRepository.save(newVoucher);
            return true;
        } catch(Exception ex) {
            System.out.printf("Error at voucher service - Error" + ex.getMessage());
            return false;
        }
    }

    @Override
    public List<?> getAll() {
        try {
            List<Voucher> vouchers = voucherRepository.findNoneDeleteVoucher();

            List<VoucherDisplayDTO> res = new ArrayList<VoucherDisplayDTO>();
            for(Voucher voucher : vouchers){
                VoucherDisplayDTO temp = new VoucherDisplayDTO();
                temp.setData(voucher);
                res.add(temp);
            }

            return res;
        } catch(Exception ex) {
            System.out.printf("Get all voucher failed - Error: " + ex.getMessage());
//            return ResponseEntity.badRequest().body("Error from services");
            return null;
        }
    }

    @Override
    public Voucher getByID(String id) {
        try {
            Voucher voucher = voucherRepository.findNoneDeleteVoucherByID(id);
            if(voucher == null) {
                System.out.println("Voucher not found");
                return null;
            }

            return voucher;
        } catch(Exception ex) {
            System.out.printf("Error from voucher service - Error: " + ex.getMessage());
            return null;
        }
    }
}

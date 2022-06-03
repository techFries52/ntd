package com.ntd.services;

import com.ntd.models.Bill;
import com.ntd.repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    BillRepository bRepo;

    @Override
    public boolean ifBillExists(int billId) {
        Optional<Bill> bill = bRepo.findById(billId);
        return bill.isPresent();
    }

    @Override
    public Bill saveBill(Bill bill) {
        return bRepo.save(bill);
    }

    @Override
    public Bill updateBill(Bill bill) {
        return bRepo.save(bill);
    }

    @Override
    public void deleteBill(Bill bill) {
        bRepo.delete(bill);
    }

    @Override
    public Bill getBillById(int billId) {
        Optional<Bill> bill = bRepo.findById(billId);
        Bill checkedBill = new Bill();

        if(bill.isPresent()){
            checkedBill = bill.get();
        }

        return checkedBill;
    }

    @Override
    public List<Bill> getAllBills() {
        return bRepo.findAll();
    }

    @Override
    public Set<Bill> getAllUnpaidBillsByUser(int user_id) {
        return bRepo.findAll().stream()
                .filter( bill -> !bill.isPaid() && bill.getUser().getUser_id() == user_id)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Bill> getAllPaidBillsByUser(int user_id) {
        return bRepo.findAll().stream()
                .filter( bill -> bill.isPaid() && bill.getUser().getUser_id() == user_id)
                .collect(Collectors.toSet());
    }
}

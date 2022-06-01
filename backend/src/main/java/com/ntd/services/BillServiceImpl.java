package com.ntd.services;

import com.ntd.models.Bill;
import com.ntd.repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return bill.get();
    }

    @Override
    public List<Bill> getAllBills() {
        List<Bill> bills = bRepo.findAll();
        return bills;
    }
}
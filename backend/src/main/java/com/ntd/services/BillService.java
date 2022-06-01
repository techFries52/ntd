package com.ntd.services;

import com.ntd.models.Bill;

import java.util.List;

public interface BillService {

    boolean ifBillExists(int billId);

    Bill saveBill(Bill bill);

    Bill updateBill(Bill bill);

    void deleteBill(Bill bill);

    Bill getBillById(int billId);

    List<Bill> getAllBills();
}

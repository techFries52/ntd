package com.ntd.services;

import com.ntd.models.Bill;

import java.util.List;
import java.util.Set;

public interface BillService {

    boolean ifBillExists(int billId);

    Bill saveBill(Bill bill);

    Bill updateBill(Bill bill);

    void deleteBill(Bill bill);

    Bill getBillById(int billId);

    List<Bill> getAllBills();

    Set<Bill> getAllUnpaidBillsByUser(int user_id);

    Set<Bill> getAllPaidBillsByUser(int user_id);
}

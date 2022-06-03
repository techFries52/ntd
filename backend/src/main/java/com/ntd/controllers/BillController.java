package com.ntd.controllers;

import com.ntd.models.Bill;
import com.ntd.services.BillService;
import com.ntd.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "bill")
public class BillController {

    @Autowired
    BillService bServ;

    @Autowired
    UserService uServ;

    @GetMapping
    public ResponseEntity<List<Bill>> getAllBills(){
        ResponseEntity<List<Bill>> response;
        List<Bill> bills = bServ.getAllBills();

        if(bills.size() > 0){
            response = new ResponseEntity<>(bills, HttpStatus.OK); // 200
        } else if (bills.size() == 0) {
            response = new ResponseEntity<>(bills, HttpStatus.NO_CONTENT); // 204
        } else {
            response = new ResponseEntity<>(bills, HttpStatus.NOT_FOUND); // 404
        }
        return response;
    }

    @GetMapping("{id}")
    public ResponseEntity<Bill> getSingleBill(@PathVariable("id")int id){
        ResponseEntity<Bill> response;
        Bill bill;

        if(!bServ.ifBillExists(id)){
            // bill does not exist
            bill = new Bill();
            response = new ResponseEntity<>(bill, HttpStatus.NOT_FOUND); // 404
        } else {
            // bill does exist
            bill = bServ.getBillById(id);
            response = new ResponseEntity<>(bill, HttpStatus.OK); // 200
        }
        return response;
    }

    @PostMapping
    public ResponseEntity<Bill> saveNewBill(@RequestBody Bill newBill){
        ResponseEntity<Bill> response;

        if(bServ.ifBillExists(newBill.getBillId())){
            // bill already exists
            response = new ResponseEntity<>(newBill, HttpStatus.CONFLICT); // 409
        } else {
            // bill does not already exist
            bServ.saveBill(newBill);
            response = new ResponseEntity<>(newBill, HttpStatus.CREATED); // 201
        }
        return response;
    }

    @PutMapping("{id}")
    public ResponseEntity<Bill> updateBill(@RequestBody Bill updatedBill, @PathVariable("id") int id){
        ResponseEntity<Bill> response;

        if(bServ.ifBillExists(updatedBill.getBillId()) && updatedBill.getBillId() == id){
            // bill exists
            bServ.updateBill(updatedBill);
            response = new ResponseEntity<>(updatedBill, HttpStatus.OK); // 200
        } else {
            // bill does not exist
            response = new ResponseEntity<>(updatedBill, HttpStatus.NOT_FOUND); // 404
        }
        return response;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Bill> deleteBill(@PathVariable("id")int id){
        ResponseEntity<Bill> response;
        Bill bill;

        if(bServ.ifBillExists(id)){
            // bill does exist
            bill = bServ.getBillById(id);
            bServ.deleteBill(bill);
            response = new ResponseEntity<>(bill, HttpStatus.OK); // 200
        } else {
            // bill does not exist
            bill = new Bill();
            response = new ResponseEntity<>(bill, HttpStatus.NOT_FOUND); // 404
        }
        return response;
    }

    @GetMapping("unpaid/{user_id}")
    public ResponseEntity<Set<Bill>> getAllUnpaidBillsByUser(@PathVariable("user_id") int user_id){
        ResponseEntity<Set<Bill>> response;
        Set<Bill> unpaidBills;

        if(uServ.ifUserExists(user_id)){
            // user does exist
            unpaidBills = bServ.getAllUnpaidBillsByUser(user_id);

            if(unpaidBills.size() > 0){
                response = new ResponseEntity<>(unpaidBills, HttpStatus.OK); //200
            } else {
                response = new ResponseEntity<>(unpaidBills, HttpStatus.NO_CONTENT); // 204
            }

        } else {
            // user does not exist
            unpaidBills= new HashSet<>();
            response = new ResponseEntity<>(unpaidBills, HttpStatus.NOT_FOUND); // 404
        }

        return response;
    }

    @GetMapping("paid/{user_id}")
    public ResponseEntity<Set<Bill>> getAllPaidBillsByUser(@PathVariable("user_id") int user_id){
        ResponseEntity<Set<Bill>> response;
        Set<Bill> paidBills;

        if(uServ.ifUserExists(user_id)){
            // user does exist
            paidBills = bServ.getAllPaidBillsByUser(user_id);

            if(paidBills.size() > 0){
                response = new ResponseEntity<>(paidBills, HttpStatus.OK); // 200
            } else {
                response = new ResponseEntity<>(paidBills, HttpStatus.NO_CONTENT); // 204
            }

        } else {
            // user does not exist
            paidBills = new HashSet<>();
            response = new ResponseEntity<>(paidBills, HttpStatus.NOT_FOUND); // 404
        }
        return response;
    }
}

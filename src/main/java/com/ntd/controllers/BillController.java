package com.ntd.controllers;

import com.ntd.models.Bill;
import com.ntd.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "bill")
public class BillController {

    @Autowired
    BillService bServ;

    @GetMapping
    public ResponseEntity<List<Bill>> getAllBills(){
        ResponseEntity<List<Bill>> response = null;
        List<Bill> bills = bServ.getAllBills();

        if(bills.size() > 0){
            response = new ResponseEntity<List<Bill>>(bills, HttpStatus.OK); // 200
        } else if (bills.size() == 0) {
            response = new ResponseEntity<List<Bill>>(bills, HttpStatus.NO_CONTENT); // 204
        } else {
            response = new ResponseEntity<List<Bill>>(bills, HttpStatus.NOT_FOUND); // 404
        }
        return response;
    }

    @GetMapping("{id}")
    public ResponseEntity<Bill> getSingleBill(@PathVariable("id")int id){
        ResponseEntity<Bill> response = null;
        Bill bill = null;

        if(!bServ.ifBillExists(id)){
            // bill does not exist
            response = new ResponseEntity<Bill>(bill, HttpStatus.NOT_FOUND); // 404
        } else {
            // bill does exist
            bill = bServ.getBillById(id);
            response = new ResponseEntity<Bill>(bill, HttpStatus.OK); // 200
        }
        return response;
    }

    @PostMapping
    public ResponseEntity<Bill> saveNewBill(@RequestBody Bill newBill){
        ResponseEntity<Bill> response = null;

        if(bServ.ifBillExists(newBill.getBillId())){
            // bill already exists
            response = new ResponseEntity<Bill>(newBill, HttpStatus.CONFLICT); // 409
        } else {
            // bill does not already exist
            bServ.saveBill(newBill);
            response = new ResponseEntity<Bill>(newBill, HttpStatus.CREATED); // 201
        }
        return response;
    }

    @PutMapping("{id}")
    public ResponseEntity<Bill> updateBill(@RequestBody Bill updatedBill){
        ResponseEntity<Bill> response = null;

        if(bServ.ifBillExists(updatedBill.getBillId())){
            // bill exists
            bServ.updateBill(updatedBill);
            response = new ResponseEntity<Bill>(updatedBill, HttpStatus.OK); // 200
        } else {
            // bill does not exist
            response = new ResponseEntity<Bill>(updatedBill, HttpStatus.NOT_FOUND); // 404
        }
        return response;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Bill> deleteBill(@PathVariable("id")int id){
        ResponseEntity<Bill> response = null;
        Bill bill = null;

        if(bServ.ifBillExists(id)){
            // bill does exist
            bill = bServ.getBillById(id);
            bServ.deleteBill(bill);
            response = new ResponseEntity<Bill>(bill, HttpStatus.OK); // 200
        } else {
            // bill does not exist
            response = new ResponseEntity<Bill>(bill, HttpStatus.NOT_FOUND); // 404
        }
        return response;
    }
}

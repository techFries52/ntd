package com.ntd.repositories;

import com.ntd.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {

    @Query(value = "SELECT * FROM bill WHERE b.is_paid == false", nativeQuery = true)
    List<Bill> getUnpaidBillsByUser(int user_id);

    @Query(value = "SELECT * FROM bill WHERE b.is_paid == true", nativeQuery = true)
    List<Bill> getPaidBillsByUser(int user_id);
}
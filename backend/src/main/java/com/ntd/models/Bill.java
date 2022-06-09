package com.ntd.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bills")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="bill_id", unique = true, nullable = false)
    private int billId;

    @Column(name="bill_name")
    private String billName;

    @Column
    private String company;

    @Column(name="amount")
    private double amountOfBill;

    @Column(name="bill_due_date")
    private Date dueDate;

    @Column(name="is_paid")
    private boolean isPaid;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

}

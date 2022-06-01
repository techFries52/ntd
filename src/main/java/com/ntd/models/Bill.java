package com.ntd.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill {

    @Id
    private int billId;

    @Column
    private String company;

    @Column
    private double amountOfBill;

    @Column
    private LocalDate dueDate;

    @Column
    private boolean isPaid;

    @Column
    private User user;

}

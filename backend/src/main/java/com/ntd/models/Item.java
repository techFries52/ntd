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
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="item_id", unique = true, nullable = false)
    private int itemId;

    @Column(name="item_name")
    private String itemName;

    @Column
    private String repetition;

    @Column
    private String notes;

    @Column
    private Boolean completed;

    @Column(name="item_priority")
    private String itemPriority;

    @Column(name="date_created")
    private Date dateCreated;

    @Column(name="date_completed")
    private Date dateCompleted;

    @Column(name="date_modified")
    private Date dateModified;

    @Column(name="due_date")
    private Date dueDate;


    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name="category_id", nullable = false)
    private Category category;

}

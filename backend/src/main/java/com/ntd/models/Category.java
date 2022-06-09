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
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="category_id", unique = true, nullable = false)
    private int categoryId;

    @Column(name="category_name")
    private String categoryName;

    @Column(name="date_created")
    private Date categoryDateCreated;

    @Column(name="date_modified")
    private Date categoryDateModified;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

}

package com.ntd.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    int user_id;

    @Column
    private String email;

    @Column(name="f_name")
    private String firstName;

    @Column(name="l_name")
    private String lastName;

    @Column
    private String password;

    @Column(name="created")
    private Date dateCreated;


    @OneToMany(mappedBy = "user")
    Set<Bill> usersBills;

    public int getUser_id() {
        return user_id;
    }
}

package com.ntd.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    int user_id;

    @OneToMany(mappedBy = "user")
    Set<Bill> usersBills;

    public int getUser_id() {
        return user_id;
    }
}

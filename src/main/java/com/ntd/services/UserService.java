package com.ntd.services;

import com.ntd.models.User;

public interface UserService {

    boolean ifUserExists(int userId);

    User saveUser(User user);

    User updateBill(User user);

    void deleteUser(int userId);
}

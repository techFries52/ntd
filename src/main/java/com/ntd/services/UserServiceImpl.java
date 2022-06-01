package com.ntd.services;

import com.ntd.models.User;
import com.ntd.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository uRepo;

    @Override
    public boolean ifUserExists(int userId) {
        Optional<User> userCheck = uRepo.findById(userId);
        return userCheck.isPresent();
    }

    @Override
    public User saveUser(User user) {
        return uRepo.save(user);
    }

    @Override
    public User updateBill(User user) {
        return uRepo.save(user);
    }

    @Override
    public void deleteUser(int userId) {
        uRepo.deleteById(userId);
    }
}

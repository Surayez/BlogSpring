package com.example.demo.service;

import com.example.demo.entity.UserObject;
import com.example.demo.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;

@Service
public class UserServiceImplement implements UserService{

    @Autowired
    UserRepository userRepository;

    public List<UserObject> retrieveAllUsers() {
        return (List<UserObject>) userRepository.findAll();
    }


    public UserObject getUser(int userId) {
        Optional<UserObject> user = userRepository.findById(userId);
        return user.get();
    }

//    public List<UserObject> getListedUser(int userId) {
//        Optional<UserObject> user = userRepository.findById(userId);
//        return new ArrayList<UserOb>(Collections.singletonList(user));
//    }

    public void saveUser(UserObject user) {
        Date currentDate = new Date();
        user.setCreatedAt(currentDate);
        user.setUpdatedAt(currentDate);
        userRepository.save(user);
    }


    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }


    public void updateUser(UserObject user, int userId){
        UserObject prevUser = getUser(userId);
        prevUser.setEmail(user.getEmail());
        prevUser.setFirst_name(user.getFirst_name());
        prevUser.setLast_name(user.getLast_name());
        prevUser.setUpdatedAt(new Date());
        userRepository.save(prevUser);
    }
}

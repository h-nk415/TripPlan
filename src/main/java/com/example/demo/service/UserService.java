package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    // ユーザーの登録
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    // IDでユーザーを取得
    public User getUserById(Integer id) {
        return userMapper.selectUserById(id);
    }

    // ユーザー情報の更新
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    // ユーザーの削除
    public void deleteUserById(Integer id) {
        userMapper.deleteUserById(id);
    }
    
    public User getUserByEmail(String email) {
        return userMapper.findByEmail(email); // emailでユーザーを取得
    }

}
package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserMapper;

import lombok.RequiredArgsConstructor;

/**
 * ユーザーに関するビジネスロジックを処理するサービスクラスです。
 * ユーザーの登録、取得などの処理を行います。
 */
@Service
@RequiredArgsConstructor
public class UserService {

    /** DI: UserMapperを注入 */
    private final UserMapper userMapper;

    /**
     * 新しいユーザーをデータベースに登録します。
     * 
     * @param user 登録するユーザーのエンティティ
     */
    public void insertUser(User user) {
        userMapper.insertUser(user); // UserMapperを使ってユーザーを登録
    }

    /**
     * 指定されたIDのユーザーを取得します。
     * 
     * @param id 取得したいユーザーのID
     * @return 取得したユーザーのエンティティ
     */
    public User getUserById(Integer id) {
        return userMapper.selectUserById(id); // IDでユーザーを検索
    }

    /**
     * 指定されたemailのユーザーを取得します。
     * 
     * @param email 取得したいユーザーのemail
     * @return 取得したユーザーのエンティティ
     */
    public User getUserByEmail(String email) {
        return userMapper.findByEmail(email); // emailでユーザーを取得
    }

}

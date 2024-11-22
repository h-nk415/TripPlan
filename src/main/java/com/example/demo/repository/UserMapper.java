package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.entity.User;

/**
 * ユーザーに関するデータベース操作を行うマッパーインターフェイスです。
 * ユーザーの登録、取得などの操作を行います。
 * このインターフェイスではユーザーの情報を操作するためのメソッドを提供します。
 */
@Mapper
public interface UserMapper {

    /**
     * 新しいユーザーをデータベースに挿入します。
     *
     * @param user 登録するユーザーのエンティティ
     */
    void insertUser(User user);

    /**
     * 指定されたIDに対応するユーザーを取得します。
     *
     * @param id 取得したいユーザーのID
     * @return 指定されたIDに対応するユーザーのエンティティ
     */
    User selectUserById(@Param("id") Integer id);
    
    /**
     * 指定されたメールアドレスに対応するユーザーを取得します。
     *
     * @param email 取得したいユーザーのメールアドレス
     * @return 指定されたメールアドレスに対応するユーザーのエンティティ
     */
    User findByEmail(@Param("email") String email);
}

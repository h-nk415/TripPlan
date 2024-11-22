package com.example.demo.security;

import java.util.ArrayList;

/**
 * Spring Securityのユーザー情報を拡張したクラスです。
 * 権限情報が空の状態で、ユーザー名とパスワードを利用して認証情報を管理します。
 * 
 * このクラスは、`org.springframework.security.core.userdetails.User` クラスを継承し、
 * Spring Securityの認証システムにユーザー情報を提供します。
 */
public class LoginUser extends org.springframework.security.core.userdetails.User {

    /**
     * ユーザー名とパスワードを用いて`LoginUser`を生成します。
     * 権限情報は空のリストを渡すことで、最小限の認証機能を提供します。
     * 
     * @param username ユーザー名
     * @param password パスワード
     */
    public LoginUser(String username, String password) {
        super(username, password, new ArrayList<>());  // 空の権限リストを渡す
    }
}

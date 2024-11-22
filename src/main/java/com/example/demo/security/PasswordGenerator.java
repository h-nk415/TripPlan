package com.example.demo.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * パスワードをBCryptでハッシュ化するためのユーティリティクラスです。
 * 
 * このクラスは、指定されたパスワードをBCryptアルゴリズムを使用してハッシュ化し、
 * 安全に保存するための文字列を生成します。
 * 
 * @see BCryptPasswordEncoder
 */
public class PasswordGenerator {

    /**
     * 指定されたパスワードをBCryptアルゴリズムでハッシュ化します。
     * 
     * @param password ハッシュ化する元のパスワード
     * @return ハッシュ化されたパスワード（BCrypt形式）
     */
    public static String hashGenerate(String password) {
        // BCryptPasswordEncoderを使用してパスワードをハッシュ化
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}

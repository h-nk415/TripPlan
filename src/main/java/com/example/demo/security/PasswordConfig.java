package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * パスワードのエンコーダを設定するためのコンフィギュレーションクラスです。
 * 
 * このクラスは、Spring Securityのパスワードエンコーダ（BCryptPasswordEncoder）をBeanとして定義します。
 * アプリケーション全体でパスワードのハッシュ化・検証に使用されます。
 * 
 * @see BCryptPasswordEncoder
 */
@Configuration // 設定クラスとして指定
public class PasswordConfig {

    /**
     * パスワードエンコーダをBeanとして登録します。
     * 
     * このメソッドは、BCryptアルゴリズムを使用してパスワードをハッシュ化するための
     * {@link BCryptPasswordEncoder} を返します。これにより、Spring Securityで使用される
     * パスワードのエンコーディングが提供されます。
     * 
     * @return PasswordEncoder BCryptPasswordEncoderのインスタンス
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCryptPasswordEncoderをBeanとして登録
        return new BCryptPasswordEncoder();
    }
}

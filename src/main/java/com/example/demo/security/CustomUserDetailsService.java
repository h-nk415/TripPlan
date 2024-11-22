package com.example.demo.security;

import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserMapper userMapper;

    /**
     * メールアドレスを基にユーザー情報を取得し、Spring SecurityのUserDetailsオブジェクトを構築します。
     * 
     * このサービスは、Spring Securityの認証機能と連携し、ユーザー情報（メールアドレスとパスワード）を基に認証を行います。
     * 
     * @param email ユーザーのメールアドレス
     * @return UserDetails ユーザーの認証情報を持つオブジェクト
     * @throws UsernameNotFoundException メールアドレスに対応するユーザーが見つからない場合にスローされます
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // emailでユーザー情報を取得
        User user = userMapper.findByEmail(email);
        
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        // UserオブジェクトをUserDetailsオブジェクトに変換
        UserBuilder userBuilder = org.springframework.security.core.userdetails.User.withUsername(user.getEmail());
        userBuilder.password(user.getPassword());
        
        // UserDetailsオブジェクトを返却
        return userBuilder.build();
    }
}

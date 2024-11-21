package com.example.demo.entity;

import java.util.ArrayList;




public class LoginUser extends org.springframework.security.core.userdetails.User{
	public LoginUser(String username, String password) {
        super(username, password, new ArrayList<>());  // 空の権限リストを渡す
    }

}

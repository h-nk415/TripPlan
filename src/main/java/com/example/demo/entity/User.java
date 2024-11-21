package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private Integer id;  // ユーザーID（主キー）
    private String email;   // メールアドレス
    private String password;  // パスワード
    private String displayname;  // 表示名


}

package com.example.demo.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserForm {

    @NotBlank(message = "メールアドレスを入力してください。")
    @Email(message = "有効なメールアドレスを入力してください。")
    private String email; // メールアドレス

    @NotBlank(message = "パスワードを入力してください。")
    @Size(min = 6, message = "パスワードは6文字以上で入力してください。")
    private String password; // パスワード

    @NotBlank(message = "表示名を入力してください。")
    @Size(max = 10, message = "表示名は10文字以内で入力してください。")
    private String displayname; // 表示名
}

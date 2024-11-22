package com.example.demo.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * ユーザー登録やログインフォームの入力を扱うクラスです。
 * このクラスは、ユーザーのメールアドレス、パスワード、表示名を管理します。
 * フォーム入力時に必要なバリデーションを行います。
 */
@Data
public class UserForm {

    /** 
     * メールアドレス
     * 必須項目であり、正しいメール形式であることが求められます。
     */
    @NotBlank(message = "メールアドレスを入力してください。")
    @Email(message = "有効なメールアドレスを入力してください。")
    private String email; // メールアドレス

    /** 
     * パスワード
     * 必須項目であり、6文字以上で入力する必要があります。
     */
    @NotBlank(message = "パスワードを入力してください。")
    @Size(min = 6, message = "パスワードは6文字以上で入力してください。")
    private String password; // パスワード

    /** 
     * 表示名
     * 必須項目であり、10文字以内で入力する必要があります。
     */
    @NotBlank(message = "表示名を入力してください。")
    @Size(max = 10, message = "表示名は10文字以内で入力してください。")
    private String displayname; // 表示名
}

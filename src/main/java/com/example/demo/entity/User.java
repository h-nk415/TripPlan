package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ユーザーを表すクラスです。
 * ユーザーのメールアドレス、パスワード、表示名を管理します。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /** 
     * ユーザーID（主キー）
     */
    private Integer id;

    /** 
     * ユーザーのメールアドレス
     * ログイン認証やユーザー識別に使用されます。
     */
    private String email;

    /** 
     * ユーザーのパスワード
     * パスワードはセキュリティのため、暗号化された状態で保存されます。
     */
    private String password;

    /** 
     * ユーザーの表示名
     * ユーザーがサイトに表示する名前などに使用されます。
     */
    private String displayname;
}

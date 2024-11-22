package com.example.demo.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ログインしたユーザーのセッション情報を保持するエンティティクラスです。
 * ユーザーのID、メールアドレス、パスワード、表示名を保持します。
 * このクラスはセッションスコープで管理され、ログインユーザーの情報をセッション中に保持します。
 */
@Component
@Scope("session")  // セッションスコープに設定
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConnectUser {

    /**
     * ユーザーのIDを表します。
     */
    private Integer id;

    /**
     * ユーザーのメールアドレスを表します。
     */
    private String email;

    /**
     * ユーザーのパスワードを表します。
     */
    private String password;

    /**
     * ユーザーの表示名を表します。
     */
    private String displayname;
}

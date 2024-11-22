package com.example.demo.entity;



import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Scope("session")  // セッションスコープに設定
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConnectUser {
    private Integer id;
    private String email;
    private String password;
    private String displayname;
}

package com.example.demo.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.demo.entity.ConnectUser;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // Authenticationオブジェクトからemailを取得
        String email = authentication.getName(); // Spring Securityでは、通常、emailはgetName()に格納される
        
        // emailを使って、UserServiceからユーザー情報を取得
        User user = userService.getUserByEmail(email); // ここでemailでユーザーを取得する

        if (user != null) {
            // ユーザー情報をConnectUserに静的に保持
            ConnectUser.id = user.getId();
            ConnectUser.email = user.getEmail();
            ConnectUser.password = user.getPassword();
            ConnectUser.displayname = user.getDisplayname();
        }

        // ログイン後にリダイレクト
        response.sendRedirect("/plans/home"); // ユーザーがログイン後、/plans/homeにリダイレクト
    }
}

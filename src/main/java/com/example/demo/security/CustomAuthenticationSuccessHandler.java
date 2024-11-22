package com.example.demo.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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

        // セッションからConnectUserを取得
        ConnectUser connectUser = (ConnectUser) ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                                        .getRequest().getSession().getAttribute("connectUser");

        if (connectUser == null) {
            connectUser = new ConnectUser();
        }

        String email = authentication.getName();
        User user = userService.getUserByEmail(email);

        if (user != null) {
            connectUser.setId(user.getId());
            connectUser.setEmail(user.getEmail());
            connectUser.setPassword(user.getPassword());
            connectUser.setDisplayname(user.getDisplayname());

            // セッションにConnectUserを保存
            request.getSession().setAttribute("connectUser", connectUser);
        }

        response.sendRedirect("/plans/home");
    }
}

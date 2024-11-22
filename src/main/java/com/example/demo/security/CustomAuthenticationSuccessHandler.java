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

/**
 * 認証成功後の処理を行うカスタムハンドラです。
 * Spring Securityの認証が成功した際に実行され、ユーザー情報をセッションに保存し、指定されたページへリダイレクトします。
 */
@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final UserService userService;

    /**
     * 認証が成功した際に呼び出されるメソッドです。
     * ユーザー情報をセッションに保存し、リダイレクト先のページへ遷移します。
     * 
     * @param request HTTPリクエスト
     * @param response HTTPレスポンス
     * @param authentication 認証情報
     * @throws IOException 入出力エラーが発生した場合にスローされます
     * @throws ServletException サーブレット関連のエラーが発生した場合にスローされます
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        // セッションからConnectUserを取得
        ConnectUser connectUser = (ConnectUser) ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                                        .getRequest().getSession().getAttribute("connectUser");

        if (connectUser == null) {
            connectUser = new ConnectUser();
        }

        // 認証されたユーザーのメールアドレスを取得
        String email = authentication.getName();
        
        // メールアドレスからユーザー情報を取得
        User user = userService.getUserByEmail(email);

        if (user != null) {
            // ユーザー情報をConnectUserに設定
            connectUser.setId(user.getId());
            connectUser.setEmail(user.getEmail());
            connectUser.setPassword(user.getPassword());
            connectUser.setDisplayname(user.getDisplayname());

            // セッションにConnectUserを保存
            request.getSession().setAttribute("connectUser", connectUser);
        }

        // ホームページにリダイレクト
        response.sendRedirect("/plans/home");
    }
}

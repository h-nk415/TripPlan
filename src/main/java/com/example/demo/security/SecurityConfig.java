package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationSuccessHandler successHandler;
    private final CustomUserDetailsService customUserDetailsService;

    // UserDetailsService を設定するBean
    @Bean
    public UserDetailsService userDetailsService() {
        return customUserDetailsService;
    }

    // DaoAuthenticationProvider を使ってユーザー情報を検証
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService); // CustomUserDetailsService をセット
        provider.setPasswordEncoder(new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder()); // パスワードエンコーダー
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // セキュリティ設定
            .authorizeHttpRequests(authz -> authz
                // 静的リソースは全てのアクセスを許可
                .requestMatchers("/image/**", "/css/**", "/js/**", "/webjars/**").permitAll()
                // ログインに関わる画面へのアクセスは誰でも可能
                .requestMatchers("/login", "/login/**").permitAll()
                // 他はログインすればアクセス可能
                .anyRequest().authenticated()
            )
            // ログイン設定
            .formLogin(form -> form
                // ログインページに使うURLはlogin
                .loginPage("/login")
                // ログイン処理に使用するURL
                .loginProcessingUrl("/authentication")
                // usernameに使う変数はemail
                .usernameParameter("email")
                // passwordに使う変数はpassword
                .passwordParameter("password")
                // ログイン成功時に実行するメソッド
                .successHandler(successHandler)
                // ログイン失敗時に飛ばすURL
                .failureUrl("/login?error")
            )
            // ログアウト設定
            .logout(logout -> logout
                // ログアウトに使うURL
                .logoutUrl("/logout")
                // ログアウトに成功した時に飛ばすURL
                .logoutSuccessUrl("/login?logout")
                // ログアウト時にセッションを無効化
                .invalidateHttpSession(true)
                // Cookieの削除
                .deleteCookies("JSESSIONID")
            );
        
        return http.build();
    }
}

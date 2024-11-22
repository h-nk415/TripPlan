package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

/**
 * アプリケーションのセキュリティ設定を行うクラスです。
 * 
 * このクラスはSpring Securityを使用して、ユーザー認証や認可の設定を行います。
 * また、ログイン処理、ログアウト処理、静的リソースへのアクセス許可などを構成します。
 * 
 * @see CustomAuthenticationSuccessHandler ログイン成功時の処理
 * @see CustomUserDetailsService ユーザー情報の取得方法
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationSuccessHandler successHandler;
    private final CustomUserDetailsService customUserDetailsService;

    /**
     * {@link UserDetailsService} の Bean 定義です。
     * 
     * Spring Securityの認証処理で使用されるユーザー情報の取得方法を提供します。
     * カスタム実装の {@link CustomUserDetailsService} を利用します。
     * 
     * @return {@link CustomUserDetailsService} のインスタンス
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return customUserDetailsService;
    }

    /**
     * {@link DaoAuthenticationProvider} の Bean 定義です。
     * 
     * ユーザー情報の取得方法（{@link CustomUserDetailsService}）と
     * パスワードエンコーダ（BCryptPasswordEncoder）を組み合わせて、
     * Spring Securityでのユーザー認証を提供します。
     * 
     * @return {@link DaoAuthenticationProvider} のインスタンス
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService); // CustomUserDetailsService をセット
        provider.setPasswordEncoder(new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder()); // パスワードエンコーダー
        return provider;
    }

    /**
     * HTTPセキュリティ設定を行います。
     * 
     * このメソッドは、リクエストに対するアクセス制御や認証方法を設定します。
     * ログイン、ログアウト、静的リソースへのアクセスなどを設定します。
     * 
     * @param http {@link HttpSecurity} オブジェクトを使用してセキュリティ設定を行います
     * @return {@link SecurityFilterChain} セキュリティ設定を反映したフィルターチェーン
     * @throws Exception 設定中に発生する可能性のある例外
     */
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

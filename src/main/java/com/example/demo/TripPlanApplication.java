package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot アプリケーションのエントリーポイントとなるクラスです。
 * アプリケーションの起動を行います。
 */
@SpringBootApplication
public class TripPlanApplication {

    /**
     * アプリケーションの起動メソッドです。
     * Spring Boot アプリケーションを実行するためのメインメソッドです。
     * 
     * @param args コマンドライン引数
     */
    public static void main(String[] args) {
        SpringApplication.run(TripPlanApplication.class, args); // Spring Boot アプリケーションを起動
    }

}

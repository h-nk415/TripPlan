package com.example.demo.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 旅行のスケジュールを表すクラスです。
 * 各旅行先の訪問時間、場所、メモ、関連URLを管理します。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {

    /** 
     * スケジュールのID（主キー）
     */
    private Integer id;

    /** 
     * スケジュールの実施時間
     */
    private LocalDateTime scheduleTime;

    /** 
     * スケジュールの予定内容（例: "観光地を訪れる"）
     */
    private String event;

    /** 
     * スケジュールのメモ（補足情報）
     */
    private String memo;

    /** 
     * スケジュールに関連するURL（例: 観光地の公式サイトなど）
     */
    private String url;
    
    /** 
     * フラグ（例: 優先度やステータスを示す）
     */
    private String flag;

    /** 
     * このスケジュールに関連する旅行プラン
     */
    private Plan plan;  // Planエンティティへの参照
}

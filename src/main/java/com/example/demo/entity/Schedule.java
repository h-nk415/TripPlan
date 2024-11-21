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
     * 時間
     */
    private LocalDateTime scheduleTime;

    /** 
     * 予定内容
     */
    private String event;

    /** 
     * メモ
     */
    private String memo;

    /** 
     * 関連URL
     */
    private String url;
    
    private String flag;

    /** 
     * このスケジュールに関連する旅行プラン
     */
    private Plan plan;  // Planエンティティへの参照
}

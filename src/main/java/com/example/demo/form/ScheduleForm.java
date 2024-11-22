package com.example.demo.form;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.example.demo.entity.Plan;
import com.example.demo.entity.Schedule;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 旅行のスケジュールを管理するフォームクラスです。
 * このクラスは、旅行のスケジュール（日時、予定内容、メモ、URL）を管理し、ユーザーからの入力を受け取ります。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleForm {

    /** 
     * スケジュールのID
     */
    private Integer id;

    /** 
     * スケジュールの日時（必須）
     */
    @NotNull(message = "時間は必須です")
    private LocalDateTime scheduleTime;

    /** 
     * スケジュールの日時を文字列形式（yyyy-MM-dd'T'HH:mm）で保持するためのフィールド
     */
    private String scheduleTimeString;

    /** 
     * 予定している内容（必須）
     */
    @NotEmpty(message = "予定している内容は必須です")
    private String event;

    /** 
     * メモ（任意）
     */
    private String memo;

    /** 
     * URL（任意）
     */
    private String url;

    /** 
     * フラグ（任意）
     */
    private String flag;

    /** 
     * このスケジュールに関連する旅行プラン
     */
    private Plan plan;

    /**
     * ScheduleエンティティからScheduleFormを生成するコンストラクタ
     * @param schedule Scheduleエンティティ
     */
    public ScheduleForm(Schedule schedule) {
        this.id = schedule.getId();
        this.scheduleTime = schedule.getScheduleTime();
        this.scheduleTimeString = schedule.getScheduleTime() != null 
                ? schedule.getScheduleTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"))
                : null;
        this.event = schedule.getEvent();
        this.memo = schedule.getMemo();
        this.url = schedule.getUrl();
        this.plan = schedule.getPlan();
    }

    /**
     * scheduleTimeStringが設定されたときに、scheduleTimeを更新する。
     * @param scheduleTimeString スケジュール日時の文字列形式（yyyy-MM-dd'T'HH:mm）
     */
    public void setScheduleTimeString(String scheduleTimeString) {
        this.scheduleTimeString = scheduleTimeString;
        if (scheduleTimeString != null && !scheduleTimeString.isEmpty()) {
            this.scheduleTime = LocalDateTime.parse(scheduleTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        }
    }

    /**
     * scheduleTimeが設定されている場合に、その文字列形式（yyyy-MM-dd'T'HH:mm）を返す。
     * @return スケジュール日時の文字列形式
     */
    public String getScheduleTimeString() {
        if (this.scheduleTime != null) {
            return this.scheduleTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        }
        return this.scheduleTimeString;
    }
}

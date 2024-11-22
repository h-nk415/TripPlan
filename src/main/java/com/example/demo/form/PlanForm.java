package com.example.demo.form;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.example.demo.entity.Plan;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 旅行プランを管理するフォームクラスです。
 * このクラスは、旅行プランの基本情報（タイトル、詳細、日付、行き先）を管理し、ユーザーからの入力を受け取ります。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanForm {

    /**
     * コンストラクタ。PlanエンティティからPlanFormを生成します。
     * @param plan 旅行プランのエンティティオブジェクト
     */
    public PlanForm(Plan plan) {
        this.id = plan.getId();
        this.title = plan.getTitle();
        this.titleDetail = plan.getTitleDetail();
        this.startDate = plan.getStartDate();
        this.endDate = plan.getEndDate();
        this.destination1 = plan.getDestination1();
        this.destination2 = plan.getDestination2();
        this.destination3 = plan.getDestination3();
    }

    private Integer id;

    /** 
     * 旅行プランのタイトル
     * タイトルは必須で、1文字以上50文字以内で入力可能です。
     */
    @NotNull(message = "タイトルは必須です。")
    @Size(min = 1, max = 50, message = "タイトルは1文字以上50文字以内で入力してください。")
    private String title;

    /** 
     * 旅行プランの詳細情報
     * 詳細は必須で、1文字以上255文字以内で入力可能です。
     */
    @NotNull(message = "詳細は必須です。")
    @Size(min = 1, max = 255, message = "詳細は1文字以上255文字以内で入力してください。")
    private String titleDetail;

    /** 
     * 旅行の開始日
     * 開始日は必須です。
     */
    @NotNull(message = "開始日は必須です。")
    private LocalDate startDate;

    /** 
     * 旅行の終了日
     * 終了日は必須です。
     */
    @NotNull(message = "終了日は必須です。")
    private LocalDate endDate;

    /** 
     * 旅行先の1つ目の行き先
     * 行き先1は必須です。
     */
    @NotNull(message = "1つ目の行き先は必須です。")
    private String destination1;

    /** 
     * 旅行先の2つ目の行き先
     * 行き先2は任意です。
     */
    private String destination2;

    /** 
     * 旅行先の3つ目の行き先
     * 行き先3は任意です。
     */
    private String destination3;

    private Integer usersId;

    /** 
     * 開始日を文字列形式で保持するためのフィールド（yyyy-MM-dd形式）
     */
    private String startDateString;

    /** 
     * 終了日を文字列形式で保持するためのフィールド（yyyy-MM-dd形式）
     */
    private String endDateString;

    /**
     * startDateStringが設定されたときに、startDateも更新します。
     * 
     * @param startDateString 開始日を示す文字列（yyyy-MM-dd形式）
     */
    public void setStartDateString(String startDateString) {
        this.startDateString = startDateString;
        if (startDateString != null && !startDateString.isEmpty()) {
            this.startDate = LocalDate.parse(startDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
    }

    /**
     * endDateStringが設定されたときに、endDateも更新します。
     * 
     * @param endDateString 終了日を示す文字列（yyyy-MM-dd形式）
     */
    public void setEndDateString(String endDateString) {
        this.endDateString = endDateString;
        if (endDateString != null && !endDateString.isEmpty()) {
            this.endDate = LocalDate.parse(endDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
    }

    /**
     * startDateが設定されている場合に、その文字列形式（yyyy-MM-dd）を返します。
     * 
     * @return startDateの文字列形式
     */
    public String getStartDateString() {
        if (this.startDate != null) {
            return this.startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        return this.startDateString;
    }

    /**
     * endDateが設定されている場合に、その文字列形式（yyyy-MM-dd）を返します。
     * 
     * @return endDateの文字列形式
     */
    public String getEndDateString() {
        if (this.endDate != null) {
            return this.endDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        return this.endDateString;
    }
}

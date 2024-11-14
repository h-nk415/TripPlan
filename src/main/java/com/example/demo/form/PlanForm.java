package com.example.demo.form;


import java.time.LocalDate;

import com.example.demo.entity.Plan;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 旅行プランを管理するフォームクラス
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanForm {
	
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
     */
    @NotNull(message = "タイトルは必須です。")
    @Size(min = 1, max = 255, message = "タイトルは1文字以上255文字以内で入力してください。")
    private String title;

    /** 
     * 旅行プランの詳細情報
     */
    @NotNull(message = "詳細は必須です。")
    @Size(min = 1, max = 255, message = "詳細は1文字以上255文字以内で入力してください。")
    private String titleDetail;

    /** 
     * 旅行の開始日
     */
    @NotNull(message = "開始日は必須です。")
    private LocalDate startDate;

    /** 
     * 旅行の終了日
     */
    @NotNull(message = "終了日は必須です。")
    private LocalDate endDate;

    /** 
     * 旅行先の1つ目の行き先
     */
    @NotNull(message = "1つ目の行き先は必須です。")
    private String destination1;

    /** 
     * 旅行先の2つ目の行き先
     */
    private String destination2;

    /** 
     * 旅行先の3つ目の行き先
     */
    private String destination3;
}

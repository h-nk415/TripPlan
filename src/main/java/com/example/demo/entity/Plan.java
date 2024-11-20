package com.example.demo.entity;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 旅行プランを表すクラスです。
 * 旅行のタイトル、詳細、開始日、終了日、行先を管理します。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plan {
	
	private List<Schedule> schedules;
	
	private List<Item> items;
	
	private List<Todo> todos;
	
    /** 
     * 旅行プランのID（主キー）
     */
    private Integer id;

    /** 
     * 旅行プランのタイトル
     */
    private String title;

    /** 
     * 旅行プランの詳細情報
     */
    private String titleDetail;

    /** 
     * 旅行の開始日
     */
    private LocalDate startDate;

    /** 
     * 旅行の終了日
     */
    private LocalDate endDate;

    /** 
     * 旅行先の1つ目の行き先
     */
    private String destination1;

    /** 
     * 旅行先の2つ目の行き先
     */
    private String destination2;

    /** 
     * 旅行先の3つ目の行き先
     */
    private String destination3;
    
    private String iconImage;

}
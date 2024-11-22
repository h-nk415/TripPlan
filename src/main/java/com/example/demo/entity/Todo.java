package com.example.demo.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 旅行のTodoリストのアイテムを表すクラスです。
 * 旅行準備のために行うべきタスクやその完了状態を管理します。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

    /** 
     * TodoアイテムのID（主キー）
     */
    private Integer id;

    /** 
     * Todoアイテムの内容（例: "パスポートを準備する"）
     */
    private String task;

    /** 
     * Todoアイテムが完了したかどうか
     */
    private Boolean completed;
    
    /** 
     * Todoアイテムの作成日時
     */
    private LocalDateTime createdAt;

    /** 
     * このTodoアイテムが関連する旅行プラン
     */
    private Plan plan;  // Planエンティティへの参照
}

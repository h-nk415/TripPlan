package com.example.demo.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 旅行のTodoリストのアイテムを表すクラスです。
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
    
    private LocalDateTime createdAt;

    /** 
     * このTodoアイテムが関連する旅行プラン（外部キー）
     */
    private Plan planId;  // Planエンティティへの参照
}

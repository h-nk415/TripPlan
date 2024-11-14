package com.example.demo.form;

import com.example.demo.entity.Plan;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 旅行のTodoリストを扱うフォームクラスです。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoForm {

    /** 
     * Todoの内容（例: "パスポートを準備する"）
     */
    @NotEmpty(message = "タスク内容は必須です")
    private String task;

    /** 
     * Todoが完了したかどうか（チェックボックス）
     */
    private Boolean completed;

    /** 
     * このTodoが関連する旅行プランのID
     */
    private Plan planId;  // 外部キーとしてPlanのIDを持つ
}

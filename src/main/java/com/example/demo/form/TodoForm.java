package com.example.demo.form;

import com.example.demo.entity.Plan;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 旅行のTodoリストを扱うフォームクラスです。
 * このクラスは、Todoリストのアイテム（タスク内容、完了状態）を管理し、ユーザーからの入力を受け取ります。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoForm {

    /** 
     * Todoの内容（例: "パスポートを準備する"）
     * タスク内容は必須で、最大50文字まで入力可能です。
     */
    @NotEmpty(message = "タスク内容は必須です")
    @Size(max = 50, message = "タスク名は50文字以内で入力してください")
    private String task;

    /** 
     * Todoが完了したかどうか（チェックボックス）
     * 完了した場合はtrue、それ以外はfalse。
     */
    private Boolean completed;

    /** 
     * このTodoが関連する旅行プランのID
     * 外部キーとしてPlanエンティティとの関連を持ちます。
     */
    private Plan plan;  // 外部キーとしてPlanのIDを持つ
}

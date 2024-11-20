package com.example.demo.form;

import com.example.demo.entity.Plan;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 旅行の持ち物アイテムを扱うフォームクラスです。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemForm {

    /** 
     * アイテム名
     */
    @NotEmpty(message = "アイテム名は必須です")
    @Size(max = 50, message = "アイテム名は50文字以内で入力してください")
    private String name;

    /** 
     * アイテムの個数
     */
    @NotNull(message = "個数は必須です")
    @Max(value = 100, message = "個数は100個以内で入力してください")
    private Integer quantity = 1;

    /** 
     * アイテムが確認されたかどうか（チェックボックス）
     */
    private Boolean checked;

    /** 
     * このアイテムが関連する旅行プランのID
     */
    private Plan plan;  // 外部キーとしてPlanのIDを持つ
}

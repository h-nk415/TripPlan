package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 持ち物リストに関連するアイテムを表すクラスです。
 * 各アイテムの名前、数量、チェック状態、および関連する旅行プランを管理します。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    /**
     * アイテムのID（主キー）
     */
    private Integer id;

    /**
     * アイテムの名前
     */
    private String name;

    /**
     * アイテムの数量
     */
    private Integer quantity;

    /**
     * アイテムのチェック状態（持っていくかどうか）
     */
    private Boolean checked;

    /**
     * アイテムが関連する旅行プラン
     */
    private Plan plan;  // Planエンティティへの参照
}

package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.entity.Item;

/**
 * 旅行の持ち物アイテムに関するデータベース操作を行うマッパーインターフェイスです。
 * アイテムの登録、取得、更新、削除などのCRUD操作を定義しています。
 */
@Mapper
public interface ItemMapper {

    /**
     * 新しいアイテムをデータベースに挿入します。
     * 
     * @param item 登録するアイテムのエンティティ
     */
    void insertItem(Item item);

    /**
     * 指定されたIDに対応するアイテムを取得します。
     * 
     * @param id 取得したいアイテムのID
     * @return 指定されたIDに対応するアイテムのエンティティ
     */
    Item selectItemById(@Param("id") Integer id);

    /**
     * 指定されたアイテムの情報を更新します。
     * 
     * @param item 更新するアイテムのエンティティ
     */
    void updateItem(Item item);

    /**
     * 指定されたIDに対応するアイテムをデータベースから削除します。
     * 
     * @param id 削除したいアイテムのID
     */
    void deleteItemById(@Param("id") Integer id);

    /**
     * 指定された旅行プランIDに関連するアイテムのリストを取得します。
     * 
     * @param planId 取得したいアイテムの旅行プランID
     * @return 指定された旅行プランIDに関連するアイテムのリスト
     */
    List<Item> selectItemsByPlanId(@Param("planId") Integer planId);
}

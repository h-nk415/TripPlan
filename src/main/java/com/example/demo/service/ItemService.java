package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Item;
import com.example.demo.repository.ItemMapper;

import lombok.RequiredArgsConstructor;

/**
 * アイテムに関するビジネスロジックを処理するサービスクラスです。
 * アイテムの作成、取得、更新、削除などの処理を行います。
 */
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemMapper itemMapper;

    /**
     * 新しいアイテムを作成します。
     * 
     * @param item 作成するアイテムのエンティティ
     */
    public void createItem(Item item) {
        itemMapper.insertItem(item); // Mapperを通じてDBに挿入
    }

    /**
     * 指定されたIDのアイテムを取得します。
     * 
     * @param id 取得したいアイテムのID
     * @return 取得したアイテムのエンティティ
     */
    public Item getItemById(Integer id) {
        return itemMapper.selectItemById(id);
    }

    /**
     * 指定された旅行プランに関連するすべてのアイテムを取得します。
     * 
     * @param planId 旅行プランのID
     * @return 旅行プランに関連するアイテムのリスト
     */
    public List<Item> getItemsByPlanId(Integer planId) {
        return itemMapper.selectItemsByPlanId(planId);
    }

    /**
     * 指定されたアイテムの情報を更新します。
     * 
     * @param item 更新するアイテムのエンティティ
     */
    public void updateItem(Item item) {
        itemMapper.updateItem(item);
    }

    /**
     * 指定されたIDのアイテムを削除します。
     * 
     * @param id 削除したいアイテムのID
     */
    public void deleteItem(Integer id) {
        itemMapper.deleteItemById(id);
    }
}

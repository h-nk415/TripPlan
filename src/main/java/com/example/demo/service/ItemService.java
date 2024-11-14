package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Item;
import com.example.demo.repository.ItemMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemMapper itemMapper;

 // アイテム作成処理
    public void createItem(Item item) {
        itemMapper.insertItem(item); // Mapperを通じてDBに挿入
    }

    public Item getItemById(Integer id) {
        return itemMapper.selectItemById(id);
    }

    public List<Item> getItemsByPlanId(Integer planId) {
        return itemMapper.selectItemsByPlanId(planId);
    }

    

    public void updateItem(Item item) {
        itemMapper.updateItem(item);
    }

    public void deleteItem(Integer id) {
        itemMapper.deleteItemById(id);
    }
}

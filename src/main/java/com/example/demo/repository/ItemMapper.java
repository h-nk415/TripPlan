package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.entity.Item;

@Mapper
public interface ItemMapper {

    void insertItem(Item item);

    Item selectItemById(@Param("id") Integer id);

    void updateItem(Item item);

    void deleteItemById(@Param("id") Integer id);

    List<Item> selectItemsByPlanId(@Param("planId") Integer planId);

    
}

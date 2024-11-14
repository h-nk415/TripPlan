package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Item;
import com.example.demo.service.ItemService;
import com.example.demo.service.PlanService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    private final PlanService planService;

    // アイテム作成処理
    @PostMapping("/create")
    public String createItem(@ModelAttribute Item item, @RequestParam("planId") Integer planId) {
        // planIdを設定
        item.setPlan(planService.getPlanById(planId));
        item.setChecked(false);
        itemService.createItem(item); // アイテムを作成するメソッドを呼び出す
        return "redirect:/plans/" + planId; // プラン詳細ページにリダイレクト
    }

    // アイテムのチェックボックスの状態を反転
    @PostMapping("/check/{id}")
    public String checkItem(@PathVariable Integer id) {
        Item item = itemService.getItemById(id);
        item.setChecked(!item.getChecked()); // チェック状態を反転
        itemService.updateItem(item); // 更新を保存
        return "redirect:/plans/" + item.getPlan().getId(); // プラン詳細ページにリダイレクト
    }

    // アイテムの削除処理
    @PostMapping("/delete/{id}")
    public String deleteItem(@PathVariable Integer id) {
        Item item = itemService.getItemById(id);
        Integer planId = item.getPlan().getId();
        itemService.deleteItem(id); // アイテム削除
        return "redirect:/plans/" + planId; // プラン詳細ページにリダイレクト
    }

    
}

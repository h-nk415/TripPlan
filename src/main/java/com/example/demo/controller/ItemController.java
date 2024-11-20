package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Item;
import com.example.demo.entity.Plan;
import com.example.demo.form.ItemForm;
import com.example.demo.service.ItemService;
import com.example.demo.service.PlanService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    private final PlanService planService;

    @GetMapping("/entry/{id}")
	public String entryItem(@PathVariable("id") int planId, @ModelAttribute ItemForm itemForm, Model model) {
		Plan plan = planService.getPlanDetails(planId);
		itemForm.setPlan(plan);

		model.addAttribute("plan", plan);  
		model.addAttribute("itemForm", itemForm);

		return "itemForm";
	}
    
    
    @PostMapping("/create")
    public String createItem(@ModelAttribute @Valid ItemForm itemForm, BindingResult bindingResult, Model model, @RequestParam("planId") Integer planId) {
        if (bindingResult.hasErrors()) {
            Plan plan = planService.getPlanById(planId);
            model.addAttribute("plan", plan);
            return "itemForm"; // エラーがあれば再表示
        }

        // ItemFormからItemエンティティにデータを変換
        Item item = new Item();
        item.setName(itemForm.getName());
        item.setQuantity(itemForm.getQuantity());
        item.setChecked(false);
        item.setPlan(planService.getPlanById(planId)); // planIdを設定
        itemService.createItem(item); // アイテムを作成するメソッドを呼び出す

        return "redirect:/items/entry/" + planId; // プラン詳細ページにリダイレクト
    }

    // アイテムのチェックボックスの状態を反転
    @PostMapping("/check/{id}")
    public String checkItem(@PathVariable Integer id) {
        Item item = itemService.getItemById(id);
        item.setChecked(!item.getChecked()); // チェック状態を反転
        itemService.updateItem(item); // 更新を保存
        return "redirect:/items/entry/" + item.getPlan().getId(); // プラン詳細ページにリダイレクト
    }

    // アイテムの削除処理
    @PostMapping("/delete/{id}")
    public String deleteItem(@PathVariable Integer id) {
        Item item = itemService.getItemById(id);
        Integer planId = item.getPlan().getId();
        itemService.deleteItem(id); // アイテム削除
        return "redirect:/items/entry/" + planId; // プラン詳細ページにリダイレクト
    }

    
}

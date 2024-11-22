package com.example.demo.controller;

import java.util.List;

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

/**
 * アイテムに関連する操作を管理するコントローラクラスです。
 * アイテムの作成、削除、チェック状態の変更などを処理します。
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    private final PlanService planService;

    /**
     * アイテムの入力フォームを表示します。
     * 特定のプランに関連するアイテムを取得し、フォームに渡します。
     * 
     * @param planId プランのID
     * @param itemForm アイテム入力フォーム
     * @param model モデル
     * @return アイテム入力フォームのビュー名
     */
    @GetMapping("/entry/{id}")
    public String entryItem(@PathVariable("id") int planId, @ModelAttribute ItemForm itemForm, Model model) {
        // プランに関連するアイテムを取得
        List<Item> items = itemService.getItemsByPlanId(planId);
        Plan plan = planService.getPlanById(planId);

        model.addAttribute("plan", plan);  
        model.addAttribute("items", items);

        return "itemForm"; // アイテムフォームを表示
    }

    /**
     * 新しいアイテムを作成します。
     * 入力されたアイテム情報を元にデータベースに保存します。
     * 
     * @param itemForm アイテム入力フォーム
     * @param bindingResult バリデーション結果
     * @param model モデル
     * @param planId プランのID
     * @return アイテム作成後、プラン詳細ページへリダイレクト
     */
    @PostMapping("/create")
    public String createItem(@ModelAttribute @Valid ItemForm itemForm, BindingResult bindingResult, Model model, @RequestParam("planId") Integer planId) {
        // 入力内容にエラーがある場合、再表示
        if (bindingResult.hasErrors()) {
            Plan plan = planService.getPlanById(planId);
            model.addAttribute("plan", plan);
            return "itemForm"; // エラーがあれば再表示
        }

        // ItemFormからItemエンティティに変換
        Item item = new Item();
        item.setName(itemForm.getName());
        item.setQuantity(itemForm.getQuantity());
        item.setChecked(false); // 初期状態はチェックされていない
        item.setPlan(planService.getPlanById(planId)); // プランを設定
        itemService.createItem(item); // アイテム作成

        return "redirect:/items/entry/" + planId; // プラン詳細ページへリダイレクト
    }

    /**
     * アイテムのチェックボックスの状態を反転させます。
     * チェックされている場合はチェックを外し、チェックされていない場合はチェックを入れます。
     * 
     * @param id アイテムのID
     * @return アイテムを更新した後、プラン詳細ページへリダイレクト
     */
    @PostMapping("/check/{id}")
    public String checkItem(@PathVariable Integer id) {
        // アイテムを取得し、チェック状態を反転
        Item item = itemService.getItemById(id);
        item.setChecked(!item.getChecked()); // チェック状態を反転
        itemService.updateItem(item); // 更新を保存

        return "redirect:/items/entry/" + item.getPlan().getId(); // プラン詳細ページへリダイレクト
    }

    /**
     * アイテムを削除します。
     * 指定されたアイテムを削除し、元のプラン詳細ページにリダイレクトします。
     * 
     * @param id アイテムのID
     * @return アイテム削除後、プラン詳細ページへリダイレクト
     */
    @PostMapping("/delete/{id}")
    public String deleteItem(@PathVariable Integer id) {
        // アイテムを削除
        Item item = itemService.getItemById(id);
        Integer planId = item.getPlan().getId();
        itemService.deleteItem(id); // アイテム削除

        return "redirect:/items/entry/" + planId; // プラン詳細ページへリダイレクト
    }
}

package com.example.demo.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Plan;
import com.example.demo.form.PlanForm;
import com.example.demo.service.PlanService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * プランに関するAPIエンドポイントを提供するコントローラークラスです。
 * プランの作成、取得、更新、削除を行うエンドポイントを含んでいます。
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/plans")
public class PlanController {

    /** DI */
    private final PlanService planService;
    
    
    /**
     * ホーム画面（プラン一覧）を表示します。
     *
     * @param model すべてのプランを格納するModelオブジェクト
     * @return ホーム画面のビュー名
     */
    @GetMapping("/home")
    public String home(Model model) {
    	List<Plan> plans = planService.getAllPlans();
    	model.addAttribute("plans", plans);
    	return "home"; // planList.htmlを返す
    }
    
    /**
     * 指定されたIDに対応するプランを取得し、Modelに追加します。
     *
     * @param id 取得したいプランのID
     * @param model プランを格納するModelオブジェクト
     * @return プラン詳細画面のビュー名
     */
    @GetMapping("/{id}")
    public String getPlanDetails(@PathVariable Integer id, Model model) {
    	Plan plan = planService.getPlanDetails(id);
    	model.addAttribute("plan", plan);
    	return "detail";  // Detail.html というビュー名
    }

    @GetMapping("/entry")
	public String entryPlan(@ModelAttribute PlanForm planForm, Model model) {

		return "planForm"; // planフォームへ遷移
	}
    
    /**
     * 新しいプランを作成します。
     *
     * @param plan 作成するプランの情報を含むオブジェクト
     * @return プラン作成後のリダイレクトURL
     */
    @PostMapping("/create")
    public String createPlan(@ModelAttribute @Valid PlanForm planForm, BindingResult bindingResult, Model model) {
    	
    	// 開始日と終了日を比較して、終了日が開始日より前ならエラーを追加
        if (planForm.getStartDate() != null && planForm.getEndDate() != null &&
                planForm.getEndDate().isBefore(planForm.getStartDate())) {
            bindingResult.rejectValue("endDate", "error.endDate", "終了日は開始日より後でなければなりません。");
        }
        
     // 目的地の重複チェック（選択された目的地のみ）
        Set<String> selectedDestinations = new HashSet<>();
        if (planForm.getDestination1() != null && !planForm.getDestination1().isEmpty()) {
            selectedDestinations.add(planForm.getDestination1());
        }
        if (planForm.getDestination2() != null && !planForm.getDestination2().isEmpty()) {
            if (!selectedDestinations.add(planForm.getDestination2())) {
                bindingResult.rejectValue("destination2", "error.destination", "目的地は異なるものを選んでください。");
            }
        }
        if (planForm.getDestination3() != null && !planForm.getDestination3().isEmpty()) {
            if (!selectedDestinations.add(planForm.getDestination3())) {
                bindingResult.rejectValue("destination3", "error.destination", "目的地は異なるものを選んでください。");
            }
        }
        
        if (bindingResult.hasErrors()) {
            return "planForm"; // エラーがあれば再表示
        }

        Plan plan = new Plan();
        plan.setTitle(planForm.getTitle());
        plan.setTitleDetail(planForm.getTitleDetail());
        plan.setStartDate(planForm.getStartDate());
        plan.setEndDate(planForm.getEndDate());
        plan.setDestination1(planForm.getDestination1());
        plan.setDestination2(planForm.getDestination2());
        plan.setDestination3(planForm.getDestination3());
        
        model.addAttribute("plan", plan);

        // ここでplanをデータベースに保存する処理を追加する
        planService.createPlan(plan);

        return "redirect:/plans/home"; // 保存後は一覧ページへリダイレクト
    }



    
 // PlanController.java
    @GetMapping("/edit/{id}")
    public String editPlan(@PathVariable Integer id, Model model) {
        Plan plan = planService.getPlanById(id); // サービス層でプランを取得
        PlanForm planForm = new PlanForm(plan); // プランからフォーム用オブジェクトを作成
        model.addAttribute("planForm", planForm);
        return "planUpdate"; // planUpdate.htmlが表示される
    }



    /**
     * プランの情報を更新します。
     *
     * @param id 更新するプランのID
     * @param planForm 更新するプランの情報を含むオブジェクト
     * @return 更新後のリダイレクトURL
     */
    @PostMapping("/update/{id}")
    public String updatePlan(@PathVariable Integer id, @ModelAttribute @Valid PlanForm planForm , BindingResult bindingResult, Model model) {
    	
    	// 開始日と終了日を比較して、終了日が開始日より前ならエラーを追加
        if (planForm.getStartDate() != null && planForm.getEndDate() != null &&
                planForm.getEndDate().isBefore(planForm.getStartDate())) {
            bindingResult.rejectValue("endDate", "error.endDate", "終了日は開始日より後でなければなりません。");
        }
    	
     // 目的地の重複チェック（選択された目的地のみ）
        Set<String> selectedDestinations = new HashSet<>();
        if (planForm.getDestination1() != null && !planForm.getDestination1().isEmpty()) {
            selectedDestinations.add(planForm.getDestination1());
        }
        if (planForm.getDestination2() != null && !planForm.getDestination2().isEmpty()) {
            if (!selectedDestinations.add(planForm.getDestination2())) {
                bindingResult.rejectValue("destination2", "error.destination", "目的地は異なるものを選んでください。");
            }
        }
        if (planForm.getDestination3() != null && !planForm.getDestination3().isEmpty()) {
            if (!selectedDestinations.add(planForm.getDestination3())) {
                bindingResult.rejectValue("destination3", "error.destination", "目的地は異なるものを選んでください。");
            }
        }
        
        
    	if (bindingResult.hasErrors()) {
            return "planForm"; // エラーがあれば再表示
        }

    	
        Plan plan = planService.getPlanById(id); // 既存のプランを取得
        plan.setId(id); // idをセット（フォームから送られてこないので手動で設定）
        plan.setTitle(planForm.getTitle());
        plan.setTitleDetail(planForm.getTitleDetail());
        plan.setStartDate(planForm.getStartDate());
        plan.setEndDate(planForm.getEndDate());
        plan.setDestination1(planForm.getDestination1());
        plan.setDestination2(planForm.getDestination2());
        plan.setDestination3(planForm.getDestination3());
        
        model.addAttribute("planForm", plan);

        planService.updatePlan(plan); // 更新処理を実行
        return "redirect:/plans/home"; // 更新後、リダイレクト
    }

    /**
     * 指定されたIDのプランを削除します。
     *
     * @param id 削除するプランのID
     * @return 削除後のリダイレクトURL
     */
    @PostMapping("/delete/{id}")
    public String deletePlan(@PathVariable Integer id) {
        planService.deletePlan(id);
        return "redirect:/plans/home";
    }
}

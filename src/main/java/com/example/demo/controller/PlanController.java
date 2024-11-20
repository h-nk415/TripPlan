package com.example.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Plan;
import com.example.demo.form.ItemForm;
import com.example.demo.form.PlanForm;
import com.example.demo.form.TodoForm;
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

    // ホーム画面（プラン一覧）を表示します
    @GetMapping("/home")
    public String home(Model model) {
        List<Plan> plans = planService.getAllPlans();
        model.addAttribute("plans", plans);
        return "home"; // planList.htmlを返す
    }

    // プラン詳細画面の取得
    @GetMapping("/{id}")
    public String getPlanDetails(@PathVariable Integer id, Model model) {
        Plan plan = planService.getPlanDetails(id);
        ItemForm itemForm = new ItemForm();
        TodoForm todoForm = new TodoForm();
        model.addAttribute("plan", plan);
        model.addAttribute("itemForm", itemForm);
        model.addAttribute("todoForm", todoForm);
        return "detail";  // Detail.html というビュー名
    }

    // プラン作成フォームへの遷移
    @GetMapping("/entry")
    public String entryPlan(@ModelAttribute PlanForm planForm, Model model) {
        return "planForm"; // planフォームへ遷移
    }

    // プランの新規作成
    @PostMapping("/create")
    public String createPlan(@ModelAttribute @Valid PlanForm planForm, BindingResult bindingResult, Model model, @RequestParam(value = "iconImage", required = false) MultipartFile iconImage, RedirectAttributes redirectAttributes) {
        // バリデーション（開始日、終了日、目的地など）
        if (planForm.getStartDate() != null && planForm.getEndDate() != null &&
                planForm.getEndDate().isBefore(planForm.getStartDate())) {
            bindingResult.rejectValue("endDate", "error.endDate", "終了日は開始日より後でなければなりません。");
        }

        // 目的地の重複チェック
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

        // バリデーションエラーがある場合、再表示
        if (bindingResult.hasErrors()) {
            return "planForm";
        }

        // Planオブジェクトを作成
        Plan plan = new Plan();
        plan.setTitle(planForm.getTitle());
        plan.setTitleDetail(planForm.getTitleDetail());
        plan.setStartDate(planForm.getStartDate());
        plan.setEndDate(planForm.getEndDate());
        plan.setDestination1(planForm.getDestination1());
        if (planForm.getDestination2() == null || planForm.getDestination2().isEmpty()) {
        	planForm.setDestination2(null); // 明示的にnullを設定
        }
        
        if (planForm.getDestination3() == null || planForm.getDestination3().isEmpty()) {
        	planForm.setDestination3(null); // 明示的にnullを設定
        }
        
        plan.setDestination2(planForm.getDestination2());
        plan.setDestination3(planForm.getDestination3());
        

        // アイコン画像の保存処理（画像が選択されている場合のみ）
        if (iconImage != null && !iconImage.isEmpty()) {
            try {
                // ファイル名を一意にするため、タイムスタンプを付ける
                String fileName = System.currentTimeMillis() + "-" + iconImage.getOriginalFilename();
                
                // 保存先ディレクトリの設定
                String uploadDir = "src/main/resources/static/uploads";
                Path uploadPath = Paths.get(uploadDir);
                
                
                
                // 画像を保存する
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(iconImage.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);  // ファイルを指定した場所に保存
                
                // 画像のパスをプランにセット
                plan.setIconImage(fileName);

            } catch (IOException e) {
                e.printStackTrace();  // 例外処理
                bindingResult.rejectValue("iconImage", "error.iconImage", "アイコン画像の保存中にエラーが発生しました。");
            }
        }

        // データベースにプランを保存
        planService.createPlan(plan);
        
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        

        return "redirect:/plans/home"; // 保存後は一覧ページへリダイレクト
    }



    // プラン編集画面
    @GetMapping("/edit/{id}")
    public String editPlan(@PathVariable("id") Integer id, Model model) {
        // IDを基にデータを取得（仮）
        Plan plan = planService.getPlanById(id);

        // PlanFormにデータをセット
        PlanForm planForm = new PlanForm(plan);

        // 都道府県リストも渡す
        model.addAttribute("planForm", planForm);
        model.addAttribute("prefectures", getPrefectures());  // 都道府県リストを渡す

        return "planUpdate";
    }

    private List<String> getPrefectures() {
        return Arrays.asList("北海道", "青森", "岩手", "宮城", "秋田", "山形", "福島", "東京", "神奈川", "千葉", "埼玉", "茨城", "栃木", "群馬", "新潟", "富山", "石川", "福井", "山梨", "長野", "岐阜", "静岡", "愛知", "三重", "滋賀", "京都", "大阪", "兵庫", "奈良", "和歌山", "鳥取", "島根", "岡山", "広島", "山口", "徳島", "香川", "愛媛", "高知", "福岡", "佐賀", "長崎", "熊本", "大分", "宮崎", "鹿児島", "沖縄");
    }

    @PostMapping("/update/{id}")
    public String updatePlan(@PathVariable Integer id, 
                             @ModelAttribute @Valid PlanForm planForm,
                             BindingResult bindingResult,
                             @RequestParam("iconImage") MultipartFile iconImage,
                             Model model, RedirectAttributes redirectAttributes) {

        // バリデーションチェック
        if (planForm.getStartDate() != null && planForm.getEndDate() != null &&
                planForm.getEndDate().isBefore(planForm.getStartDate())) {
            bindingResult.rejectValue("endDate", "error.endDate", "終了日は開始日より後でなければなりません。");
        }

        // 目的地の重複チェック
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
            model.addAttribute("planForm", planForm);  // エラー情報をフォームに戻す
            return "planUpdate";  // バリデーションエラーがあればそのまま戻る
        }

        // Planの更新処理
        Plan plan = planService.getPlanById(id);
        plan.setTitle(planForm.getTitle());
        plan.setTitleDetail(planForm.getTitleDetail());
        plan.setStartDate(planForm.getStartDate());
        plan.setEndDate(planForm.getEndDate());
        plan.setDestination1(planForm.getDestination1());
        if (planForm.getDestination2() == null || planForm.getDestination2().isEmpty()) {
            planForm.setDestination2(null); // 明示的にnullを設定
        }

        if (planForm.getDestination3() == null || planForm.getDestination3().isEmpty()) {
            planForm.setDestination3(null); // 明示的にnullを設定
        }

        // 画像の更新（新しい画像がアップロードされた場合のみ処理）
        if (!iconImage.isEmpty()) {
            try {
                // 既存の画像を削除
                if (plan.getIconImage() != null) {
                    Path existingFilePath = Paths.get("src/main/resources/static/uploads", plan.getIconImage());
                    Files.deleteIfExists(existingFilePath);
                }

                // 新しい画像を保存
                String fileName = System.currentTimeMillis() + "-" + iconImage.getOriginalFilename();
                String uploadDir = "src/main/resources/static/uploads";  // configにより変更可能
                Path uploadPath = Paths.get(uploadDir);
                Files.createDirectories(uploadPath);

                Path filePath = uploadPath.resolve(fileName);
                Files.copy(iconImage.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                plan.setIconImage(fileName);

            } catch (IOException e) {
                e.printStackTrace();
                bindingResult.rejectValue("iconImage", "error.iconImage", "アイコン画像の保存中にエラーが発生しました。");
                model.addAttribute("planForm", planForm);  // エラー情報をフォームに戻す
                return "planUpdate";  // エラーがあれば再度フォームへ
            }
        }

        // Planを保存
        planService.updatePlan(plan);
        
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // 更新後、ホームページにリダイレクト
        return "redirect:/plans/home";
    }
    
    @PostMapping("/delete/{id}")
    public String deletePlan(@PathVariable Integer id) {
        planService.deletePlan(id);
        return "redirect:/plans/home";
    }
}

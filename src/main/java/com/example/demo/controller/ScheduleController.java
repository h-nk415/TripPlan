package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Plan;
import com.example.demo.entity.Schedule;
import com.example.demo.form.ScheduleForm;
import com.example.demo.service.PlanService;
import com.example.demo.service.ScheduleService;

import lombok.RequiredArgsConstructor;

/**
 * スケジュールに関する操作を管理するコントローラークラスです。
 * スケジュールの作成、取得、更新、削除を行います。
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    /** スケジュール関連のサービス */
    private final ScheduleService scheduleService;

    /** プラン関連のサービス */
    private final PlanService planService;

    /**
     * プランに基づいたスケジュール作成フォームを表示します。
     * 
     * @param planId プランID
     * @param scheduleForm スケジュールフォーム
     * @param model 画面にデータを渡すためのモデル
     * @return スケジュール作成フォームのビュー名
     */
    @GetMapping("/entry/{id}")
    public String entrySchedule(@PathVariable("id") int planId, @ModelAttribute ScheduleForm scheduleForm, Model model) {
        Plan plan = planService.getPlanDetails(planId);
        scheduleForm.setPlan(plan);

        model.addAttribute("plan", plan);
        model.addAttribute("scheduleForm", scheduleForm);

        return "scheduleForm"; // scheduleForm.htmlを返す
    }

    /**
     * 新しいスケジュールを作成します。
     * 
     * @param scheduleForm スケジュールの情報を含むフォーム
     * @return 作成したスケジュールに関連するプランの詳細画面へのリダイレクトURL
     */
    @PostMapping("/create")
    public String createSchedule(@ModelAttribute ScheduleForm scheduleForm) {
        // フォームから送信されたプランIDを使って完全なPlanオブジェクトを取得
        Plan completePlan = planService.getPlanDetails(scheduleForm.getPlan().getId());

        Schedule schedule = new Schedule();
        schedule.setPlan(completePlan); // 完全なPlanオブジェクトをセット
        schedule.setScheduleTime(scheduleForm.getScheduleTime());
        schedule.setEvent(scheduleForm.getEvent());
        schedule.setMemo(scheduleForm.getMemo());
        schedule.setUrl(scheduleForm.getUrl());
        schedule.setFlag(scheduleForm.getFlag()); // flagを設定

        scheduleService.createSchedule(schedule); // スケジュールを作成

        return "redirect:/plans/" + completePlan.getId(); // プラン詳細画面にリダイレクト
    }

    /**
     * 指定されたIDのスケジュールを編集するためのフォームを表示します。
     * 
     * @param id 編集するスケジュールのID
     * @param model 画面にデータを渡すためのモデル
     * @return スケジュール編集フォームのビュー名
     */
    @GetMapping("/edit/{id}")
    public String editSchedule(@PathVariable Integer id, Model model) {
        Schedule schedule = scheduleService.getScheduleById(id);
        ScheduleForm scheduleForm = new ScheduleForm(schedule);
        model.addAttribute("scheduleForm", scheduleForm);

        return "scheduleUpdate"; // scheduleUpdate.htmlを返す
    }

    /**
     * 既存のスケジュールを更新します。
     * 
     * @param scheduleForm 更新するスケジュールの情報を含むフォーム
     * @return 更新したスケジュールに関連するプランの詳細画面へのリダイレクトURL
     */
    @PostMapping("/update")
    public String updateSchedule(@ModelAttribute ScheduleForm scheduleForm) {
        Schedule schedule = scheduleService.getScheduleById(scheduleForm.getId());

        if (scheduleForm.getPlan() != null && scheduleForm.getPlan().getId() != null) {
            Plan plan = planService.getPlanDetails(scheduleForm.getPlan().getId());
            schedule.setPlan(plan); // 新しいPlanを設定
        }

        schedule.setScheduleTime(scheduleForm.getScheduleTime());
        schedule.setEvent(scheduleForm.getEvent());
        schedule.setMemo(scheduleForm.getMemo());
        schedule.setUrl(scheduleForm.getUrl());
        schedule.setFlag(scheduleForm.getFlag()); // flagを更新

        scheduleService.updateSchedule(schedule); // スケジュールを更新

        return "redirect:/plans/" + schedule.getPlan().getId(); // プラン詳細画面にリダイレクト
    }

    /**
     * 指定されたIDのスケジュールを削除します。
     * 
     * @param id 削除するスケジュールのID
     * @return 削除後のプラン詳細画面へのリダイレクトURL
     */
    @PostMapping("/delete/{id}")
    public String deleteSchedule(@PathVariable Integer id) {
        Schedule schedule = scheduleService.getScheduleById(id);
        Integer planId = schedule.getPlan().getId(); // 関連するプランIDを取得
        scheduleService.deleteSchedule(id); // スケジュールを削除

        return "redirect:/plans/" + planId; // プラン詳細画面にリダイレクト
    }
}

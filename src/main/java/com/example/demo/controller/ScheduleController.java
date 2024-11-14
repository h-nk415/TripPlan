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
 * スケジュールに関するAPIエンドポイントを提供するコントローラークラスです。
 * スケジュールの作成、取得、更新、削除を行うエンドポイントを含んでいます。
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

	/** DI */
	private final ScheduleService scheduleService;
	private final PlanService planService;


	// プランを元にスケジュールフォームを表示
	@GetMapping("/entry/{id}")
	public String entrySchedule(@PathVariable("id") int planId, @ModelAttribute ScheduleForm scheduleForm, Model model) {
		Plan plan = planService.getPlanDetails(planId);
		scheduleForm.setPlan(plan);

		model.addAttribute("plan", plan);  
		model.addAttribute("scheduleForm", scheduleForm);

		return "scheduleForm";
	}

	// スケジュール作成
	@PostMapping("/create")
	public String createSchedule(@ModelAttribute ScheduleForm scheduleForm) {
		// scheduleFormから受け取ったplan.idを使用して完全なPlanオブジェクトを取得
		Plan completePlan = planService.getPlanDetails(scheduleForm.getPlan().getId());

		Schedule schedule = new Schedule();
		schedule.setPlan(completePlan); // 取得した完全なPlanをセット
		schedule.setScheduleTime(scheduleForm.getScheduleTime());
		schedule.setEvent(scheduleForm.getEvent());
		schedule.setMemo(scheduleForm.getMemo());
		schedule.setUrl(scheduleForm.getUrl());

		scheduleService.createSchedule(schedule);

		return "redirect:/plans/" + completePlan.getId();
	}


	@GetMapping("/edit/{id}")
    public String editSchedule(@PathVariable Integer id, Model model) {
        Schedule schedule = scheduleService.getScheduleById(id);
        ScheduleForm scheduleForm = new ScheduleForm(schedule);
        model.addAttribute("scheduleForm", scheduleForm);
        return "scheduleUpdate";
    }

    @PostMapping("/update")
    public String updateSchedule(@ModelAttribute ScheduleForm scheduleForm) {
        Schedule schedule = scheduleService.getScheduleById(scheduleForm.getId());
        
        if (scheduleForm.getPlan() != null && scheduleForm.getPlan().getId() != null) {
            Plan plan = planService.getPlanDetails(scheduleForm.getPlan().getId());
            schedule.setPlan(plan);
        }
        
        schedule.setScheduleTime(scheduleForm.getScheduleTime());
        schedule.setEvent(scheduleForm.getEvent());
        schedule.setMemo(scheduleForm.getMemo());
        schedule.setUrl(scheduleForm.getUrl());

        scheduleService.updateSchedule(schedule);

        return "redirect:/plans/" + schedule.getPlan().getId();
    }
	/**
	 * 指定されたIDのスケジュールを削除します。
	 *
	 * @param id 削除するスケジュールのID
	 * @return 削除後のリダイレクトURL
	 */
    @PostMapping("/delete/{id}")
    public String deleteSchedule(@PathVariable Integer id) {
        Schedule schedule = scheduleService.getScheduleById(id);
        Integer planId = schedule.getPlan().getId();
        scheduleService.deleteSchedule(id);
        return "redirect:/plans/" + planId;
    }
}

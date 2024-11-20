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

import com.example.demo.entity.Plan;
import com.example.demo.entity.Todo;
import com.example.demo.form.TodoForm;
import com.example.demo.service.PlanService;
import com.example.demo.service.TodoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;
    private final PlanService planService;
    
    @GetMapping("/entry/{id}")
	public String entryTodo(@PathVariable("id") int planId, @ModelAttribute TodoForm todoForm, Model model) {
		Plan plan = planService.getPlanDetails(planId);
		todoForm.setPlan(plan);

		model.addAttribute("plan", plan);  
		model.addAttribute("todoForm", todoForm);

		return "todoForm";
	}

    @PostMapping("/create")
    public String createTodo(@ModelAttribute @Valid TodoForm todoForm, Model model, @RequestParam("planId") Integer planId, BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) {
        	model.addAttribute("todoForm", todoForm);
            return "todoForm"; // エラーがあれば再表示
        }

        // ItemFormからItemエンティティにデータを変換
    	Todo todo = new Todo();
        todo.setTask(todoForm.getTask());
        todo.setCompleted(false);
        todo.setPlan(planService.getPlanById(planId)); // planIdを設定
        todoService.createTodo(todo); 
        return "redirect:/todos/entry/" + planId; // プランに関連するTODO一覧へリダイレクト
    }

    @PostMapping("/update/{id}")
    public String updateTodo(@PathVariable Integer id) {
        Todo todo = todoService.getTodoById(id);
        todo.setCompleted(!todo.getCompleted()); // チェック状態を反転
        todoService.updateTodo(todo); // 更新を保存
        return "redirect:/todos/entry/" + todo.getPlan().getId(); // プラン詳細ページにリダイレクト
    }

    @PostMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Integer id) {
        Todo todo = todoService.getTodoById(id);
        Integer planId = todo.getPlan().getId();
        todoService.deleteTodo(id); // アイテム削除
        return "redirect:/todos/entry/" + planId; // プランに関連するTODO一覧へリダイレクト
    }
}

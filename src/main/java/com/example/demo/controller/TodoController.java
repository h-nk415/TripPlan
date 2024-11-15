package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Todo;
import com.example.demo.service.PlanService;
import com.example.demo.service.TodoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;
    private final PlanService planService;

    @PostMapping("/create")
    public String createTodo(@ModelAttribute Todo todo, @RequestParam("planId") Integer planId) {
        todo.setPlan(planService.getPlanById(planId)); // 新しいTODOアイテムに関連するプランを設定
        todo.setCompleted(false);
        todoService.createTodo(todo);
        return "redirect:/plans/" + planId; // プランに関連するTODO一覧へリダイレクト
    }

    @PostMapping("/update/{id}")
    public String updateTodo(@PathVariable Integer id) {
        Todo todo = todoService.getTodoById(id);
        todo.setCompleted(!todo.getCompleted()); // チェック状態を反転
        todoService.updateTodo(todo); // 更新を保存
        return "redirect:/plans/" + todo.getPlan().getId(); // プラン詳細ページにリダイレクト
    }

    @PostMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Integer id) {
        Todo todo = todoService.getTodoById(id);
        Integer planId = todo.getPlan().getId();
        todoService.deleteTodo(id); // アイテム削除
        return "redirect:/plans/" + planId; // プランに関連するTODO一覧へリダイレクト
    }
}

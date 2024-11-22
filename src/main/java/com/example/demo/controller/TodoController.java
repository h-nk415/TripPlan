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

import com.example.demo.entity.Plan;
import com.example.demo.entity.Todo;
import com.example.demo.form.TodoForm;
import com.example.demo.service.PlanService;
import com.example.demo.service.TodoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * Todoに関連する操作を管理するコントローラークラスです。
 * Todoの作成、更新、削除、および取得を行います。
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/todos")
public class TodoController {

    /** Todoに関するサービス */
    private final TodoService todoService;

    /** プランに関するサービス */
    private final PlanService planService;

    /**
     * プランに関連するTodoを表示するフォームを表示します。
     * 
     * @param planId プランID
     * @param todoForm Todoフォーム
     * @param model 画面にデータを渡すためのモデル
     * @return Todo作成フォームのビュー名
     */
    @GetMapping("/entry/{id}")
    public String entryTodo(@PathVariable("id") int planId, @ModelAttribute TodoForm todoForm, Model model) {
        List<Todo> todos = todoService.getTodosByPlanId(planId);
        Plan plan = planService.getPlanById(planId);

        model.addAttribute("plan", plan);  
        model.addAttribute("todos", todos);

        return "todoForm"; // todoForm.htmlを返す
    }

    /**
     * 新しいTodoを作成します。
     * 
     * @param todoForm Todoの情報を含むフォーム
     * @param model 画面にデータを渡すためのモデル
     * @param planId プランID
     * @param bindingResult バリデーションエラーを格納するオブジェクト
     * @return 作成したTodoに関連するプランのTodo一覧ページへのリダイレクトURL
     */
    @PostMapping("/create")
    public String createTodo(@ModelAttribute @Valid TodoForm todoForm, Model model, @RequestParam("planId") Integer planId, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("todoForm", todoForm);
            return "todoForm"; // エラーがあれば再表示
        }

        // TodoFormからTodoエンティティにデータを変換
        Todo todo = new Todo();
        todo.setTask(todoForm.getTask());
        todo.setCompleted(false); // 完了状態は初期は未完了
        todo.setPlan(planService.getPlanById(planId)); // planIdを設定
        todoService.createTodo(todo); 

        return "redirect:/todos/entry/" + planId; // プランに関連するTODO一覧へリダイレクト
    }

    /**
     * 指定されたIDのTodoの完了状態を更新します。
     * 完了状態を反転させます。
     * 
     * @param id 更新するTodoのID
     * @return 更新後のTodoに関連するプランのTodo一覧ページへのリダイレクトURL
     */
    @PostMapping("/update/{id}")
    public String updateTodo(@PathVariable Integer id) {
        Todo todo = todoService.getTodoById(id);
        todo.setCompleted(!todo.getCompleted()); // チェック状態を反転
        todoService.updateTodo(todo); // 更新を保存

        return "redirect:/todos/entry/" + todo.getPlan().getId(); // プラン詳細ページにリダイレクト
    }

    /**
     * 指定されたIDのTodoを削除します。
     * 
     * @param id 削除するTodoのID
     * @return 削除後のTodoに関連するプランのTodo一覧ページへのリダイレクトURL
     */
    @PostMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Integer id) {
        Todo todo = todoService.getTodoById(id);
        Integer planId = todo.getPlan().getId();
        todoService.deleteTodo(id); // Todoを削除

        return "redirect:/todos/entry/" + planId; // プランに関連するTODO一覧へリダイレクト
    }
}

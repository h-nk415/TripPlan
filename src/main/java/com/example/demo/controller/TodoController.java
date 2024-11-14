package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Todo;
import com.example.demo.service.TodoService;

import lombok.RequiredArgsConstructor;

/**
 * Todoリストに関するリクエストを処理するコントローラークラスです。
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/todos")
public class TodoController {

    /** DI */
    private final TodoService todoService;

    /**
     * 指定された旅行プランIDに関連するTodoアイテムのリストを取得し、Modelに追加します。
     *
     * @param planId 旅行プランのID
     * @param model 取得したTodoアイテムを格納するModelオブジェクト
     * @return Todoアイテムの一覧ページのビュー名
     */
    @GetMapping("/plan/{planId}")
    public String getTodosByPlanId(@PathVariable Integer planId, Model model) {
        List<Todo> todos = todoService.getTodosByPlanId(planId);
        model.addAttribute("todos", todos);
        return "todoList"; // Todoアイテムの一覧ページのビュー名
    }

    /**
     * 指定されたIDのTodoアイテムを取得し、Modelに追加します。
     *
     * @param id 取得したいTodoアイテムのID
     * @param model 取得したTodoアイテムを格納するModelオブジェクト
     * @return Todoアイテムの詳細ページのビュー名
     */
    @GetMapping("/{id}")
    public String getTodoById(@PathVariable Integer id, Model model) {
        Todo todo = todoService.getTodoById(id);
        model.addAttribute("todo", todo);
        return "todoDetail"; // Todoアイテムの詳細ページのビュー名
    }

    /**
     * 新しいTodoアイテムを登録します。
     *
     * @param todo 登録するTodoアイテムのエンティティ
     * @return 登録後のリダイレクトURL
     */
    @PostMapping("/create")
    public String createTodo(@ModelAttribute Todo todo) {
        todoService.createTodo(todo);
        return "redirect:/todos/plan";
    }

    /**
     * 指定されたIDのTodoアイテムを更新します。
     *
     * @param id 更新するTodoアイテムのID
     * @param todo 更新するTodoアイテムの情報
     * @return 更新後のリダイレクトURL
     */
    @PostMapping("/update/{id}")
    public String updateTodo(@PathVariable Integer id, @ModelAttribute Todo todo) {
        todo.setId(id);
        todoService.updateTodo(todo);
        return "redirect:/todos/" + id;
    }

    /**
     * 指定されたIDのTodoアイテムを削除します。
     *
     * @param id 削除するTodoアイテムのID
     * @return 削除後のリダイレクトURL
     */
    @PostMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Integer id) {
        todoService.deleteTodo(id);
        return "redirect:/todos";
    }
}

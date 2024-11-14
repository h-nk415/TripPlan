package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Todo;
import com.example.demo.repository.TodoMapper;

import lombok.RequiredArgsConstructor;

/**
 * Todoリストに関するビジネスロジックを処理するサービスクラスです。
 * Todoアイテムの登録、取得、更新、削除を行います。
 */
@Service
@RequiredArgsConstructor
public class TodoService {

	/** DI */
    private final TodoMapper todoMapper;

    /**
     * 新しいTodoアイテムをデータベースに登録します。
     *
     * @param todo 登録するTodoアイテムのエンティティ
     */
    public void createTodo(Todo todo) {
        todoMapper.insertTodo(todo);
    }

    /**
     * 指定されたIDのTodoアイテムを取得します。
     *
     * @param id 取得したいTodoアイテムのID
     * @return 取得したTodoアイテムのエンティティ
     */
    public Todo getTodoById(Integer id) {
        return todoMapper.selectTodoById(id);
    }

    /**
     * 指定された旅行プランIDに関連するTodoアイテムのリストを取得します。
     *
     * @param planId 旅行プランのID
     * @return 指定されたプランIDに関連するTodoアイテムのリスト
     */
    public List<Todo> getTodosByPlanId(Integer planId) {
        return todoMapper.selectTodosByPlanId(planId);
    }

    /**
     * 指定されたTodoアイテムの情報を更新します。
     *
     * @param todo 更新するTodoアイテムのエンティティ
     */
    public void updateTodo(Todo todo) {
        todoMapper.updateTodo(todo);
    }

    /**
     * 指定されたIDのTodoアイテムを削除します。
     *
     * @param id 削除するTodoアイテムのID
     */
    public void deleteTodo(Integer id) {
        todoMapper.deleteTodoById(id);
    }
}

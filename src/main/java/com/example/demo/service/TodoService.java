package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Todo;
import com.example.demo.repository.TodoMapper;

import lombok.RequiredArgsConstructor;

/**
 * 旅行のTodoリストに関するビジネスロジックを処理するサービスクラスです。
 * Todoリストの登録、取得、更新、削除などの処理を行います。
 */
@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoMapper todoMapper;

    /**
     * 新しいTodoをデータベースに登録します。
     * 
     * @param todo 登録するTodoのエンティティ
     */
    public void createTodo(Todo todo) {
        todoMapper.insertTodo(todo);
    }

    /**
     * 指定されたIDのTodoを取得します。
     * 
     * @param id 取得したいTodoのID
     * @return 取得したTodoのエンティティ
     */
    public Todo getTodoById(Integer id) {
        return todoMapper.selectTodoById(id);
    }

    /**
     * 指定された旅行プランIDに関連するTodoのリストを取得します。
     * 
     * @param planId 旅行プランのID
     * @return 旅行プランに関連するTodoのリスト
     */
    public List<Todo> getTodosByPlanId(Integer planId) {
        return todoMapper.selectTodosByPlanId(planId);
    }

    /**
     * 指定されたTodoの情報を更新します。
     * 
     * @param todo 更新するTodoのエンティティ
     */
    public void updateTodo(Todo todo) {
        todoMapper.updateTodo(todo);
    }

    /**
     * 指定されたIDのTodoを削除します。
     * 
     * @param id 削除するTodoのID
     */
    public void deleteTodo(Integer id) {
        todoMapper.deleteTodoById(id);
    }
}

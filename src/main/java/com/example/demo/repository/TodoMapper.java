package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.entity.Todo;

/**
 * 旅行のTodoリストに関するデータベース操作を行うマッパーインターフェイスです。
 * Todoアイテムの登録、取得、更新、削除などのCRUD操作を定義しています。
 */
@Mapper
public interface TodoMapper {

    /**
     * 新しいTodoアイテムをデータベースに挿入します。
     *
     * @param todo 登録するTodoアイテムのエンティティ
     */
    void insertTodo(Todo todo);

    /**
     * 指定されたIDに対応するTodoアイテムを取得します。
     *
     * @param id 取得したいTodoアイテムのID
     * @return 指定されたIDに対応するTodoアイテムのエンティティ
     */
    Todo selectTodoById(@Param("id") Integer id);

    /**
     * 指定されたTodoアイテムの情報を更新します。
     *
     * @param todo 更新するTodoアイテムのエンティティ
     */
    void updateTodo(Todo todo);

    /**
     * 指定されたIDに対応するTodoアイテムをデータベースから削除します。
     *
     * @param id 削除したいTodoアイテムのID
     */
    void deleteTodoById(@Param("id") Integer id);

    /**
     * 指定された旅行プランIDに関連するTodoアイテムのリストを取得します。
     *
     * @param planId 関連する旅行プランのID
     * @return 指定されたプランIDに対応するTodoアイテムのリスト
     */
    List<Todo> selectTodosByPlanId(@Param("planId") Integer planId);
}

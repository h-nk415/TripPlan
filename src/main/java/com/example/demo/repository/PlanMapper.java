package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.entity.Plan;

/**
 * 旅行プランに関するデータベース操作を行うマッパーインターフェイスです。
 * 旅行プランの登録、取得、更新、削除などのCRUD操作を定義しています。
 */
@Mapper
public interface PlanMapper {
	
	Plan selectPlanDetails(int id);

    /**
     * 新しい旅行プランをデータベースに挿入します。
     * 
     * @param plan 登録する旅行プランのエンティティ
     */
    void insertPlan(Plan plan);

    /**
     * 指定されたIDに対応する旅行プランを取得します。
     * 
     * @param id 取得したい旅行プランのID
     * @return 指定されたIDに対応する旅行プランのエンティティ
     */
    Plan selectPlanById(@Param("id") Integer id);

    /**
     * データベース内のすべての旅行プランを取得します。
     * 
     * @return すべての旅行プランのリスト
     */
    List<Plan> selectAllPlans(int id);

    /**
     * 指定された旅行プランの情報を更新します。
     * 
     * @param plan 更新する旅行プランのエンティティ
     */
    void updatePlan(Plan plan);

    /**
     * 指定されたIDに対応する旅行プランをデータベースから削除します。
     * 
     * @param id 削除したい旅行プランのID
     */
    void deletePlanById(@Param("id") Integer id);
}
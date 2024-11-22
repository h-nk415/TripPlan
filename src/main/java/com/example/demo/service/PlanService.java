package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Plan;
import com.example.demo.repository.PlanMapper;

import lombok.RequiredArgsConstructor;

/**
 * 旅行プランに関するビジネスロジックを処理するサービスクラスです。
 * 旅行プランの登録、取得、更新、削除などの処理を行います。
 */
@Service
@RequiredArgsConstructor
public class PlanService {

	/** DI */
    private final PlanMapper planMapper;
    
    public Plan getPlanDetails(int id) {
        return planMapper.selectPlanDetails(id);
    }

    /**
     * 新しい旅行プランをデータベースに登録します。
     * 
     * @param plan 登録する旅行プランのエンティティ
     */
    public void createPlan(Plan plan) {
        planMapper.insertPlan(plan);
    }

    /**
     * 指定されたIDの旅行プランを取得します。
     * 
     * @param id 取得したい旅行プランのID
     * @return 取得した旅行プランのエンティティ
     */
    public Plan getPlanById(Integer id) {
        return planMapper.selectPlanById(id);
    }

    /**
     * すべての旅行プランを取得します。
     * 
     * @return すべての旅行プランのリスト
     */
    public List<Plan> getPlansByUserId(Integer userId) {
        return planMapper.selectAllPlans(userId);
    }

    /**
     * 指定された旅行プランの情報を更新します。
     * 
     * @param plan 更新する旅行プランのエンティティ
     */
    public void updatePlan(Plan plan) {
        planMapper.updatePlan(plan);
    }

    /**
     * 指定されたIDの旅行プランを削除します。
     * 
     * @param id 削除したい旅行プランのID
     */
    public void deletePlan(Integer id) {
        planMapper.deletePlanById(id);
    }
}
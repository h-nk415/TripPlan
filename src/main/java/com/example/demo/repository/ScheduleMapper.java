package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.entity.Schedule;

/**
 * 旅行のスケジュールに関するデータベース操作を行うマッパーインターフェイスです。
 * このインターフェイスはスケジュールの登録、取得、更新、削除を行うCRUD操作を提供します。
 * 
 * 旅行プランに関連するスケジュール情報を管理し、必要に応じて旅行プランIDに基づいて
 * スケジュールを取得することもできます。
 */
@Mapper
public interface ScheduleMapper {

    /**
     * 新しいスケジュールをデータベースに挿入します。
     * 
     * @param schedule 登録するスケジュールのエンティティ
     *                scheduleTime, spot, memo, spotUrl, plan_id などを含む
     */
    void insertSchedule(Schedule schedule);

    /**
     * 指定されたIDに対応するスケジュールを取得します。
     * 
     * @param id 取得したいスケジュールのID
     * @return 指定されたIDに対応するスケジュールのエンティティ
     */
    Schedule selectScheduleById(@Param("id") Integer id);

    /**
     * 指定されたスケジュールの情報を更新します。
     * 
     * @param schedule 更新するスケジュールのエンティティ
     *                 scheduleTime, spot, memo, spotUrl, plan_id などを更新
     */
    void updateSchedule(Schedule schedule);

    /**
     * 指定されたIDに対応するスケジュールをデータベースから削除します。
     * 
     * @param id 削除したいスケジュールのID
     */
    void deleteScheduleById(@Param("id") Integer id);

    /**
     * 指定された旅行プランIDに関連するスケジュールのリストを取得します。
     * 
     * @param planId 旅行プランのID
     * @return 指定されたプランIDに関連するスケジュールのリスト
     */
    List<Schedule> selectSchedulesByPlanId(@Param("planId") Integer planId);
}

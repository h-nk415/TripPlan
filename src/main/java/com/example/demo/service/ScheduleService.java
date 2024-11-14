package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Schedule;
import com.example.demo.repository.ScheduleMapper;

import lombok.RequiredArgsConstructor;

/**
 * 旅行のスケジュールに関するビジネスロジックを処理するサービスクラスです。
 * スケジュールの登録、取得、更新、削除などの処理を行います。
 */
@Service
@RequiredArgsConstructor
public class ScheduleService {

	/** DI */
    private final ScheduleMapper scheduleMapper;

    /**
     * 新しいスケジュールをデータベースに登録します。
     * 
     * @param schedule 登録するスケジュールのエンティティ
     */
    public void createSchedule(Schedule schedule) {
        scheduleMapper.insertSchedule(schedule);
    }

    /**
     * 指定されたIDのスケジュールを取得します。
     * 
     * @param id 取得したいスケジュールのID
     * @return 取得したスケジュールのエンティティ
     */
    public Schedule getScheduleById(Integer id) {
        return scheduleMapper.selectScheduleById(id);
    }

    /**
     * 指定された旅行プランIDに関連するスケジュールのリストを取得します。
     * 
     * @param planId 旅行プランのID
     * @return 旅行プランに関連するスケジュールのリスト
     */
    public List<Schedule> getSchedulesByPlanId(Integer planId) {
        return scheduleMapper.selectSchedulesByPlanId(planId);
    }

    /**
     * 指定されたスケジュールの情報を更新します。
     * 
     * @param schedule 更新するスケジュールのエンティティ
     */
    public void updateSchedule(Schedule schedule) {
        scheduleMapper.updateSchedule(schedule);
    }

    /**
     * 指定されたIDのスケジュールを削除します。
     * 
     * @param id 削除するスケジュールのID
     */
    public void deleteSchedule(Integer id) {
        scheduleMapper.deleteScheduleById(id);
    }
}

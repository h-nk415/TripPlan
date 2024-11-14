package com.example.demo.form;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.example.demo.entity.Plan;
import com.example.demo.entity.Schedule;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleForm {
    
    private Integer id;

    @NotNull(message = "時間は必須です")
    private LocalDateTime scheduleTime;
    
    private String scheduleTimeString;

    @NotEmpty(message = "予定している内容は必須です")
    private String event;

    private String memo;

    private String url;

    private Plan plan;

    public ScheduleForm(Schedule schedule) {
        this.id = schedule.getId();
        this.scheduleTime = schedule.getScheduleTime();
        this.scheduleTimeString = schedule.getScheduleTime() != null 
                ? schedule.getScheduleTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"))
                : null;
        this.event = schedule.getEvent();
        this.memo = schedule.getMemo();
        this.url = schedule.getUrl();
        this.plan = schedule.getPlan();
    }

    public String getScheduleTimeString() {
        if (this.scheduleTime != null) {
            return this.scheduleTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        }
        return this.scheduleTimeString;
    }

    public void setScheduleTimeString(String scheduleTimeString) {
        this.scheduleTimeString = scheduleTimeString;
        if (scheduleTimeString != null && !scheduleTimeString.isEmpty()) {
            this.scheduleTime = LocalDateTime.parse(scheduleTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        }
    }
}
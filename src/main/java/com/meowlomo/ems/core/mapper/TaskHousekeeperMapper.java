package com.meowlomo.ems.core.mapper;

import java.util.List;

import com.meowlomo.ems.core.model.Task;

public interface TaskHousekeeperMapper {

    List<Task> selectAbnormalTasks();

    List<Task> selectRecyclableTasks();

    List<Task> selectFinishableTasks();
}
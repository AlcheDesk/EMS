package com.meowlomo.ems.core.service.background;

public interface TaskHousekeeperService {

    long recycleTasks();

    long cleanAbnormalTasks();

    long finishTasks();
}

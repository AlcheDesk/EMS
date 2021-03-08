package com.meowlomo.ems.core.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.ems.core.model.Task;

public interface TaskAssignmentMapper {

    int updateTaskToWIPByPrimaryKeyForAssignment(Task record);

    int rollbackTaskByPrimaryKeyFromAssignment(Long id);

    List<Task> getTasksForAssignment(RowBounds rowBounds);
}

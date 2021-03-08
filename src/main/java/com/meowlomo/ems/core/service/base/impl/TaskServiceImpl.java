package com.meowlomo.ems.core.service.base.impl;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.ems.core.mapper.TaskMapper;
import com.meowlomo.ems.core.model.Task;
import com.meowlomo.ems.core.model.TaskExample;
import com.meowlomo.ems.core.service.base.TaskService;
import com.meowlomo.ems.core.service.execution.TaskExecutionControlLogicService;
import com.meowlomo.ems.core.service.filter.TaskFilterService;
import com.meowlomo.ems.core.service.util.TaskUtilService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TaskServiceImpl implements TaskService {
    
    @Autowired
    private TaskMapper taskMapper;
    
    @Autowired
    private TaskUtilService taskUtilService;
    
    @Autowired
    private TaskFilterService taskFilterService;
    
    @Autowired
    private TaskExecutionControlLogicService taskExecutionControlLogicService;

    @Override
    public long countByExample(TaskExample example) {
        return taskMapper.countByExample(example);
    }

    @Override
    public long insert(Task record) {
        int returnRecord = taskMapper.insert(record);
        if (returnRecord == 1) {
            taskUtilService.recordTaskExecutionControl(record);
        }
        return returnRecord;
    }

    @Override
    public long insertSelective(Task record) {
        int returnRecord = taskMapper.insertSelective(record);
        if (returnRecord == 1) {
            taskUtilService.recordTaskExecutionControl(record);
        }
        return returnRecord;
    }

    @Override
    public int updateByExampleSelective(Task record, TaskExample example) {
        List<Task> oldTasks = taskMapper.selectByExample(example);
        if (oldTasks.isEmpty()) {
            return 0;
        }
        taskUtilService.updateLinkedWorkerStatus(record, oldTasks);
        taskUtilService.updateJobStatusByTaskWithExample(record, oldTasks);
        taskFilterService.generateExecutionStartAndExecutionEndAndFinished(record, oldTasks);
        taskExecutionControlLogicService.triggerSingletonFinished(record, oldTasks);
        return taskMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Task record, TaskExample example) {
        List<Task> oldTasks = taskMapper.selectByExample(example);
        if (oldTasks.isEmpty()) {
            return 0;
        }
        taskUtilService.updateLinkedWorkerStatus(record, oldTasks);
        taskUtilService.updateJobStatusByTaskWithExample(record, oldTasks);
        taskFilterService.generateExecutionStartAndExecutionEndAndFinished(record, oldTasks);
        taskExecutionControlLogicService.triggerSingletonFinished(record, oldTasks);
        return taskMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Task record) {
        Task oldTask = taskMapper.selectByPrimaryKey(record.getId());
        if (oldTask == null) {
            return 0;
        }
        taskUtilService.updateLinkedWorkerStatus(record, oldTask);
        taskUtilService.updateJobStatusByTask(record, oldTask);
        taskFilterService.generateExecutionStartAndExecutionEndAndFinished(record, oldTask);
        taskExecutionControlLogicService.triggerSingletonFinished(record, oldTask);
        return taskMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Task record) {
        Task oldTask = taskMapper.selectByPrimaryKey(record.getId());
        if (oldTask == null) {
            return 0;
        }
        taskUtilService.updateLinkedWorkerStatus(record, oldTask);
        taskUtilService.updateJobStatusByTask(record, oldTask);
        taskFilterService.generateExecutionStartAndExecutionEndAndFinished(record, oldTask);
        taskExecutionControlLogicService.triggerSingletonFinished(record, oldTask);
        return taskMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Task> selectByExample(TaskExample example) {
        return taskMapper.selectByExample(example);
    }

    @Override
    public List<Task> selectByExampleWithRowbounds(TaskExample example, RowBounds rowBounds) {
        return taskMapper.selectByExampleWithRowbounds(example, rowBounds);
    }

    @Override
    public Task selectByPrimaryKey(Long id) {
        return taskMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteByExample(TaskExample example) {
        return taskMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return taskMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Task selectByUuid(UUID taskUuid) {
        TaskExample example = new TaskExample();
        example.createCriteria().andUuidEqualTo(taskUuid);
        return taskMapper.selectByExample(example).get(0);
    }
}

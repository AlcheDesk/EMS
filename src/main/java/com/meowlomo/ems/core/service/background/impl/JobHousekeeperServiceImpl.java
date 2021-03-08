package com.meowlomo.ems.core.service.background.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.meowlomo.ems.core.mapper.JobLogMapper;
import com.meowlomo.ems.core.mapper.JobMapper;
import com.meowlomo.ems.core.mapper.TaskMapper;
import com.meowlomo.ems.core.model.Job;
import com.meowlomo.ems.core.model.JobExample;
import com.meowlomo.ems.core.model.JobLog;
import com.meowlomo.ems.core.model.Task;
import com.meowlomo.ems.core.model.TaskExample;
import com.meowlomo.ems.core.service.background.JobHouseKeeperService;

@Service
public class JobHousekeeperServiceImpl implements JobHouseKeeperService {

    static final Logger logger = LoggerFactory.getLogger(TaskHousekeeperServiceImpl.class);

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private JobLogMapper JobLogMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public long finishJobs() {
        // select all unfinished jobs
        JobExample unfinishedJobExample = new JobExample();
        unfinishedJobExample.or().andFinishedEqualTo(false);
        List<Job> unfinishedJobs = jobMapper.selectByExample(unfinishedJobExample);
        long count = 0;
        for (int jobCount = 0; jobCount < unfinishedJobs.size(); jobCount++) {
            Job job = unfinishedJobs.get(jobCount);
            // select finished tasks by the job
            TaskExample finishedTaskExample = new TaskExample();
            finishedTaskExample.or().andFinishedEqualTo(true).andJobIdEqualTo(job.getId());
            List<Task> finishedTasks = taskMapper.selectByExample(finishedTaskExample);
            TaskExample taskExample = new TaskExample();
            taskExample.or().andJobIdEqualTo(job.getId());
            List<Task> totalTasks = taskMapper.selectByExample(taskExample);
            if (!totalTasks.isEmpty()) {
                if (finishedTasks.size() == totalTasks.size()) {
                    // update the job to finish
                    JobLog log = new JobLog();
                    log.setMessage("set job to finished by the system.");
                    log.setJobId(job.getId());
                    JobLogMapper.insert(log);
                    Job updateJob = new Job();
                    updateJob.setFinished(true);
                    updateJob.setId(job.getId());
                    if (job.getStatus().equalsIgnoreCase("wip") || job.getStatus().equalsIgnoreCase("new")) {
                        JobLog logDone = new JobLog();
                        logDone.setMessage("set job to DONE by the system.");
                        logDone.setJobId(job.getId());
                        JobLogMapper.insert(logDone);
                        updateJob.setStatus("DONE");
                    }
                    if (jobMapper.updateByPrimaryKeySelective(updateJob) == 1) {
                        count = count + 1;
                    } else {
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    }
                }
            }
        }
        return count;
    }
}

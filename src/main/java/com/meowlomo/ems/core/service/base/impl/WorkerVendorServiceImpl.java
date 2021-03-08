package com.meowlomo.ems.core.service.base.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.ems.core.mapper.VendorMapper;
import com.meowlomo.ems.core.mapper.WorkerMapper;
import com.meowlomo.ems.core.mapper.WorkerVendorMapper;
import com.meowlomo.ems.core.model.VendorExample;
import com.meowlomo.ems.core.model.Worker;
import com.meowlomo.ems.core.model.WorkerExample;
import com.meowlomo.ems.core.model.WorkerVendor;
import com.meowlomo.ems.core.model.WorkerVendorExample;
import com.meowlomo.ems.core.service.base.WorkerVendorService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class WorkerVendorServiceImpl implements WorkerVendorService {

    @Autowired
    private WorkerVendorMapper vendorWorkerLinkMapper;
    @Autowired
    private VendorMapper vendorMapper;
    @Autowired
    private WorkerMapper workerMapper;

    @Override
    public long countByExample(WorkerVendorExample example) {
        return vendorWorkerLinkMapper.countByExample(example);
    }

    @Override
    public long insert(WorkerVendor record) {
        return vendorWorkerLinkMapper.insert(record);
    }

    @Override
    public int updateByExampleSelective(WorkerVendor record, WorkerVendorExample example) {
        return vendorWorkerLinkMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(WorkerVendor record, WorkerVendorExample example) {
        return vendorWorkerLinkMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(WorkerVendor record) {
        return vendorWorkerLinkMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(WorkerVendor record) {
        return vendorWorkerLinkMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<WorkerVendor> selectByExample(WorkerVendorExample example) {
        return vendorWorkerLinkMapper.selectByExample(example);
    }

    @Override
    public List<WorkerVendor> selectByExampleWithRowbounds(WorkerVendorExample example,
            RowBounds rowBounds) {
        return vendorWorkerLinkMapper.selectByExampleWithRowbounds(example, rowBounds);
    }

    @Override
    public WorkerVendor selectByPrimaryKey(Long id) {
        return vendorWorkerLinkMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteByExample(WorkerVendorExample example) {
        return vendorWorkerLinkMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return vendorWorkerLinkMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Worker> listWorkerByVendorPrimaryKey(Long workerId) {
        WorkerVendorExample linkExample = new WorkerVendorExample();
        linkExample.createCriteria().andWorkerIdEqualTo(workerId);
        List<WorkerVendor> targetLinks = vendorWorkerLinkMapper.selectByExample(linkExample);
        List<Long> targetIds = new ArrayList<Long>();
        for (int i = 0; i < targetLinks.size(); i++) {
            targetIds.add(targetLinks.get(i).getWorkerId());
        }
        if (targetIds.size() == 0) {
            return new ArrayList<Worker>();
        }
        else {
            WorkerExample targetExample = new WorkerExample();
            targetExample.createCriteria().andIdIn(targetIds);
            return workerMapper.selectByExample(targetExample);
        }
    }

    @Override
    public int insertWorkerAndLinkToVendorByPrimaryKey(Long vendorId, Worker record) {
        int insertResult = workerMapper.insertSelective(record);
        if (insertResult == 1) {
            WorkerVendor linkRecord = new WorkerVendor();
            linkRecord.setVendorId(vendorId);
            linkRecord.setWorkerId(record.getId());
            return vendorWorkerLinkMapper.insert(linkRecord);
        }
        else {
            return 0;
        }
    }

    @Override
    public List<com.meowlomo.ems.core.model.Vendor> listVendorByWorkerPrimaryKey(Long vendorId) {
        WorkerVendorExample linkExample = new WorkerVendorExample();
        linkExample.createCriteria().andVendorIdEqualTo(vendorId);
        List<WorkerVendor> targetLinks = vendorWorkerLinkMapper.selectByExample(linkExample);
        List<Long> targetIds = new ArrayList<Long>();
        for (int i = 0; i < targetLinks.size(); i++) {
            targetIds.add(targetLinks.get(i).getVendorId());
        }
        if (targetIds.size() == 0) {
            return new ArrayList<com.meowlomo.ems.core.model.Vendor>();
        }
        else {
            VendorExample targetExample = new VendorExample();
            targetExample.createCriteria().andIdIn(targetIds);
            return vendorMapper.selectByExample(targetExample);
        }
    }

    @Override
    public int insertVendorAndLinkToWorkerByPrimaryKey(Long workerId,
            com.meowlomo.ems.core.model.Vendor record) {
        int insertResult = vendorMapper.insertSelective(record);
        if (insertResult == 1) {
            WorkerVendor linkRecord = new WorkerVendor();
            linkRecord.setWorkerId(workerId);
            linkRecord.setVendorId(record.getId());
            return vendorWorkerLinkMapper.insert(linkRecord);
        }
        else {
            return 0;
        }
    }

    @Override
    public List<com.meowlomo.ems.core.model.Vendor> listVendorByWorkerUuid(UUID workerUuid) {
        WorkerExample tempExample = new WorkerExample();
        tempExample.createCriteria().andUuidEqualTo(workerUuid);
        List<Worker> targetRecords = workerMapper.selectByExample(tempExample);

        WorkerVendorExample linkExample = new WorkerVendorExample();
        linkExample.createCriteria().andWorkerIdEqualTo(targetRecords.get(0).getId());
        List<WorkerVendor> targetLinks = vendorWorkerLinkMapper.selectByExample(linkExample);

        List<Long> targetIds = new ArrayList<Long>();
        for (int i = 0; i < targetLinks.size(); i++) {
            targetIds.add(targetLinks.get(i).getWorkerId());
        }
        if (targetIds.size() == 0) {
            return new ArrayList<com.meowlomo.ems.core.model.Vendor>();
        }
        else {
            VendorExample targetExample = new VendorExample();
            targetExample.createCriteria().andIdIn(targetIds);
            return vendorMapper.selectByExample(targetExample);
        }
    }

    @Override
    public int insertVendorAndLinkToWorkerByUuid(UUID workerUuid,
            com.meowlomo.ems.core.model.Vendor record) {
        WorkerExample sourceExample = new WorkerExample();
        sourceExample.createCriteria().andUuidEqualTo(workerUuid);
        List<Worker> records = workerMapper.selectByExample(sourceExample);
        int insertResult = vendorMapper.insertSelective(record);
        if (insertResult == 1) {
            WorkerVendor linkRecord = new WorkerVendor();
            linkRecord.setWorkerId(records.get(0).getId());
            linkRecord.setVendorId(record.getId());
            return vendorWorkerLinkMapper.insert(linkRecord);
        }
        else {
            return 0;
        }
    }
}

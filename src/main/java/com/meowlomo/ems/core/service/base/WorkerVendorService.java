package com.meowlomo.ems.core.service.base;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.ems.core.model.Vendor;
import com.meowlomo.ems.core.model.Worker;
import com.meowlomo.ems.core.model.WorkerVendor;
import com.meowlomo.ems.core.model.WorkerVendorExample;

public interface WorkerVendorService {
    // mapper methods
    long insert(WorkerVendor record);

    int updateByExampleSelective(WorkerVendor record, WorkerVendorExample example);

    int updateByExample(WorkerVendor record, WorkerVendorExample example);

    int updateByPrimaryKeySelective(WorkerVendor record);

    int updateByPrimaryKey(WorkerVendor record);

    List<WorkerVendor> selectByExample(WorkerVendorExample example);

    List<WorkerVendor> selectByExampleWithRowbounds(WorkerVendorExample example, RowBounds rowBounds);

    WorkerVendor selectByPrimaryKey(Long id);

    int deleteByExample(WorkerVendorExample example);

    int deleteByPrimaryKey(Long id);

    long countByExample(WorkerVendorExample example);

    // link methods
    List<Vendor> listVendorByWorkerPrimaryKey(Long componentId);

    int insertVendorAndLinkToWorkerByPrimaryKey(Long workerId, Vendor record);

    List<Worker> listWorkerByVendorPrimaryKey(Long componentId);

    int insertWorkerAndLinkToVendorByPrimaryKey(Long componentId, Worker record);

    List<Vendor> listVendorByWorkerUuid(UUID workerUuid);

    int insertVendorAndLinkToWorkerByUuid(UUID workerUuid, Vendor record);
}

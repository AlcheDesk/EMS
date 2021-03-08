package com.meowlomo.ems.core.resource;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.meowlomo.ems.core.model.Job;
import com.meowlomo.ems.core.model.JobExample;
import com.meowlomo.ems.core.model.Task;
import com.meowlomo.ems.core.model.TaskExample;
import com.meowlomo.ems.core.resource.exception.CustomBadRequestException;
import com.meowlomo.ems.core.resource.exception.CustomForbiddenException;
import com.meowlomo.ems.core.resource.exception.CustomInternalServerErrorException;
import com.meowlomo.ems.core.resource.exception.CustomNotAcceptableException;
import com.meowlomo.ems.core.resource.exception.CustomNotAllowedException;
import com.meowlomo.ems.core.resource.exception.CustomNotAuthorizedException;
import com.meowlomo.ems.core.resource.exception.CustomNotFoundException;
import com.meowlomo.ems.core.resource.exception.CustomNotSupportedException;
import com.meowlomo.ems.core.resource.exception.CustomServiceUnavailableException;
import com.meowlomo.ems.core.resource.model.MeowlomoErrorResponse;
import com.meowlomo.ems.core.resource.model.MeowlomoResponse;
import com.meowlomo.ems.core.service.base.JobReferenceService;
import com.meowlomo.ems.core.service.base.JobService;
import com.meowlomo.ems.core.service.base.TaskReferenceService;
import com.meowlomo.ems.core.service.base.TaskService;
import com.meowlomo.ems.core.service.util.JobUtilService;
import com.meowlomo.ems.core.service.util.TaskUtilService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@Path("/utils")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "util resources", produces = "application/json")
public class UtilResource {

    private final Logger logger = LoggerFactory.getLogger(UtilResource.class);
    
    private static final String ERROR_TYPE = "UTL";

    @Context
    UriInfo uriInfo;

    @Context
    SecurityContext securityContext;

    @Context
    HttpServletRequest httpRequest;
    
    @Autowired
    private TaskService taskService;
    
    @Autowired
    private TaskUtilService taskUtilService;
    
    @Autowired
    private JobService jobService;
    
    @Autowired
    private JobUtilService jobUtilService;
    
    @Autowired
    private JobReferenceService jobReferenceService;
    
    @Autowired
    private TaskReferenceService taskReferenceService;
    
    @DELETE
    @Path("/tasks/termination/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "终止Task", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "DELETE")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoErrorResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "指定删除的对象不存在，请检查。错误唯一码[\"+exuuid+\"]", response = MeowlomoErrorResponse.class) })
    public MeowlomoResponse terminateTask(@PathParam("uuid") String uuid) throws Exception {
        logger.debug("received task termination call");
        try {
            UUID taskUuid = UUID.fromString(uuid);
            TaskExample example = new TaskExample();
            example.or().andIdIsNotNull().andUuidEqualTo(taskUuid);

            List<Task> records = taskService.selectByExample(example);
            if (records.isEmpty()) {
                //chouldn't find the task, should return bad request
                UUID exuuid = UUID.randomUUID();
                String developerMessage = "exception UUID=" + exuuid + " task with uuid "+uuid+" not exists";
                String message = "所要终止的Task["+uuid+"]不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01DT";
                logger.error(developerMessage, httpRequest.getContextPath());
                throw new CustomBadRequestException(null, message, developerMessage, code, exuuid);
            }
            else {
                //found the task
                Task targetTask = records.get(0);
                boolean killResult = taskUtilService.killTask(targetTask, null);
                if (killResult) {
                    records = taskReferenceService.selectByExample(example);
                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                    metadata.put("count", records.size());
                    return new MeowlomoResponse(metadata, records, null);
                }
                else {
                    UUID exuuid = UUID.randomUUID();
                    String developerMessage = "exception UUID=" + exuuid + " job with uuid "+uuid+" not exists";
                    String message = "无法正确终止Task。问题唯一码[" + exuuid + "]";
                    String code = ERROR_TYPE + "01DT";
                    logger.error(developerMessage, httpRequest.getContextPath());
                    throw new CustomServiceUnavailableException(null, message, developerMessage, code, exuuid);
                }
            }
        }
        catch (CustomNotAuthorizedException | CustomBadRequestException | CustomForbiddenException
                | CustomNotAcceptableException | CustomNotAllowedException | CustomNotFoundException
                | CustomNotSupportedException | CustomServiceUnavailableException ex) {
            throw ex;
        }
        catch (Exception ex) {
            UUID exuuid = UUID.randomUUID();
            String message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[" + exuuid + "]。";
            String code = ERROR_TYPE + "01SYS";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }
    
    @DELETE
    @Path("jobs/termination/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "终止Job", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "DELETE")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoErrorResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "指定删除的对象不存在，请检查。错误唯一码[\"+exuuid+\"]", response = MeowlomoErrorResponse.class) })
    public MeowlomoResponse terminateJob(@PathParam("uuid") String uuid) throws Exception {
        logger.debug("received job termination call");
        try {
            UUID jobUuid = UUID.fromString(uuid);
            JobExample example = new JobExample();
            example.or().andIdIsNotNull().andUuidEqualTo(jobUuid);

            List<Job> records = jobService.selectByExample(example);
            if (records.isEmpty()) {
                //chouldn't find the job, should return bad request
                UUID exuuid = UUID.randomUUID();
                String developerMessage = "exception UUID=" + exuuid + " job with uuid "+uuid+" not exists";
                String message = "所要终止的Job["+uuid+"]不存在。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01DT";
                logger.error(developerMessage, httpRequest.getContextPath());
                throw new CustomBadRequestException(null, message, developerMessage, code, exuuid);
            }
            else {
                //found the job
                Job targetJob = records.get(0);
                boolean killResult = jobUtilService.killJob(targetJob);
                if (killResult) {
                    records = jobReferenceService.selectByExample(example);
                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                    metadata.put("count", records.size());
                    return new MeowlomoResponse(metadata, records, null);
                }
                else {
                    UUID exuuid = UUID.randomUUID();
                    String developerMessage = "exception UUID=" + exuuid + " job with uuid "+uuid+" not exists";
                    String message = "无法正确终止Job。问题唯一码[" + exuuid + "]";
                    String code = ERROR_TYPE + "01DT";
                    logger.error(developerMessage, httpRequest.getContextPath());
                    throw new CustomServiceUnavailableException(null, message, developerMessage, code, exuuid);
                }

            }
        }
        catch (CustomNotAuthorizedException | CustomBadRequestException | CustomForbiddenException
                | CustomNotAcceptableException | CustomNotAllowedException | CustomNotFoundException
                | CustomNotSupportedException | CustomServiceUnavailableException ex) {
            throw ex;
        }
        catch (Exception ex) {
            UUID exuuid = UUID.randomUUID();
            String message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[" + exuuid + "]。";
            String code = ERROR_TYPE + "01SYS";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }
    
}

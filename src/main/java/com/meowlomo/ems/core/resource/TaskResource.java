package com.meowlomo.ems.core.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.meowlomo.ems.core.model.TaskExample;
import com.meowlomo.ems.core.model.TaskLog;
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
import com.meowlomo.ems.core.resource.query.SearchExampleGenerator;
import com.meowlomo.ems.core.service.base.TaskLogService;
import com.meowlomo.ems.core.service.base.TaskReferenceService;
import com.meowlomo.ems.core.service.base.TaskService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@Path("/tasks")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "task resources", produces = "application/json")
public class TaskResource {

    private final Logger logger = LoggerFactory.getLogger(TaskResource.class);

    @Context
    UriInfo uriInfo;

    @Context
    SecurityContext securityContext;

    @Context
    HttpServletRequest httpRequest;

    @Autowired
    private SearchExampleGenerator searchExampleGenerator;

    private static final String ERROR_TYPE = "TSK";

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskReferenceService taskReferenceService;
    
    @Autowired
    private TaskLogService taskLogService;

    // ========================================Get Method
    // Start========================================

    /**
     * Select. default return all
     *
     * @return the response entity
     * @throws ServerErrorException
     *             the server error exception
     */
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @ApiOperation(value = "读取Task", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoErrorResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "输入搜索条件name,模糊搜索时在搜索条件两侧加%,如%xx%", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "priority", value = "task priority", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "task type [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "uuid", value = "task UUID", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "task status [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "start date [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "end date [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "pageNumber", value = "page number", required = false, defaultValue = "1", allowEmptyValue = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "page size", required = false, defaultValue = "20", allowEmptyValue = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "orderBy", value = "输入排序条件orderBy,比如按创建时间排序,正序为createdAt asc，降序为createdAt desc", required = false, dataType = "string", paramType = "query") })
    public MeowlomoResponse select() {
        logger.debug("received task select call");
        try {
            MultivaluedMap<String, String> queryParams = this.uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {
                TaskExample example = new TaskExample();
                example.or().andIdIsNotNull();
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(this.uriInfo);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", taskService.countByExample(example));
                List<com.meowlomo.ems.core.model.Task> records = taskService.selectByExampleWithRowbounds(example, rowBounds);
                return new MeowlomoResponse(metadata, records, null);
            }
            else {
                TaskExample example = this.searchExampleGenerator.generateExample(this.uriInfo, null, TaskExample.class);
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(this.uriInfo);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", taskService.countByExample(example));
                List<com.meowlomo.ems.core.model.Task> records = null;
                if (queryParams.containsKey("ref")) {
                    records = taskReferenceService.selectByExampleWithRowbounds(example, rowBounds);
                }
                else {
                    records = taskService.selectByExampleWithRowbounds(example, rowBounds);
                }
                return new MeowlomoResponse(metadata, records, null);
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

    /**
     * Select by primary uuid.
     *
     * @param uuid
     *            the uuid
     * @return the response entity
     * @throws ServerErrorException
     *             the server error exception
     */
    @GET
    @Path("/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取单个Task", response = MeowlomoResponse.class, httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoErrorResponse.class) })
    @ApiImplicitParam(name = "uuid", value = "task UUID", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse selectByUUID(@PathParam("uuid") String uuidString) throws ServerErrorException {
        logger.debug("received task select by primary uuid call");
        try {
            MultivaluedMap<String, String> queryParams = this.uriInfo.getQueryParameters();
            UUID uuid = UUID.fromString(uuidString);
            TaskExample example = new TaskExample();
            example.or().andUuidEqualTo(uuid);
            List<com.meowlomo.ems.core.model.Task> selectRecord = null;
            if (queryParams.containsKey("ref")) {
                selectRecord = taskReferenceService.selectByExample(example);
            }
            else {
                selectRecord = taskService.selectByExample(example);
            }
            if (selectRecord != null) {
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", 1);
                return new MeowlomoResponse(metadata, selectRecord, null);
            }
            else {
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", 0);
                return new MeowlomoResponse(metadata, null, null);
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
            String code = ERROR_TYPE + "02SYS";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }
    // ========================================Get Method
    // End========================================

    // ========================================Delete Method
    // Start========================================

    /**
     * Inactive.
     *
     * @return the response entity
     * @throws Exception
     *             the exception
     */
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "删除Task", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "DELETE")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoErrorResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "指定删除的对象不存在，请检查。错误唯一码[\"+exuuid+\"]", response = MeowlomoErrorResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "priority", value = "task priority", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "task type [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "uuid", value = "task UUID", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "task status [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "start date [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "end date [unix second]", required = false, dataType = "long", paramType = "query") })
    public MeowlomoResponse delete() throws Exception {
        logger.debug("received task inactive call");
        try {
            MultivaluedMap<String, String> queryParams = this.uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {
                TaskExample example = new TaskExample();
                example.or().andIdIsNotNull();
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                List<com.meowlomo.ems.core.model.Task> records = taskService.selectByExample(example);
                metadata.put("count", taskService.countByExample(example));
                int inactiveResult = taskService.deleteByExample(example);
                if (inactiveResult == records.size()) {
                    return new MeowlomoResponse(metadata, records, null);
                }
                else {
                    try {
                        TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                    }
                    catch (Exception e) {

                    }
                    UUID exuuid = UUID.randomUUID();
                    String developerMessage = "exception UUID=" + exuuid + " target count <> delete count ";
                    String message = "无法完成删除，删除数与目标数。问题唯一码[" + exuuid + "]";
                    String code = ERROR_TYPE + "02DEL";
                    logger.error(developerMessage, httpRequest.getContextPath());
                    throw new CustomNotAcceptableException(null, message, developerMessage, code, exuuid);
                }
            }
            else {
                com.meowlomo.ems.core.model.TaskExample.Criteria crieria = new TaskExample().or();
                crieria.andIdIsNotNull();
                TaskExample example = this.searchExampleGenerator.generateExample(this.uriInfo, crieria, TaskExample.class);
                List<com.meowlomo.ems.core.model.Task> records = taskService.selectByExample(example);
                int inactiveResult = taskService.deleteByExample(example);
                if (inactiveResult == records.size()) {
                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                    metadata.put("count", taskService.countByExample(example));
                    return new MeowlomoResponse(metadata, records, null);
                }
                else {
                    try {
                        TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                    }
                    catch (Exception e) {

                    }
                    UUID exuuid = UUID.randomUUID();
                    String developerMessage = "exception UUID=" + exuuid + " target count <> delete count ";
                    String message = "无法完成删除，删除数与目标数。问题唯一码[" + exuuid + "]";
                    String code = ERROR_TYPE + "03DEL";
                    logger.error(developerMessage, httpRequest.getContextPath());
                    throw new CustomNotAcceptableException(null, message, developerMessage, code, exuuid);
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
            String code = ERROR_TYPE + "04SYS";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }

    /**
     * Delete by id.
     *
     * @param uuid
     *            the uuid
     * @return the response entity
     * @throws Exception
     *             the exception
     */
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{uuid}")
    @ApiOperation(value = "删除单个Task", response = MeowlomoResponse.class, httpMethod = "DELETE")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "不存在UUID为\"+uuid+\"的对象，无法冻结。问题唯一码[\"+exuuid+\"]", response = MeowlomoErrorResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoErrorResponse.class) })
    @ApiImplicitParam(name = "uuid", value = "task UUID", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse deleteByUUID(@PathParam("uuid") String uuidString) throws Exception {
        logger.debug("received task inactive by UUID call");
        try {
            
            // select the record first
            UUID uuid = UUID.fromString(uuidString);
            TaskExample example = new TaskExample();
            example.or().andUuidEqualTo(uuid);
            com.meowlomo.ems.core.model.Task record = taskService.selectByExample(example).get(0);
            int deleteResult = taskService.deleteByExample(example);
            if (deleteResult == 0 && record == null) {
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", 0);
                return new MeowlomoResponse(metadata, null, null);
            }
            else if (deleteResult == 1 && record != null) {
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", 1);
                return new MeowlomoResponse(metadata, record, null);
            }
            else {
                try {
                    TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                }
                catch (Exception e) {

                }
                UUID exuuid = UUID.randomUUID();
                String developerMessage = "exception UUID=" + exuuid + " resource with UUID = " + uuid
                        + " does not exists.";
                String message = "不存在UUID为" + uuid + "的对象，无法更新。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01DEL";
                logger.error(developerMessage, httpRequest.getContextPath());
                throw new CustomNotFoundException(null, message, developerMessage, code, exuuid);
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
            String code = ERROR_TYPE + "03SYS";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }
    // ========================================Delete Method
    // End========================================

    // ========================================Patch Method
    // Start========================================

    /**
     * Update selective.
     *
     * @param record
     *            the record
     * @return the response entity
     * @throws Exception
     *             the exception
     */
    @PATCH
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "更新Task", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "PATCH")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoErrorResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "更改操作无法完成，请与管理员联系。并提供唯一码[\"+exuuid+\"]", response = MeowlomoErrorResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "priority", value = "task priority", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "task type [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "uuid", value = "task UUID", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "task status [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "start date [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "end date [unix second]", required = false, dataType = "long", paramType = "query") })
    public MeowlomoResponse updateSelective(com.meowlomo.ems.core.model.Task[] records) throws Exception {
        logger.debug("received patch task call " + uriInfo.toString());
        try {
            // empty just return
            if (records == null) {
                UUID exuuid = UUID.randomUUID();
                String developerMessage = "exception UUID=" + exuuid + " patch body is empty ";
                String message = "更新内容为空。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01PAT";
                logger.error(developerMessage, httpRequest.getContextPath());
                throw new CustomBadRequestException(null, message, developerMessage, code, exuuid);
            }
            else if (records.length == 0) {
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", 0);
                return new MeowlomoResponse(metadata, null, null);
            }
            List<UUID> targetUuids = new ArrayList<UUID>();
            // loop and check each record
            for (int i = 0; i < records.length; i++) {
                com.meowlomo.ems.core.model.Task record = records[i];
                if (record.getUuid() != null) {
                    targetUuids.add(record.getUuid());
                }
            }
            // check all have uuid
            List<UUID> errorIndex = new ArrayList<UUID>();
            if (records.length != targetUuids.size()) {
                UUID exuuid = UUID.randomUUID();
                String developerMessage = "exception UUID=" + exuuid + " patch body is empty ";
                String message = "部分更新请求不含ID。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "02PAT";
                logger.error(developerMessage, httpRequest.getContextPath());
                throw new CustomBadRequestException(null, message, developerMessage, code, exuuid);
            }
            else {
                // start the update
                // update one by one
                for (int i = 0; i < records.length; i++) {
                    com.meowlomo.ems.core.model.Task record = records[i];
                    TaskExample example = new TaskExample();
                    example.or().andUuidEqualTo(record.getUuid());
                    int updateResult = taskService.updateByExampleSelective(record, example);
                    if (updateResult != 1) {
                        errorIndex.add(record.getUuid());
                    }
                    else {
                        //check if there is logs need to add
                        List<TaskLog> logs = record.getLogs();
                        if (logs != null) {
                            Long taskId = records[i].getId();
                            for (TaskLog log : logs) {
                                log.setTaskId(taskId);
                                taskLogService.insertSelective(log);
                            }
                        }
                    }
                }
            }
            // check all update sucess
            if (errorIndex.isEmpty()) {
                try {
                    TransactionAspectSupport.currentTransactionStatus().isCompleted();
                }
                catch (Exception e) {

                }
                TaskExample example = new TaskExample();
                example.or().andUuidIn(targetUuids);
                List<com.meowlomo.ems.core.model.Task> finalRecords = taskService.selectByExample(example);
                // sort return result
                List<com.meowlomo.ems.core.model.Task> finalReturnRecords = new ArrayList<com.meowlomo.ems.core.model.Task>();
                for (UUID uuid : targetUuids) {
                    for (com.meowlomo.ems.core.model.Task oRecord : finalRecords) {
                        if (oRecord.getUuid().equals(uuid)) {
                            finalReturnRecords.add(oRecord);
                        }
                    }
                }
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", finalRecords.size());
                return new MeowlomoResponse(metadata, finalReturnRecords, null);
            }
            else {// not all success
                try {
                    TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                }
                catch (Exception e) {

                }
                UUID exuuid = UUID.randomUUID();
                String developerMessage = "exception UUID=" + exuuid + " could not path all record ";
                String message = "部分或全部更新失败，失败序列。" + errorIndex.toString() + " 问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "03PAT";
                logger.error(developerMessage, httpRequest.getContextPath());
                throw new CustomNotAcceptableException(null, message, developerMessage, code, exuuid);
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
            String code = ERROR_TYPE + "05SYS";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }

    // ========================================Patch Method
    // End========================================

    // ========================================Put Method
    // Start========================================

    /**
     * Update selective by example.
     *
     * @param uuid
     *            the uuid
     * @param record
     *            the record
     * @return the meowlomo response
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "替换或添加Task", response = MeowlomoResponse.class, httpMethod = "PUT")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "exception UUID=\" + exuuid + \" put body is empty", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "部分替换请求不含ID。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "部分或全部替换失败，失败序列。\" + ??? + \" 问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    public MeowlomoResponse replace(com.meowlomo.ems.core.model.Task[] records) {
        logger.debug("received put task by primary uuid call");
        try {
            // empty just return
            if (records == null) {
                UUID exuuid = UUID.randomUUID();
                String developerMessage = "exception UUID=" + exuuid + " put body is empty";
                String message = "替换内容为空。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01PUT";
                logger.error(developerMessage, httpRequest.getContextPath());
                throw new CustomBadRequestException(null, message, developerMessage, code, exuuid);
            }
            else if (records.length == 0) {
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", 0);
                return new MeowlomoResponse(metadata, null, null);
            }
            List<UUID> targetUuids = new ArrayList<UUID>();
            // loop and check each record
            for (int i = 0; i < records.length; i++) {
                com.meowlomo.ems.core.model.Task record = records[i];
                if (record.getUuid() != null) {
                    targetUuids.add(record.getUuid());
                }
            }
            // check all have uuid
            List<UUID> errorIndex = new ArrayList<UUID>();
            if (records.length != targetUuids.size()) {
                UUID exuuid = UUID.randomUUID();
                String developerMessage = "exception UUID=" + exuuid + " patch body is empty ";
                String message = "部分替换请求不含ID。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "02PUT";
                logger.error(developerMessage, httpRequest.getContextPath());
                throw new CustomBadRequestException(null, message, developerMessage, code, exuuid);
            }
            else {
                // start the update
                // update one by one
                for (int i = 0; i < records.length; i++) {
                    com.meowlomo.ems.core.model.Task record = records[i];
                    int updateResult = taskService.updateByPrimaryKey(record);
                    if (updateResult != 1) {
                        errorIndex.add(record.getUuid());
                    }
                }
            }
            // check all update sucess
            if (errorIndex.isEmpty()) {
                try {
                    TransactionAspectSupport.currentTransactionStatus().isCompleted();
                }
                catch (Exception e) {

                }
                TaskExample example = new TaskExample();
                example.or().andUuidIn(targetUuids);
                List<com.meowlomo.ems.core.model.Task> finalRecords = taskService.selectByExample(example);
                // sort return result
                List<com.meowlomo.ems.core.model.Task> finalReturnRecords = new ArrayList<com.meowlomo.ems.core.model.Task>();
                for (UUID uuid : targetUuids) {
                    for (com.meowlomo.ems.core.model.Task oRecord : finalRecords) {
                        if (oRecord.getUuid() == uuid) {
                            finalReturnRecords.add(oRecord);
                        }
                    }
                }
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", finalRecords.size());
                return new MeowlomoResponse(metadata, finalReturnRecords, null);
            }
            else {// not all success
                try {
                    TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                }
                catch (Exception e) {

                }
                UUID exuuid = UUID.randomUUID();
                String developerMessage = "exception UUID=" + exuuid + " could not path all record ";
                String message = "部分或全部替换失败，失败序列。" + errorIndex.toString() + " 问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "03PUT";
                logger.error(developerMessage, httpRequest.getContextPath());
                throw new CustomForbiddenException(null, message, developerMessage, code, exuuid);
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
            String code = ERROR_TYPE + "06SYS";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }

    // ========================================Put Method
    // End========================================

    // ========================================Post Method
    // Start========================================

    /**
     * Update by primary key.
     *
     * @param record
     *            the record
     * @param uriInfo
     *            the uri info
     * @return the response entity
     * @throws Exception
     *             the exception
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "添加Task", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "添加操作无法完成，请与管理员联系。并提供唯一码[\"+exuuid+\"]", response = MeowlomoErrorResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoErrorResponse.class) })
    @ApiImplicitParam(name = "uuid", value = "task UUID", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse insert(com.meowlomo.ems.core.model.Task[] records) throws Exception {
        logger.debug("received post task call ");
        try {
            // empty just return
            if (records == null) {
                UUID exuuid = UUID.randomUUID();
                String developerMessage = "exception UUID=" + exuuid + " patch body is empty ";
                String message = "添加内容为空。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01POS";
                logger.error(developerMessage, httpRequest.getContextPath());
                throw new CustomBadRequestException(null, message, developerMessage, code, exuuid);
            }
            else if (records.length == 0) {
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", 0);
                return new MeowlomoResponse(metadata, null, null);
            }

            List<Long> ids = new ArrayList<Long>();
            // check all have id
            List<Long> errorIndex = new ArrayList<Long>();
            // start the insert
            // insert one by one
            for (int i = 0; i < records.length; i++) {
                com.meowlomo.ems.core.model.Task record = records[i];
                record.setId(null);
                long insertResult = taskService.insertSelective(record);
                if (insertResult != 1) {
                    errorIndex.add(record.getId());
                }
                else {
                    ids.add(record.getId());
                    //check if there is logs need to add
                    List<TaskLog> logs = record.getLogs();
                    if (logs != null) {
                        Long taskId = records[i].getId();
                        for (TaskLog log : logs) {
                            log.setTaskId(taskId);
                            taskLogService.insertSelective(log);
                        }
                    }
                }
            }
            // check all insert success
            if (errorIndex.isEmpty()) {
                TaskExample example = new TaskExample();
                example.or().andIdIn(ids);
                List<com.meowlomo.ems.core.model.Task> finalRecords = taskService.selectByExample(example);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", finalRecords.size());
                return new MeowlomoResponse(metadata, finalRecords, null);
            }
            else {// not all success
                try {
                    TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                }
                catch (Exception e) {

                }
                UUID exuuid = UUID.randomUUID();
                String developerMessage = "exception UUID=" + exuuid + " could not path all record ";
                String message = "部分或全部添加失败，失败序列。" + errorIndex.toString() + " 问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "02POS";
                logger.error(developerMessage, httpRequest.getContextPath());
                throw new CustomNotAcceptableException(null, message, developerMessage, code, exuuid);
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
            String code = ERROR_TYPE + "07SYS";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }
    // ========================================Post Method
    // End========================================
}

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
import com.meowlomo.ems.core.model.Job;
import com.meowlomo.ems.core.model.JobExample;
import com.meowlomo.ems.core.model.Task;
import com.meowlomo.ems.core.model.TaskExample;
import com.meowlomo.ems.core.model.TaskExample.Criteria;
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
import com.meowlomo.ems.core.service.base.JobReferenceService;
import com.meowlomo.ems.core.service.base.JobService;
import com.meowlomo.ems.core.service.base.TaskReferenceService;
import com.meowlomo.ems.core.service.base.TaskService;
import com.meowlomo.ems.core.service.filter.TaskFilterService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@Path("/jobs")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "job resources", produces = "application/json")
public class JobResource {

    private final Logger logger = LoggerFactory.getLogger(JobResource.class);

    @Context
    UriInfo uriInfo;

    @Context
    SecurityContext securityContext;

    @Context
    HttpServletRequest httpRequest;

    @Autowired
    private SearchExampleGenerator searchExampleGenerator;

    private static final String ERROR_TYPE = "JOB";

    @Autowired
    private JobService jobService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskReferenceService taskReferenceService;

    @Autowired
    private JobReferenceService jobReferenceService;
    
    @Autowired
    private TaskFilterService jobTaskService;

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
    @ApiOperation(value = "读取Job", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoErrorResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "输入搜索条件name,模糊搜索时在搜索条件两侧加%,如%xx%", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "priority", value = "job priority", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "job type [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "uuid", value = "job UUID", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "job status [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "start date [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "end date [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "pageNumber", value = "page number", required = false, defaultValue = "1", allowEmptyValue = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "page size", required = false, defaultValue = "20", allowEmptyValue = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "orderBy", value = "输入排序条件orderBy,比如按创建时间排序,正序为createdAt asc，降序为createdAt desc", required = false, dataType = "string", paramType = "query") })
    public MeowlomoResponse select() {
        logger.debug("received job select call");
        try {
            MultivaluedMap<String, String> queryParams = this.uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {
                JobExample example = new JobExample();
                example.or().andIdIsNotNull();
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(this.uriInfo);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", jobService.countByExample(example));
                List<com.meowlomo.ems.core.model.Job> records = jobService.selectByExampleWithRowbounds(example,
                        rowBounds);
                return new MeowlomoResponse(metadata, records, null);
            }
            else {
                JobExample example = this.searchExampleGenerator.generateExample(this.uriInfo, null, JobExample.class);
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(this.uriInfo);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", jobService.countByExample(example));
                List<com.meowlomo.ems.core.model.Job> records = null;
                if (queryParams.containsKey("ref")) {
                    records = jobReferenceService.selectByExampleWithRowbounds(example, rowBounds);
                }
                else {
                    records = jobService.selectByExampleWithRowbounds(example, rowBounds);
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
    @ApiOperation(value = "读取单个Job", response = MeowlomoResponse.class, httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoErrorResponse.class) })
    @ApiImplicitParam(name = "uuid", value = "job UUID", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse selectByUUID(@PathParam("uuid") String uuidString) throws ServerErrorException {
        logger.debug("received job select by primary uuid call");
        try {
            MultivaluedMap<String, String> queryParams = this.uriInfo.getQueryParameters();
            UUID uuid = UUID.fromString(uuidString);
            JobExample example = new JobExample();
            example.or().andUuidEqualTo(uuid);
            List<com.meowlomo.ems.core.model.Job> selectRecord = null;
            if (queryParams.containsKey("ref")) {
                selectRecord = jobReferenceService.selectByExample(example);
            }
            else {
                selectRecord = jobService.selectByExample(example);
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
    @ApiOperation(value = "删除Job", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "DELETE")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoErrorResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "指定删除的对象不存在，请检查。错误唯一码[\"+exuuid+\"]", response = MeowlomoErrorResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "priority", value = "job priority", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "job type [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "uuid", value = "job UUID", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "job status [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "start date [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "end date [unix second]", required = false, dataType = "long", paramType = "query") })
    public MeowlomoResponse delete() throws Exception {
        logger.debug("received job inactive call");
        try {
            MultivaluedMap<String, String> queryParams = this.uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {
                JobExample example = new JobExample();
                example.or().andIdIsNotNull();
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                List<com.meowlomo.ems.core.model.Job> records = jobService.selectByExample(example);
                metadata.put("count", jobService.countByExample(example));
                int inactiveResult = jobService.deleteByExample(example);
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
                com.meowlomo.ems.core.model.JobExample.Criteria crieria = new JobExample().or();
                crieria.andIdIsNotNull();
                JobExample example = this.searchExampleGenerator.generateExample(this.uriInfo, crieria, JobExample.class);
                List<com.meowlomo.ems.core.model.Job> records = jobService.selectByExample(example);
                int inactiveResult = jobService.deleteByExample(example);
                if (inactiveResult == records.size()) {
                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                    metadata.put("count", jobService.countByExample(example));
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
    @ApiOperation(value = "删除单个Job", response = MeowlomoResponse.class, httpMethod = "DELETE")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "不存在UUID为\"+uuid+\"的对象，无法冻结。问题唯一码[\"+exuuid+\"]", response = MeowlomoErrorResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoErrorResponse.class) })
    @ApiImplicitParam(name = "uuid", value = "job UUID", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse deleteByUUID(@PathParam("uuid") String uuidString) throws Exception {
        logger.debug("received job inactive by UUID call");
        try {
            // select the record first
            UUID uuid = UUID.fromString(uuidString);
            JobExample example = new JobExample();
            example.or().andUuidEqualTo(uuid);
            com.meowlomo.ems.core.model.Job record = jobService.selectByExample(example).get(0);
            int deleteResult = jobService.deleteByExample(example);
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
    @ApiOperation(value = "更新Job", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "PATCH")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoErrorResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "更改操作无法完成，请与管理员联系。并提供唯一码[\"+exuuid+\"]", response = MeowlomoErrorResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "priority", value = "job priority", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "job type [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "uuid", value = "job UUID", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "job status [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "start date [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "end date [unix second]", required = false, dataType = "long", paramType = "query") })
    public MeowlomoResponse updateSelective(com.meowlomo.ems.core.model.Job[] records) throws Exception {
        logger.debug("received patch job call " + uriInfo.toString());
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
                com.meowlomo.ems.core.model.Job record = records[i];
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
                    com.meowlomo.ems.core.model.Job record = records[i];
                    JobExample example = new JobExample();
                    example.or().andUuidEqualTo(records[i].getUuid());
                    int updateResult = jobService.updateByExampleSelective(records[i], example);
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
                JobExample example = new JobExample();
                example.or().andUuidIn(targetUuids);
                List<com.meowlomo.ems.core.model.Job> finalRecords = jobService.selectByExample(example);
                // sort return result
                List<com.meowlomo.ems.core.model.Job> finalReturnRecords = new ArrayList<com.meowlomo.ems.core.model.Job>();
                for (UUID uuid : targetUuids) {
                    for (com.meowlomo.ems.core.model.Job oRecord : finalRecords) {
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
    @ApiOperation(value = "替换或添加Job", response = MeowlomoResponse.class, httpMethod = "PUT")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "exception UUID=\" + exuuid + \" put body is empty", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "部分替换请求不含ID。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "部分或全部替换失败，失败序列。\" + ??? + \" 问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    public MeowlomoResponse replace(com.meowlomo.ems.core.model.Job[] records) {
        logger.debug("received put job by primary uuid call");
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
                com.meowlomo.ems.core.model.Job record = records[i];
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
                    com.meowlomo.ems.core.model.Job record = records[i];
                    int updateResult = jobService.updateByPrimaryKey(record);
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
                JobExample example = new JobExample();
                example.or().andUuidIn(targetUuids);
                List<com.meowlomo.ems.core.model.Job> finalRecords = jobService.selectByExample(example);
                // sort return result
                List<com.meowlomo.ems.core.model.Job> finalReturnRecords = new ArrayList<com.meowlomo.ems.core.model.Job>();
                for (UUID uuid : targetUuids) {
                    for (com.meowlomo.ems.core.model.Job oRecord : finalRecords) {
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
    @ApiOperation(value = "添加Job", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "添加操作无法完成，请与管理员联系。并提供唯一码[\"+exuuid+\"]", response = MeowlomoErrorResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoErrorResponse.class) })
    @ApiImplicitParam(name = "uuid", value = "job UUID", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse insert(com.meowlomo.ems.core.model.Job[] records) throws Exception {
        logger.debug("received post job call ");
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
            List<Integer> errorIndex = new ArrayList<Integer>();
            // start the insert
            // insert one by one
            List<Integer> errorTaskInsertIndex = new ArrayList<Integer>();
            for (int i = 0; i < records.length; i++) {
                com.meowlomo.ems.core.model.Job record = records[i];               
                record.setId(null);
                long insertResult = jobService.insertSelective(record);
                if (insertResult != 1) {
                    errorIndex.add(i);
                }
                else {
                    //check linked the tasks
                    List<Task> tasks = records[i].getTasks();
                    if (!tasks.isEmpty()) {
                        for (int taskCount = 0; taskCount < tasks.size(); taskCount++) {
                            Task taskRecord = jobTaskService.processTaskFromJob(tasks.get(taskCount),record);
                            taskRecord.setJobId(record.getId());
                            long insertLinkedTaskResult = taskService.insertSelective(taskRecord);
                            if (insertLinkedTaskResult != 1) {
                                errorTaskInsertIndex.add(taskCount);
                                errorIndex.add(i);
                            }
                        }
                        if(errorTaskInsertIndex.isEmpty()) {
                            ids.add(record.getId());
                        }
                    }
                    else {
                        ids.add(record.getId());
                    }
                }
            }
            // check all insert success
            if (errorIndex.isEmpty()) {
                JobExample example = new JobExample();
                example.or().andIdIn(ids);
                List<com.meowlomo.ems.core.model.Job> finalRecords = jobReferenceService.selectByExample(example);
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
                String message = "部分或全部添加失败，失败序列=>" + errorIndex.toString() +" 关联Task失败序列=>"+errorTaskInsertIndex.toString()+" 问题唯一码[" + exuuid + "]";
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

    // ========================================Link Method
    // Start========================================
    /**
     * Select. default return all
     *
     * @param uuid
     *            the uuid
     * @return the response entity
     * @throws ServerErrorException
     *             the server error exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{uuid}/tasks")
    @ApiOperation(value = "Get task resources by job uuid. Version 1 - (version in URL)", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoErrorResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uuid", value = "job UUID", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "pageNumber", value = "page number", required = false, defaultValue = "0", allowEmptyValue = false, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "page size", required = false, defaultValue = "20", allowEmptyValue = false, dataType = "int", paramType = "path") })
    public MeowlomoResponse selectTasksByJobUUID(@PathParam("uuid") String uuidString) {
        logger.debug("received job select call");
        try {
            UUID uuid = UUID.fromString(uuidString);
            JobExample example = new JobExample();
            example.or().andUuidEqualTo(uuid);
            List<Job> records = jobService.selectByExample(example);
            if (records.isEmpty()) {
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", 0);
                return new MeowlomoResponse(metadata, null, null);
            }
            else {
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                // select the tasks
                TaskExample externalExample = new TaskExample();
                // targetExample.or().andJobIdEqualTo(records.get(0).getId());
                Criteria externalCriteria = externalExample.createCriteria().andJobIdEqualTo(records.get(0).getId());
                TaskExample targetExample = searchExampleGenerator.generateExample(uriInfo, externalCriteria, TaskExample.class);
                long count = taskService.countByExample(targetExample);
                List<Task> targetRecords;
                if (uriInfo.getQueryParameters().containsKey("ref")) {
                    targetRecords = taskReferenceService.selectByExampleWithRowbounds(targetExample, rowBounds);
                }
                else {
                    // not reference call
                    targetRecords = taskService.selectByExampleWithRowbounds(targetExample, rowBounds);
                }
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", count);
                return new MeowlomoResponse(metadata, targetRecords, null);
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
            String code = ERROR_TYPE + "01N";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }
    // ========================================Link Method
    // End========================================
}

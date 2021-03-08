package com.meowlomo.ems.core.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
import com.meowlomo.ems.core.model.Worker;
import com.meowlomo.ems.core.model.WorkerExample;
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
import com.meowlomo.ems.core.service.base.WorkerService;
import com.meowlomo.ems.core.service.util.WorkerUtilService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@Path("/workers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "worker resources", produces = "application/json")
public class WorkerResource {

    private final Logger logger = LoggerFactory.getLogger(WorkerResource.class);

    @Context
    UriInfo uriInfo;

    @Context
    SecurityContext securityContext;

    @Context
    HttpServletRequest httpRequest;

    @Autowired
    private SearchExampleGenerator searchExampleGenerator;

    private static final String ERROR_TYPE = "WRK";

    @Autowired
    private WorkerService workerService;
    
    @Autowired
    private WorkerUtilService workerUtilService;


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
    @ApiOperation(value = "读取Worker", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoErrorResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "输入搜索条件name,模糊搜索时在搜索条件两侧加%,如%xx%", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "priority", value = "worker priority", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "worker type [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "uuid", value = "worker UUID", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "worker status [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "start date [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "end date [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "pageNumber", value = "page number", required = false, defaultValue = "1", allowEmptyValue = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "page size", required = false, defaultValue = "20", allowEmptyValue = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "orderBy", value = "输入排序条件orderBy,比如按创建时间排序,正序为createdAt asc，降序为createdAt desc", required = false, dataType = "string", paramType = "query") })
    public MeowlomoResponse select() {
        logger.debug("received worker select call");
        try {
            MultivaluedMap<String, String> queryParams = this.uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {
                WorkerExample example = new WorkerExample();
                example.or().andIdIsNotNull();
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(this.uriInfo);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", workerService.countByExample(example));
                List<com.meowlomo.ems.core.model.Worker> records = workerService
                        .selectByExampleWithRowbounds(example, rowBounds);
                return new MeowlomoResponse(metadata, records, null);
            }
            else {
                WorkerExample example = this.searchExampleGenerator.generateExample(this.uriInfo, null, WorkerExample.class);
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(this.uriInfo);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", workerService.countByExample(example));
                List<com.meowlomo.ems.core.model.Worker> records = workerService
                        .selectByExampleWithRowbounds(example, rowBounds);
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
    @ApiOperation(value = "读取单个Worker", response = MeowlomoResponse.class, httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoErrorResponse.class) })
    @ApiImplicitParam(name = "uuid", value = "worker UUID", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse selectByUUID(@PathParam("uuid") String uuidString) throws ServerErrorException {
        logger.debug("received worker select by primary uuid call");
        try {
            UUID uuid = UUID.fromString(uuidString);
            WorkerExample example = new WorkerExample();
            example.or().andUuidEqualTo(uuid);
            List<com.meowlomo.ems.core.model.Worker> selectRecord = workerService.selectByExample(example);
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
//    @DELETE
//    @Produces(MediaType.APPLICATION_JSON)
//    @ApiOperation(value = "删除Worker", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "DELETE")
//    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
//            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoErrorResponse.class),
//            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "指定删除的对象不存在，请检查。错误唯一码[\"+exuuid+\"]", response = MeowlomoErrorResponse.class) })
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "name", value = "name", required = false, dataType = "string", paramType = "query"),
//            @ApiImplicitParam(name = "priority", value = "worker priority", required = false, dataType = "int", paramType = "query"),
//            @ApiImplicitParam(name = "type", value = "worker type [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
//            @ApiImplicitParam(name = "uuid", value = "worker UUID", required = false, dataType = "string", paramType = "query"),
//            @ApiImplicitParam(name = "status", value = "worker status [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
//            @ApiImplicitParam(name = "startDate", value = "start date [unix second]", required = false, dataType = "long", paramType = "query"),
//            @ApiImplicitParam(name = "endDate", value = "end date [unix second]", required = false, dataType = "long", paramType = "query") })
//    public MeowlomoResponse delete() throws Exception {
//        logger.debug("received worker inactive call");
//        try {
//            MultivaluedMap<String, String> queryParams = this.uriInfo.getQueryParameters();
//            if (queryParams.isEmpty()) {
//                WorkerExample example = new WorkerExample();
//                example.or().andIdIsNotNull();
//                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
//                List<com.meowlomo.ems.core.model.Worker> records = workerService.selectByExample(example);
//                metadata.put("count", workerService.countByExample(example));
//                int inactiveResult = workerService.deleteByExample(example);
//                if (inactiveResult == records.size()) {
//                    return new MeowlomoResponse(metadata, records, null);
//                }
//                else {
//                    try {
//                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//                    }
//                    catch (Exception e) {
//
//                    }
//                    UUID exuuid = UUID.randomUUID();
//                    String developerMessage = "exception UUID=" + exuuid + " target count <> delete count ";
//                    String message = "无法完成删除，删除数与目标数。问题唯一码[" + exuuid + "]";
//                    String code = ERROR_TYPE + "02DEL";
//                    logger.error(developerMessage, httpRequest.getContextPath());
//                    throw new CustomNotAcceptableException(null, message, developerMessage, code, exuuid);
//                }
//            }
//            else {
//                com.meowlomo.ems.core.model.WorkerExample.Criteria crieria = new WorkerExample().or();
//                crieria.andIdIsNotNull();
//                WorkerExample example = this.searchExampleGenerator.generateExample(this.uriInfo, crieria, WorkerExample.class);
//                List<com.meowlomo.ems.core.model.Worker> records = workerService.selectByExample(example);
//                int inactiveResult = workerService.deleteByExample(example);
//                if (inactiveResult == records.size()) {
//                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
//                    metadata.put("count", workerService.countByExample(example));
//                    return new MeowlomoResponse(metadata, records, null);
//                }
//                else {
//                    try {
//                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//                    }
//                    catch (Exception e) {
//
//                    }
//                    UUID exuuid = UUID.randomUUID();
//                    String developerMessage = "exception UUID=" + exuuid + " target count <> delete count ";
//                    String message = "无法完成删除，删除数与目标数。问题唯一码[" + exuuid + "]";
//                    String code = ERROR_TYPE + "03DEL";
//                    logger.error(developerMessage, httpRequest.getContextPath());
//                    throw new CustomNotAcceptableException(null, message, developerMessage, code, exuuid);
//                }
//            }
//        }
//        catch (CustomNotAuthorizedException | CustomBadRequestException | CustomForbiddenException
//                | CustomNotAcceptableException | CustomNotAllowedException | CustomNotFoundException
//                | CustomNotSupportedException | CustomServiceUnavailableException ex) {
//            throw ex;
//        }
//        catch (Exception ex) {
//            UUID exuuid = UUID.randomUUID();
//            String message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[" + exuuid + "]。";
//            String code = ERROR_TYPE + "04SYS";
//            logger.error(message, ex);
//            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
//        }
//    }

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
    @ApiOperation(value = "删除单个Worker", response = MeowlomoResponse.class, httpMethod = "DELETE")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "不存在UUID为\"+uuid+\"的对象，无法冻结。问题唯一码[\"+exuuid+\"]", response = MeowlomoErrorResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoErrorResponse.class) })
    @ApiImplicitParam(name = "uuid", value = "worker UUID", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse deleteByUUID(@PathParam("uuid") String uuidString) throws Exception {
        logger.debug("received worker inactive by UUID call");
        try {
            // select the record first
            UUID uuid = UUID.fromString(uuidString);
            WorkerExample example = new WorkerExample();
            example.or().andUuidEqualTo(uuid);
            com.meowlomo.ems.core.model.Worker record = workerService.selectByExample(example).get(0);
            int deleteResult = workerService.deleteByExample(example);
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
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
//    @PATCH
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    @ApiOperation(value = "更新Worker", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "PATCH")
//    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
//            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoErrorResponse.class),
//            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "更改操作无法完成，请与管理员联系。并提供唯一码[\"+exuuid+\"]", response = MeowlomoErrorResponse.class) })
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "name", value = "name", required = false, dataType = "string", paramType = "query"),
//            @ApiImplicitParam(name = "priority", value = "worker priority", required = false, dataType = "int", paramType = "query"),
//            @ApiImplicitParam(name = "type", value = "worker type [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
//            @ApiImplicitParam(name = "uuid", value = "worker UUID", required = false, dataType = "string", paramType = "query"),
//            @ApiImplicitParam(name = "status", value = "worker status [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
//            @ApiImplicitParam(name = "startDate", value = "start date [unix second]", required = false, dataType = "long", paramType = "query"),
//            @ApiImplicitParam(name = "endDate", value = "end date [unix second]", required = false, dataType = "long", paramType = "query") })
//    public MeowlomoResponse updateSelective(com.meowlomo.ems.core.model.Worker[] records) throws Exception {
//        logger.debug("received patch worker call " + uriInfo.toString());
//        try {
//            // empty just return
//            if (records == null) {
//                UUID exuuid = UUID.randomUUID();
//                String developerMessage = "exception UUID=" + exuuid + " patch body is empty ";
//                String message = "更新内容为空。问题唯一码[" + exuuid + "]";
//                String code = ERROR_TYPE + "01PAT";
//                logger.error(developerMessage, httpRequest.getContextPath());
//                throw new CustomBadRequestException(null, message, developerMessage, code, exuuid);
//            }
//            else if (records.length == 0) {
//                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
//                metadata.put("count", 0);
//                return new MeowlomoResponse(metadata, null, null);
//            }
//            List<UUID> targetUuids = new ArrayList<UUID>();
//            // loop and check each record
//            for (int i = 0; i < records.length; i++) {
//                com.meowlomo.ems.core.model.Worker record = records[i];
//                if (record.getUuid() != null) {
//                    targetUuids.add(record.getUuid());
//                }
//            }
//            // check all have uuid
//            List<UUID> errorIndex = new ArrayList<UUID>();
//            if (records.length != targetUuids.size()) {
//                UUID exuuid = UUID.randomUUID();
//                String developerMessage = "exception UUID=" + exuuid + " patch body is empty ";
//                String message = "部分更新请求不含ID。问题唯一码[" + exuuid + "]";
//                String code = ERROR_TYPE + "02PAT";
//                logger.error(developerMessage, httpRequest.getContextPath());
//                throw new CustomBadRequestException(null, message, developerMessage, code, exuuid);
//            }
//            else {
//                // start the update
//                // update one by one
//                for (int i = 0; i < records.length; i++) {
//                    com.meowlomo.ems.core.model.Worker record = records[i];
//                    WorkerExample example = new WorkerExample();
//                    example.or().andUuidEqualTo(records[i].getUuid());
//                    int updateResult = workerService.updateByExampleSelective(records[i], example);
//                    if (updateResult != 1) {
//                        errorIndex.add(record.getUuid());
//                    }
//                }
//            }
//            // check all update sucess
//            if (errorIndex.isEmpty()) {
//                try {
//                    TransactionAspectSupport.currentTransactionStatus().isCompleted();
//                }
//                catch (Exception e) {
//
//                }
//                WorkerExample example = new WorkerExample();
//                example.or().andUuidIn(targetUuids);
//                List<com.meowlomo.ems.core.model.Worker> finalRecords = workerService.selectByExample(example);
//                // sort return result
//                List<com.meowlomo.ems.core.model.Worker> finalReturnRecords = new ArrayList<com.meowlomo.ems.core.model.Worker>();
//                for (UUID uuid : targetUuids) {
//                    for (com.meowlomo.ems.core.model.Worker oRecord : finalRecords) {
//                        if (oRecord.getUuid().equals(uuid)) {
//                            finalReturnRecords.add(oRecord);
//                        }
//                    }
//                }
//                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
//                metadata.put("count", finalRecords.size());
//                return new MeowlomoResponse(metadata, finalReturnRecords, null);
//            }
//            else {// not all success
//                try {
//                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//                }
//                catch (Exception e) {
//
//                }
//                UUID exuuid = UUID.randomUUID();
//                String developerMessage = "exception UUID=" + exuuid + " could not path all record ";
//                String message = "部分或全部更新失败，失败序列。" + errorIndex.toString() + " 问题唯一码[" + exuuid + "]";
//                String code = ERROR_TYPE + "03PAT";
//                logger.error(developerMessage, httpRequest.getContextPath());
//                throw new CustomNotAcceptableException(null, message, developerMessage, code, exuuid);
//            }
//
//        }
//        catch (CustomNotAuthorizedException | CustomBadRequestException | CustomForbiddenException
//                | CustomNotAcceptableException | CustomNotAllowedException | CustomNotFoundException
//                | CustomNotSupportedException | CustomServiceUnavailableException ex) {
//            throw ex;
//        }
//        catch (Exception ex) {
//            UUID exuuid = UUID.randomUUID();
//            String message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[" + exuuid + "]。";
//            String code = ERROR_TYPE + "05SYS";
//            logger.error(message, ex);
//            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
//        }
//    }

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
//    @PUT
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    @ApiOperation(value = "替换或添加Worker", response = MeowlomoResponse.class, httpMethod = "PUT")
//    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
//            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "exception UUID=\" + exuuid + \" put body is empty", response = MeowlomoResponse.class),
//            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "部分替换请求不含ID。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
//            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "部分或全部替换失败，失败序列。\" + ??? + \" 问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
//            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
//    public MeowlomoResponse replace(com.meowlomo.ems.core.model.Worker[] records) {
//        logger.debug("received put worker by primary uuid call");
//        try {
//            // empty just return
//            if (records == null) {
//                UUID exuuid = UUID.randomUUID();
//                String developerMessage = "exception UUID=" + exuuid + " put body is empty";
//                String message = "替换内容为空。问题唯一码[" + exuuid + "]";
//                String code = ERROR_TYPE + "01PUT";
//                logger.error(developerMessage, httpRequest.getContextPath());
//                throw new CustomBadRequestException(null, message, developerMessage, code, exuuid);
//            }
//            else if (records.length == 0) {
//                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
//                metadata.put("count", 0);
//                return new MeowlomoResponse(metadata, null, null);
//            }
//            List<UUID> targetUuids = new ArrayList<UUID>();
//            // loop and check each record
//            for (int i = 0; i < records.length; i++) {
//                com.meowlomo.ems.core.model.Worker record = records[i];
//                if (record.getUuid() != null) {
//                    targetUuids.add(record.getUuid());
//                }
//            }
//            // check all have uuid
//            List<UUID> errorIndex = new ArrayList<UUID>();
//            if (records.length != targetUuids.size()) {
//                UUID exuuid = UUID.randomUUID();
//                String developerMessage = "exception UUID=" + exuuid + " patch body is empty ";
//                String message = "部分替换请求不含ID。问题唯一码[" + exuuid + "]";
//                String code = ERROR_TYPE + "02PUT";
//                logger.error(developerMessage, httpRequest.getContextPath());
//                throw new CustomBadRequestException(null, message, developerMessage, code, exuuid);
//            }
//            else {
//                // start the update
//                // update one by one
//                for (int i = 0; i < records.length; i++) {
//                    com.meowlomo.ems.core.model.Worker record = records[i];
//                    int updateResult = workerService.updateByPrimaryKey(record);
//                    if (updateResult != 1) {
//                        errorIndex.add(record.getUuid());
//                    }
//                }
//            }
//            // check all update sucess
//            if (errorIndex.isEmpty()) {
//                try {
//                    TransactionAspectSupport.currentTransactionStatus().isCompleted();
//                }
//                catch (Exception e) {
//
//                }
//                WorkerExample example = new WorkerExample();
//                example.or().andUuidIn(targetUuids);
//                List<com.meowlomo.ems.core.model.Worker> finalRecords = workerService.selectByExample(example);
//                // sort return result
//                List<com.meowlomo.ems.core.model.Worker> finalReturnRecords = new ArrayList<com.meowlomo.ems.core.model.Worker>();
//                for (UUID uuid : targetUuids) {
//                    for (com.meowlomo.ems.core.model.Worker oRecord : finalRecords) {
//                        if (oRecord.getUuid() == uuid) {
//                            finalReturnRecords.add(oRecord);
//                        }
//                    }
//                }
//                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
//                metadata.put("count", finalRecords.size());
//                return new MeowlomoResponse(metadata, finalReturnRecords, null);
//            }
//            else {// not all success
//                try {
//                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//                }
//                catch (Exception e) {
//
//                }
//                UUID exuuid = UUID.randomUUID();
//                String developerMessage = "exception UUID=" + exuuid + " could not path all record ";
//                String message = "部分或全部替换失败，失败序列。" + errorIndex.toString() + " 问题唯一码[" + exuuid + "]";
//                String code = ERROR_TYPE + "03PUT";
//                logger.error(developerMessage, httpRequest.getContextPath());
//                throw new CustomForbiddenException(null, message, developerMessage, code, exuuid);
//            }
//        }
//        catch (CustomNotAuthorizedException | CustomBadRequestException | CustomForbiddenException
//                | CustomNotAcceptableException | CustomNotAllowedException | CustomNotFoundException
//                | CustomNotSupportedException | CustomServiceUnavailableException ex) {
//            throw ex;
//        }
//        catch (Exception ex) {
//            UUID exuuid = UUID.randomUUID();
//            String message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[" + exuuid + "]。";
//            String code = ERROR_TYPE + "06SYS";
//            logger.error(message, ex);
//            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
//        }
//    }

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
    @ApiOperation(value = "Create a worker resource. Version 1 - (version in URL)", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "添加操作无法完成，请与管理员联系。并提供唯一码[\"+exuuid+\"]", response = MeowlomoErrorResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoErrorResponse.class) })
    @ApiImplicitParam(name = "uuid", value = "worker UUID", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse insert(Worker[] records) throws Exception {
        logger.debug("received post worker call " + records.toString());
        try {
            // empty just return
            if (records == null || records.length == 0) {
                UUID exuuid = UUID.randomUUID();
                String developerMessage = "exception UUID=" + exuuid + " patch body is empty ";
                String message = "注册内容为空。问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "01POS";
                logger.error(developerMessage, httpRequest.getContextPath());
                throw new CustomBadRequestException(null, message, developerMessage, code, exuuid);
            }
            // Check the UUIDs
            List<UUID> uuidList = new ArrayList<UUID>();
            for (int i = 0 ; i < records.length; i++) {
                Worker record = records[i];
                if (record.getUuid() != null) {
                    uuidList.add(record.getUuid());
                }
            }
            if (uuidList.size() != records.length) {
                UUID exuuid = UUID.randomUUID();
                String developerMessage = "exception UUID=" + exuuid + " couldn't execute insert. some recods' UUID is missing";
                String code = ERROR_TYPE + "10B";
                String message = "缺少UUID，注册操作无法完成，请与管理员联系。并提供唯一码[" + exuuid + "]";
                logger.error(developerMessage, httpRequest.getContextPath());
                throw new CustomBadRequestException(null, message, developerMessage, code, exuuid);
            }
            else {
                List<UUID> uuids = new ArrayList<UUID>();
                // check all have id
                List<UUID> errorIndex = new ArrayList<UUID>();
                //insert the worker one by one 
                // check the work exists
                for (int workerCount = 0 ; workerCount < records.length; workerCount++) {
                    Worker record = records[workerCount];
                    //check worker exsist
                    WorkerExample example = new WorkerExample();
                    example.or().andUuidEqualTo(record.getUuid());
                    List<Worker> exsistRecords = workerService.selectByExample(example);
                    if (!exsistRecords.isEmpty()) {
                        logger.debug("Existing worker reregistration.");
                        /*
                         * we may need to do the following, this api will only be called by worker
                         * register 1: set the status to free 2: clear the linked tasks if any
                         */
                        // Update worker to free
                        // Worker updateWorker = new Worker();
                        // updateWorker.setStatus("FREE");
                        WorkerExample updateExample = new WorkerExample();
                        updateExample.or().andUuidEqualTo(record.getUuid());
                        Worker originWorker = workerService.selectByUuid(record.getUuid());
                        Worker updateWorker = workerUtilService.generateWorkerFieldsForUpdate(record,originWorker);
                        updateWorker.setId(originWorker.getId());
                        updateWorker.setUuid(originWorker.getUuid());
                        updateWorker.setStatus("FREE");
                        updateWorker.setActive(true);
                        updateWorker.setTask(null);
                        // generate the token for this registration
                        updateWorker.setManageable(true);
                        updateWorker.setToken(UUID.randomUUID());
                        int updateWorkerResult = workerService.updateByExample(updateWorker, updateExample); 

                        if(updateWorkerResult == 1) {
                            uuids.add(record.getUuid());
                        }
                        else {
                            errorIndex.add(record.getUuid());
                        }

                    }
                    else { 
                        // insert new worker
                        // generate the token for this registration
                        record.setToken(UUID.randomUUID());
                        long insertWorkerResult = workerService.insertSelective(record); 
                        if(insertWorkerResult == 1) {
                            uuids.add(record.getUuid());
                        }
                        else {
                            errorIndex.add(record.getUuid());
                        }                        
                    }                    
                }
                // check all insert success
                if (errorIndex.isEmpty()) {
                    try {
                        TransactionAspectSupport.currentTransactionStatus().isCompleted();
                    }catch(Exception ex) {
                        
                    }
                    WorkerExample example = new WorkerExample();
                    example.or().andUuidIn(uuids);
                    List<Worker> finalRecords = workerService.selectByExample(example);
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
                    String developerMessage = "exception UUID=" + exuuid + " could not register all records ";
                    String message = "部分或全部添加失败，失败序列。" + errorIndex.toString() + " 问题唯一码[" + exuuid + "]";
                    String code = ERROR_TYPE + "02POS";
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
            String message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[" + exuuid + "]。";
            String code = ERROR_TYPE + "01N";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }
    // ========================================Post Method
    // End========================================
}

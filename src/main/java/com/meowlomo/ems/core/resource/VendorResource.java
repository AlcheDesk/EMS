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
import com.meowlomo.ems.core.model.Vendor;
import com.meowlomo.ems.core.model.VendorExample;
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
import com.meowlomo.ems.core.service.base.VendorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@Path("/vendors")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "vendor resources", produces = "application/json")
public class VendorResource {

    private final Logger logger = LoggerFactory.getLogger(VendorResource.class);

    @Context
    UriInfo uriInfo;

    @Context
    SecurityContext securityContext;

    @Context
    HttpServletRequest httpRequest;

    @Autowired
    private SearchExampleGenerator searchExampleGenerator;

    private static final String ERROR_TYPE = "COM";

    @Autowired
    private VendorService vendorService;

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
    @ApiOperation(value = "读取Vendor", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoErrorResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "输入搜索条件name,模糊搜索时在搜索条件两侧加%,如%xx%", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "priority", value = "vendor priority", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "vendor type [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "uuid", value = "vendor UUID", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "vendor status [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "start date [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "end date [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "pageNumber", value = "page number", required = false, defaultValue = "1", allowEmptyValue = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "page size", required = false, defaultValue = "20", allowEmptyValue = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "orderBy", value = "输入排序条件orderBy,比如按创建时间排序,正序为createdAt asc，降序为createdAt desc", required = false, dataType = "string", paramType = "query") })
    public MeowlomoResponse select() {
        logger.debug("received vendor select call");
        try {
            MultivaluedMap<String, String> queryParams = this.uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {
                VendorExample example = new VendorExample();
                example.or().andIdIsNotNull();
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(this.uriInfo);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", vendorService.countByExample(example));
                List<com.meowlomo.ems.core.model.Vendor> records = vendorService
                        .selectByExampleWithRowbounds(example, rowBounds);
                return new MeowlomoResponse(metadata, records, null);
            }
            else {
                VendorExample example = this.searchExampleGenerator.generateExample(this.uriInfo, null, VendorExample.class);
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(this.uriInfo);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", vendorService.countByExample(example));
                List<com.meowlomo.ems.core.model.Vendor> records = null;
                records = vendorService.selectByExampleWithRowbounds(example, rowBounds);
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
     * Select by primary id.
     *
     * @param id
     *            the id
     * @return the meowlomo response
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取单个Vendor", response = MeowlomoResponse.class, httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParam(name = "id", value = "instruction id", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse selectByPrimaryId(@PathParam("id") Long id) {
        logger.debug("received vendor select by id call");
        try {
            Vendor selectRecord = vendorService.selectByPrimaryKey(id);
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
    @ApiOperation(value = "删除Vendor", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "DELETE")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoErrorResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "指定删除的对象不存在，请检查。错误唯一码[\"+exuuid+\"]", response = MeowlomoErrorResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "priority", value = "vendor priority", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "vendor type [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "uuid", value = "vendor UUID", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "vendor status [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "start date [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "end date [unix second]", required = false, dataType = "long", paramType = "query") })
    public MeowlomoResponse delete() throws Exception {
        logger.debug("received vendor inactive call");
        try {
            MultivaluedMap<String, String> queryParams = this.uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {
                VendorExample example = new VendorExample();
                example.or().andIdIsNotNull();
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                List<com.meowlomo.ems.core.model.Vendor> records = vendorService.selectByExample(example);
                metadata.put("count", vendorService.countByExample(example));
                int inactiveResult = vendorService.deleteByExample(example);
                if (inactiveResult == records.size()) {
                    return new MeowlomoResponse(metadata, records, null);
                }
                else {
                    try {
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
                com.meowlomo.ems.core.model.VendorExample.Criteria crieria = new VendorExample().or();
                crieria.andIdIsNotNull();
                VendorExample example = this.searchExampleGenerator.generateExample(this.uriInfo, crieria, VendorExample.class);
                List<com.meowlomo.ems.core.model.Vendor> records = vendorService.selectByExample(example);
                int inactiveResult = vendorService.deleteByExample(example);
                if (inactiveResult == records.size()) {
                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                    metadata.put("count", vendorService.countByExample(example));
                    return new MeowlomoResponse(metadata, records, null);
                }
                else {
                    try {
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
     * Delete by ID.
     *
     * @param id
     *            the id
     * @return the meowlomo response
     */
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "删除单个Vendor", response = MeowlomoResponse.class, httpMethod = "DELETE")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParam(name = "id", value = "task id", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse deleteByID(@PathParam("id") long id) {
        logger.debug("received vendor delete by id call " + uriInfo.getPath());
        try {
            // select the record first
            Vendor record = vendorService.selectByPrimaryKey(id);
            int deleteResult = vendorService.deleteByPrimaryKey(id);
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
                String developerMessage = "exception UUID=" + exuuid + " resource with UUID = " + id
                        + " does not exists.";
                String message = "不存在ID为" + id + "的对象，无法更新。问题唯一码[" + exuuid + "]";
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
    @ApiOperation(value = "更新Vendor", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "PATCH")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoErrorResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "更改操作无法完成，请与管理员联系。并提供唯一码[\"+exuuid+\"]", response = MeowlomoErrorResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "priority", value = "vendor priority", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "vendor type [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "uuid", value = "vendor UUID", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "vendor status [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "start date [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "end date [unix second]", required = false, dataType = "long", paramType = "query") })
    public MeowlomoResponse updateSelective(com.meowlomo.ems.core.model.Vendor[] records) throws Exception {
        logger.debug("received patch vendor call " + uriInfo.toString());
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
            List<Long> targetIds = new ArrayList<Long>();
            // loop and check each record
            for (int i = 0; i < records.length; i++) {
                com.meowlomo.ems.core.model.Vendor record = records[i];
                if (record.getId() != null && record.getId() > 0) {
                    targetIds.add(record.getId());
                }
            }
            // check all have id
            List<Long> errorIndex = new ArrayList<Long>();
            if (records.length != targetIds.size()) {
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
                    com.meowlomo.ems.core.model.Vendor record = records[i];
                    int updateResult = vendorService.updateByPrimaryKeySelective(records[i]);
                    if (updateResult != 1) {
                        errorIndex.add(record.getId());
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
                VendorExample example = new VendorExample();
                example.or().andIdIn(targetIds);
                List<com.meowlomo.ems.core.model.Vendor> finalRecords = vendorService.selectByExample(example);
                // sort return result
                List<com.meowlomo.ems.core.model.Vendor> finalReturnRecords = new ArrayList<com.meowlomo.ems.core.model.Vendor>();
                for (Long id : targetIds) {
                    for (com.meowlomo.ems.core.model.Vendor oRecord : finalRecords) {
                        if (oRecord.getId() == id) {
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
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
     * @param id
     *            the id
     * @param record
     *            the record
     * @return the meowlomo response
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "替换或添加Vendor", response = MeowlomoResponse.class, httpMethod = "PUT")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "exception UUID=\" + exuuid + \" put body is empty", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "部分替换请求不含ID。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "部分或全部替换失败，失败序列。\" + ??? + \" 问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    public MeowlomoResponse replace(Vendor[] records) {
        logger.debug("received put vendor by primary id call");
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
            List<Long> targetIds = new ArrayList<Long>();
            // loop and check each record
            for (int i = 0; i < records.length; i++) {
                Vendor record = records[i];
                if (record.getId() != null && record.getId() > 0) {
                    targetIds.add(record.getId());
                }
            }
            // check all have id
            List<Long> errorIndex = new ArrayList<Long>();
            if (records.length != targetIds.size()) {
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
                    Vendor record = records[i];
                    int updateResult = vendorService.updateByPrimaryKey(record);
                    if (updateResult != 1) {
                        errorIndex.add(record.getId());
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
                VendorExample example = new VendorExample();
                example.or().andIdIn(targetIds);
                List<Vendor> finalRecords = vendorService.selectByExample(example);
                // sort return result
                List<Vendor> finalReturnRecords = new ArrayList<Vendor>();
                for (Long id : targetIds) {
                    for (Vendor oRecord : finalRecords) {
                        if (oRecord.getId() == id) {
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
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
    @ApiOperation(value = "添加Vendor", response = MeowlomoResponse.class, httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "添加操作无法完成，请与管理员联系。并提供唯一码[\"+exuuid+\"]", response = MeowlomoErrorResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoErrorResponse.class) })
    @ApiImplicitParam(name = "uuid", value = "vendor UUID", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse insert(com.meowlomo.ems.core.model.Vendor[] records) throws Exception {
        logger.debug("received post vendor call ");
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
                com.meowlomo.ems.core.model.Vendor record = records[i];
                record.setId(null);
                long insertResult = vendorService.insertSelective(record);
                if (insertResult != 1) {
                    errorIndex.add(record.getId());
                }
                else {
                    ids.add(record.getId());
                }
            }
            // check all insert success
            if (errorIndex.isEmpty()) {
                VendorExample example = new VendorExample();
                example.or().andIdIn(ids);
                List<com.meowlomo.ems.core.model.Vendor> finalRecords = vendorService.selectByExample(example);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", finalRecords.size());
                return new MeowlomoResponse(metadata, finalRecords, null);
            }
            else {// not all success
                try {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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

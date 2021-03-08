package com.meowlomo.ems.core.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.meowlomo.ems.core.coordination.ShareData;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@Path("/shareData")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "share data resources", produces = "application/json")
public class ShareDataResource {

    private final Logger logger = LoggerFactory.getLogger(ShareDataResource.class);

    @Context
    UriInfo uriInfo;

    @Context
    SecurityContext securityContext;

    @Context
    HttpServletRequest httpRequest;

    private static final String ERROR_TYPE = "SD";

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
    @ApiOperation(value = "读取share data", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoErrorResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "输入搜索条件name,模糊搜索时在搜索条件两侧加%,如%xx%", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "priority", value = "share data priority", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "share data type [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "uuid", value = "share data UUID", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "share data status [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "start date [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "end date [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "pageNumber", value = "page number", required = false, defaultValue = "1", allowEmptyValue = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "page size", required = false, defaultValue = "20", allowEmptyValue = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "orderBy", value = "输入排序条件orderBy,比如按创建时间排序,正序为createdAt asc，降序为createdAt desc", required = false, dataType = "string", paramType = "query") })
    public MeowlomoResponse select() {
        logger.debug("received share data select call");
        try {
            Map<UUID, HashMap<String, String>> shareDataMap = ShareData.getShareDataMap();
            ObjectNode metadata = JsonNodeFactory.instance.objectNode();
            metadata.put("count", ShareData.getShareDataMap().size());
            Set<Entry<UUID, HashMap<String, String>>> entrySet = shareDataMap.entrySet();
            List<Entry<UUID, HashMap<String, String>>> shareDataList = new ArrayList<Entry<UUID, HashMap<String, String>>>();
            shareDataList.addAll(entrySet);
            return new MeowlomoResponse(metadata, shareDataList, null);
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
    @Path("/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取单个share data", response = MeowlomoResponse.class, httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParam(name = "id", value = "instruction id", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse selectByPrimaryId(@PathParam("uuid") UUID uuid) {
        logger.debug("received share data select by uuid call");
        try {
            Map<UUID, HashMap<String, String>> shareDataMap = ShareData.getShareDataMap();
            if (shareDataMap.containsKey(uuid)) {
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", 1);
                HashMap<String, String> entry = shareDataMap.get(uuid);
                List<Entry<String, String>> shareDataList = new ArrayList<Entry<String, String>>();
                shareDataList.addAll(entry.entrySet());
                return new MeowlomoResponse(metadata, shareDataList, null);
            }
            else {
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", ShareData.getShareDataMap().size());
                Set<Entry<UUID, HashMap<String, String>>> entrySet = shareDataMap.entrySet();
                List<Entry<UUID, HashMap<String, String>>> shareDataList = new ArrayList<Entry<UUID, HashMap<String, String>>>();
                shareDataList.addAll(entrySet);
                return new MeowlomoResponse(metadata, shareDataList, null);
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
    
    @GET
    @Path("/{uuid}/{key}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取单个share data", response = MeowlomoResponse.class, httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParam(name = "id", value = "instruction id", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse selectValue(@PathParam("uuid") UUID uuid, @PathParam("key") String key) {
        logger.debug("received share data get value call");
        try {
                String value = ShareData.getValue(uuid, key);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", value == null? 0 : 1);
                return new MeowlomoResponse(metadata, value, null);
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
    @ApiOperation(value = "删除share data", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "DELETE")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoErrorResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "指定删除的对象不存在，请检查。错误唯一码[\"+exuuid+\"]", response = MeowlomoErrorResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "priority", value = "share data priority", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "share data type [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "uuid", value = "share data UUID", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "share data status [ALL UPPER CASE]", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "start date [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "end date [unix second]", required = false, dataType = "long", paramType = "query") })
    public MeowlomoResponse delete() throws Exception {
        logger.debug("received share data delete call");
        try {
            Map<UUID, HashMap<String, String>> shareDataMap = ShareData.getShareDataMap();
            ShareData.deleteShareDataMap();
            ObjectNode metadata = JsonNodeFactory.instance.objectNode();
            metadata.put("count", ShareData.getShareDataMap().size());
            Set<Entry<UUID, HashMap<String, String>>> entrySet = shareDataMap.entrySet();
            List<Entry<UUID, HashMap<String, String>>> shareDataList = new ArrayList<Entry<UUID, HashMap<String, String>>>();
            shareDataList.addAll(entrySet);
            return new MeowlomoResponse(metadata, shareDataList, null);
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
    @Path("/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "删除单个share data", response = MeowlomoResponse.class, httpMethod = "DELETE")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParam(name = "id", value = "task id", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse deleteMap(@PathParam("uuid") UUID uuid) {
        logger.debug("received share data delete by uuid call " + uriInfo.getPath());
        try {
            HashMap<String, String> record = ShareData.getShareDataMap().get(uuid);
            if (ShareData.deleteShareDataMapByUuid(uuid)) {
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", 1);
                return new MeowlomoResponse(metadata, record, null);
            }
            else {
                UUID exuuid = UUID.randomUUID();
                String developerMessage = "exception UUID=" + exuuid + " could not path all record ";
                String message = "部分或删除更新失败。 问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "03PAT";
                logger.error(developerMessage, httpRequest.getContextPath());
                throw new CustomInternalServerErrorException(null, message, developerMessage, code, exuuid);
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

    @DELETE
    @Path("/{uuid}/{key}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "删除单个share data", response = MeowlomoResponse.class, httpMethod = "DELETE")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParam(name = "id", value = "task id", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse deleteValue(@PathParam("uuid") UUID uuid, @PathParam("key") String key) {
        logger.debug("received share data delete value call " + uriInfo.getPath());
        try {
            String value = ShareData.getValue(uuid, key);
            if (ShareData.deleteValue(uuid, key)) {
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", 1);
                return new MeowlomoResponse(metadata, value, null);
            }
            else {
                UUID exuuid = UUID.randomUUID();
                String developerMessage = "exception UUID=" + exuuid + " could not path all record ";
                String message = "部分或删除更新失败。 问题唯一码[" + exuuid + "]";
                String code = ERROR_TYPE + "03PAT";
                logger.error(developerMessage, httpRequest.getContextPath());
                throw new CustomInternalServerErrorException(null, message, developerMessage, code, exuuid);
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
     * 
     * 
     * 
     * 
     */
    
    @PUT
    @Path("/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "替换或添加share data", response = MeowlomoResponse.class, httpMethod = "PUT")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "exception UUID=\" + exuuid + \" put body is empty", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "部分替换请求不含ID。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "部分或全部替换失败，失败序列。\" + ??? + \" 问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    public MeowlomoResponse replaceMap(@PathParam("uuid") UUID uuid, Map<String, String> records) {
        logger.debug("received put share data by primary id call");
        try {
            ShareData.putMap(uuid, records);
            ObjectNode metadata = JsonNodeFactory.instance.objectNode();
            metadata.put("count", 1);
            HashMap<String, String> entry = ShareData.getShareDataMap().get(uuid);
            List<Entry<String, String>> shareDataList = new ArrayList<Entry<String, String>>();
            shareDataList.addAll(entry.entrySet());
            return new MeowlomoResponse(metadata, shareDataList, null);
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

    @PUT
    @Path("/{uuid}/{key}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "替换或添加share data", response = MeowlomoResponse.class, httpMethod = "PUT")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "exception UUID=\" + exuuid + \" put body is empty", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "部分替换请求不含ID。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "部分或全部替换失败，失败序列。\" + ??? + \" 问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    public MeowlomoResponse replaceValue(@PathParam("uuid") UUID uuid, @PathParam("key") String key, String value) {
        logger.debug("received put share data by primary id call");
        try {
            ShareData.putValue(uuid, key, value);
            ObjectNode metadata = JsonNodeFactory.instance.objectNode();
            metadata.put("count", 1);
            String recordValue = ShareData.getValue(uuid, key);
            return new MeowlomoResponse(metadata, recordValue, null);
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
}

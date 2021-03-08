package com.meowlomo.ems.core.resource;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.meowlomo.ems.core.model.WorkerExample;
import com.meowlomo.ems.core.model.custom.WorkerStatus;
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
import com.meowlomo.ems.core.service.custom.WorkerStatusService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@Path("/status")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "worker resources", produces = "application/json")
public class InfoStatusResource {

    private final Logger logger = LoggerFactory.getLogger(InfoStatusResource.class);

    @Context
    UriInfo uriInfo;

    @Context
    SecurityContext securityContext;

    @Context
    HttpServletRequest httpRequest;

    @Autowired
    private SearchExampleGenerator searchExampleGenerator;

    private static final String ERROR_TYPE = "STS";

    @Autowired
    private WorkerStatusService workerStatusService;


    // ========================================Get Method
    // Start========================================

    /**
     * Select. default return all
     *
     * @return the response entity
     * @throws ServerErrorException
     *             the server error exception
     */

    @GET
    @Path("/workers")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取WorkerStatus", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "GET")
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
                metadata.put("count", workerStatusService.countByExample(example));
                List<WorkerStatus> records = workerStatusService.selectByExampleWithRowbounds(example, rowBounds);
                return new MeowlomoResponse(metadata, records, null);
            }
            else {
                WorkerExample example = this.searchExampleGenerator.generateExample(this.uriInfo, null, WorkerExample.class);
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(this.uriInfo);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", workerStatusService.countByExample(example));
                List<WorkerStatus> records = workerStatusService.selectByExampleWithRowbounds(example, rowBounds);
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
    @Path("/worker/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取单个WorkerStatus", response = MeowlomoResponse.class, httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoErrorResponse.class) })
    @ApiImplicitParam(name = "uuid", value = "worker UUID", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse selectByUUID(@PathParam("uuid") String uuidString) throws ServerErrorException {
        logger.debug("received worker select by primary uuid call");
        try {
            UUID uuid = UUID.fromString(uuidString);
            WorkerExample example = new WorkerExample();
            example.or().andUuidEqualTo(uuid);
            List<WorkerStatus> selectRecord = workerStatusService.selectByExample(example);
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
}

package com.meowlomo.ems.core.resource.exception.mapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.meowlomo.ems.core.resource.exception.CustomNotAuthorizedException;
import com.meowlomo.ems.core.resource.model.MeowlomoErrorResponse;
import com.meowlomo.ems.core.resource.model.MeowlomoResponse;

@Component
@Provider
public class CustomNotAuthorizedExceptionMapper implements ExceptionMapper<CustomNotAuthorizedException> {

    private static final Logger logger = LoggerFactory.getLogger(CustomNotAuthorizedException.class);

    @Override
    public Response toResponse(CustomNotAuthorizedException exception) {

        MeowlomoErrorResponse restErrorResponse = new MeowlomoErrorResponse(exception.getStatus(),
                // type
                exception.getType(),
                // message
                exception.getMessage(),
                // developer message
                exception.getDeveloperMessage(), exception.getCode(), exception.getUuid());

        MeowlomoResponse response = new MeowlomoResponse(null, null, restErrorResponse);

        logger.error(response.toString());

        return Response.status(restErrorResponse.getStatusCode()).entity(response).type(MediaType.APPLICATION_JSON)
                .build();
    }

}

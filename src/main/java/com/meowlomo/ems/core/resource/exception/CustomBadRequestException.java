package com.meowlomo.ems.core.resource.exception;

import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.BadRequestException;

import com.meowlomo.ems.config.Constant;

public class CustomBadRequestException extends BadRequestException {

    private static final long serialVersionUID = 9115226990509052440L;

    private String message;
    private String developerMessage;
    private int status;
    private String code;
    private String type;
    private UUID uuid;

    public CustomBadRequestException(Exception ex, String message, String developerMessage, String code, UUID uuid) {
        this.message = message;
        this.developerMessage = developerMessage;
        this.type = ex == null ? BadRequestException.class.getName() : ex.getClass().getName();
        this.uuid = uuid;
        this.code = code == null ? Constant.APP_NAME + HttpServletResponse.SC_BAD_REQUEST
                : Constant.APP_NAME + HttpServletResponse.SC_BAD_REQUEST + code;
        this.status = HttpServletResponse.SC_BAD_REQUEST;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

}

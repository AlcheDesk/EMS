package com.meowlomo.ems.core.resource.exception;

import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.core.MediaType;

import com.meowlomo.ems.config.Constant;

public class CustomNotAllowedException extends NotAllowedException {

    private static final long serialVersionUID = 9115226990509052440L;

    private String message;
    private String developerMessage;
    private int status;
    private String code;
    private String type;
    private UUID uuid;

    public CustomNotAllowedException(Exception ex, String message, String developerMessage, String code, UUID uuid) {
        super(message, MediaType.APPLICATION_JSON, new String[] { MediaType.APPLICATION_JSON_PATCH_JSON });
        this.message = message;
        this.developerMessage = developerMessage;
        this.type = ex == null ? NotAllowedException.class.getName() : ex.getClass().getName();
        this.uuid = uuid;
        this.code = code == null ? Constant.APP_NAME + HttpServletResponse.SC_METHOD_NOT_ALLOWED
                : Constant.APP_NAME + HttpServletResponse.SC_METHOD_NOT_ALLOWED + code;
        this.status = HttpServletResponse.SC_METHOD_NOT_ALLOWED;
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

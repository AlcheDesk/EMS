package com.meowlomo.ems.external.vmc.api.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.meowlomo.ems.core.model.Task;
import com.meowlomo.ems.core.resource.model.MeowlomoResponse;
import com.meowlomo.ems.external.vmc.api.VMCApiService;

@Service
public class VMCApiServiceImpl implements VMCApiService {

    private final Logger logger = LoggerFactory.getLogger(VMCApiServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ApplicationContext appContext;

    public VMCApiServiceImpl(ApplicationContext cc) {
        appContext = cc;
    }

    @Override
    public MeowlomoResponse sendTaskToWorker(String workerBaseUrl, String token, Task task) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("token", token);
        RestTemplate newRestTemplate = appContext.getBean(RestTemplate.class, headers);
        String apiUrl = "api/tasks";
        try {
            URI uri = new URI(StringUtils.removeEnd(workerBaseUrl, "/") + "/" + apiUrl);
            return newRestTemplate.postForObject(uri, task, MeowlomoResponse.class);
        }
        catch (URISyntaxException e) {
            logger.error("url error from base url :[{}] api url : [{}]", workerBaseUrl, apiUrl);
        }
        catch (ResourceAccessException e) {
            logger.error("access timeouted :[{}] api url : [{}]", workerBaseUrl, apiUrl);
        }
        return null;
    }

    @Override
    public MeowlomoResponse getTaskerFromWorker(String workerBaseUrl) {
        String apiUrl = "api/tasks";
        try {
            URI uri = new URI(StringUtils.removeEnd(workerBaseUrl, "/") + "/" + apiUrl);
            return restTemplate.getForObject(uri, MeowlomoResponse.class);
        }
        catch (URISyntaxException e) {
            logger.error("url error from base url :[{}] api url : [{}]", workerBaseUrl, apiUrl);
        }
        catch (ResourceAccessException e) {
            logger.error("access timeouted :[{}] api url : [{}]", workerBaseUrl, apiUrl);
        }
        return null;
    }

    @Override
    public MeowlomoResponse checkWorkerHealth(String workerBaseUrl) {
        String apiUrl = "api/check";
        try {
            URI uri = new URI(StringUtils.removeEnd(workerBaseUrl, "/") + "/" + apiUrl);
            return restTemplate.getForObject(uri, MeowlomoResponse.class);
        }
        catch (URISyntaxException e) {
            logger.error("url error from base url :[{}] api url : [{}]", workerBaseUrl, apiUrl, e);
        }
        catch (ResourceAccessException e) {
            logger.error("access timeouted :[{}] api url : [{}]", workerBaseUrl, apiUrl);
        }
        return null;
    }

    @Override
    public MeowlomoResponse endManagerRequest(String workerBaseUrl, String token, ObjectNode JsonContent) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("token", token);
        RestTemplate newRestTemplate = appContext.getBean(RestTemplate.class, headers);
        String apiUrl = "api/manager";
        try {
            URI uri = new URI(StringUtils.removeEnd(workerBaseUrl, "/") + "/" + apiUrl);
            return newRestTemplate.postForObject(uri, JsonContent, MeowlomoResponse.class);
        }
        catch (URISyntaxException e) {
            logger.error("url error from base url :[{}] api url : [{}]", workerBaseUrl, apiUrl, e);
        }
        catch (ResourceAccessException e) {
            logger.error("access timeouted :[{}] api url : [{}]", workerBaseUrl, apiUrl);
        }
        return null;
    }

    @Override
    public MeowlomoResponse rebootWorker(String workerBaseUrl) {
        String apiUrl = "api/reboot";
        try {
            URI uri = new URI(StringUtils.removeEnd(workerBaseUrl, "/") + "/" + apiUrl);
            return restTemplate.getForObject(uri, MeowlomoResponse.class);
        }
        catch (URISyntaxException e) {
            logger.error("url error from base url :[{}] api url : [{}]", workerBaseUrl, apiUrl, e);
        }
        catch (ResourceAccessException e) {
            logger.error("access timeouted :[{}] api url : [{}]", workerBaseUrl, apiUrl);
        }
        return null;
    }

    @Override
    public MeowlomoResponse restartWorker(String workerBaseUrl) {
        String apiUrl = "api/restart";
        try {
            URI uri = new URI(StringUtils.removeEnd(workerBaseUrl, "/") + "/" + apiUrl);
            return restTemplate.getForObject(uri, MeowlomoResponse.class);
        }
        catch (URISyntaxException e) {
            logger.error("url error from base url :[{}] api url : [{}]", workerBaseUrl, apiUrl, e);
        }
        catch (ResourceAccessException e) {
            logger.error("access timeouted :[{}] api url : [{}]", workerBaseUrl, apiUrl);
        }
        return null;
    }

    @Override
    public MeowlomoResponse getWorkerStatus(String workerBaseUrl) {
        String apiUrl = "api/status";
        try {
            URI uri = new URI(StringUtils.removeEnd(workerBaseUrl, "/") + "/" + apiUrl);
            return restTemplate.getForObject(uri, MeowlomoResponse.class);
        }
        catch (URISyntaxException e) {
            logger.error("url error from base url :[{}] api url : [{}]", workerBaseUrl, apiUrl, e);
        }
        catch (ResourceAccessException e) {
            logger.error("access timeouted :[{}] api url : [{}]", workerBaseUrl, apiUrl);
        }
        return null;
    }

    @Override
    public MeowlomoResponse stopWorker(String workerBaseUrl, String token, ObjectNode JsonContent) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("token", token);
        RestTemplate newRestTemplate = appContext.getBean(RestTemplate.class, headers);
        String apiUrl = "api/stop";
        try {
            URI uri = new URI(StringUtils.removeEnd(workerBaseUrl, "/") + "/" + apiUrl);
            return newRestTemplate.postForObject(uri, JsonContent, MeowlomoResponse.class);
        }
        catch (URISyntaxException e) {
            logger.error("url error from base url :[{}] api url : [{}]", workerBaseUrl, apiUrl, e);
        }
        catch (ResourceAccessException e) {
            logger.error("access timeouted :[{}] api url : [{}]", workerBaseUrl, apiUrl);
        }
        return null;
    }

}

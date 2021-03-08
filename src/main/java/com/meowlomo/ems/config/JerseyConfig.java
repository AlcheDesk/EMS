package com.meowlomo.ems.config;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.wadl.internal.WadlResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

@Component
public class JerseyConfig extends ResourceConfig {

    private static final Logger logger = LoggerFactory.getLogger(JerseyConfig.class);

    @Value("${spring.jersey.application-path}")
    private String apiPath;

    @Value("${meowlomo.ems.swaggerr.enable:false}")
    private boolean enableSwagger;
    
    @Inject
    private ApplicationContext appCtx;

    public JerseyConfig() {
        logger.debug("Loading Jersey configuration.");
        // Register endpoints, providers, ...
    }

    @PostConstruct
    public void init() {
        // Register components
        logger.debug("registering jersey resoures");
        this.registerEndpoints();
        if (enableSwagger) {
            logger.debug("configuring Swagger");
            this.configureSwagger();
        }

    }

    private void registerEndpoints() {
        this.scanResources();
        if (enableSwagger) {
            this.register(WadlResource.class);
        }       
        this.register(JacksonFeature.class);
        this.register(LoggingFeature.class);
    }

    private void configureSwagger() {
        // Available at localhost:port/api/swagger.json
        this.register(ApiListingResource.class);
        this.register(SwaggerSerializers.class);

        BeanConfig config = new BeanConfig();
        config.setConfigId("EMS");
        config.setTitle("meowlomo Execution Management System (EMS) API");
        config.setVersion("v1.4");
        config.setContact("meowlomo");
        config.setSchemes(new String[] { "http", "https" });
        config.setBasePath(this.apiPath);
        config.setResourcePackage("com.meowlomo.ems.core.resource");
        config.setPrettyPrint(true);
        config.setScan(true);
    }

    private void scanResources() {
        logger.debug("Rest Endpoint classes found:");
        Map<String, Object> pathBeans = appCtx.getBeansWithAnnotation(Path.class);
        for (Object o : pathBeans.values()) {
            logger.debug(" -> " + o.getClass().getName());
            register(o);
        }

        logger.debug("Rest Provider classes found:");
        Map<String, Object> providerBeans = appCtx.getBeansWithAnnotation(Provider.class);
        for (Object o : providerBeans.values()) {
            logger.debug(" -> " + o.getClass().getName());
            register(o);
        }
    }
}
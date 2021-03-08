package com.meowlomo.ems.config;

import java.util.concurrent.TimeUnit;

import javax.cache.CacheManager;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.Duration;
import javax.cache.expiry.TouchedExpiryPolicy;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfiguration implements JCacheManagerCustomizer {

    @Override
    public void customize(CacheManager cacheManager) {
        cacheManager.createCache("deafult",
                new MutableConfiguration<>()
                        .setExpiryPolicyFactory(TouchedExpiryPolicy.factoryOf(new Duration(TimeUnit.SECONDS, 10)))
                        .setStoreByValue(false).setStatisticsEnabled(true));
    }

}

package com.meowlomo.ems.config.multitenancy;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;

@Component
public class TentantContext {
    private static final String KEY_CURRENT_TENANT_ID = "CURRENT_TENANT_ID";
    private static final Map<String, Long> mContext = Maps.newConcurrentMap();

    private static final String TENANT_ID_COLUMN_NAME = "tenant_id";
    private static final List<String> IGNORE_TENANT_TABLES = Lists.newArrayList(
            "execution_control_type",
            "job_type",
            "\"group\"",
            "operating_system",
            "status",
            "task_type",
            "vendor",
            "worker_type",
            "worker_vendor");

    private ApplicationContext appContext;

    @Value("${meowlomo.ems.multitenancy.enable:false}")
    private boolean enableMultitenancy;

    public TentantContext(ApplicationContext cc) {
        appContext = cc;
    }

    public void setCurrentTentatantId(Long currentTenantId) {
        if (currentTenantId != null && currentTenantId.longValue() != 1) {
            TenantSqlParser tenantSqlParser = new TenantSqlParser().setTenantHandler(new TenantHandler() {
                @Override
                public Expression getTenantId(boolean where) {
                    if (enableMultitenancy) {
                        throw new RuntimeException("error on getting tennant id from logined use context.");
                    }
                    return new LongValue(currentTenantId);
                }

                @Override
                public String getTenantIdColumn() {
                    return TENANT_ID_COLUMN_NAME;
                }

                @Override
                public boolean doTableFilter(String tableName) {
                    return TentantContext.IGNORE_TENANT_TABLES.stream().anyMatch((e) -> e.equalsIgnoreCase(tableName));
                }
            });
            PaginationInterceptor bean = appContext.getBean(PaginationInterceptor.class);
            bean.setSqlParserList(Lists.newArrayList(tenantSqlParser));
        }
        else {
            PaginationInterceptor bean = appContext.getBean(PaginationInterceptor.class);
            bean.setSqlParserList(null);
        }
        mContext.put(KEY_CURRENT_TENANT_ID, currentTenantId);
    }

    public Long getCurrentTenantId() {
        return mContext.get(KEY_CURRENT_TENANT_ID);
    }

}

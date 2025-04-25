package com.qh.infra.config;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.common.utils.JacksonUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * @author Jinx
 */
@Configuration
@RequiredArgsConstructor
public class RouteConfig implements InitializingBean {

    /**
     * nacos 提供的 client。 可根据该 manager 获取到 service（类似client） 调用 nacos-server 的 api
     */
    private final NacosConfigManager manager;
    private final NacosConfigProperties properties;

    /**
     * RouteDefinition repo
     */
    private final RouteDefinitionWriter writer;
    /**
     * 事件发布器
     */
    private final ApplicationEventPublisher publisher;
    /**
     * nacos 路由配置文件 data_id
     */
    private final static String ROUTE_CONFIG_ON_NACOS = "route.json";


    @Override
    public void afterPropertiesSet() throws Exception {
        Listener listener = new RouteConfigListener();

        listener.receiveConfigInfo(
                manager.getConfigService().getConfigAndSignListener(
                        ROUTE_CONFIG_ON_NACOS,
                        properties.getGroup(),
                        properties.getTimeout(),
                        listener
                )
        );
    }

    /**
     * nacos 路由配置监听器
     */
    private class RouteConfigListener implements Listener {

        // @formatter:off
        private static final TypeReference<List<RouteDefinition>> RD_LIST = new TypeReference<>() {};
        // @formatter:on

        @Override
        public Executor getExecutor() {
            // 处理该回调的 Executor ， 如果不设置使用默认逻辑
            // com.alibaba.nacos.client.config.impl.CacheData.safeNotifyListener
            // com.alibaba.nacos.client.config.impl.CacheData.NotifyTask
            return null;
        }

        @Override
        public void receiveConfigInfo(String configInfo) {
            if (StringUtils.hasText(configInfo)) {
                for (RouteDefinition rd : JacksonUtils.toObj(configInfo, RD_LIST)) {
                    // 保存 routeDefinition
                    writer.save(Mono.just(rd)).subscribe();
                    // 发布路由刷新事件 - spring gateway 会更新 applicationContext 中的 bean
                    publisher.publishEvent(new RefreshRoutesEvent(this));
                }
            }
        }
    }
}

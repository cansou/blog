package com.blog.cloud.config.swagger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: GatewaySwaggerResourcesProvider</p>
 * <p>Description: 网关资源配置</p>
 *
 * @author jimmy.fang
 * @date 2018年4月24日
 */
@Slf4j
@Component
@Primary
public class GatewaySwaggerResourcesProvider implements SwaggerResourcesProvider {
    private final RouteLocator routeLocator;

    public GatewaySwaggerResourcesProvider(RouteLocator routeLocator) {
        this.routeLocator = routeLocator;
    }

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        List<Route> routes = routeLocator.getRoutes();
        for (Route route : routes) {
            log.info(route.toString());
            if (route.getId().contains("api-")) {
                resources.add(swaggerResource(route.getId(), route.getFullPath().replace("**", "v2/api-docs")));
            }
        }
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        log.info(location);
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }
}

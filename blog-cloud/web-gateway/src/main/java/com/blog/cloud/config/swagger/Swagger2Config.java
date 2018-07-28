package com.blog.cloud.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * <p>Title: Swagger2Config</p>
 * <p>Description: doc API 配置</p>
 *
 * @author jimmy.fang
 * @date 2018年4月24日
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("说明文档")
				.description("接口说明文档")
				.termsOfServiceUrl("")
				.contact(new Contact("shawnbronte", "2030238228@qq.com", "2030238228@qq.com"))
				.version("1.0")
				.build();
	}
}

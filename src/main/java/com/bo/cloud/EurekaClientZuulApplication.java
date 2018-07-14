package com.bo.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
@EnableEurekaClient // 声明为Eureka客户端
@EnableZuulProxy // 开启zuul的功能
public class EurekaClientZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientZuulApplication.class, args);
	}
	
	/**
	 * 跨域过滤器
	 *
	 * @return
	 */
//	@Bean
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);//允许跨域访问(在响应报文里带上跨域请求的凭证，和浏览器请求里面xhrFields相匹配，前后端才能正常通信)
		corsConfiguration.addAllowedOrigin("*");// 把允许的跨域源添加到corsConfiguration中
		corsConfiguration.addAllowedHeader("*");// 不对head做限制
		corsConfiguration.addAllowedMethod("*");// 不对method做限制,允许所有method请求(get,post....)
		source.registerCorsConfiguration("/**",corsConfiguration);//指定对当前这个服务下的所有请求都启用corsConfiguration的配置
		return new CorsFilter(source);
	}
}

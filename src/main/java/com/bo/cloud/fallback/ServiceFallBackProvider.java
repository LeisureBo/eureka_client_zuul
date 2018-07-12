package com.bo.cloud.fallback;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

/**
 * @Description 服务熔断处理
 * Zuul 目前只支持服务级别的熔断，不支持具体到某个URL进行熔断
 * @Author Bo
 * @Version 2018年7月10日　下午4:24:35
 * @码云 https://gitee.com/LeisureBo
 */
@Component
public class ServiceFallBackProvider implements FallbackProvider {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 1.返回指定的要处理的service-id
	 * 2.返回全局默认熔断策略：返回"*"或null
	 */
	@Override
	public String getRoute() {
		return "*";
	}

	// 服务熔断返回的内容
	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
		if (cause != null && cause.getCause() != null) {
			String reason = cause.getCause().getMessage();
			logger.info("Excption {}", reason);
		}
		return new ClientHttpResponse() {
			
			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
				return headers;
			}
			
			@Override
			public InputStream getBody() throws IOException {
				return new ByteArrayInputStream("The service is unavailable.".getBytes());
			}
			
			@Override
			public String getStatusText() throws IOException {
				return "OK";
			}
			
			@Override
			public HttpStatus getStatusCode() throws IOException {
				return HttpStatus.OK;
			}
			
			@Override
			public int getRawStatusCode() throws IOException {
				return 200;
			}
			
			@Override
			public void close() {
				
			}
		};
	}
	
	
}

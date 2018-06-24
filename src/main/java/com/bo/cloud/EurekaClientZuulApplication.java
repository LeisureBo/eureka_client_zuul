package com.bo.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableEurekaClient // 声明为Eureka客户端
@EnableZuulProxy // 开启zuul的功能
public class EurekaClientZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientZuulApplication.class, args);
	}
}

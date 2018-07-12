package com.bo.cloud.web.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.auth.AUTH;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * @Description 自定义Zuul过滤器
 * @author 王博
 * @version 2018年6月24日　下午7:46:59
 */
//@Component
public class TokenAuthFilter extends ZuulFilter {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问
	 */
	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		String URL = request.getRequestURL().toString();
		logger.info("zuul filter: request method=" + request.getMethod() + ", request url: " + URL);
		String accessToken = request.getParameter("token");
		if (accessToken == null) {
			logger.warn("token is null");
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
			try {
				ctx.getResponse().getWriter().write("token is empty");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		logger.info("token auth through");
		return null;
	}

	/**
	 * to-do 编写是否过滤逻辑..
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/**
	 * 过滤的顺序
	 */
	@Override
	public int filterOrder() {
		return 1;
	}

	/**
	 * 返回一个字符串代表过滤器的类型，在zuul中定义了4种不同生命周期的过滤器类型:
	 * pre：路由之前
	 * routing：路由之时
	 * post： 路由之后
	 * error：发送错误调用
	 */
	@Override
	public String filterType() {
		return "pre";
	}

}

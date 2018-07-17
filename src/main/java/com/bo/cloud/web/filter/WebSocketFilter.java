package com.bo.cloud.web.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * @Description websocket路由转发配置
 * @Author Bo
 * @Version 2018年7月10日　下午4:00:21
 * @码云 https://gitee.com/LeisureBo
 */
@Component
public class WebSocketFilter extends ZuulFilter {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Object run() throws ZuulException {
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		logger.info("step into websocket filter --> " + request.getRequestURL());
		String upgradeHeader = request.getHeader("Upgrade");
		if(upgradeHeader == null) upgradeHeader = request.getHeader("upgrade");
		if (null != upgradeHeader && "websocket".equalsIgnoreCase(upgradeHeader)) {
			RequestContext.getCurrentContext().addZuulRequestHeader("connection", "Upgrade");
        }
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public String filterType() {
		return "pre";
	}

}

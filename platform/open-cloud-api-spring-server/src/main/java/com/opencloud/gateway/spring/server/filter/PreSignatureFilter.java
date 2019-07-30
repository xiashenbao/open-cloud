package com.opencloud.gateway.spring.server.filter;

import com.google.common.collect.Maps;
import com.opencloud.base.client.model.entity.BaseApp;
import com.opencloud.common.constants.CommonConstants;
import com.opencloud.common.exception.OpenSignatureException;
import com.opencloud.common.model.ResultBody;
import com.opencloud.common.utils.SignatureUtils;
import com.opencloud.gateway.spring.server.configuration.ApiProperties;
import com.opencloud.gateway.spring.server.exception.JsonSignatureDeniedHandler;
import com.opencloud.gateway.spring.server.service.feign.BaseAppServiceClient;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.support.WebExchangeDataBinder;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 数字验签前置过滤器
 *
 * @author: liuyadu
 * @date: 2018/11/28 18:26
 * @description:
 */
public class PreSignatureFilter implements WebFilter {
    private JsonSignatureDeniedHandler signatureDeniedHandler;
    private BaseAppServiceClient baseAppServiceClient;
    private ApiProperties apiGatewayProperties;
    private static final AntPathMatcher pathMatch = new AntPathMatcher();
    /**
     * 忽略签名
     */
    private final static List<String> NOT_SIGN = getIgnoreMatchers(
            "/**/login/**",
            "/**/logout/**"
    );

    public PreSignatureFilter(BaseAppServiceClient baseAppServiceClient, ApiProperties apiGatewayProperties,JsonSignatureDeniedHandler signatureDeniedHandler) {
        this.baseAppServiceClient = baseAppServiceClient;
        this.apiGatewayProperties = apiGatewayProperties;
        this.signatureDeniedHandler = signatureDeniedHandler;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String requestPath = request.getURI().getPath();
        if (apiGatewayProperties.getCheckSign() && !notSign(requestPath)) {
            try {
                Map params = Maps.newHashMap();
                WebExchangeDataBinder.extractValuesToBind(exchange).subscribe(objectMap -> {
                            params.putAll(objectMap);
                        }
                );
                // 验证请求参数
                SignatureUtils.validateParams(params);
                //开始验证签名
                if (baseAppServiceClient != null) {
                    String appId = params.get("appId").toString();
                    // 获取客户端信息
                    ResultBody<BaseApp> result = baseAppServiceClient.getApp(appId);
                    BaseApp app = result.getData();
                    if (app == null) {
                        return signatureDeniedHandler.handle(exchange, new OpenSignatureException("appId无效"));
                    }
                    // 强制覆盖请求参数clientId
                    params.put(CommonConstants.SIGN_APP_ID_KEY, app.getAppId());
                    // 服务器验证签名结果
                    if (!SignatureUtils.validateSign(params, app.getSecretKey())) {
                        return signatureDeniedHandler.handle(exchange, new OpenSignatureException("签名验证失败!"));
                    }
                }
            } catch (Exception ex) {
                return signatureDeniedHandler.handle(exchange, new OpenSignatureException(ex.getMessage()));
            }
        }
        return chain.filter(exchange);
    }


    protected static List<String> getIgnoreMatchers(String... antPatterns) {
        List<String> matchers = new CopyOnWriteArrayList();
        for (String path : antPatterns) {
            matchers.add(path);
        }
        return matchers;
    }

    protected boolean notSign(String requestPath) {
        for (String path : NOT_SIGN) {
            if (pathMatch.match(path, requestPath)) {
                return true;
            }
        }
        return false;
    }
}

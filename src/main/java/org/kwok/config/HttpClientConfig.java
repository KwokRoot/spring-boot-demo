package org.kwok.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.UUID;

/**
 * @description: 配置 Spring RestTemplate。
 * @author: Kwok
 * @date: 2021/4/26
 */
@Configuration
public class HttpClientConfig {

    public static final Logger logger = LoggerFactory.getLogger(HttpClientConfig.class);

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

        ClientHttpRequestInterceptor clientHttpRequestInterceptor = new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {

                String requestId = UUID.randomUUID().toString();
                String url = request.getURI().toString();

                long startTime = System.currentTimeMillis();
                ClientHttpResponse clientHttpResponse;
                try {
                    clientHttpResponse = clientHttpRequestExecution.execute(request, bytes);
                    long spendTime = System.currentTimeMillis() - startTime;
                    logger.info(">>> Send HTTP Request, id: {}, url: {}, method: {}, param: {}, resultStatus: {}, spendTime: {} ms",
                            requestId, url, request.getMethodValue(), new String(bytes),
                            clientHttpResponse.getStatusCode(), spendTime);
                } catch (Exception e) {
                    long spendTime = System.currentTimeMillis() - startTime;
                    logger.error(">>> Send HTTP Request, id: {}, url: {}, method: {}, param: {}, resultStatus: {}, spendTime: {} ms",
                            requestId, url, request.getMethodValue(), new String(bytes),
                            e.getMessage(), spendTime);
                    throw e;
                }
                return clientHttpResponse;
            }
        };

        restTemplate.setInterceptors(Arrays.asList(clientHttpRequestInterceptor));

        //处理返回体中文乱码
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        return restTemplate;
    }

}

package com.zbkj.service.mpay.util;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zqq
 */
public class HttpUtil {
    private static PoolingHttpClientConnectionManager connectionManager;
    private static RequestConfig requestConfig;
    private static final int MAX_TIMEOUT = 9000;

    private static CloseableHttpClient httpClient;

    static {
        // 设置连接池
        connectionManager = new PoolingHttpClientConnectionManager();
        // 设置连接池大小
        connectionManager.setMaxTotal(100);
        connectionManager.setDefaultMaxPerRoute(connectionManager.getMaxTotal());

        requestConfig = RequestConfig.custom()
                .setSocketTimeout(MAX_TIMEOUT)
                .setConnectTimeout(MAX_TIMEOUT)
                .setConnectionRequestTimeout(MAX_TIMEOUT)
                .setRedirectsEnabled(false)
                .build();

    }


    public static CloseableHttpClient getHttpClient() {
        if (httpClient == null) {
            httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).setConnectionManager(connectionManager).build();
        }
        return httpClient;
    }

    /**
     * 发送 GET 请求（HTTP），K-V形式
     *
     * @param url    请求地址
     * @param params 参数
     * @return 请求结果
     */
    public static String doGet(String url, Map<String, Object> params) {
        String result = null;
        HttpGet httpGet = buildGet(url, params);
        try (CloseableHttpResponse response = getHttpClient().execute(httpGet)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();
                result = IOUtils.toString(instream, StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static HttpGet buildGet(String url, Map<String, Object> params) {
        StringBuilder apiUrl = new StringBuilder(url);
        if (MapUtils.isNotEmpty(params)) {
            apiUrl.append("?");
            params.forEach((key, val) -> apiUrl.append(key).append("=").append(val).append("&"));
        }
        return new HttpGet(apiUrl.toString());
    }


    /**
     * 发送 POST 请求（HTTP），K-V形式
     *
     * @param apiUrl API接口URL
     * @param params 参数map
     * @return 请求结果
     */
    public static String doPost(String apiUrl, Map<String, Object> params) {
        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        List<NameValuePair> pairList = new ArrayList<>();
        params.forEach((key, val) -> {
            NameValuePair pair = new BasicNameValuePair(key, val == null ? null : val.toString());
            pairList.add(pair);
        });
        httpPost.setEntity(new UrlEncodedFormEntity(pairList, StandardCharsets.UTF_8));

        try (CloseableHttpResponse response = getHttpClient().execute(httpPost)) {
            HttpEntity entity = response.getEntity();
            httpStr = EntityUtils.toString(entity, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return httpStr;
    }

}

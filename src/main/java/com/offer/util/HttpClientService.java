package com.offer.util;

import com.alibaba.fastjson.JSONObject;
import com.offer.util.model.commLog;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: lizhen
 * @Description:
 * @Date: Created in 19:51 2018/1/7
 */
@Service
public class HttpClientService implements commLog{
    @Autowired
    private CloseableHttpClient httpClient;
    @Autowired
    private RequestConfig requestConfig;
    @Autowired
    PoolingHttpClientConnectionManager httpClientConnectionManager;
    private static final String code = "UTF-8";

    /**
     *  执行GET请求
     * @param url
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public String doGet(String url) throws ClientProtocolException,IOException {
        // 创建http GET请求
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(this.requestConfig);
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpClient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200){
                return EntityUtils.toString(response.getEntity(),code);
            }
        }finally {
           closeHttp(response);
        }
        return null;
    }

    /**
     *带有参数的GET请求
     * @param url
     * @param params
     * @return
     * @throws ClientProtocolException
     * @throws URISyntaxException
     * @throws IOException
     */
    public String doGet(String url, Map<String,String> params) throws ClientProtocolException, URISyntaxException, IOException {
        URIBuilder uriBuilder = new URIBuilder();
       for (String key : params.keySet()){
           uriBuilder.addParameter(key,params.get(key));
       }
       return this.doGet(uriBuilder.build().toString());
    }

    /**
     * 带有参数的post请求
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public HttpResult doPost(String url,Map<String,String> params) throws UnsupportedEncodingException {
        /*HttpHost proxy = new HttpHost("dev-proxy.oa.com", 8080, "http");
        CloseableHttpClient httpClient = HttpClients.createDefault();//添加代理*/
        // 创建http POST请求
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        httpPost.getConfig();
//        httpPost.setConfig(requestConfig.custom().setProxy(proxy).build());
        if (params != null){
            // 设置2个post参数，一个是scope、一个是q
            List<NameValuePair> parameters = new ArrayList<NameValuePair>();
            for (String key : params.keySet()){
                parameters.add(new BasicNameValuePair(key,params.get(key)));
            }
            // 构造一个form表单式的实体
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters,code);
            // 将请求实体设置到httpPost对象中
            httpPost.setEntity(formEntity);
        }
        try {
            // 执行请求
            response = httpClient.execute(httpPost);
            return new HttpResult(response.getStatusLine().getStatusCode(),EntityUtils.toString(response.getEntity(),code));
        }catch (IOException e){
                System.out.println("post请求提交失败："+url +"--------"+e.getMessage());
                logger.error("post请求提交失败："+e.getMessage());
        }finally {
            closeHttp(response);
        }
        return null;
    }

    /**
     * 执行POST请求
     * @param url
     * @return
     * @throws IOException
     */
    public HttpResult doPost(String url) throws IOException {
        return this.doPost(url,null);
    }

    /**
     * 提交json数据
     * @param url
     * @param json
     * @param str
     * @return
     */
    public String postSendHttp(String url, String json,String str) {
        // 创建http POST请求
        long start = System.currentTimeMillis();
        String rst = null;
        if (url == null || "".equals(url)){
            logger.info("request url is empty.");
            return rst;
        }
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(this.requestConfig);

        if (json != null) {
            // 构造一个form表单式的实体
            StringEntity stringEntity = new StringEntity(json,
                    ContentType.APPLICATION_JSON);
            // 将请求实体设置到httpPost对象中
            httpPost.setEntity(stringEntity);
        }
        CloseableHttpResponse response = null;
        BufferedReader br = null;
        InputStreamReader isr = null;
        try {
            // 执行请求
            response = this.httpClient.execute(httpPost);
            // 响应分析
            HttpEntity entity = response.getEntity();
            isr = new InputStreamReader(entity.getContent(), code);
            br = new BufferedReader(isr);
            StringBuffer responseString = new StringBuffer();
            String result = br.readLine();
            while (result != null)
            {
                responseString.append(result);
                result = br.readLine();
            }
            long end = System.currentTimeMillis();
            logger.info("请求[" + url + "]消耗时间 " + (end - start)
                    + "毫秒");
            rst = responseString.toString();
        }catch (Exception e){
            logger.error("post req error:",e);
            JSONObject backJson = new JSONObject();
            backJson.put("code", BackEnum.REQUESTERROR.getCode());
            backJson.put("errorMsg", BackEnum.REQUESTERROR.getMsg());
            rst = backJson.toJSONString();
        }finally {
            closeHttp(response);
        }
        logger.info("rst:"+rst);
        logger.info("PoolStats：=="+httpClientConnectionManager.getTotalStats());
        return rst;
    }

    /**
     * 关闭请求
     * @param response
     * @throws IOException
     */
    private static void closeHttp(CloseableHttpResponse response){
        if (response != null){
            try {
                response.close();
            } catch (IOException e) {
                logger.error("连接关闭异常：",e);
            }
        }
    }
}

package com.blog.web;

import com.blog.cloud.utils.DateUtil;
import com.blog.cloud.utils.HttpUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author lxw
 * @date 2019/1/18
 * @description
 */
public class WeixinWeb {

    public static void main(String[] args) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("appid", "wx782c26e4c19acffb");
        params.put("redirect_uri", "https://wx.qq.com/cgi-bin/mmwebwx-bin/webwxnewloginpage");
        params.put("fun", "new");
        params.put("lang", "zh_CN");
        DateUtil.getCurrentTimeMillis();
        long currentTimeMillis = System.currentTimeMillis();
        Long aLong = DateUtil.normalParseToDateLong("1970-01-01 00:00:00");
        params.put("_", aLong - currentTimeMillis + "");
        String content = HttpUtils.httpGet("https://login.weixin.qq.com/jslogin", params);
        String webWechatSessionId = content.substring(content.indexOf("\"") + 1, content.lastIndexOf("\""));
        getUriResource(webWechatSessionId, "D:\\work\\1.jpg");

    }


    public static void getUriResource(String webWechatSessionId, String path) {
        String imgUrl = "https://login.weixin.qq.com/qrcode/" + webWechatSessionId;

        // 获取连接客户端工具
        CloseableHttpClient httpClient = HttpClients.createDefault();

        CloseableHttpResponse response = null;

        try {

            // 创建POST请求对象
            HttpGet httpGet = new HttpGet(imgUrl);

            // 执行请求
            response = httpClient.execute(httpGet);
            // 获得响应的实体对象
            HttpEntity entity = response.getEntity();
            /*
             * 获取到响应信息的流
             */
            InputStream is = entity.getContent();
            // 包装成高效流
            BufferedInputStream bis = new BufferedInputStream(is);

            // 写入本地 D 盘
            File file = new File(path);
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));

            byte[] byt = new byte[1024 * 8];
            Integer len = -1;
            while ((len = bis.read(byt)) != -1) {
                bos.write(byt, 0, len);
            }
            bos.close();
            bis.close();
        } catch (ClientProtocolException e) {
            System.err.println("Http协议出现问题");
            e.printStackTrace();
        } catch (ParseException e) {
            System.err.println("解析错误");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("IO异常");
            e.printStackTrace();
        } finally {
            // 释放连接
            if (null != response) {
                try {
                    response.close();
                    httpClient.close();
                } catch (IOException e) {
                    System.err.println("释放连接出错");
                    e.printStackTrace();
                }
            }
        }
    }


}

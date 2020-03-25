package com.tianbsli.filter.common;

import com.tianbsli.util.RequestUtil;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author Jorsh
 * @version 1.0
 * @date 2020/3/25 3:30 下午
 *
 *  获取到请求的输入流，从该输入流中可以读取到请求体。
 *  可以通过将 HttpServletRequest 对象包装一层的方式来实现可重复读取流。
 *  HttpServletRequestWrapper 实现对 httpServletRequest 的装饰。[todo:将在博客里分析]
 */
public class RequestWrapper extends HttpServletRequestWrapper {


    //body即为从request中读取出的body数据
    private static String body;
    /**
     * 返回body
     */
    public String getBody() {
        return body;
    }

    /**
     * RequestWrapper的带参构造函数
     * 从request中读取数据并写入body
     */
    public RequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        body = RequestUtil.readRequestBody(request);
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {

        final ByteArrayInputStream bais = new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8));

        return new ServletInputStream() {

            @Override
            public int read() throws IOException {
                return bais.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }
        };
    }

}
